package shreb.me.vanillabosses.listeners;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import shreb.me.vanillabosses.bossclasses.Bosses;
import shreb.me.vanillabosses.main.Main;

public class BossAttackDamageChanger implements Listener {

    Configuration config = Main.getInstance().getConfig();

    @EventHandler
    public void onBossDamageEntity(EntityDamageByEntityEvent event) {
        Entity e = event.getDamager();

        switch (e.getType()) {

            case ARROW:
                if(e.getScoreboardTags().contains("applyModifier")){
                    event.setDamage(event.getDamage() * Bosses.SKELETON.damageModifier);
                }

            case SKELETON:

                if(e.getScoreboardTags().contains("BossSkeleton")){
                    event.setDamage(event.getDamage() * Bosses.SKELETON.damageModifier);
                }
                break;

            case CREEPER:
                if(e.getScoreboardTags().contains("BossCreeper")){
                    event.setDamage(event.getDamage() * Bosses.CREEPER.damageModifier);
                }
                break;

            case SPIDER:
                if(e.getScoreboardTags().contains("BossSpider")){
                    event.setDamage(event.getDamage() * Bosses.SPIDER.damageModifier);
                }
                break;

            case BLAZE:
                if(e.getScoreboardTags().contains("BossBlaze")){
                    event.setDamage(event.getDamage() * Bosses.BLAZE.damageModifier);
                }
                break;

            case ENDERMAN:
                if(e.getScoreboardTags().contains("BossEnderman")){
                    event.setDamage(event.getDamage() * Bosses.ENDERMAN.damageModifier);
                }
                break;

            case ZOMBIE:
                if(e.getScoreboardTags().contains("BossZombie")){
                    event.setDamage(event.getDamage() * Bosses.ZOMBIE.damageModifier);
                }
                break;

            case ZOMBIFIED_PIGLIN:
                if(e.getScoreboardTags().contains("BossZombified_Piglin")){
                    event.setDamage(event.getDamage() * Bosses.ZOMBIFIED_PIGLIN.damageModifier);
                }
                break;

            case SLIME:
                if(e.getScoreboardTags().contains("BossSlime")){
                    event.setDamage(event.getDamage() * Bosses.SLIME.damageModifier);
                }
                break;

            case WITHER:
                if(e.getScoreboardTags().contains("BossWither")){
                    event.setDamage(event.getDamage() * Bosses.WITHER.damageModifier);
                }
                break;
        }
    }
}
