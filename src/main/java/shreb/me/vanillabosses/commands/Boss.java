package shreb.me.vanillabosses.commands;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.bossclasses.*;
import shreb.me.vanillabosses.main.Main;

import java.util.Objects;

public class Boss implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Configuration config = Main.getInstance().getConfig();

        if (args.length < 1) return false;

        Location specLoc = null;
        World world = null;

        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
            world = p.getWorld();
        }


        if (args[0].equalsIgnoreCase("list")) {
            sender.sendMessage(ChatColor.AQUA + "####");
            sender.sendMessage(ChatColor.GOLD + " -Blaze");
            sender.sendMessage(ChatColor.GOLD + " -Creeper");
            sender.sendMessage(ChatColor.GOLD + " -Enderman");
            sender.sendMessage(ChatColor.GOLD + " -Skeleton");
            sender.sendMessage(ChatColor.GOLD + " -Spider");
            sender.sendMessage(ChatColor.GOLD + " -Witch");
            sender.sendMessage(ChatColor.GOLD + " -Wither");
            sender.sendMessage(ChatColor.GOLD + " -Zombie");
            sender.sendMessage(ChatColor.GOLD + " -Zombified_Piglin");
            sender.sendMessage(ChatColor.GOLD + " -Slime");
            sender.sendMessage(ChatColor.GOLD + " -Magmacube");
            sender.sendMessage(ChatColor.AQUA + "####");

            return true;
        }

        if(!sender.hasPermission("VB.SummonBosses")){
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            return true;
        }

        if (args.length == 5) {
            try {

                world = Main.getInstance().getServer().getWorld(args[1]);

                if (world == null) {
                    Main.getInstance().getServer().getConsoleSender().sendMessage("Vanilla Bosses: The world specified in the issued Boss command doesn't seem to exist");
                    return true;
                }

                specLoc = new Location(world, Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));

            } catch (NumberFormatException e) {
                Main.getInstance().getServer().getConsoleSender().sendMessage("Vanilla Bosses: The Issued Boss Command seems to have Incorrect values for coordinates");
                return true;
            }
        }

        if (world == null) {
            sender.sendMessage("Vanilla Bosses: Error. check the command for mistakes. If you cannot find any, message the author of the plugin.");
            Main.getInstance().getServer().getConsoleSender().sendMessage("Vanilla Bosses: The Boss command did not receive a valid world input. If you believe this to be in error, please contact the author of the plugin.");
            return true;
        }

        if (args[0].equalsIgnoreCase("skeleton")) {

            if (!(config.getBoolean("Bosses.SkeletonBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.SkeletonBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }

            Location loc;

            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossSkeleton.makeBossSkeleton(loc, world);

            sender.sendMessage(ChatColor.AQUA + "The Boss Skeleton has been summoned successfully");

            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.SkeletonBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }

            return true;

        }

//BossSpider

        if (args[0].equalsIgnoreCase("spider")) {

            if (!(config.getBoolean("Bosses.SpiderBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.SpiderBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }
            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossSpider.makeBossSpider(loc, world);
            sender.sendMessage(ChatColor.AQUA + "The Boss Spider has been summoned successfully");
            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.SpiderBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }

            return true;
        }

//BossCreeper

        if (args[0].equalsIgnoreCase("creeper")) {

            if (!(config.getBoolean("Bosses.CreeperBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.CreeperBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }
            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossCreeper.makeBossCreeper(loc, world);
            sender.sendMessage(ChatColor.AQUA + "The Boss Creeper has been summoned successfully");

            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.CreeperBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }

            return true;
        }

//BossWither

        if (args[0].equalsIgnoreCase("wither")) {

            if (!(config.getBoolean("Bosses.WitherBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.WitherBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }

            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Wither wither = (Wither) world.spawnEntity(loc, EntityType.WITHER);
            sender.sendMessage(ChatColor.AQUA + "The Boss Wither has been summoned successfully");
            wither.addScoreboardTag("BossWither");

            wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.WITHER.health);
            wither.setHealth(Bosses.WITHER.health);


            wither.setCustomName(ChatColor.valueOf(Bosses.WITHER.nameColor) + Bosses.WITHER.displayName);
            wither.setCustomNameVisible(config.getBoolean("Bosses.WitherBoss.showDisplayNameAlways"));

            PersistentDataContainer container = wither.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.WitherBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }

            return true;
        }

//BossBlaze

        if (args[0].equalsIgnoreCase("blaze")) {

            if (!(config.getBoolean("Bosses.BlazeBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.BlazeBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }

            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossBlaze.makeBossBlaze(loc, world);
            sender.sendMessage(ChatColor.AQUA + "The Boss Blaze has been summoned successfully");
            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.BlazeBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }

            return true;
        }

//EndermanBoss

        if (args[0].equalsIgnoreCase("enderman")) {

            if (!(config.getBoolean("Bosses.EndermanBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.EndermanBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }

            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossEnderman.makeBossEnderman(loc, world);
            sender.sendMessage(ChatColor.AQUA + "The Boss Enderman has been summoned successfully");
            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.EndermanBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }

            return true;

        }

//ZombieBoss

        if (args[0].equalsIgnoreCase("zombie")) {

            if (!(config.getBoolean("Bosses.ZombieBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.ZombieBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }

            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossZombie.makeBossZombie(loc, world);
            sender.sendMessage(ChatColor.AQUA + "The Boss Zombie has been summoned successfully");
            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.ZombieBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }

            return true;

        }

//Zombified_PiglinBoss

        if (args[0].equalsIgnoreCase("Zombified_Piglin")) {

            if (!(config.getBoolean("Bosses.Zombified_PiglinBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.Zombified_PiglinBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }

            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossZombified_Piglin.makeBossZombified_Piglin(loc, world);
            sender.sendMessage(ChatColor.AQUA + "The Boss Zombiepigman has been summoned successfully");
            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.Zopmbified_PiglinBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }

            return true;
        }

//WitchBoss

        if (args[0].equalsIgnoreCase("witch")) {

            if (!(config.getBoolean("Bosses.WitchBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.WitchBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }

            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossWitch.makeBossWitch(loc, world);
            sender.sendMessage(ChatColor.AQUA + "The Boss Witch has been summoned successfully");
            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.WitchBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }

            return true;

        }

//BossSlime

        if (args[0].equalsIgnoreCase("slime")) {

            if (!(config.getBoolean("Bosses.SlimeBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.SlimeBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }

            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossSlime.makeBossSlime(loc, world);
            sender.sendMessage(ChatColor.AQUA + "The Boss Slime has been summoned successfully");
            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.SlimeBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }
            return true;

        }

//BossMagmacube

        if (args[0].equalsIgnoreCase("magmacube")) {

            if (!(config.getBoolean("Bosses.Magma_cubeBoss.enableSummonCommand"))) {
                sender.sendMessage("This command is disabled in the config");
                return true;
            }

            if (!(config.getBoolean("Bosses.Magma_cubeBoss.enabled"))) {
                sender.sendMessage("This Boss is disabled in the config file. To run this command the boss must be enabled");
                return true;
            }

            Location loc;
            if (p != null) {
                loc = p.getTargetBlock(null, 5).getLocation();

                if (loc.getBlock().getType() != Material.AIR) loc = p.getTargetBlock(null, 1).getLocation();
                if (loc.getBlock().getType() != Material.AIR) loc = p.getLocation();
            } else if (specLoc != null) {
                loc = specLoc;
            } else {
                sender.sendMessage("Vanilla Bosses: Something went wrong, check if you entered the command correctly");
                return true;
            }

            Entity entity = BossMagmacube.makeBossMagmacube(loc, world);
            sender.sendMessage(ChatColor.AQUA + "The Boss Magmacube has been summoned successfully");
            PersistentDataContainer container = entity.getPersistentDataContainer();
            String cmd = config.getStringList("Bosses.CommandsExecutedOnBossDeath").get(config.getInt("Bosses.Magma_cubeBoss.CommandToBeExecutedOnDeath"));
            if (!cmd.equals("")) {
                container.set(new NamespacedKey(Main.getInstance(), "VanillaBossesCommandOnDeath"), PersistentDataType.STRING, cmd);
            }
            return true;

        }

        return false;

    }
}

