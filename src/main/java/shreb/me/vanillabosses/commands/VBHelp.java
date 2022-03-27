package shreb.me.vanillabosses.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.gui.ConfigGUI;
import shreb.me.vanillabosses.items.HeatedMagmaCream;
import shreb.me.vanillabosses.items.Items;
import shreb.me.vanillabosses.items.SlimeBoots;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;


public class VBHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        if (args.length == 0) {
            sender.sendMessage(ChatColor.AQUA + Main.getCurrentLanguage().vbh0);
            return true;
        }

        if (args[0].equalsIgnoreCase("inv")) {
            ConfigGUI.openInventory((HumanEntity) sender);
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

        if(args[0].equalsIgnoreCase("replaceItems")){

                ArrayList<ItemStack> replaceableItems = (ArrayList<ItemStack>) Arrays.stream(((Player) sender).getInventory().getContents())
                        .filter(Objects::nonNull)
                        .filter(n -> n.getType() != Material.AIR)
                        //filtering out Air because of itemMeta nullpointers
                        .filter(n -> n.getItemMeta().getPersistentDataContainer().has(Items.HEATEDMAGMACREAM1.identifyingPDCKey, PersistentDataType.INTEGER)
                                || n.getItemMeta().getPersistentDataContainer().has(Items.HEATEDMAGMACREAM2.identifyingPDCKey, PersistentDataType.INTEGER)
                                || n.getItemMeta().getPersistentDataContainer().has(Items.HEATEDMAGMACREAM3.identifyingPDCKey, PersistentDataType.INTEGER)
                                || n.getItemMeta().getPersistentDataContainer().has(Items.BOUNCYSLIME.identifyingPDCKey, PersistentDataType.STRING))
                        //filtering for all replacable items!)
                        .collect(Collectors.toList());

                if(replaceableItems.isEmpty()) {
                    sender.sendMessage("You do not have any items to replace!");
                    return true;
                }

                for(ItemStack stack : replaceableItems){
                    switch(stack.getType()){

                        case SLIME_BALL:
                            ((Player) sender).getInventory().remove(stack);
                            ((Player) sender).getInventory().addItem(SlimeBoots.replaceBouncySlime(stack));
                            break;

                        case MAGMA_CREAM:
                            ((Player) sender).getInventory().remove(stack);
                            ((Player) sender).getInventory().addItem(HeatedMagmaCream.replaceHMC(stack));
                            break;

                        default:
                            sender.sendMessage("Weird Error 102");

                    }
                }
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

