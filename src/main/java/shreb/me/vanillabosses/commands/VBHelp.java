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
            sender.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().vbh0);
            return true;
        }

        if (args[0].equalsIgnoreCase("skeleton")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhSkeleton);
            return true;
        }

        if (args[0].equalsIgnoreCase("creeper")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhCreeper);
            return true;
        }

        if (args[0].equalsIgnoreCase("spider")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhSpider);
            return true;
        }

        if (args[0].equalsIgnoreCase("wither")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhWither);
            return true;
        }

        if (args[0].equalsIgnoreCase("blaze")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhBlaze);
            return true;
        }

        if (args[0].equalsIgnoreCase("enderman")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhEnderman);
            return true;
        }

        if (args[0].equalsIgnoreCase("witch")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhWitch);
            return true;
        }

        if (args[0].equalsIgnoreCase("zombie")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhZombie);
            return true;
        }

        if (args[0].equalsIgnoreCase("Zombified_Piglin")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhPiglin);

            return true;
        }

        if (args[0].equalsIgnoreCase("slime")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhSlime);
            return true;
        }

        if (args[0].equalsIgnoreCase("magmacube")) {
            sender.sendMessage(ChatColor.GREEN + Main.getCurrentLanguage().vbhMagmaCube);
            return true;
        }


        if (args[0].equalsIgnoreCase("info")) {
            sender.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().vbhInfo);

            return true;
        }

        if(args[0].equalsIgnoreCase("discord")){
            sender.sendMessage("https://discord.gg/stAd5ccDZT");
        }

        //reload config file
        if (args[0].equalsIgnoreCase("rlvb")) {

            if (!sender.isOp()) {
                sender.sendMessage(Main.getCurrentLanguage().badPermissions);
                return true;
            }

            Main.getInstance().reloadConfig();
            sender.sendMessage("The configuration has been reloaded");
            return true;
        }

        return false;
    }
}

