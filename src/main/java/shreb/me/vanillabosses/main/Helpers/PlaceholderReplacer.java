package shreb.me.vanillabosses.main.Helpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import shreb.me.vanillabosses.bossclasses.Bosses;
import shreb.me.vanillabosses.main.BossDamagedTracker;
import shreb.me.vanillabosses.main.Main;

public class PlaceholderReplacer {

    public static String replaceInDeathEvent(EntityDeathEvent event, String toReplaceIn) {

        if(toReplaceIn == null || toReplaceIn.equals("") && event.getEntity().getKiller()!= null) event.getEntity().getKiller().sendMessage(Main.getCurrentLanguage().errorMessage);

        String returnString = toReplaceIn;

        if (event.getEntity().getKiller() != null && event.getEntity().getKiller() != null) {
            returnString = toReplaceIn.replace("<killer>", event.getEntity().getKiller().getDisplayName());
        }
        returnString = returnString.replace("<killedName>", net.md_5.bungee.api.ChatColor.of(Bosses.valueOf(event.getEntityType().toString().toUpperCase()).nameColor) + Bosses.valueOf(event.getEntityType().toString().toUpperCase()).displayName + ChatColor.WHITE);

        returnString = returnString.replace("<mostDamage>", BossDamagedTracker.getDamageTrackerHelperByUUID(event.getEntity().getUniqueId()).getHighestDamagePlayer().getName());

        return returnString;

    }
}
