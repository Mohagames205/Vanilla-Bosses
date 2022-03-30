package shreb.me.vanillabosses.bossclasses;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class BossWither implements Listener {

    private static final Configuration config = Main.getInstance().getConfig();
    public static List<UUID> passiveWitherList = new ArrayList<>();


    @EventHandler
    public void onPlaceWitherEgg(BlockPlaceEvent event) {

        if (!(event.getBlock().getType().equals(Material.DRAGON_EGG))) return;
        if (!event.getItemInHand().getItemMeta().hasLore()) return;

        if (Objects.requireNonNull(event.getItemInHand().getItemMeta().getLore()).contains("What will hatch from this?")) {

            BlockData data = event.getBlock().getBlockData();

            if (event.getBlock().getWorld().getBlockAt(event.getBlock().getLocation().getBlockX(), event.getBlock().getLocation().getBlockY() - 1, event.getBlock().getLocation().getBlockZ()).getType().equals(Material.ANVIL)) {

                Location eggLoc = event.getBlock().getLocation();
                Location anvilLoc = event.getBlock().getWorld().getBlockAt(event.getBlock().getLocation().getBlockX(), event.getBlock().getLocation().getBlockY() - 1, event.getBlock().getLocation().getBlockZ()).getLocation();

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                    if (eggLoc.getBlock().getType().equals(Material.DRAGON_EGG)
                            && eggLoc.getBlock().getBlockData().matches(data)
                            && anvilLoc.getBlock().getType().equals(Material.ANVIL)) {

                        Wither wither = (Wither) event.getPlayer().getWorld().spawnEntity(eggLoc, EntityType.WITHER);

                        wither.setCustomName(event.getPlayer().getName() + "s Pet Wither");

                        wither.setAI(false);

                        wither.addScoreboardTag("PassiveWither");

                        wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.getInstance().getConfig().getDouble("Items.WitherEgg.petWitherHP"));
                        wither.setHealth(Main.getInstance().getConfig().getDouble("Items.WitherEgg.petWitherHP"));

                        eggLoc.getBlock().setType(Material.AIR);
                        anvilLoc.getBlock().setType(Material.AIR);

                        Location loc = wither.getLocation();
                        wither.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 150);
                        passiveWitherList.add(wither.getUniqueId());

                        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {

                            passiveWitherTarget(wither);

                        }, 20, 15);

                    } else if (eggLoc.getBlock().getType().equals(Material.DRAGON_EGG) && eggLoc.getBlock().getBlockData().matches(data)) {
                        eggLoc.getBlock().setType(Material.AIR);

                        ItemStack egg = new ItemStack(Material.DRAGON_EGG);

                        ItemMeta meta = egg.getItemMeta();
                        meta.setDisplayName("A Withers Egg");

                        ArrayList<String> lore = new ArrayList<>();
                        lore.add("What will hatch from this?");
                        lore.add(ChatColor.BLACK + "Place on an Anvil to find out!");
                        meta.setLore(lore);

                        egg.setItemMeta(meta);
                        event.getBlock().getWorld().dropItem(eggLoc, egg);
                    }


                }, Main.getInstance().getConfig().getInt("Items.WitherEgg.timeToHatch") * 20L);
            }
        }
    }

    public static void passiveWitherTarget(Wither wither) {

        if(wither.getHealth() < 0.001) return;

        int range = config.getInt("Items.WitherEgg.arrowRange");

        List<Entity> targetList = wither.getNearbyEntities(range, range, range)
                .stream()
                .filter(n -> n instanceof LivingEntity)
                .filter(n -> n instanceof Monster)
                .filter(wither::hasLineOfSight)
                .collect(Collectors.toList());

        if (targetList.isEmpty()) return;

        LivingEntity target = (LivingEntity) targetList.get(0);
        if(target.isDead()) return;

        Location witherLoc = wither.getEyeLocation();
        Location targetLoc = target.getEyeLocation();

        Arrow arrow = wither.getLocation().getWorld().spawnArrow(
                witherLoc,
                new Vector(targetLoc.getX() - witherLoc.getX(),
                        targetLoc.getY() - witherLoc.getY(),
                        targetLoc.getZ() - witherLoc.getZ()),
                5,
                1
        );
        arrow.setDamage(config.getDouble("Items.WitherEgg.arrowDamageMultiplier") * arrow.getDamage());
        arrow.setGravity(false);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);

        arrow.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "targetUUID"), PersistentDataType.STRING, target.getUniqueId().toString());
        arrow.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "shooterUUID"), PersistentDataType.STRING, wither.getUniqueId().toString());

        Bukkit.getScheduler().runTaskLater(Main.getInstance(),()->{
            //if(arrow.isInBlock() || arrow.isInWater()) {
                arrow.remove();
            //}
        }, 100);
    }


    public static ItemStack makeWitherEgg() {

        ItemStack witherEgg = new ItemStack(Material.DRAGON_EGG);

        ItemMeta meta = witherEgg.getItemMeta();
        meta.setDisplayName("A Withers Egg");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("What will hatch from this?");
        lore.add(ChatColor.BLACK + "Place on an Anvil to find out!");
        meta.setLore(lore);

        witherEgg.setItemMeta(meta);

        return witherEgg;
    }

    @EventHandler
    public void onWitherSpawn(CreatureSpawnEvent event) {

        if (event.getEntityType().equals(EntityType.WITHER)) {

            if (!config.getBoolean("Bosses.WitherBoss.enabled")) return;

            LivingEntity wither = event.getEntity();

            int x = wither.getLocation().getBlockX();
            int y = wither.getLocation().getBlockY();
            int z = wither.getLocation().getBlockZ();

            if (wither.getWorld().getBlockAt(x, y - 1, z).getType() == Material.NETHERITE_BLOCK) {

                wither.addScoreboardTag("BossWither");
                wither.getScoreboardTags().add("VanillaBossesDamageTracker");

                Objects.requireNonNull(wither.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Bosses.WITHER.health);
                wither.setHealth(Bosses.WITHER.health);

                wither.setCustomName(net.md_5.bungee.api.ChatColor.of(Bosses.WITHER.nameColor) + Bosses.WITHER.displayName);
                wither.setCustomNameVisible(config.getBoolean("Bosses.WitherBoss.showDisplayNameAlways"));

                if (Main.getInstance().getConfig().getBoolean("Bosses.WitherBoss.removeNetheriteBlockOnSpawn")) {
                    wither.getWorld().getBlockAt(x, y - 1, z).setType(Material.AIR);
                }
                if (config.getBoolean("Bosses.bossesGetGlowingPotionEffect")) {
                    wither.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
                }

            }
        }

    }

    @EventHandler
    public void onWitherArrowHit(EntityDamageByEntityEvent event){

        if(!(event.getDamager() instanceof Arrow)
                || !event.getDamager().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "targetUUID"), PersistentDataType.STRING)){
            return;
        }

        if(event.getEntity() instanceof HumanEntity || !(event.getEntity() instanceof Monster)) event.getDamager().setGravity(true);

        if((!event.getEntity().getUniqueId().toString().equals(event.getDamager().getPersistentDataContainer().get(new NamespacedKey(Main.getInstance(), "targetUUID"), PersistentDataType.STRING))
         && !(event.getEntity() instanceof Monster))
                || event.getEntity().getUniqueId().toString().equals(event.getDamager().getPersistentDataContainer().get(new NamespacedKey(Main.getInstance(), "shooterUUID"), PersistentDataType.STRING))
        ) {
            event.setCancelled(true);
            event.setDamage(0.05 * ((Arrow) event.getDamager()).getDamage());
            event.getDamager().setGravity(true);
        }
    }

    public static Wither makeBossWither(Location loc, World w) {

        Wither wither = (Wither) w.spawnEntity(loc, EntityType.WITHER);
        wither.addScoreboardTag(Bosses.WITHER.scoreboardBossTag);
        wither.getScoreboardTags().add("VanillaBossesDamageTracker");

        wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.WITHER.health);
        wither.setHealth(Bosses.WITHER.health);

        wither.setCustomName(net.md_5.bungee.api.ChatColor.of(Bosses.WITHER.nameColor) + Bosses.WITHER.displayName);
        wither.setCustomNameVisible(config.getBoolean("Bosses.WitherBoss.showDisplayNameAlways"));

        if (config.getBoolean("Bosses.bossesGetGlowingPotionEffect")) {
            wither.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

        return wither;
    }


}
