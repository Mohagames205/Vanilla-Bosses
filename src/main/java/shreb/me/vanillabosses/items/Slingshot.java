package shreb.me.vanillabosses.items;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;
import java.util.Objects;

public class Slingshot implements Listener {

    int taskID;

    @EventHandler
    public void onRCItem(PlayerInteractEvent event) {

        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getItem() == null) return;
        if (!event.getItem().hasItemMeta()) return;
        if (!event.getPlayer().isSneaking()) return;

        if (event.getItem().getItemMeta().getPersistentDataContainer().has(Items.SLINGSHOT.identifyingPDCKey, PersistentDataType.INTEGER)) {
            event.getPlayer().getScoreboardTags().add("NoFallDMG");

            if(taskID != 0){

                Bukkit.getScheduler().cancelTask(taskID);

            }
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                event.getPlayer().getScoreboardTags().remove("NoFallDMG");
                taskID = 0;

            }, 20*20);
            double multiplier = Main.getInstance().getConfig().getDouble("Items.slingshot.thrustMultiplier");
            Vector v = event.getPlayer().getLocation().getDirection();
            double x = v.getX() * multiplier;
            double y = v.getY() * multiplier;
            double z = v.getZ() * multiplier;
            v = new Vector(x, y, z);
            event.getPlayer().setVelocity(v);
            if (Main.getInstance().getConfig().getBoolean("Items.slingshot.enableBoostSound")) {
                event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PARROT_IMITATE_SPIDER, (float)Main.getInstance().getConfig().getDouble("Items.slingshot.boostSoundVolume"), 0.5F);
            }
            if (Main.getInstance().getConfig().getBoolean("Items.slingshot.enableDamagingOnUse")) {
                ItemStack item = event.getItem();
                ItemMeta meta = item.getItemMeta();
                ((Damageable) meta).setDamage(((Damageable) meta).getDamage() + Main.getInstance().getConfig().getInt("Items.slingshot.damageOnUseAmount"));
                item.setItemMeta(meta);
                if (((Damageable) item.getItemMeta()).getDamage() > item.getType().getMaxDurability()) {      //Item breaking upon reaching 0 Durability
                    event.getPlayer().getEquipment().setItem(event.getHand(), new ItemStack(Material.AIR));
                    event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                }
            }
        }
    }

    static int damageOnUse = Main.getInstance().getConfig().getInt("Items.SlimeBoots.damagePerUse");

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {

        if (!event.getEntityType().equals(EntityType.PLAYER)) return;
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) return;

        if (event.getEntity().getScoreboardTags().contains("NoFallDMG")) {

            event.getEntity().getScoreboardTags().removeIf(s -> s.equals("NoFallDMG"));

            event.setCancelled(true);
            return;
        }

        //This is for the Slimeboots, didnt wanna make a new event.
        if(((Player)event.getEntity()).getEquipment().getBoots() != null){
            if(((Player)event.getEntity()).getEquipment().getBoots().getItemMeta().getPersistentDataContainer().has(Items.SLINGSHOT.identifyingPDCKey, PersistentDataType.STRING)){

                event.setDamage(event.getDamage() / 5);

                damageOnUse = (int)(event.getDamage() * Main.getInstance().getConfig().getInt("Items.SlimeBoots.damageOnUseMultiplier"));

                ItemMeta meta = ((Player)event.getEntity()).getEquipment().getBoots().getItemMeta();
                ((Damageable)meta).setDamage(((Damageable)meta).getDamage() + damageOnUse);
                ((Player)event.getEntity()).getEquipment().getBoots().setItemMeta(meta);

                ((Player) event.getEntity()).playSound(event.getEntity().getLocation(), Sound.ENTITY_SLIME_SQUISH, 1f, 1f);
                ((Player) event.getEntity()).spawnParticle(Particle.SLIME, event.getEntity().getLocation(), 100, 2, 1, 2);

                if(((Damageable) meta).getDamage() > Material.LEATHER_BOOTS.getMaxDurability()){
                    ((Player)event.getEntity()).getEquipment().setBoots(new ItemStack(Material.AIR));
                    event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                }
            }
        }
    }

    public static void removeNoFallDMGTagTimer() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), () -> {
            for (Player p : Main.getInstance().getServer().getOnlinePlayers()) {
                Location loc = p.getLocation();
                loc.subtract(0,0.5,0);
                if (p.getScoreboardTags().contains("NoFallDMG") &&
                        (!p.getWorld().getBlockAt(loc).getType().equals(Material.AIR)
                        || p.isGliding())
                ) {
                    p.getScoreboardTags().removeIf(n -> n.equals("NoFallDMG"));
                }
            }
        }, 100, 120);
    }

    public static ItemStack makeSlingshot(){
        ItemStack slingshot = new ItemStack(Objects.requireNonNull(Material.getMaterial(Main.getInstance().getConfig().getString("Items.slingshot.itemMaterial").toUpperCase())));
        ItemMeta meta = slingshot.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.DARK_RED + "Slingshot");
        lore.addAll(Main.getInstance().getConfig().getStringList("Items.slingshot.Lore"));
        meta.setLore(lore);
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(Items.SLINGSHOT.identifyingPDCKey, PersistentDataType.INTEGER,  1);
        slingshot.setItemMeta(meta);

        return slingshot;
    }

}
