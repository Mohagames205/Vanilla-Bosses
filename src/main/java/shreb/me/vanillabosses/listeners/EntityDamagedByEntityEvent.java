package shreb.me.vanillabosses.listeners;

import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class EntityDamagedByEntityEvent implements Listener {

    static int witchAbilityCooldown;
    static boolean isJumping = false;


    @EventHandler
    public void onBossIsHit(EntityDamageByEntityEvent event) {

        Configuration config = Main.getInstance().getConfig();

        if (event.getDamager() instanceof Player) {

            if (!Methods.prevloc.containsKey(event.getDamager().getUniqueId())) {
                Methods.prevloc.put(event.getDamager().getUniqueId(), event.getDamager().getLocation());
            }
            if (!Methods.counter.containsKey(event.getDamager().getUniqueId())) {
                Methods.counter.put(event.getDamager().getUniqueId(), 0);
            }

            Methods.afkCheck(event);
        }



//BossSkeleton

        if (event.getEntity().getScoreboardTags().contains("BossSkeleton") && event.getEntityType() == EntityType.SKELETON) {

            if (!(event.getDamager() instanceof Player)) return;

            Entity entity = event.getEntity();

            int rn = Methods.randomNumber(0, 100);

            int chanceReflectDamage = config.getInt("Bosses.SkeletonBoss.onHitEvents.reflectDamage.chance");
            int chanceSpawnMinions = config.getInt("Bosses.SkeletonBoss.onHitEvents.spawnMinions.chance");
            int chanceInvulnerability = config.getInt("Bosses.SkeletonBoss.onHitEvents.invulnerability.chance");

            int currentChance = chanceReflectDamage;

            if (rn <= currentChance) {

                event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.BLOCK_ANVIL_FALL, 1F, 2F);

                double damage = event.getDamage();

                damage *= config.getDouble("Bosses.SkeletonBoss.onHitEvents.reflectDamage.damageMultiplier");

                ((Player) event.getDamager()).damage(damage);

                event.setCancelled(true);

                return;
            }

            currentChance += chanceInvulnerability;

            if (rn <= currentChance) {

                if (!(entity.isInvulnerable())) {

                    int seconds = config.getInt("Bosses.SkeletonBoss.onHitEvents.invulnerability.durationInSeconds");

                    entity.setInvulnerable(true);
                    entity.getWorld().spawnParticle(Particle.SMALL_FLAME, entity.getLocation(), 25);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                        entity.setInvulnerable(false);
                        entity.getWorld().spawnParticle(Particle.HEART, entity.getLocation(), 25);
                        ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10 * 20, 5));

                    }, seconds * 20L);
                }
                return;
            }

            currentChance += chanceSpawnMinions;

            if (rn <= currentChance) {

                World w = entity.getWorld();

                w.playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1F, 1F);

                int x = entity.getLocation().getBlockX();
                int y = entity.getLocation().getBlockY();
                int z = entity.getLocation().getBlockZ();

                ArrayList<Entity> minions = new ArrayList<>();

                if (config.getBoolean("Bosses.SkeletonBoss.onHitEvents.spawnMinions.abilityRemovesBlocks")) {

                    Location[] blocks = {
                            w.getBlockAt(x, y, z).getLocation(),
                            w.getBlockAt(x, y + 1, z).getLocation(),
                            w.getBlockAt(x - 1, y, z).getLocation(),
                            w.getBlockAt(x - 1, y + 1, z).getLocation(),
                            w.getBlockAt(x + 1, y, z).getLocation(),
                            w.getBlockAt(x + 1, y + 1, z).getLocation(),
                            w.getBlockAt(x, y, z - 1).getLocation(),
                            w.getBlockAt(x, y + 1, z - 1).getLocation(),
                            w.getBlockAt(x, y, z + 1).getLocation(),
                            w.getBlockAt(x, y + 1, z + 1).getLocation(),
                            w.getBlockAt(x, y + 2, z).getLocation()
                    };

                    for (Location loc : blocks
                    ) {

                        if (!(loc.getBlock().getType().equals(Material.OBSIDIAN)) && !(loc.getBlock().getType().equals(Material.BEDROCK)) && !(loc.getBlock().getType().equals(Material.BEACON))) {

                            loc.getBlock().setType(Material.AIR);

                        }

                    }
                }

                Entity tempEnt = w.spawnEntity(w.getBlockAt(x + 1, y, z).getLocation(), EntityType.SKELETON);
                minions.add(tempEnt);
                tempEnt = w.spawnEntity(w.getBlockAt(x - 1, y, z).getLocation(), EntityType.SKELETON);
                minions.add(tempEnt);
                tempEnt = w.spawnEntity(w.getBlockAt(x, y, z + 1).getLocation(), EntityType.SKELETON);
                minions.add(tempEnt);
                tempEnt = w.spawnEntity(w.getBlockAt(x, y, z - 1).getLocation(), EntityType.SKELETON);
                minions.add(tempEnt);

                for (Entity e : minions
                ) {
                    ((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30 * 20, 1));
                    Objects.requireNonNull(((LivingEntity) e).getEquipment()).setHelmet(new ItemStack(Material.IRON_HELMET));

                    if (((LivingEntity) e).getEquipment() == null) return;

                    e.removeScoreboardTag("BossSkeleton");
                    e.setCustomName("The Kings Minion");
                    e.setCustomNameVisible(false);
                    ((LivingEntity) e).getEquipment().setBoots(null);
                    ((LivingEntity) e).getEquipment().setLeggings(null);
                    ((LivingEntity) e).getEquipment().setChestplate(null);
                    ((LivingEntity) e).getEquipment().setItemInOffHand(null);
                    ((LivingEntity) e).getEquipment().setItemInMainHand(new ItemStack(Material.BOW));

                }
            }
        }
//Spider
        if (event.getEntity().getScoreboardTags().contains("BossSpider") && event.getEntityType() == EntityType.SPIDER) {

            if (!(event.getDamager() instanceof Player)) return;

            Spider spider = (Spider) event.getEntity();

            Location originalLoc = spider.getLocation();
            Location tempLoc = event.getDamager().getLocation();

            int chanceInvisibility;
            int chanceLeap;

            int rn = Methods.randomNumber(0, 100);

            chanceInvisibility = config.getInt("Bosses.SpiderBoss.onHitEvents.invisibility.chance");
            chanceLeap = config.getInt("Bosses.SpiderBoss.onHitEvents.leap.chance");

            int currentChance = 0;

            currentChance += chanceInvisibility;

            if (rn <= currentChance) {

                ArrayList<PotionEffect> effects = (ArrayList<PotionEffect>) spider.getActivePotionEffects();

                if (config.getBoolean("Bosses.SpiderBoss.onHitEvents.invisibility.teleportToPlayer")) {

                    if (spider.getScoreboardTags().contains("isInvis")) return;
                    spider.addScoreboardTag("isInvis");

                    ArrayList<PotionEffect> tempEffects = new ArrayList<>();
                    tempEffects.add(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 5));
                    tempEffects.add(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3));
                    spider.addPotionEffects(tempEffects);

                    spider.teleport(tempLoc);

                    if (config.getBoolean("Bosses.SpiderBoss.onHitEvents.invisibility.teleportBack")) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> spider.teleport(originalLoc), 20L * config.getInt("Bosses.SpiderBoss.onHitEvents.invisibility.duration"));
                    }
                }

                spider.setInvisible(true);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                    spider.setInvisible(false);
                    spider.getScoreboardTags().remove("isInvis");

                    for (PotionEffect p : spider.getActivePotionEffects()
                    ) {
                        spider.removePotionEffect(p.getType());
                    }
                    spider.addPotionEffects(effects);


                }, 20L * config.getInt("Bosses.SpiderBoss.onHitEvents.invisibility.duration"));

                return;
            }

            currentChance += chanceLeap;

            if (rn <= currentChance) {

                if (spider.getScoreboardTags().contains("preparingToJump")) return;

                spider.addScoreboardTag("preparingToJump");

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                    int playerX = event.getDamager().getLocation().getBlockX();
                    int playerY = event.getDamager().getLocation().getBlockY();
                    int playerZ = event.getDamager().getLocation().getBlockZ();

                    int spiderX = spider.getLocation().getBlockX();
                    int spiderY = spider.getLocation().getBlockY();
                    int spiderZ = spider.getLocation().getBlockZ();

                    Vector v = new Vector((playerX - spiderX) / 2, (playerY - spiderY) / 2, (playerZ - spiderZ) / 2);

                    spider.setVelocity(v);

                    spider.removeScoreboardTag("preparingToJump");

                }, 20L * Methods.randomNumber(1, config.getInt("Bosses.SpiderBoss.onHitEvents.leap.maxDelayAfterHit")));

                return;
            }
        }
//Wither
        if (event.getEntity().getScoreboardTags().contains("BossWither")) {

            if (!(event.getEntityType().equals(EntityType.WITHER))) return;

            if (event.getDamager().getType().equals(EntityType.SPECTRAL_ARROW)) {
                event.setDamage(event.getDamage() * config.getDouble("Bosses.WitherBoss.onHitEvents.spectralArrowDamageMultiplier"));

                if (Material.getMaterial(Objects.requireNonNull(config.getString("Bosses.WitherBoss.onHitEvents.dropItemOnHitBySpectralArrow"))) != null && Material.getMaterial(Objects.requireNonNull(config.getString("Bosses.WitherBoss.onHitEvents.dropItemOnHitBySpectralArrow"))) != Material.AIR) {
                    event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Objects.requireNonNull(Material.getMaterial(config.getString("Bosses.WitherBoss.onHitEvents.dropItemOnHitBySpectralArrow").toUpperCase())), 1));
                }
            }
        }
//Blaze
        if (event.getEntity().getScoreboardTags().contains("BossBlaze") && event.getEntityType() == EntityType.BLAZE) {

            if (event.getDamager().getType().equals(EntityType.SPECTRAL_ARROW)) {
                event.setDamage(event.getDamage() * config.getDouble("Bosses.BlazeBoss.onHitEvents.spectralArrowDamageMultiplier"));

                if (Material.getMaterial(Objects.requireNonNull(config.getString("Bosses.BlazeBoss.onHitEvents.dropItemOnHitBySpectralArrow"))) != null && Material.getMaterial(Objects.requireNonNull(config.getString("Bosses.BlazeBoss.onHitEvents.dropItemOnHitBySpectralArrow"))) != Material.AIR) {
                    event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Objects.requireNonNull(Material.getMaterial(config.getString("Bosses.BlazeBoss.onHitEvents.dropItemOnHitBySpectralArrow").toUpperCase())), 1));
                }
            }
        }
//Enderman
        if (event.getEntity().getScoreboardTags().contains("BossEnderman") && event.getEntityType() == EntityType.ENDERMAN) {

            if (!(event.getDamager() instanceof Player)) return;

            Enderman enderman = (Enderman) event.getEntity();

            for (String s : config.getStringList("Bosses.EndermanBoss.onHitEvents.potionEffects")) {
                String[] strings = s.split(":");
                PotionEffectType type = PotionEffectType.getByName(strings[0].toUpperCase());

                if (type != null && Methods.randomNumber(0, 100) < Integer.parseInt(strings[3])) {
                    enderman.addPotionEffect(new PotionEffect(type, Integer.parseInt(strings[2]) * 20, Integer.parseInt(strings[1])));
                }

            }

            if (Methods.randomNumber(0, 100) < config.getInt("Bosses.EndermanBoss.onHitEvents.teleportBehindPlayer.chance") && config.getBoolean("Bosses.EndermanBoss.onHitEvents.teleportBehindPlayer.enabled")) {
                int x = event.getDamager().getLocation().getBlockX() - enderman.getLocation().getBlockX();
                int y = event.getDamager().getLocation().getBlockY() - enderman.getLocation().getBlockY();
                int z = event.getDamager().getLocation().getBlockZ() - enderman.getLocation().getBlockZ();
                Vector v = new Vector(x / 4, y / 3, z / 4);

                Location newLoc = event.getDamager().getLocation();
                Location originalLoc = enderman.getLocation();

                EnderPearl ep = (EnderPearl) enderman.getWorld().spawnEntity(enderman.getEyeLocation(), EntityType.ENDER_PEARL);
                ep.setVelocity(v);

                enderman.teleport(newLoc);

                if (config.getBoolean("Bosses.EndermanBoss.onHitEvents.teleportBehindPlayer.teleportBackToOldLocation")) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                        enderman.teleport(originalLoc);
                    }, 20L * config.getInt("Bosses.EndermanBoss.onHitEvents.teleportBehindPlayer.teleportBackDelay"));

                }

                if (config.getBoolean("Bosses.EndermanBoss.onHitEvents.teleportBehindPlayer.invisibility")) {
                    enderman.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, config.getInt("Bosses.EndermanBoss.onHitEvents.teleportBehindPlayer.invisibilityDuration") * 20, 3));
                }
            }
        }

//BossWitch

        if (event.getEntity().getScoreboardTags().contains("BossWitch") && event.getEntityType().equals(EntityType.WITCH) && event.getDamager() instanceof Player) {

            if (witchAbilityCooldown > 0) {
                witchAbilityCooldown--;
                return;
            }

            if (config.getBoolean("Bosses.WitchBoss.onHitEvents.PlayersSwitchPlaces.enabled") && Methods.randomNumber(0, 100) < config.getInt("Bosses.WitchBoss.onHitEvents.PlayersSwitchPlaces.chance")) {

                witchAbilityCooldown = config.getInt("Bosses.WitchBoss.onHitEvents.PlayersSwitchPlaces.cooldown");

                ArrayList<Entity> list1 = (ArrayList<Entity>) event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 30, 5, 30);

                list1.removeIf(n -> (!n.getType().equals(EntityType.PLAYER)));

                ArrayList<Player> playerList = new ArrayList<>();
                for (Entity p : list1) {
                    playerList.add((Player) p);
                }

                Location loc1;
                Location loc2;

                if (config.getBoolean("Bosses.WitchBoss.onHitEvents.PlayersSwitchPlaces.canSwitchWithOtherEntities")) {

                    ArrayList<Entity> list2 = (ArrayList<Entity>) event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 30, 5, 30);

                    list2.removeIf(n -> !(n instanceof LivingEntity));
                    list2.removeIf(n -> (n.getType().equals(EntityType.WITCH))); //removing Witches from the list
                    list2.removeIf(n -> (n.getType().equals(EntityType.CREEPER))); //removing Witches from the list (to be nice!)

                    Collections.shuffle(list2);  //shuffling the list to get more random results

                    ArrayList<LivingEntity> list3 = new ArrayList<>();

                    for (Entity entity : list2) {
                        list3.add((LivingEntity) entity);
                    }

                    event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 1F, 1F);

                    for (int i = 0; i < list3.size(); i += 2) {

                        if (list3.size() - i == 1) return;

                        loc1 = list3.get(i).getLocation();
                        loc2 = list3.get(i + 1).getLocation();

                        list3.get(i).teleport(loc2);
                        list3.get(i + 1).teleport(loc1);

                        list3.get(i).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 7 * 20, 0));
                        list3.get(i + 1).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 7 * 20, 0));

                    }
                } else {

                    for (int i = 0; i < playerList.size(); i += 2) {

                        if (playerList.size() - i == 1) return;

                        loc1 = playerList.get(i).getLocation();
                        loc2 = playerList.get(i + 1).getLocation();

                        playerList.get(i).teleport(loc2);
                        playerList.get(i + 1).teleport(loc1);

                        playerList.get(i).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 7 * 20, 0));
                        playerList.get(i + 1).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 7 * 20, 0));

                    }
                }
            }
        }
//Slime

        if (event.getEntity().getScoreboardTags().contains("BossSlime") && event.getEntityType() == EntityType.SLIME) {

            if (!(event.getDamager() instanceof Player)) return;
            int jumpSlamChance = Main.getInstance().getConfig().getInt("Bosses.SlimeBoss.onHitEvents.JumpSlam.chance");

            if (Methods.randomNumber(0, 100) < jumpSlamChance) {

                if (event.getFinalDamage() > ((LivingEntity) event.getEntity()).getHealth()) return;

                if (isJumping) return;
                isJumping = true;
                Slime slime = (Slime) event.getEntity();

                ArrayList<Entity> list = (ArrayList<Entity>) event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 15, 10, 15, n -> (n instanceof Player));

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                    slime.setVelocity(new Vector(0, 2, 0));
                }, 1);

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                    slime.setVelocity(new Vector(0, -5, 0));
                    slime.getScoreboardTags().add("NoFallDMG");


                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                        for (Entity e : list) {
                            if (e.getWorld().getBlockAt(e.getLocation().subtract(0, 0.1, 0)).getType() != Material.AIR
                                    && e.getWorld().getBlockAt(e.getLocation().subtract(0, 0.1, 0)).getType() != Material.LAVA
                                    && e.getWorld().getBlockAt(e.getLocation().subtract(0, 0.1, 0)).getType() != Material.GRASS
                                    && e.getWorld().getBlockAt(e.getLocation().subtract(0, 0.1, 0)).getType() != Material.TALL_GRASS
                                    && !e.isInWater()
                            ) {
                                int x = e.getLocation().getBlockX() - slime.getLocation().getBlockX();
                                int z = e.getLocation().getBlockZ() - slime.getLocation().getBlockZ();
                                Vector v = new Vector(x, 2, z);
                                e.setVelocity(v);
                            }
                        }

                        event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                        isJumping = false;

                    }, 8);

                }, 20);
            }
        }
//Zombie
        if (event.getEntity().getScoreboardTags().contains("BossZombie") && event.getEntityType() == EntityType.ZOMBIE) {

            if (!(event.getDamager() instanceof Player)) return;
        }
//Zombie Pigman
        if (event.getEntity().getScoreboardTags().contains("BossZombified_Piglin") && event.getEntityType() == EntityType.ZOMBIFIED_PIGLIN) {

            if (!(event.getDamager() instanceof Player)) return;
        }
//Creeper
        if (event.getEntity().getScoreboardTags().contains("BossCreeper") && event.getEntityType() == EntityType.CREEPER) {

            if (!(event.getDamager() instanceof Player)) return;
        }

    }



    @EventHandler
    public void onItemBlownUp(EntityDamageByEntityEvent event) {

        if (event.getDamager().getScoreboardTags().contains("dontBlowUpItems") && event.getEntity().getType() == EntityType.DROPPED_ITEM) {
            event.setCancelled(true);
        }
    }
}
