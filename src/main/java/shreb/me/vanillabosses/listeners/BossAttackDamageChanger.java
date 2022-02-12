package shreb.me.vanillabosses.listeners;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import shreb.me.vanillabosses.main.Main;

public class BossAttackDamageChanger implements Listener {

    Configuration config = Main.getInstance().getConfig();

    @EventHandler
    public void onBossDamageEntity(EntityDamageByEntityEvent event) {
        Entity e = event.getDamager();

        switch (e.getType()) {

            case ARROW:
                if(e.getScoreboardTags().contains("applyModifier")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.SkeletonBoss.DamageModifier"));
                }

            case SKELETON:

                if(e.getScoreboardTags().contains("BossSkeleton")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.SkeletonBoss.DamageModifier"));
                }
                break;

            case CREEPER:
                if(e.getScoreboardTags().contains("BossCreeper")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.CreeperBoss.DamageModifier"));
                }
                break;

            case SPIDER:
                if(e.getScoreboardTags().contains("BossSpider")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.SpiderBoss.DamageModifier"));
                }
                break;

            case BLAZE:
                if(e.getScoreboardTags().contains("BossBlaze")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.BlazeBoss.DamageModifier"));
                }
                break;

            case ENDERMAN:
                if(e.getScoreboardTags().contains("BossEnderman")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.EndermanBoss.DamageModifier"));
                }
                break;

            case ZOMBIE:
                if(e.getScoreboardTags().contains("BossZombie")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.ZombieBoss.DamageModifier"));
                }
                break;

            case ZOMBIFIED_PIGLIN:
                if(e.getScoreboardTags().contains("BossZombified_Piglin")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.Zombified_PiglinBoss.DamageModifier"));
                }
                break;

            case SLIME:
                if(e.getScoreboardTags().contains("BossSlime")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.SlimeBoss.DamageModifier"));
                }
                break;

            case WITHER:
                if(e.getScoreboardTags().contains("BossWither")){
                    event.setDamage(event.getDamage() * config.getDouble("Bosses.WitherBoss.DamageModifier"));
                }
                break;
        }
    }
}
