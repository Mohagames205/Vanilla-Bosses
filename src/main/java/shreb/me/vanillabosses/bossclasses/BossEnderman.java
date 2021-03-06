package shreb.me.vanillabosses.bossclasses;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shreb.me.vanillabosses.main.Main;

public class BossEnderman implements Listener {

    static Configuration config = Main.getInstance().getConfig();

    @EventHandler
    public void onTeleport(EntityTeleportEvent event) {

        if (event.getEntity().getType().equals(EntityType.ENDERMAN) && event.getEntity().getScoreboardTags().contains("BossEnderman")) {

            if (Main.getInstance().getConfig().getBoolean("Bosses.EndermanBoss.onHitEvents.endermiteSpawnOnTeleport.enabled")) {
                for (int i = 0; i <= Main.getInstance().getConfig().getInt("Bosses.EndermanBoss.onHitEvents.endermiteSpawnOnTeleport.amount"); i++) {
                    event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.ENDERMITE);
                }
            }
        }
    }

    @EventHandler
    public void onEndermanTargetMite(EntityTargetEvent event) {

        if(event.getTarget() == null) return;

        if(event.getEntity().getScoreboardTags().contains("BossEnderman")){

            if(event.getTarget().getType() == EntityType.ENDERMITE){
                event.setCancelled(true);
                return;
            }
        }

        if(event.getEntityType() == EntityType.ENDERMITE && event.getTarget().getScoreboardTags().contains("BossEnderman")){
            event.setCancelled(true);
        }

    }


    /**
     * spawns the boss as a new Entity
     * @param location specifies the location in which the boss will spawn
     */

    public static Enderman makeBossEnderman(Location location, World w){

        Enderman enderman = (Enderman) w.spawnEntity(location, EntityType.ENDERMAN);
        enderman.addScoreboardTag("BossEnderman");
        enderman.getScoreboardTags().add("VanillaBossesDamageTracker");
        enderman.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.ENDERMAN.health);
        enderman.setHealth(Bosses.ENDERMAN.health);
        enderman.setCustomName(net.md_5.bungee.api.ChatColor.of(Bosses.ENDERMAN.nameColor) + Bosses.ENDERMAN.displayName);
        enderman.setCustomNameVisible(config.getBoolean("Bosses.EndermanBoss.showDisplayNameAlways"));
        if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            enderman.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

        return enderman;
    }

    /**
     * edits an existing mob into the boss version.
     * @param enderman the entity which to edit into the boss version.
     */

    public static void editToBossEnderman(Enderman enderman){

        enderman.addScoreboardTag("BossEnderman");
        enderman.getScoreboardTags().add("VanillaBossesDamageTracker");
        enderman.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.ENDERMAN.health);
        enderman.setHealth(Bosses.ENDERMAN.health);
        enderman.setCustomName(ChatColor.of(Bosses.ENDERMAN.nameColor) + Bosses.ENDERMAN.displayName);
        enderman.setCustomNameVisible(config.getBoolean("Bosses.EndermanBoss.showDisplayNameAlways"));
        if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            enderman.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }
    }

}



