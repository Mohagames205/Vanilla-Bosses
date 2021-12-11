package shreb.me.vanillabosses.bossclasses;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shreb.me.vanillabosses.main.Main;

public class BossSpider implements Listener {

    static Configuration config = Main.getInstance().getConfig();

    /**
     * spawns the boss as a new Entity
     * @param location specifies the location in which the boss will spawn
     */

    public static Spider makeBossSpider(Location location, World w){

        Spider spider = (Spider) w.spawnEntity(location, EntityType.SPIDER);
        spider.addScoreboardTag("BossSpider");
        spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(config.getDouble("Bosses.SpiderBoss.health"));
        spider.setHealth(spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        spider.addScoreboardTag("removeInvisibilityOnDisable");
        spider.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 3));
        spider.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
        spider.setCustomName(config.getString("Bosses.SpiderBoss.displayName"));
        spider.setCustomNameVisible(config.getBoolean("Bosses.SpiderBoss.showDisplayNameAlways"));
        return spider;
    }

    /**
     * edits an existing mob into the boss version.
     * @param spider the entity which to edit into the boss version.
     */

    public static void editToBossSpider(Spider spider){

        spider.addScoreboardTag("BossSpider");
        spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(config.getDouble("Bosses.SpiderBoss.health"));
        spider.setHealth(spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        spider.addScoreboardTag("removeInvisibilityOnDisable");
        spider.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 3));
        spider.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
        spider.setCustomName(config.getString("Bosses.SpiderBoss.displayName"));
        spider.setCustomNameVisible(config.getBoolean("Bosses.SpiderBoss.showDisplayNameAlways"));
    }

}
