package shreb.me.vanillabosses.listeners;

import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.bossclasses.*;
import shreb.me.vanillabosses.items.*;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class EntityDeathEvent implements Listener {

    @EventHandler
    public void onBossDeath(org.bukkit.event.entity.EntityDeathEvent event) {


//BossWither death


        if (event.getEntityType().equals(EntityType.WITHER) && event.getEntity().getScoreboardTags().contains("BossWither") && Main.getInstance().getConfig().getBoolean("Items.WitherEgg.witherBossDropsEgg")) {

            Configuration config = Main.getInstance().getConfig();

            if (config.getInt("Bosses.WitherBoss.droppedXP") >= 0) {
                event.setDroppedExp(config.getInt("Bosses.WitherBoss.droppedXP"));
            } else if (config.getInt("Bosses.WitherBoss.droppedXP") < 0) event.setDroppedExp(0);

            if (config.getBoolean("Bosses.WitherBoss.disableVanillaDrops")) {
                event.getDrops().clear();
            }

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.WitherBoss.droppedItems")));

            ItemStack witherEgg = BossWither.makeWitherEgg();

            event.getDrops().add(witherEgg);

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null)  {
                event.getEntity().getServer().broadcastMessage(ChatColor.MAGIC + "##" + ChatColor.DARK_PURPLE + event.getEntity().getCustomName() + ChatColor.MAGIC + "" + ChatColor.WHITE + "##" + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }



        }


//PassiveWither death


        if (event.getEntityType().equals(EntityType.WITHER) && event.getEntity().getScoreboardTags().contains("PassiveWither")) {

            event.setDroppedExp(0);

            event.getDrops().remove(new ItemStack(Material.NETHER_STAR));

            if(event.getEntity().getKiller() == null && event.getEntity().getCustomName() != null){
                event.getEntity().getServer().broadcastMessage(event.getEntity().getCustomName() + " has been killed by " + event.getEntity().getKiller().getName());
            } else {
                event.getEntity().getServer().broadcastMessage(event.getEntity().getCustomName() + " has died");
            }

        }


//BossCreeper


        if (event.getEntity().getScoreboardTags().contains("BossCreeper")) {

            Configuration config = Main.getInstance().getConfig();

            if (config.getInt("Bosses.CreeperBoss.droppedXP") >= 0) {
                event.setDroppedExp(config.getInt("Bosses.CreeperBoss.droppedXP"));
            } else if (config.getInt("Bosses.CreeperBoss.droppedXP") < 0) event.setDroppedExp(0);

            if (config.getBoolean("Bosses.CreeperBoss.disableVanillaDrops")) {
                event.getDrops().clear();
            }

            event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.CreeperBoss.droppedItems")));

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.GREEN + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }

        }


//BossSpider


        if (event.getEntity().getScoreboardTags().contains("BossSpider")) {

            Configuration config = Main.getInstance().getConfig();

            if (config.getInt("Bosses.SpiderBoss.droppedXP") >= 0) {
                event.setDroppedExp(config.getInt("Bosses.SpiderBoss.droppedXP"));
            } else if (config.getInt("Bosses.SpiderBoss.droppedXP") < 0) event.setDroppedExp(0);

            if (config.getBoolean("Bosses.SpiderBoss.disableVanillaDrops")) {
                event.getDrops().clear();
            }

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.SpiderBoss.droppedItems")));

            if (config.getBoolean("Items.slingshot.enableDropping") && Methods.randomNumber(0, 100) < config.getInt("Items.slingshot.chance")) {
                event.getDrops().add(Slingshot.makeSlingshot());
            }

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.RED + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }

        }


//BossSkeleton


        if (event.getEntity().getScoreboardTags().contains("BossSkeleton")) {

            Configuration config = Main.getInstance().getConfig();

            event.setDroppedExp(Math.max(config.getInt("Bosses.SkeletonBoss.droppedXP"), 0));

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.SkeletonBoss.droppedItems")));

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.WHITE + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }

        }


//BossBlaze

        if (event.getEntity().getScoreboardTags().contains("BossBlaze")) {

            Configuration config = Main.getInstance().getConfig();

            event.setDroppedExp(Math.max(config.getInt("Bosses.BlazeBoss.droppedXP"), 0));

            if (config.getBoolean("Bosses.BlazeBoss.disableVanillaDrops")) {
                event.getDrops().clear();
            }

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.BlazeBoss.droppedItems")));

            if (Methods.randomNumber(0, 100) < config.getInt("Items.Blazer.dropChance")) {
                event.getDrops().add(Blazer.makeBlazer());
            }

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.YELLOW + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }

            BossBlaze.bossBlazeTargetMap.remove(event.getEntity().getEntityId());
        }

//BossEnderman

        if (event.getEntity().getScoreboardTags().contains("BossEnderman")) {

            Configuration config = Main.getInstance().getConfig();

            event.setDroppedExp(Math.max(config.getInt("Bosses.EndermanBoss.droppedXP"), 0));

            if (config.getBoolean("Bosses.EndermanBoss.disableVanillaDrops")) {
                event.getDrops().clear();
            }

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.EndermanBoss.droppedItems")));

            if (config.getBoolean("Items.cloakOfInvisibility.enableDropping") && Methods.randomNumber(0, 100) < config.getInt("Items.cloakOfInvisibility.chance")) {
                event.getDrops().add(InvisibilityCloak.makeCloak());
            }

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.BLACK + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }

        }


//BossZombie


        if (event.getEntity().getScoreboardTags().contains("BossZombie")) {

            Configuration config = Main.getInstance().getConfig();

            event.setDroppedExp(Math.max(config.getInt("Bosses.ZombieBoss.droppedXP"), 0));

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.ZombieBoss.droppedItems")));

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.DARK_GREEN + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }
        }


//BossZombified_Piglin


        if (event.getEntity().getScoreboardTags().contains("BossZombified_Piglin")) {

            Configuration config = Main.getInstance().getConfig();

            event.setDroppedExp(Math.max(config.getInt("Bosses.Zombified_PiglinBoss.droppedXP"), 0));

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.Zombified_PiglinBoss.droppedItems")));

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }
        }


//BossWitch


        if (event.getEntity().getScoreboardTags().contains("BossWitch")) {

            Configuration config = Main.getInstance().getConfig();

            event.setDroppedExp(Math.max(config.getInt("Bosses.WitchBoss.droppedXP"), 0));

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.WitchBoss.droppedItems")));

            int rn = Methods.randomNumber(0, 100);
            if (rn < config.getIntegerList("Bosses.WitchBoss.dropCustomPotionsChances").get(0))
                event.getDrops().add(BossWitch.makeDamagePot());
            rn = Methods.randomNumber(0, 100);
            if (rn < config.getIntegerList("Bosses.WitchBoss.dropCustomPotionsChances").get(1))
                event.getDrops().add(BossWitch.makePoisonPot());
            rn = Methods.randomNumber(0, 100);
            if (rn < config.getIntegerList("Bosses.WitchBoss.dropCustomPotionsChances").get(2))
                event.getDrops().add(BossWitch.makeBlindnessPot());
            rn = Methods.randomNumber(0, 100);
            if (rn < config.getIntegerList("Bosses.WitchBoss.dropCustomPotionsChances").get(3))
                event.getDrops().add(BossWitch.makeWitherPot());
            rn = Methods.randomNumber(0, 100);
            if (rn < config.getIntegerList("Bosses.WitchBoss.dropCustomPotionsChances").get(4))
                event.getDrops().add(BossWitch.makeHungerPot());

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.DARK_PURPLE + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }

        }


//BossSlime


        if (event.getEntity().getScoreboardTags().contains("BossSlime")) {

            int chance = Main.getInstance().getConfig().getInt("Items.SlimeBoots.BouncySlime.dropChance");
            for (int i = 0; i < Main.getInstance().getConfig().getInt("Items.SlimeBoots.BouncySlime.maxDropped"); i++) {

                if (Methods.randomNumber(0, 100) < chance) {
                    event.getDrops().add(SlimeBoots.makeBouncySlime());
                }

            }

            if (Methods.randomNumber(0, 100) < Main.getInstance().getConfig().getInt("Items.SlimeBoots.dropChance")) {
                event.getDrops().add(SlimeBoots.makeSlimeBoots());
            }

            Configuration config = Main.getInstance().getConfig();

            event.setDroppedExp(Math.max(config.getInt("Bosses.SlimeBoss.droppedXP"), 0));

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.SlimeBoss.droppedItems")));

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.DARK_GREEN + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }
        }

        //BossMagmacube


        if (event.getEntity().getScoreboardTags().contains(Bosses.MAGMA_CUBE.scoreboardBossTag)) {

            Configuration config = Main.getInstance().getConfig();

            int chance = config.getInt("Items.HeatedMagmaCream.Level1.dropChance");
            int maxDropped = config.getInt("Items.HeatedMagmaCream.Level1.maxDropped");
            for (int i = 0; i < maxDropped; i++) {

                if (Methods.randomNumber(0, 100) < chance) {
                    event.getDrops().add(HeatedMagmaCream.makeHeatedMagmaCream(1));
                }
            }

            chance = config.getInt("Items.HeatedMagmaCream.Level2.dropChance");
            maxDropped = config.getInt("Items.HeatedMagmaCream.Level2.maxDropped");

            for (int i = 0; i < maxDropped; i++) {

                if (Methods.randomNumber(0, 100) < chance) {
                    event.getDrops().add(HeatedMagmaCream.makeHeatedMagmaCream(2));
                }
            }

            chance = config.getInt("Items.HeatedMagmaCream.Level3.dropChance");
            maxDropped = config.getInt("Items.HeatedMagmaCream.Level3.maxDropped");

            for (int i = 0; i < maxDropped; i++) {

                if (Methods.randomNumber(0, 100) < chance) {
                    event.getDrops().add(HeatedMagmaCream.makeHeatedMagmaCream(3));
                }
            }

            event.setDroppedExp(Math.max(config.getInt("Bosses.Magma_cubeBoss.droppedXP"), 0));

            event.getDrops().addAll(Methods.addItemsFromConfig(config.getStringList("Bosses.Magma_cubeBoss.droppedItems")));

            if (event.getEntity().getKiller() != null && config.getBoolean("Bosses.enableBossKilledMessage") && event.getEntity().getCustomName() != null) {
                event.getEntity().getServer().broadcastMessage(ChatColor.DARK_RED + event.getEntity().getCustomName() + ChatColor.WHITE + " has been slain by " + ChatColor.AQUA + event.getEntity().getKiller().getName());
            }
        }

        event.getEntity().setCustomName("");



//Respawning of bosses if set to in the config


        if (event.getEntity().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER) &&
                event.getEntity().getPersistentDataContainer().get(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER) != -1
        ) {

            BarColor color = BarColor.BLUE;

            if (RespawningBosses.bossBarHashMap.containsKey(event.getEntity().getUniqueId()) && RespawningBosses.bossBarHashMap.get(event.getEntity().getUniqueId()) != null) {

                color = RespawningBosses.bossBarHashMap.get(event.getEntity().getUniqueId()).getColor();

                RespawningBosses.bossBarHashMap.get(event.getEntity().getUniqueId()).removeAll();

                RespawningBosses.bossBarHashMap.remove(event.getEntity().getUniqueId());
            }


            RespawningBosses.respawningBosses.get(event.getEntityType()).remove(event.getEntity().getUniqueId());

            PersistentDataContainer container = event.getEntity().getPersistentDataContainer();

            Integer time = container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER);
            Double x = container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE);
            Double y = container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE);
            Double z = container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE);
            World w;
            if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING)) {
                w = Main.getInstance().getServer().getWorld(Objects.requireNonNull(container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING)));
            } else {
                return;
            }

            Location loc = event.getEntity().getWorld().getBlockAt(0, 0, 0).getLocation();
            loc.add(new Vector(x, y, z));

            BarColor finalColor = color;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                if(!event.getEntity().isDead() || event.getEntity().getHealth() <= 0) return;

                LivingEntity livingEntity = RespawningBosses.bossSpawnMethod(event.getEntityType(), loc, w);

                PersistentDataContainer container1 = livingEntity.getPersistentDataContainer();

                if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING) != null) {
                    container1.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnWorld"), PersistentDataType.STRING));
                }

                if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING) != null) {
                    container1.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING));
                }

                if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER) != null) {
                    container1.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER));
                }

                if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER) != null) {
                    container1.set(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesRespawnTime"), PersistentDataType.INTEGER));
                }

                if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE) != null) {
                    container1.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationX"), PersistentDataType.DOUBLE));
                }

                if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE) != null) {
                    container1.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationY"), PersistentDataType.DOUBLE));
                }

                if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE) && container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE) != null) {
                    container1.set(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE, container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesSpawnLocationZ"), PersistentDataType.DOUBLE));
                }


                livingEntity.getScoreboardTags().add("removeOnDisable");
                livingEntity.setRemoveWhenFarAway(false);



                RespawningBosses.respawningBosses.get(event.getEntityType()).add(livingEntity.getUniqueId());

                if(event.getEntityType() != EntityType.WITHER) {

                    BossBar bossBar = Bukkit.createBossBar(
                            Objects.requireNonNull(livingEntity).getCustomName(),
                            finalColor,
                            BarStyle.SOLID);

                    bossBar.setProgress(1);

                    RespawningBosses.bossBarHashMap.put(livingEntity.getUniqueId(), bossBar);
                }
            }, 20L * time + 1);
        }


        if (event.getEntity().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING)) {

            if (event.getEntity().getKiller() == null) return;

            NamespacedKey key = new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath");

            PersistentDataContainer container = event.getEntity().getPersistentDataContainer();
            String command = container.get(key, PersistentDataType.STRING);

            Integer time = 0;
            if (container.has(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER)) {
                time = container.get(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeathTimer"), PersistentDataType.INTEGER);
            }

            if (command != null && command.contains("<killer>")) {

                command = command.replace("<killer>", event.getEntity().getKiller().getName());

            } else if (command != null && command.contains("<") && command.contains(">")) {

                String[] strings = command.split("<");
                String first = strings[0];
                String[] strings1 = strings[1].split(">");
                String third = "";
                third = strings1[1];
                int radius = Integer.parseInt(strings1[0]);

                ArrayList<Entity> entityList = (ArrayList<Entity>) event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), radius, radius, radius, n -> (n.getType() == EntityType.PLAYER));
                ArrayList<Player> players = new ArrayList<>();
                for (Entity e : entityList) {
                    players.add((Player) e);
                }

                for (Player p : players) {

                    StringBuilder stringBuilder = new StringBuilder();
                    strings1[1] = p.getName();
                    stringBuilder.append(first);
                    stringBuilder.append(strings1[1]);
                    stringBuilder.append(third);
                    String cmd = stringBuilder.toString();


                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Main.getInstance().getServer().getConsoleSender(), cmd), 20L * time + 1);

                }
                return;
            }

            if (command != null) {
                String finalCommand = command;
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Main.getInstance().getServer().getConsoleSender(), finalCommand), 20L * time + 1);
            }
        }
    }
}
