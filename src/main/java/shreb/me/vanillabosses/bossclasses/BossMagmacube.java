package shreb.me.vanillabosses.bossclasses;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

import java.util.ArrayList;

public class BossMagmacube implements Listener {

    private static final Configuration config = Main.getInstance().getConfig();

    public static MagmaCube makeBossMagmacube(Location location, World w){

        MagmaCube magma = (MagmaCube) w.spawnEntity(location, EntityType.MAGMA_CUBE);
        magma.getScoreboardTags().add("BossMagmacube");
        magma.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.MAGMACUBE.health);
        magma.setHealth(Bosses.MAGMACUBE.health);
        magma.setCustomName(config.getString("Bosses.MagmacubeBoss.displayName"));
        magma.setCustomNameVisible(config.getBoolean("Bosses.MagmacubeBoss.showDisplayNameAlways"));

        return magma;
    }

    public static void editToBossMagmacube(MagmaCube magma) {

        magma.getScoreboardTags().add("BossMagmacube");
        magma.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.MAGMACUBE.health);
        magma.setHealth(Bosses.MAGMACUBE.health);
        magma.setCustomName(config.getString("Bosses.MagmacubeBoss.displayName"));
        magma.setCustomNameVisible(config.getBoolean("Bosses.MagmacubeBoss.showDisplayNameAlways"));

    }

    //Boss Ability: summon particles around the boss and set anyone inside the particles on fire
    //              happens with a certain chance whenever the boss hits a player after a little time.
    //              lasts for a small amount of time, between the triggering hit and the ability trigger the boss will not move
    //              and a sound will play informing the players of the ability being triggered.

    @EventHandler
    public void onPlayerHitByBossMagma(EntityDamageByEntityEvent event){

        if(!(event.getEntity() instanceof Player))                                                  return;
        if(!event.getDamager().getScoreboardTags().contains(Bosses.MAGMACUBE.scoreboardBossTag))    return;
        if(event.getDamager().getType() != EntityType.MAGMA_CUBE)                                   return;

        Player player   = (Player) event.getEntity();
        MagmaCube magma = (MagmaCube) event.getDamager();
        Location magmaLoc = magma.getLocation();
        int radius = 5;

        if(magma.getHealth() <= 0)                                                                  return;

        if(config.getBoolean("Bosses.MagmacubeBoss.onHitEvents.BurningAir.enabled")
                && Methods.randomNumber(0,100) < config.getInt("Bosses.MagmacubeBoss.onHitEvents.BurningAir.chance")){

            Methods.spawnParticles(Particle.FIREWORKS_SPARK, magma.getWorld(), magmaLoc, radius, radius, radius,150,3);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () ->{

                magma.getWorld().spawnParticle(Particle.FLAME, magmaLoc, 100, radius, radius, radius);

                for(Entity e : magmaLoc.getWorld().getNearbyEntities(magmaLoc, radius, radius, radius, n -> n instanceof LivingEntity)){
                    e.setFireTicks(60);
                }

            }, 60L);

        }
    }
}
