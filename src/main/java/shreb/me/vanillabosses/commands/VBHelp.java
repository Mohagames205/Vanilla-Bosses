package shreb.me.vanillabosses.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shreb.me.vanillabosses.main.Main;



public class VBHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        if (args.length == 0) {
            sender.sendMessage(ChatColor.AQUA + "Possible commands:");
            sender.sendMessage(ChatColor.GOLD + " - /vbh discord (this will send a link to the plugin discord)");
            sender.sendMessage(ChatColor.GOLD + " - /vbh  (shows this help message)");
            sender.sendMessage(ChatColor.GOLD + " - /vbh <EntityType>  (gives you information about the boss of the specified type. type '/boss list' to see all the available bosses!)");
            sender.sendMessage(ChatColor.GOLD + " - /vbh info  (gives you information about the plugin)");
            if (sender.isOp()) sender.sendMessage(ChatColor.AQUA + "Only for OP Players:");
            if (sender.isOp()) sender.sendMessage(ChatColor.GOLD + " - /vbh rlvb (reloads the plugin configuration. This will not work for every aspect. /reload or restart to be sure to get everything.)");
            return true;
        }


        if (args[0].equalsIgnoreCase("skeleton")) {
            sender.sendMessage(ChatColor.GREEN + "The Skeleton King was once the regent of a prosperous nation");
            sender.sendMessage(ChatColor.GREEN + "He died alongside his army and nation in a final stand in front of his castles walls");
            sender.sendMessage(ChatColor.GREEN + "With his trusty Skeletor bow made from Deerbones he does a lot of damage and summons the power of TNT upon his enemies");
            sender.sendMessage(ChatColor.GREEN + "When hit he can reflect the damage, become invulnerable for a certain amount of time or summon a part of his once great army to serve him once more");
            sender.sendMessage(ChatColor.GREEN + "When he came back to the world of the living he was able to take a small amount of treasure with him which he may drop on being killed");
            sender.sendMessage(ChatColor.GREEN + "Since his bow is in a rather bad condition it may not be salvaged upon death");
            sender.sendMessage(ChatColor.GREEN + "Drops the Skeletor");
            return true;
        }

        if (args[0].equalsIgnoreCase("creeper")) {
            sender.sendMessage(ChatColor.GREEN + "Bomby is a Creeper with great anger management");
            sender.sendMessage(ChatColor.GREEN + "He still gets really angry when he is lit on fire by anything tho.");
            sender.sendMessage(ChatColor.GREEN + "He lets out his anger in the form of 8 primed blocks of TNT which fly in all directions when he is about to explode");
            sender.sendMessage(ChatColor.GREEN + "Bomby does a lot of damage when he gets angry but can also be defeated by making him angry in quick succession");
            return true;
        }

        if (args[0].equalsIgnoreCase("spider")) {
            sender.sendMessage(ChatColor.GREEN + "Dolores is a Spider.");
            sender.sendMessage(ChatColor.GREEN + "If you hit her, she can become invisible and gain buffs in addition to teleporting behind you");
            sender.sendMessage(ChatColor.GREEN + "additionally she has a powerful jump she can use to get close to you");
            sender.sendMessage(ChatColor.GREEN + "Drops the Slingshot");
            return true;
        }

        if (args[0].equalsIgnoreCase("wither")) {
            sender.sendMessage(ChatColor.GREEN + "The Boss wither is a rather unfortunate Experiment");
            sender.sendMessage(ChatColor.GREEN + "Trying to make the antichrist has not ended well for anyone. ever.");
            sender.sendMessage(ChatColor.GREEN + "But here you are. Combining the 2 things that remind people the most about the nether.");
            sender.sendMessage(ChatColor.GREEN + "It is spawned by building a standard Wither directly on top of a Netherite Block. The Netherite Block will disappear if not changed in the config");
            sender.sendMessage(ChatColor.GREEN + "On death he (or she?) will drop a wither egg.");
            sender.sendMessage(ChatColor.GREEN + "To hatch it, you have to follow the instructions written in black underneath the name of the egg");
            return true;
        }

        if (args[0].equalsIgnoreCase("blaze")) {
            sender.sendMessage(ChatColor.GREEN + "A normal blaze which has been studying the art of not caring what it has for projectiles.");
            sender.sendMessage(ChatColor.GREEN + "This blaze has more health than a normal one, but can be cleansed by spectral arrows, doing more damage than usual.");
            sender.sendMessage(ChatColor.GREEN + "Upon being hit with a spectral arrow the blaze may drop an item (if not disabled in the config)");
            return true;
        }

        if (args[0].equalsIgnoreCase("enderman")) {
            sender.sendMessage(ChatColor.GREEN + "An enderman with a slight drinking problem. ");
            sender.sendMessage(ChatColor.GREEN + "If you hit this enderman it may just become even more angry than a normal enderman would be.");
            sender.sendMessage(ChatColor.GREEN + "Whenever hit, there is a chance that the enderman gains configurable potion effects.");
            sender.sendMessage(ChatColor.GREEN + "When this enderman teleports, it will spawn endermites (if not disabled in the config)");
            sender.sendMessage(ChatColor.GREEN + "Drops the Cloak of Invisibility");
            return true;
        }

        if (args[0].equalsIgnoreCase("witch")) {
            sender.sendMessage(ChatColor.GREEN + "This Witch is an overachiever.");
            sender.sendMessage(ChatColor.GREEN + "She has figured out how to make potions which are much better than those they teach at the academy.");
            sender.sendMessage(ChatColor.GREEN + "Experimenting with ingredients she has also made some forgotten potions, but sadly she will not tell us what ingredients she used...");
            return true;
        }

        if (args[0].equalsIgnoreCase("zombie")) {
            sender.sendMessage(ChatColor.GREEN + "Formerly known as 'Bob' in some parts, this Zombie always has his gang around to help out.");
            sender.sendMessage(ChatColor.GREEN + "With Bob wearing fully enchanted Iron armor and having a good amount of HP the gang doesn't have much to do tho.");
            sender.sendMessage(ChatColor.GREEN + "Drops the Baseball Bat");
            return true;
        }

        if (args[0].equalsIgnoreCase("Zombified_Piglin")) {
            sender.sendMessage(ChatColor.GREEN + "One of the few Pigmen who were able to mine gold and enchant their armor!");
            sender.sendMessage(ChatColor.GREEN + "The Butchers axe this Pigman is holding has a chance to cripple anyone unfortunate enough to be hit by it.");
            sender.sendMessage(ChatColor.GREEN + "Drops the Butchers Axe");
            return true;
        }

        if (args[0].equalsIgnoreCase("slime")) {
            sender.sendMessage(ChatColor.GREEN + "The Slimiest and bounciest out there!");
            sender.sendMessage(ChatColor.GREEN + "When hit it can redirect the knockback downwards to boost itself into the air, then crashing into the ground at a high speed.");
            sender.sendMessage(ChatColor.GREEN + "Any Player touching the ground at the moment the slime crashes into the ground will be flung away!");
            sender.sendMessage(ChatColor.GREEN + "Drops the Slime Boots and Bouncy Slime");
            return true;
        }

        if (args[0].equalsIgnoreCase("magmacube")) {
            sender.sendMessage(ChatColor.GREEN + "Always wants to cuddle people!");
            sender.sendMessage(ChatColor.GREEN + "Can get really angry when hit and set everything around it on fire.");
            sender.sendMessage(ChatColor.GREEN + "Doesn't want to be the bad guy but cuddling people when youre as hot as lava hurts slightly");
            sender.sendMessage(ChatColor.GREEN + "Has a Chance of dropping each of the 3 levels of Heated Magma Cream.");
            return true;
        }


        if (args[0].equalsIgnoreCase("info")) {
            sender.sendMessage(ChatColor.AQUA + "The Vanilla Bosses plugin was made by Shreb (On Spigot)");
            sender.sendMessage(ChatColor.AQUA + "Please report any attempts of copying my content to me via Spigot or discord");
            sender.sendMessage(ChatColor.AQUA + "If you're enjoying my plugin, please do leave a rating and tell your friends :D");
            sender.sendMessage(ChatColor.AQUA + "In Case you have any ideas about new bosses or items and would like to see them in the plugin, please shoot me a message.");
            sender.sendMessage(ChatColor.AQUA + "Have fun playing and stay healthy :)");
            return true;
        }

        if(args[0].equalsIgnoreCase("discord")){
            sender.sendMessage("https://discord.gg/stAd5ccDZT");
        }

        //reload config file
        if (args[0].equalsIgnoreCase("rlvb")) {

            if (!sender.isOp()) {
                sender.sendMessage("You do not have the necessary permissions to run this command");
                return true;
            }

            Main.getInstance().reloadConfig();
            sender.sendMessage("The configuration has been reloaded");
            return true;
        }

        return false;
    }
}

