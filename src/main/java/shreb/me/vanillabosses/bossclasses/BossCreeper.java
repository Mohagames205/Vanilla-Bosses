package shreb.me.vanillabosses.bossclasses;

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
        creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(config.getDouble("Bosses.CreeperBoss.health"));
        creeper.setHealth(creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        creeper.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(creeper.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() * config.getDouble("Bosses.CreeperBoss.DamageModifier"));
        creeper.setMaxFuseTicks(40);
        creeper.setFuseTicks(40);
        PersistentDataContainer container = creeper.getPersistentDataContainer();
        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER, -1);
        creeper.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.6);
        creeper.setCustomName(config.getString("Bosses.CreeperBoss.displayName"));
        creeper.setCustomNameVisible(config.getBoolean("Bosses.CreeperBoss.showDisplayNameAlways"));
        return creeper;
    }

    /**
     * edits an existing mob into the boss version.
     * @param creeper the entity which to edit into the boss version.
     */

    public static void editToBossCreeper(Creeper creeper){

        creeper.addScoreboardTag("BossCreeper");
        creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(config.getDouble("Bosses.CreeperBoss.health"));
        creeper.setHealth(creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        creeper.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(creeper.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() * config.getDouble("Bosses.CreeperBoss.DamageModifier"));
        creeper.setMaxFuseTicks(40);
        creeper.setFuseTicks(40);
        PersistentDataContainer container = creeper.getPersistentDataContainer();
        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER, -1);
        creeper.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.6);
        creeper.setCustomName(config.getString("Bosses.CreeperBoss.displayName"));
        creeper.setCustomNameVisible(config.getBoolean("Bosses.CreeperBoss.showDisplayNameAlways"));
    }

}
