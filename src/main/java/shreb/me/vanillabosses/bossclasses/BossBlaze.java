package shreb.me.vanillabosses.bossclasses;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class BossBlaze implements Listener {

    public static HashMap<Integer, UUID> bossBlazeTargetMap = new HashMap<>();

    @EventHandler
    public void onBossTarget(EntityTargetLivingEntityEvent event) {
        if (event.getEntityType() != EntityType.BLAZE || !(event.getEntity().getScoreboardTags().contains("BossBlaze")))
            return;
        if (!(event.getTarget() instanceof Player)) return;

        bossBlazeTargetMap.put(event.getEntity().getEntityId(), event.getTarget().getUniqueId());

        Location loc = event.getTarget().getLocation();
        loc.subtract(Methods.randomNumber(-3,2), 0, Methods.randomNumber(-3,2));
        event.getTarget().teleport(loc);
    }


    @EventHandler
    public void onBossShoot(ProjectileLaunchEvent event) {

        if (!(event.getEntity().getShooter() instanceof Entity)) return;

        Vector v = null;
        Entity projectile = event.getEntity();

        if (bossBlazeTargetMap.containsKey(((Entity) event.getEntity().getShooter()).getEntityId())) {
            v = Objects.requireNonNull(Main.getInstance().getServer().getPlayer(bossBlazeTargetMap.get(((Entity) event.getEntity().getShooter()).getEntityId()))).getLocation().subtract(
                    event.getLocation()).toVector();
            v.divide(new Vector(8, 15, 8));
        }

        Entity e = (Entity) event.getEntity().getShooter();
        if (e.getScoreboardTags().contains("BossBlaze") && e.getType().equals(EntityType.BLAZE)) {

            World w = event.getEntity().getWorld();
            int rn = Methods.randomNumber(0, 100);

            int currentChance = Main.getInstance().getConfig().getInt("Bosses.BlazeBoss.blazeShootEventsChances.witherSkull");;
            if (rn < currentChance) {
                WitherSkull entity = (WitherSkull) w.spawnEntity(event.getEntity().getLocation(), EntityType.WITHER_SKULL);

                if (v != null) {
                    entity.setVelocity(v);
                } else {
                    entity.setVelocity(projectile.getVelocity());
                }
                event.getEntity().remove();

                Vector finalV = v;
                for(int i=0; i<6; i++){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                        if (finalV != null) {
                            entity.setVelocity(finalV);
                        } else {
                            entity.setVelocity(projectile.getVelocity());
                        }
                    }, 15 * i);
                }

                return;
            }
            currentChance += Main.getInstance().getConfig().getInt("Bosses.BlazeBoss.blazeShootEventsChances.enderDragonFireBall");
            if (rn < currentChance) {
                DragonFireball entity = (DragonFireball) w.spawnEntity(event.getEntity().getLocation(), EntityType.DRAGON_FIREBALL);

                if (v != null) {
                    entity.setVelocity(v);
                } else {
                    entity.setVelocity(projectile.getVelocity());
                }
                event.getEntity().remove();

                Vector finalV = v;
                for(int i=0; i<6; i++){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                        if (finalV != null) {
                            entity.setVelocity(finalV);
                        } else {
                            entity.setVelocity(projectile.getVelocity());
                        }
                    }, 15 * i);
                }

                return;
            }
            currentChance += Main.getInstance().getConfig().getInt("Bosses.BlazeBoss.blazeShootEventsChances.largeFireBall");
            if (rn < currentChance) {
                Fireball entity = (Fireball) w.spawnEntity(event.getEntity().getLocation(), EntityType.FIREBALL);

                if (v != null) {
                    entity.setVelocity(v);
                } else {
                    entity.setVelocity(projectile.getVelocity());
                }
                event.getEntity().remove();

                Vector finalV = v;

                for(int i=0; i<6; i++){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                        if (finalV != null) {
                            entity.setVelocity(finalV);
                        } else {
                            entity.setVelocity(projectile.getVelocity());
                        }
                    }, 15 * i);
                }

            }
        }
    }

    /**
     * spawns the boss as a new Entity
     *
     * @param location specifies the location in which the boss will spawn
     */

    public static Blaze makeBossBlaze(Location location, World w) {

        Blaze blaze = (Blaze) w.spawnEntity(location, EntityType.BLAZE);
        blaze.getScoreboardTags().add("BossBlaze");
        blaze.getScoreboardTags().add("VanillaBossesDamageTracker");
        blaze.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.BLAZE.health);
        blaze.setHealth(Bosses.BLAZE.health);
        blaze.setCustomName(ChatColor.of(Bosses.BLAZE.nameColor) + Bosses.BLAZE.displayName);
        blaze.setCustomNameVisible(Main.getInstance().getConfig().getBoolean("Bosses.BlazeBoss.showDisplayNameAlways"));

        if(Main.getInstance().getConfig().getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            blaze.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

        return blaze;
    }

    /**
     * edits an existing mob into the boss version.
     *
     * @param blaze the entity which to edit into the boss version.
     */

    public static void editToBossBlaze(Blaze blaze) {

        blaze.getScoreboardTags().add("BossBlaze");
        blaze.getScoreboardTags().add("VanillaBossesDamageTracker");
        blaze.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.BLAZE.health);
        blaze.setHealth(Bosses.BLAZE.health);
        blaze.setCustomName(ChatColor.of(Bosses.BLAZE.nameColor) + Bosses.BLAZE.displayName);
        blaze.setCustomNameVisible(Main.getInstance().getConfig().getBoolean("Bosses.BlazeBoss.showDisplayNameAlways"));

        if(Main.getInstance().getConfig().getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            blaze.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }
    }
}
