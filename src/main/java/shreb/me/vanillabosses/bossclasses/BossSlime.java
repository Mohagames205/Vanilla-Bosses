package shreb.me.vanillabosses.bossclasses;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shreb.me.vanillabosses.main.Main;

public class BossSlime implements Listener {

    static Configuration config = Main.getInstance().getConfig();
    static String displayName = config.getString("Bosses.SlimeBoss.displayName");
    static boolean nameVisible = config.getBoolean("Bosses.SlimeBoss.showDisplayNameAlways");

    /**
     * spawns the boss as a new Entity
     * @param location specifies the location in which the boss will spawn
     */

    public static Slime makeBossSlime(Location location, World w){

        Slime slime = (Slime) w.spawnEntity(location, EntityType.SLIME);
        slime.addScoreboardTag("BossSlime");
        slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.SLIME.health);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> slime.setHealth(Bosses.SLIME.health), 5);

        slime.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 3));
        slime.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
        slime.setCustomName(displayName);
        slime.setCustomNameVisible(nameVisible);
        slime.setSize(5);
        return slime;
    }

    /**
     * edits an existing mob into the boss version.
     * @param slime the entity which to edit into the boss version.
     */

    public static void editToBossSlime(Slime slime){

        slime.addScoreboardTag("BossSlime");
        slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.SLIME.health);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> slime.setHealth(Bosses.SLIME.health), 5);

        slime.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 3));
        slime.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
        slime.setCustomName(displayName);
        slime.setCustomNameVisible(nameVisible);
        slime.setSize(5);
    }

    @EventHandler
    public void onSlimeBossFallDMG(EntityDamageEvent event){

        if(event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        if(event.getEntity().getScoreboardTags().contains("NoFallDMG") && event.getEntity().getScoreboardTags().contains("BossSlime")){
            event.setCancelled(true);
            event.getEntity().getScoreboardTags().removeIf(n -> n.equals("NoFallDMG"));
        }

    }

}
