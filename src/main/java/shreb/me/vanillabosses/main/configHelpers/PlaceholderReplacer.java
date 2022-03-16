package shreb.me.vanillabosses.main.configHelpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import shreb.me.vanillabosses.bossclasses.Bosses;

public class PlaceholderReplacer {

    public static String replaceInDeathEvent(EntityDeathEvent event, String toReplaceIn) {

        String returnString = toReplaceIn;

        String entityType;

        if (event.getEntity().getKiller() != null) {
            returnString = toReplaceIn.replace("<killer>", event.getEntity().getKiller().getDisplayName());
        }
        returnString = returnString.replace("<killedName>", ChatColor.valueOf(Bosses.valueOf(event.getEntityType().toString().toUpperCase()).nameColor) + Bosses.valueOf(event.getEntityType().toString().toUpperCase()).displayName + ChatColor.WHITE);


        return returnString;

    }

}
