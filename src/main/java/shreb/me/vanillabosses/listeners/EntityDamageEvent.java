package shreb.me.vanillabosses.listeners;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import shreb.me.vanillabosses.bossclasses.BossWither;
import shreb.me.vanillabosses.bossclasses.RespawningBosses;

import java.util.Objects;

public class EntityDamageEvent implements Listener {

    @EventHandler
    public void onRespawningBossDamage(org.bukkit.event.entity.EntityDamageEvent event) {

        if (!event.getEntityType().isAlive()) return;

        LivingEntity entity = (LivingEntity) event.getEntity();

        if (entity.getScoreboardTags().contains("BossCreeper")) {

            if (event.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || event.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                event.setCancelled(true);
            }
        }

        if (RespawningBosses.bossBarHashMap.containsKey(entity.getUniqueId()) && RespawningBosses.bossBarHashMap.get(entity.getUniqueId()) != null) {
            RespawningBosses.bossBarHashMap.get(entity.getUniqueId()).setProgress(entity.getHealth() / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        }
    }

    @EventHandler
    public void onPassiveWitherDamaged(org.bukkit.event.entity.EntityDamageEvent event){

        if(event.getEntityType() == EntityType.WITHER){
            Wither w = (Wither) event.getEntity();
            if(BossWither.passiveWitherList.contains(w.getUniqueId())){
                w.getBossBar().setProgress(w.getHealth() / Objects.requireNonNull(w.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());
            }
        }
    }
}
