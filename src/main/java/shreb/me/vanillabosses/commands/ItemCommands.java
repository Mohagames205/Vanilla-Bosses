package shreb.me.vanillabosses.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import shreb.me.vanillabosses.bossclasses.BossWither;
import shreb.me.vanillabosses.items.*;
import shreb.me.vanillabosses.main.Main;

public class ItemCommands implements CommandExecutor {

    static ItemStack skeletor = Skeletor.makeSkeletor();
    static ItemStack cloak = InvisibilityCloak.makeCloak();
    static ItemStack butchersAxe = ButchersAxe.makeButchersAxe();
    static ItemStack slingshot = Slingshot.makeSlingshot();
    static ItemStack baseballBat = BaseballBat.makeBaseballBat();
    static ItemStack witherEgg = BossWither.makeWitherEgg();
    static ItemStack slimeboots = SlimeBoots.makeSlimeBoots();
    static ItemStack bouncyslime = SlimeBoots.makeBouncySlime();
    static ItemStack blazer = Blazer.makeBlazer();

    static Configuration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && args.length < 2) {
            sender.sendMessage("You are not a Player. Specify A player to give the item to.");
            return true;
        }

        Player p;

        if (args.length >= 2 && Main.getInstance().getServer().getOnlinePlayers().contains(Main.getInstance().getServer().getPlayer(args[1]))) {
            p = Main.getInstance().getServer().getPlayer(args[1]);
        } else if (!(sender instanceof Player)) {
            sender.sendMessage("Vanilla Bosses: In case youre typing this command into the console, you will have to specify an online player");
            return true;
        } else {
            try {
                p = (Player) sender;
            } catch (ClassCastException e) {
                sender.sendMessage(Main.getCurrentLanguage().errorMessage);
                return true;
            }
        }


        if (args.length < 1) return false;

        if (args[0].equalsIgnoreCase("skeletor")) {
            if (!config.getBoolean("Items.Skeletor.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(skeletor);
                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemSkeletorGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);

            }

            return true;
        }

        if (args[0].equalsIgnoreCase("slingshot")) {
            if (!config.getBoolean("Items.slingshot.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(slingshot);
                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemSlingshotGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);

            }
            return true;
        }

        if (args[0].equalsIgnoreCase("cloak")) {
            if (!config.getBoolean("Items.cloakOfInvisibility.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(cloak);
                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemInvisibilityCloakNameGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);

            }
            return true;
        }

        if (args[0].equalsIgnoreCase("butchersaxe")) {
            if (!config.getBoolean("Items.ButchersAxe.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(butchersAxe);
                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemButchersAxeNameGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);

            }
            return true;
        }

        if (args[0].equalsIgnoreCase("baseballbat")) {
            if (!config.getBoolean("Items.BaseballBat.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(baseballBat);
                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemBaseballBatGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);

            }
            return true;
        }

        if (args[0].equalsIgnoreCase("witheregg")) {
            if (!config.getBoolean("Items.WitherEgg.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(witherEgg);
                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemWitherEggGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);

            }
            return true;
        }

        if (args[0].equalsIgnoreCase("slimeboots")) {
            if (!config.getBoolean("Items.SlimeBoots.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(slimeboots);
                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemSlimeBootsGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);

            }
            return true;
        }

        if (args[0].equalsIgnoreCase("bouncyslime")) {
            if (!config.getBoolean("Items.SlimeBoots.BouncySlime.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                int amount;

                if (args.length == 1) {
                    amount = 1;
                } else {
                    try {
                        amount = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badAmount + " Expected: 1-64");
                        return true;
                    }
                }

                if (amount > 64) {
                    sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badAmount + " Expected: 1-64");
                    return true;
                }

                for (int i = 0; i < amount; i++) {
                    p.getInventory().addItem(bouncyslime);
                }

                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemBouncySlimeGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);

            }
            return true;
        }

        if (args[0].equalsIgnoreCase("blazer")) {
            if (!config.getBoolean("Items.Blazer.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(blazer);
                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemBlazerNameGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("heatedMagmaCream")) {
            if (!config.getBoolean("Items.HeatedMagmaCream.enableSpawnCommand")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().commandDisabled);
                return true;
            }

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                int amount;
                ItemStack cream;

                if (args.length < 3) {
                    amount = 1;
                } else {
                    try {
                        amount = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badAmount + " Expected: 1-64");
                        return true;
                    }
                }

                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().notEnoughArguments);
                    return true;
                }

                if (amount > 64) {
                    sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badAmount + " Expected: 1-64");
                    return true;
                }

                try {

                    cream = HeatedMagmaCream.makeHeatedMagmaCream(Integer.parseInt(args[1]));

                } catch (IllegalArgumentException e) {
                    sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badAmount + " Expected: 1-3");
                    return true;
                }

                for (int i = 0; i < amount; i++) p.getInventory().addItem(cream);

                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemHMCNameGivenMessage);
            } else {

                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);

            }
            return true;
        }

        if (args[0].equalsIgnoreCase("egg")) {

            if (!sender.hasPermission("VB.SummonItems")) {
                sender.sendMessage(ChatColor.RED + Main.getCurrentLanguage().badPermissions);
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage(Main.getCurrentLanguage().notEnoughArguments);
                return true;
            }


            int amount;
            EntityType type;

            try {
                if (args.length > 2) {
                    amount = Integer.parseInt(args[2]);
                } else {
                    amount = 1;
                }
                type = EntityType.valueOf(args[1].toUpperCase());
            } catch (NumberFormatException e) {
                sender.sendMessage(Main.getCurrentLanguage().badAmount + " Expected: 1-64");
                return true;
            } catch (IllegalArgumentException e) {
                sender.sendMessage("Expected a boss type for the second argument");
                return true;
            }

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(BossEggs.makeBossEgg(type, amount));
                p.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().itemBossEggGivenMessage);
            } else {

                p.sendMessage(ChatColor.RED + Main.getCurrentLanguage().inventoryFull);
            }
            return true;

        }



        if (args[0].equalsIgnoreCase("list")) {

            sender.sendMessage(ChatColor.AQUA + "Available Items:");
            sender.sendMessage(ChatColor.GOLD + " -Skeletor");
            sender.sendMessage(ChatColor.GOLD + " -Slingshot");
            sender.sendMessage(ChatColor.GOLD + " -Cloak");
            sender.sendMessage(ChatColor.GOLD + " -ButchersAxe");
            sender.sendMessage(ChatColor.GOLD + " -Baseballbat");
            sender.sendMessage(ChatColor.GOLD + " -Witheregg");
            sender.sendMessage(ChatColor.GOLD + " -Slimeboots");
            sender.sendMessage(ChatColor.GOLD + " -bouncyslime <amount>");
            sender.sendMessage(ChatColor.GOLD + " -Blazer");
            sender.sendMessage(ChatColor.GOLD + " -HeatedMagmaCream <level> <amount>");
            sender.sendMessage(ChatColor.GOLD + " -egg <bosstype> <amount>");

            return true;
        }

        return false;
    }
}
