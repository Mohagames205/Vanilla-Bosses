package shreb.me.vanillabosses.bossclasses;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

import java.util.ArrayList;

public class BossMagmacube implements Listener {

    private static final Configuration config = Main.getInstance().getConfig();

    public static MagmaCube makeBossMagmacube(Location location, World w) {

        MagmaCube magma = (MagmaCube) w.spawnEntity(location, EntityType.MAGMA_CUBE);
        magma.getScoreboardTags().add(Bosses.MAGMA_CUBE.scoreboardBossTag);
        magma.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.MAGMA_CUBE.health);
        magma.setHealth(Bosses.MAGMA_CUBE.health);
        magma.setCustomName(ChatColor.valueOf(Bosses.MAGMA_CUBE.nameColor) + Bosses.MAGMA_CUBE.displayName);
        magma.setCustomNameVisible(config.getBoolean("Bosses.Magma_cubeBoss.showDisplayNameAlways"));
        if (config.getBoolean("Bosses.bossesGetGlowingPotionEffect")) {
            magma.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

        return magma;
    }

    public static void editToBossMagmacube(MagmaCube magma) {

        magma.getScoreboardTags().add(Bosses.MAGMA_CUBE.scoreboardBossTag);
        magma.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.MAGMA_CUBE.health);
        magma.setHealth(Bosses.MAGMA_CUBE.health);
        magma.setCustomName(Bosses.MAGMA_CUBE.nameColor + Bosses.MAGMA_CUBE.displayName);
        magma.setCustomNameVisible(config.getBoolean("Bosses.Magma_cubeBoss.showDisplayNameAlways"));
        if (config.getBoolean("Bosses.bossesGetGlowingPotionEffect")) {
            magma.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

    }

    //Boss Ability: summon particles around the boss and set anyone inside the particles on fire
    //              happens with a certain chance whenever the boss hits a player after a little time.
    //              lasts for a small amount of time, between the triggering hit and the ability trigger the boss will not move
    //              and a sound will play informing the players of the ability being triggered.

    @EventHandler
    public void onPlayerHitByBossMagma(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;
        if (!event.getEntity().getScoreboardTags().contains(Bosses.MAGMA_CUBE.scoreboardBossTag)) return;
        if (event.getEntity().getType() != EntityType.MAGMA_CUBE) return;

        Player player = (Player) event.getDamager();
        MagmaCube magma = (MagmaCube) event.getEntity();
        Location magmaLoc = magma.getLocation();
        int radius = config.getInt("Bosses.Magma_cubeBoss.onHitEvents.BurningAir.range");
        int time = config.getInt("Bosses.Magma_cubeBoss.onHitEvents.BurningAir.time");

        if (magma.getHealth() <= 0) return;

        if (config.getBoolean("Bosses.Magma_cubeBoss.onHitEvents.BurningAir.enabled")
                && Methods.randomNumber(0, 100) < config.getInt("Bosses.Magma_cubeBoss.onHitEvents.BurningAir.chance")
                && magma.getHealth() > 0) {


            Methods.spawnParticles(Particle.FIREWORKS_SPARK, magma.getWorld(), magmaLoc, radius, radius, radius, 150, 3);
            player.getWorld().playSound(magmaLoc, Sound.ENTITY_SLIME_SQUISH, 1.0f, 1.0f);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                if (magma.getHealth() < 0) return;

                magma.getWorld().spawnParticle(Particle.FLAME, magma.getLocation(), 100, radius, radius, radius);

                for (Entity e : magma.getLocation().getWorld().getNearbyEntities(magma.getLocation(), radius, radius, radius, n -> n instanceof LivingEntity)) {
                    e.setFireTicks(20 * time);
                }

            }, 60L);
        }
    }
}
