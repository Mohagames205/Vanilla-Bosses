package shreb.me.vanillabosses.bossclasses;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shreb.me.vanillabosses.main.Main;



public class BossCreeper implements Listener {

    static Configuration config = Main.getInstance().getConfig();

    @EventHandler
    public void bossLitOnFire(EntityCombustEvent event){

        if(event.getEntityType().equals(EntityType.CREEPER) && event.getEntity().getScoreboardTags().contains("BossCreeper")){

            event.setCancelled(true);

            Creeper creeper = (Creeper) event.getEntity();

            if(!(creeper.getScoreboardTags().contains("ExplodingATM"))) {
                creeper.ignite();
                creeper.addScoreboardTag("ExplodingATM");
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () ->{

                creeper.removeScoreboardTag("ExplodingATM");

            },20L * config.getInt("Bosses.CreeperBoss.thrownTNT.TNTFuse") +5 );
        }
    }

    @EventHandler
    public void bossIgnite(EntityCombustEvent event){
        if(event.getEntity().getType().equals(EntityType.CREEPER) && event.getEntity().getScoreboardTags().contains("BossCreeper")){
            if(event.getEntity().getScoreboardTags().contains("ExplodingATM")){
                event.setCancelled(true);
            }
        }
    }

    /**
     * spawns the boss as a new Entity
     * @param location specifies the location in which the boss will spawn
     */

    public static Creeper makeBossCreeper(Location location, World w){

        Creeper creeper = (Creeper) w.spawnEntity(location, EntityType.CREEPER);
        creeper.addScoreboardTag("BossCreeper");
        creeper.getScoreboardTags().add("VanillaBossesDamageTracker");
        creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.CREEPER.health);
        creeper.setHealth(Bosses.CREEPER.health);
        creeper.setMaxFuseTicks(40);
        PersistentDataContainer container = creeper.getPersistentDataContainer();
        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER, -1);
        creeper.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.6);
        creeper.setCustomName(net.md_5.bungee.api.ChatColor.of(Bosses.CREEPER.nameColor) + Bosses.CREEPER.displayName);
        creeper.setCustomNameVisible(config.getBoolean("Bosses.CreeperBoss.showDisplayNameAlways"));
        if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            creeper.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

        if(config.getBoolean("Bosses.CreeperBoss.thrownTNT.TNTDoesNoBlockDamage")) creeper.getScoreboardTags().add("dontBlowUpItems");

        return creeper;
    }

    /**
     * edits an existing mob into the boss version.
     * @param creeper the entity which to edit into the boss version.
     */

    public static void editToBossCreeper(Creeper creeper){

        creeper.addScoreboardTag("BossCreeper");
        creeper.getScoreboardTags().add("VanillaBossesDamageTracker");
        creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.CREEPER.health);
        creeper.setHealth(Bosses.CREEPER.health);
        creeper.setMaxFuseTicks(40);
        PersistentDataContainer container = creeper.getPersistentDataContainer();
        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER, -1);
        creeper.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.6);
        creeper.setCustomName(ChatColor.of(Bosses.CREEPER.nameColor) + Bosses.CREEPER.displayName);
        creeper.setCustomNameVisible(config.getBoolean("Bosses.CreeperBoss.showDisplayNameAlways"));
        if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            creeper.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

        if(config.getBoolean("Bosses.CreeperBoss.thrownTNT.TNTDoesNoBlockDamage")) creeper.getScoreboardTags().add("dontBlowUpItems");

    }

}
