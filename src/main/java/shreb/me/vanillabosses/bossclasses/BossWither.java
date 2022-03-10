package shreb.me.vanillabosses.bossclasses;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BossWither implements Listener {

    private static final Configuration config = Main.getInstance().getConfig();
    public static List<UUID> passiveWitherList = new ArrayList<>();

    @EventHandler
    public void onPlaceWitherEgg(BlockPlaceEvent event) {

        if (!(event.getBlock().getType().equals(Material.DRAGON_EGG))) return;
        if(!event.getItemInHand().getItemMeta().hasLore()) return;

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

                    } else if(eggLoc.getBlock().getType().equals(Material.DRAGON_EGG) && eggLoc.getBlock().getBlockData().matches(data)) {
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

    public static ItemStack makeWitherEgg(){

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
    public void onWitherSpawn(CreatureSpawnEvent event){

        if (event.getEntityType().equals(EntityType.WITHER)) {

            if (!config.getBoolean("Bosses.WitherBoss.enabled")) return;

            LivingEntity wither = event.getEntity();

            int x = wither.getLocation().getBlockX();
            int y = wither.getLocation().getBlockY();
            int z = wither.getLocation().getBlockZ();

            if (wither.getWorld().getBlockAt(x, y - 1, z).getType() == Material.NETHERITE_BLOCK) {

                wither.addScoreboardTag("BossWither");

                Objects.requireNonNull(wither.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Bosses.WITHER.health);
                wither.setHealth(Bosses.WITHER.health);

                wither.setCustomName(ChatColor.valueOf(Bosses.WITHER.nameColor) + Bosses.WITHER.displayName);
                wither.setCustomNameVisible(config.getBoolean("Bosses.WitherBoss.showDisplayNameAlways"));

                if (Main.getInstance().getConfig().getBoolean("Bosses.WitherBoss.removeNetheriteBlockOnSpawn")) {
                    wither.getWorld().getBlockAt(x, y - 1, z).setType(Material.AIR);
                }
                if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
                    wither.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
                }

            }
        }

    }

    public static Wither makeBossWither(Location loc, World w){

        Wither wither = (Wither) w.spawnEntity(loc, EntityType.WITHER);
        wither.addScoreboardTag(Bosses.WITHER.scoreboardBossTag);

        wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.WITHER.health);
        wither.setHealth(Bosses.WITHER.health);

        wither.setCustomName(ChatColor.valueOf(Bosses.WITHER.nameColor) + Bosses.WITHER.displayName);
        wither.setCustomNameVisible(config.getBoolean("Bosses.WitherBoss.showDisplayNameAlways"));

        if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            wither.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

        return wither;
    }


}
