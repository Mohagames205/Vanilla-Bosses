package shreb.me.vanillabosses.bossclasses;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.main.Main;

import java.util.*;

public class RespawningBosses {

    public static HashMap<EntityType, Set<UUID>> respawningBosses = new HashMap<>();
    public static HashMap<UUID, BossBar> bossBarHashMap = new HashMap<>();
    public static ArrayList<String> deathCommands = new ArrayList<>(Main.getInstance().getConfig().getStringList("Bosses.CommandsExecutedOnBossDeath"));


    public static final String[] strings = {
            "Blaze",
            "Creeper",
            "Enderman",
            "Skeleton",
            "Slime",
            "Spider",
            "Witch",
            "Zombie",
            "Wither",
            "Zombified_Piglin",
            "Magmacube"
    };

    public static LivingEntity bossSpawnMethod(EntityType type, Location loc, World w) {

        LivingEntity entity;

        switch (type) {

            case BLAZE:
                entity = BossBlaze.makeBossBlaze(loc, w);
                break;

            case CREEPER:
                entity = BossCreeper.makeBossCreeper(loc, w);
                break;

            case ENDERMAN:
                entity = BossEnderman.makeBossEnderman(loc, w);
                break;

            case SKELETON:
                entity = BossSkeleton.makeBossSkeleton(loc, w);
                break;

            case SLIME:
                entity = BossSlime.makeBossSlime(loc, w);
                break;

            case SPIDER:
                entity = BossSpider.makeBossSpider(loc, w);
                break;

            case WITCH:
                entity = BossWitch.makeBossWitch(loc, w);
                break;

            case ZOMBIE:
                entity = BossZombie.makeBossZombie(loc, w);
                break;

            case ZOMBIFIED_PIGLIN:
                entity = BossZombified_Piglin.makeBossZombified_Piglin(loc, w);
                break;

            case WITHER:
                entity = BossWither.makeBossWither(loc, w);
                break;

            case MAGMA_CUBE:
                entity = BossMagmacube.makeBossMagmacube(loc, w);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return entity;
    }

    public static void spawnMobs() {

        for (String s : strings) {

            Set<UUID> set = new HashSet<>();
            EntityType type = EntityType.valueOf(s.toUpperCase());
            respawningBosses.put(type, set);

            List<String> strings = Main.getInstance().getConfig().getStringList("Bosses." + s + "Boss.worldsLocationsTimer");

            if (!strings.isEmpty()) {
                for (String s1 : strings) {

                    String[] stringArray = s1.split(":");

                    World w = Main.getInstance().getServer().getWorld(stringArray[0]);

                    if (w != null) {

                        Location loc = w.getBlockAt(0, 0, 0).getLocation();
                        loc.add(new Vector(Double.parseDouble(stringArray[1]), Double.parseDouble(stringArray[2]), Double.parseDouble(stringArray[3])));

                        LivingEntity entity = bossSpawnMethod(type, loc, w);
                        entity.getScoreboardTags().add("removeOnDisable");
                        entity.setRemoveWhenFarAway(false);

                        PersistentDataContainer container = entity.getPersistentDataContainer();

                        if (stringArray.length >= 6) {

                            try {
                                if (Integer.parseInt(stringArray[5]) >= deathCommands.size()) {

                                    Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "Vanilla Bosses: Error: There was an error loading command number " + stringArray[5] + ".");
                                    Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Respawning Bosses Were not spawned correctly, it is recommended to fix the error in the config and reload/restart the server.");
                                    entity.remove();
                                    return;
                                }
                            } catch (NumberFormatException e) {
                                Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "Vanilla Bosses: Error: " + stringArray[5] + " is not an accepted numerical value.");
                                Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Respawning Bosses Were not spawned correctly, it is recommended to fix the error in the config and reload/restart the server.");
                                entity.remove();
                                return;
                            }

                            String command = deathCommands.get(Integer.parseInt(stringArray[5]));


                            container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, command);
                            if (stringArray.length >= 7) {
                                int timer = Integer.parseInt(stringArray[6]);
                                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER, timer);
                            }
                        }

                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING, stringArray[0]);
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE, Double.parseDouble(stringArray[1]));
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE, Double.parseDouble(stringArray[2]));
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE, Double.parseDouble(stringArray[3]));
                        container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER, Integer.parseInt(stringArray[4]));
                        respawningBosses.get(entity.getType()).add(entity.getUniqueId());
                    }
                }
            }
        }
    }

    public static void bossBarTimer() {

        BossBar bossBar;
        LivingEntity livingEntity;

        HashMap<EntityType, String> configValues = new HashMap<>();

        for (String s : strings) {
            configValues.put(EntityType.valueOf(s.toUpperCase()), s);
        }

        for (EntityType type : respawningBosses.keySet()) {

            if (!configValues.get(type).equalsIgnoreCase("Wither") && Main.getInstance().getConfig().getBoolean("Bosses." + configValues.get(type) + "Boss.enableBossBars")) {

                for (UUID id : respawningBosses.get(type)) {

                    if (Main.getInstance().getServer().getEntity(id) == null) {

                        bossBarHashMap.remove(id);

                    } else if (Main.getInstance().getServer().getEntity(id).getCustomName() != null) {

                        livingEntity = (LivingEntity) Main.getInstance().getServer().getEntity(id);
                        bossBar = Bukkit.createBossBar(
                                Objects.requireNonNull(livingEntity).getCustomName(),
                                BarColor.valueOf(Main.getInstance().getConfig().getString("Bosses." + configValues.get(type) + "Boss.bossBarColor").toUpperCase()),
                                BarStyle.SOLID);

                        bossBar.setProgress(1.0);

                        bossBarHashMap.put(id, bossBar);
                    }
                }
            }
        }


        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {

            List<UUID> uuidsToRemove = new ArrayList<>();

            for (UUID id : bossBarHashMap.keySet()) {

                if (Main.getInstance().getServer().getEntity(id) == null) {
                    bossBarHashMap.get(id).removeAll();
                    uuidsToRemove.add(id);
                }

                if (bossBarHashMap.get(id) == null) {
                    uuidsToRemove.add(id);
                }

            }

            for (UUID uuid : uuidsToRemove) {
                bossBarHashMap.remove(uuid);
            }

            LivingEntity entity;
            List<Player> entityList = new ArrayList<>();
            ArrayList<Player> onlinePlayers = new ArrayList<>(Main.getInstance().getServer().getOnlinePlayers());
            ArrayList<Player> onlinePlayersCopy = new ArrayList<>();

            for (UUID uuid : bossBarHashMap.keySet()) {

                onlinePlayersCopy.clear();
                onlinePlayersCopy.addAll(onlinePlayers);

                entity = (LivingEntity) Main.getInstance().getServer().getEntity(uuid);
                World w = entity.getWorld();


                bossBarHashMap.get(uuid).setProgress(entity.getHealth() / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                bossBarHashMap.get(uuid).setTitle(bossBarHashMap.get(uuid).getTitle());


                for (Entity e : w.getNearbyEntities(entity.getLocation(), 25, 25, 25, n -> (n instanceof Player))) {
                    entityList.add((Player) e);
                }

                for (Player p : entityList) {
                    bossBarHashMap.get(uuid).addPlayer(p);
                }

                if (!entityList.isEmpty()) {
                    onlinePlayersCopy.removeAll(entityList);
                }

                for (Player p : onlinePlayersCopy) {
                    bossBarHashMap.get(uuid).removePlayer(p);
                }

                entityList.clear();
            }

        }, 20, 50);

    }

}
