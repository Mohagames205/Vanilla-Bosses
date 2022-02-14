package shreb.me.vanillabosses.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.bossclasses.*;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

public class EntitySpawnEvent implements Listener {

    static Configuration config = Main.getInstance().getConfig();
    public static boolean spawn = true;

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {

        if (!spawn) return;

        if(event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER ) {

            if(event.getEntityType() != EntityType.BLAZE) {
                return;
            } else if(config.getBoolean("Bosses.disableBlazeBossesSpawningFromSpawners")){
                return;
            }
        }

//SkeletonBoss

        if (event.getEntityType().equals(EntityType.SKELETON)) {

            if (!config.getBoolean("Bosses.SkeletonBoss.enabled")) return;

            if (config.getBoolean("Bosses.SkeletonBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, 1000) <= Bosses.SKELETON.spawnChance) {

                    BossSkeleton.editToBossSkeleton((Skeleton) event.getEntity());

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.SkeletonBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }
                }
            }
        }

//CreeperBoss

        if (event.getEntityType().equals(EntityType.CREEPER)) {

            if (!config.getBoolean("Bosses.CreeperBoss.enabled")) return;

            if (config.getBoolean("Bosses.CreeperBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, 1000) < Bosses.CREEPER.spawnChance) {

                    BossCreeper.editToBossCreeper((Creeper) event.getEntity());

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.CreeperBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }
                }
            }
        }

//SpiderBoss

        if (event.getEntityType().equals(EntityType.SPIDER)) {

            if (!config.getBoolean("Bosses.SpiderBoss.enabled")) return;

            if (config.getBoolean("Bosses.SpiderBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, 1000) < Bosses.SPIDER.spawnChance) {

                    BossSpider.editToBossSpider((Spider) event.getEntity());

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.SpiderBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }

                }
            }
        }

//BlazeBoss

        if (event.getEntityType().equals(EntityType.BLAZE)) {

            if (!config.getBoolean("Bosses.BlazeBoss.enabled")) return;

            if (config.getBoolean("Bosses.BlazeBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, 1000) < Bosses.BLAZE.spawnChance) {

                    BossBlaze.editToBossBlaze((Blaze) event.getEntity());

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.BlazeBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }

                }
            }
        }

//Enderman Boss

        if (event.getEntityType().equals(EntityType.ENDERMAN)) {

            if (!config.getBoolean("Bosses.EndermanBoss.enabled")) return;

            if (config.getBoolean("Bosses.CreeperBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, 1000) < Bosses.ENDERMAN.spawnChance) {

                    BossEnderman.editToBossEnderman((Enderman) event.getEntity());

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.EndermanBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }

                }
            }
        }

//ZombieBoss

        if (event.getEntityType().equals(EntityType.ZOMBIE)) {

            if (!config.getBoolean("Bosses.ZombieBoss.enabled")) return;

            if (config.getBoolean("Bosses.CreeperBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, 1000) < Bosses.ZOMBIE.spawnChance) {

                    BossZombie.editToBossZombie((Zombie) event.getEntity());

                    BossZombie.zombieHorde(4, 12, event.getEntity().getLocation());
                    Methods.mobHorde(4, 12, "zombie", event.getLocation());

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.ZombieBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }

                }
            }
        }

//Zombified_PiglinBoss

        if (event.getEntityType().equals(EntityType.ZOMBIFIED_PIGLIN)) {

            if (!config.getBoolean("Bosses.Zombified_PiglinBoss.enabled")) return;

            if (config.getBoolean("Bosses.CreeperBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, 1000) < Bosses.ZOMBIEPIGMAN.spawnChance) {

                    BossZombified_Piglin.editToBossZombified_Piglin((PigZombie) event.getEntity());

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.Zombified_PiglinBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }

                }
            }
        }

//WitchBoss

        if (event.getEntityType().equals(EntityType.WITCH)) {

            if (!config.getBoolean("Bosses.WitchBoss.enabled")) return;

            if (config.getBoolean("Bosses.CreeperBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, 1000) < Bosses.WITCH.spawnChance) {

                    BossWitch.editToBossWitch((Witch) event.getEntity());

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.WitchBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }

                }
            }
        }

//SlimeBoss

        if (event.getEntityType() == EntityType.SLIME) {

            if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SLIME_SPLIT) {

                event.getEntity().setCustomName("");

                return;
            }

            if (!config.getBoolean("Bosses.SlimeBoss.enabled")) return;

            if (config.getBoolean("Bosses.CreeperBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, 1000) < Bosses.SLIME.spawnChance) {

                    BossSlime.editToBossSlime((Slime) event.getEntity());

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.SlimeBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }

                }
            }
        }
    }
}
