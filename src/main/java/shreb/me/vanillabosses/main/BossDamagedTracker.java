package shreb.me.vanillabosses.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import shreb.me.vanillabosses.listeners.EntitySpawnEvent;
import shreb.me.vanillabosses.main.Helpers.BossDamageTrackerHelper;

import java.util.*;

public class BossDamagedTracker implements Listener {

    public static HashMap<UUID, BossDamageTrackerHelper> bossDamageTracker = new HashMap<>();

    /**
     * This method is the one to be used for an update of the DamageTracker.
     *
     * @param event event the boss was damaged in.
     */
    public void updateDamageTracker(EntityDamageByEntityEvent event) {

        BossDamageTrackerHelper helper = bossDamageTracker.get(event.getEntity().getUniqueId());
        if(helper == null) return;

        try {
            helper.setPlayerDamage(event);
        } catch (IllegalArgumentException e) {
            return;
        }

        bossDamageTracker.put(event.getEntity().getUniqueId(), helper);
    }

    public static void replaceBossUUID(UUID oldID, UUID newID) {
        bossDamageTracker.put(newID, bossDamageTracker.get(oldID));
        bossDamageTracker.remove(oldID);
    }

    /**
     * @param uuid the UUID of the boss to get the TrackerHelper of
     * @return the BossDamageTrackerHelper related to the UUID passed in
     */
    public static BossDamageTrackerHelper getDamageTrackerHelperByUUID(UUID uuid) {
        if(bossDamageTracker.get(uuid) == null) {
            bossDamageTracker.put(uuid, new BossDamageTrackerHelper());
        }
        return bossDamageTracker.get(uuid);
    }

    @EventHandler
    public void onBossDamaged(EntityDamageByEntityEvent event) {

        if (!event.getEntity().getScoreboardTags().contains(EntitySpawnEvent.damageTrackerSBTag)) return;

        if (!bossDamageTracker.containsKey(event.getEntity().getUniqueId())) {
            bossDamageTracker.put(event.getEntity().getUniqueId(), new BossDamageTrackerHelper());
        }

        updateDamageTracker(event);

    }

    @EventHandler
    public void onBossDeath(EntityDeathEvent event) {
        ArrayList<UUID> uuidList = new ArrayList<>(bossDamageTracker.keySet());
        for (UUID id : uuidList) {
            if (Main.getInstance().getServer().getEntity(id) == null || Main.getInstance().getServer().getEntity(id).isDead()) {
                bossDamageTracker.remove(id);
            }
        }
    }

}
