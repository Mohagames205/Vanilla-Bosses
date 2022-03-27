package shreb.me.vanillabosses.main.Helpers;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import shreb.me.vanillabosses.listeners.EntitySpawnEvent;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class BossDamageTrackerHelper {

    HashMap<UUID, Double> damagerHashmap = new HashMap<>();

    /**
     * @return the UUID of the player which has the most damage on the boss this helper is set to
     */
    public UUID getHighestDamageUUID() {

        ArrayList<UUID> playerList = new ArrayList<>(damagerHashmap.keySet());
        ArrayList<Double> damageList = new ArrayList<>(damagerHashmap.values());

        return playerList.get(damageList.indexOf(Collections.max(damageList)));
    }

    public Player getHighestDamagePlayer() {

        ArrayList<UUID> playerList = new ArrayList<>(damagerHashmap.keySet());
        ArrayList<Double> damageList = new ArrayList<>(damagerHashmap.values());

        return Main.getInstance().getServer().getPlayer(playerList.get(damageList.indexOf(Collections.max(damageList))));
    }

    public HashMap<UUID, Double> getDamagerHashmap() {
        return damagerHashmap;
    }

    /**
     * Used to update the Damage Hashmap assigned to each boss inside the updateDamageTracker.
     * If the involved boss has not yet been entered into the hashmap this method will do that aswell.
     * This is not the method to be used for an update of the DamageTracker. Use BossDamageTracker.updateDamageTracker()
     * <p>
     * Inside this Method the following checks are carried out:
     * - is the Damager a player? if not, cancel method.
     * - does the damaged Entity contain the ScoreboardTag for Bosses of this plugin? if not, cancel method.
     *
     * @param event event to be used to set the involved players damage on a boss
     */
    public void setPlayerDamage(EntityDamageByEntityEvent event) throws IllegalArgumentException {

        UUID playerUUID = event.getDamager().getUniqueId();

        if (event.getDamager() instanceof AbstractArrow
                && ((AbstractArrow) event.getDamager()).getShooter() instanceof Player
                && ((AbstractArrow) event.getDamager()).getShooter() != null) {

            playerUUID = ((Player) ((AbstractArrow) event.getDamager()).getShooter()).getUniqueId();

        } else if ((!(event.getDamager() instanceof Player) || !(event.getEntity().getScoreboardTags().contains(EntitySpawnEvent.damageTrackerSBTag))))
            throw new IllegalArgumentException(); // Checks


        double damageDone = event.getFinalDamage();

        double oldDamage = 0;
        if (damagerHashmap.containsKey(playerUUID)) oldDamage = damagerHashmap.get(playerUUID);
        damagerHashmap.put(playerUUID, oldDamage + damageDone);

    }
}

