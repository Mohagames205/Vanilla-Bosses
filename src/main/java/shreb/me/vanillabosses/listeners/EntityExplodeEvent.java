package shreb.me.vanillabosses.listeners;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.bossclasses.RespawningBosses;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

import java.util.Set;
import java.util.UUID;

public class EntityExplodeEvent implements Listener {

    @EventHandler
    public void onEntityExplode(org.bukkit.event.entity.EntityExplodeEvent event) {

//BossCreeper

        if (event.getEntity().getScoreboardTags().contains("BossCreeper") && event.getEntityType() == EntityType.CREEPER) {

            Configuration config = Main.getInstance().getConfig();

            Creeper creeper = (Creeper) event.getEntity();
            Creeper creeperNew = (Creeper) creeper.getWorld().spawnEntity(creeper.getLocation(), EntityType.CREEPER);
            PersistentDataContainer containerNew = creeperNew.getPersistentDataContainer();

            Methods.spawnParticles(Particle.SMALL_FLAME, event.getEntity().getWorld(), event.getLocation(), 4, 2, 4, 30, 3);

            PersistentDataContainer container = creeper.getPersistentDataContainer();
            String name = creeper.getCustomName();
            boolean alwaysVisible = creeper.isCustomNameVisible();
            double hp = creeper.getHealth();
            UUID id = creeper.getUniqueId();
            Set<String> scoreboardTags = creeper.getScoreboardTags();


            if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING) != null) {
                containerNew.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING));
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING) != null) {
                containerNew.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING));
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER) != null) {
                containerNew.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER));
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER) != null) {
                containerNew.set(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER));
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE) != null) {
                containerNew.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE));
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE) != null) {
                containerNew.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE));
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE) != null) {
                containerNew.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE));
            }

            event.getEntity().getWorld().playSound(event.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1F, 1F);

            creeperNew.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

            creeperNew.setHealth(hp);
            creeperNew.getScoreboardTags().addAll(scoreboardTags);
            creeperNew.setCustomName(name);
            UUID newID = creeperNew.getUniqueId();
            creeperNew.setCustomNameVisible(alwaysVisible);

            if(RespawningBosses.bossBarHashMap.containsKey(id)) {
                RespawningBosses.bossBarHashMap.put(newID, RespawningBosses.bossBarHashMap.get(id));
                RespawningBosses.respawningBosses.get(EntityType.CREEPER).add(newID);
            }

            creeperNew.getScoreboardTags().add("removeOnDisable");
            creeperNew.setRemoveWhenFarAway(false);

            event.setCancelled(true);

            creeperNew.addScoreboardTag("ExplodingATM");
            Creeper finalCreeper1 = creeperNew;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                finalCreeper1.removeScoreboardTag("ExplodingATM");
            }, 20L * config.getInt("Bosses.CreeperBoss.thrownTNT.TNTFuse"));

            creeperNew.setExplosionRadius(20);

            if (config.getBoolean("Bosses.CreeperBoss.thrownTNT.throwTNTEnable")) {

                Entity[] TNTArry = {
                        creeperNew.getWorld().spawnEntity(creeperNew.getLocation(), EntityType.PRIMED_TNT),
                        creeperNew.getWorld().spawnEntity(creeperNew.getLocation(), EntityType.PRIMED_TNT),
                        creeperNew.getWorld().spawnEntity(creeperNew.getLocation(), EntityType.PRIMED_TNT),
                        creeperNew.getWorld().spawnEntity(creeperNew.getLocation(), EntityType.PRIMED_TNT),
                        creeperNew.getWorld().spawnEntity(creeperNew.getLocation(), EntityType.PRIMED_TNT),
                        creeperNew.getWorld().spawnEntity(creeperNew.getLocation(), EntityType.PRIMED_TNT),
                        creeperNew.getWorld().spawnEntity(creeperNew.getLocation(), EntityType.PRIMED_TNT),
                        creeperNew.getWorld().spawnEntity(creeperNew.getLocation(), EntityType.PRIMED_TNT),
                };

                for (Entity e : TNTArry
                ) {
                    ((TNTPrimed) e).setYield(config.getInt("Bosses.CreeperBoss.thrownTNT.TNTYield"));
                    ((TNTPrimed) e).setFuseTicks(20 * config.getInt("Bosses.CreeperBoss.thrownTNT.TNTFuse"));
                }

                double multiplier = config.getDouble("Bosses.CreeperBoss.thrownTNT.TNTSpreadMultiplier");

                TNTArry[0].setVelocity(new Vector(0.25 * multiplier, 0.5, 0));
                TNTArry[1].setVelocity(new Vector(-0.25 * multiplier, 0.5, 0));
                TNTArry[2].setVelocity(new Vector(0, 0.5, 0.25 * multiplier));
                TNTArry[3].setVelocity(new Vector(0, 0.5, -0.25 * multiplier));
                TNTArry[4].setVelocity(new Vector(0.25 * multiplier, 0.5, 0.25 * multiplier));
                TNTArry[5].setVelocity(new Vector(-0.25 * multiplier, 0.5, 0.25 * multiplier));
                TNTArry[6].setVelocity(new Vector(0.25 * multiplier, 0.5, -0.25 * multiplier));
                TNTArry[7].setVelocity(new Vector(-0.25 * multiplier, 0.5, -0.25 * multiplier));

                if (config.getBoolean("Bosses.CreeperBoss.thrownTNT.TNTDoesNoBlockDamage")) {
                    for (Entity e : TNTArry) {
                        e.addScoreboardTag("CancelOnExplode");
                        e.getScoreboardTags().add("dontBlowUpItems");
                    }
                }
            }
        }



//Exploding Arrow cancelling

        if (event.getEntity().getScoreboardTags().contains("CancelOnExplode")) {

            event.setCancelled(true);
            event.getEntity().getWorld().playSound(event.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1F, 1F);

        }
    }
}
