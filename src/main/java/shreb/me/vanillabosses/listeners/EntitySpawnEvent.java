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
import shreb.me.vanillabosses.main.BossDamagedTracker;
import shreb.me.vanillabosses.main.Helpers.BossDamageTrackerHelper;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

import java.util.List;

public class EntitySpawnEvent implements Listener {

    static Configuration config = Main.getInstance().getConfig();
    public static boolean spawn = true;
    private static boolean bossSpawned = false;
    public static String damageTrackerSBTag = "VanillaBossesDamageTracker";

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

        String type = event.getEntityType().toString();
        try {
            List<String> worlds = config.getStringList("Bosses." + Bosses.valueOf(type).configSectionName + ".spawnWorlds");
            if(!worlds.isEmpty() && !worlds.contains(event.getLocation().getWorld().getName())) return;
        }  catch(IllegalArgumentException e){
            return;
        }


//SkeletonBoss

        if (event.getEntityType().equals(EntityType.SKELETON)) {

            if (!config.getBoolean("Bosses.SkeletonBoss.enabled")) return;

            if (config.getBoolean("Bosses.SkeletonBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) <= Bosses.SKELETON.spawnChance) {

                    BossSkeleton.editToBossSkeleton((Skeleton) event.getEntity());
                    bossSpawned = true;

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

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) < Bosses.CREEPER.spawnChance) {

                    BossCreeper.editToBossCreeper((Creeper) event.getEntity());
                    bossSpawned = true;

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

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) < Bosses.SPIDER.spawnChance) {

                    BossSpider.editToBossSpider((Spider) event.getEntity());
                    bossSpawned = true;

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

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) < Bosses.BLAZE.spawnChance) {

                    BossBlaze.editToBossBlaze((Blaze) event.getEntity());
                    bossSpawned = true;

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

            if (config.getBoolean("Bosses.EndermanBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) < Bosses.ENDERMAN.spawnChance) {

                    BossEnderman.editToBossEnderman((Enderman) event.getEntity());
                    bossSpawned = true;

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

            if (config.getBoolean("Bosses.ZombieBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) < Bosses.ZOMBIE.spawnChance) {

                    BossZombie.editToBossZombie((Zombie) event.getEntity());
                    bossSpawned = true;

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

            if (config.getBoolean("Bosses.Zombified_PiglinBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) < Bosses.ZOMBIFIED_PIGLIN.spawnChance) {

                    BossZombified_Piglin.editToBossZombified_Piglin((PigZombie) event.getEntity());
                    bossSpawned = true;

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

            if (config.getBoolean("Bosses.WitchBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) < Bosses.WITCH.spawnChance) {

                    BossWitch.editToBossWitch((Witch) event.getEntity());
                    bossSpawned = true;

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

            if (config.getBoolean("Bosses.SlimeBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) < Bosses.SLIME.spawnChance) {

                    BossSlime.editToBossSlime((Slime) event.getEntity());
                    bossSpawned = true;

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.SlimeBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }

                }
            }
        }

//MagmacubeBoss

        if (event.getEntityType() == EntityType.MAGMA_CUBE) {

            if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SLIME_SPLIT) {

                event.getEntity().setCustomName("");

                return;
            }

            if (!config.getBoolean("Bosses.Magma_cubeBoss.enabled")) return;

            if (config.getBoolean("Bosses.Magma_cubeBoss.spawnNaturally")) {

                if (Methods.randomNumber(0, config.getInt("Bosses.MaxSpawnChance")) < Bosses.MAGMA_CUBE.spawnChance) {

                    BossMagmacube.editToBossMagmacube((MagmaCube) event.getEntity());
                    bossSpawned = true;

                    PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
                    String command = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.Magma_cubeBoss.CommandToBeExecutedOnDeath"));
                    if(!command.equals("")){
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                    }
                }
            }
        }

        if(bossSpawned) BossDamagedTracker.bossDamageTracker.put(event.getEntity().getUniqueId(), new BossDamageTrackerHelper());
    }
}
