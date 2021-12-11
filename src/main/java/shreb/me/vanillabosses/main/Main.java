package shreb.me.vanillabosses.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import shreb.me.vanillabosses.bossclasses.*;
import shreb.me.vanillabosses.commands.Boss;
import shreb.me.vanillabosses.commands.Items;
import shreb.me.vanillabosses.commands.VBHelp;
import shreb.me.vanillabosses.items.*;
import shreb.me.vanillabosses.listeners.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public final class Main extends JavaPlugin {

    private static Main instance;


    @Override
    public void onEnable() {
        // Plugin startup logic

        createFiles();

        loadConfig();

        instance = this;

        if(!config.getBoolean("Bosses.enablePlugin")) {
            getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "The VanillaBosses Plugin is installed, but disabled in the config.");
           return;
        }

        PluginManager pm = Bukkit.getPluginManager();

        //NonPerformance Section:
        pm.registerEvents(new BossSkeleton(), this);
        pm.registerEvents(new BossWither(), this);
        pm.registerEvents(new BossCreeper(), this);
        pm.registerEvents(new BossSpider(), this);
        pm.registerEvents(new BossBlaze(), this);
        pm.registerEvents(new BossEnderman(), this);
        pm.registerEvents(new BossWitch(), this);
        pm.registerEvents(new BossZombie(), this);
        pm.registerEvents(new BossZombified_Piglin(), this);
        pm.registerEvents(new BossSlime(), this);
        pm.registerEvents(new EntityDamagedByEntityEvent(), this);
        pm.registerEvents(new EntityDamageEvent(), this);
        pm.registerEvents(new EntityDeathEvent(), this);
        pm.registerEvents(new EntityExplodeEvent(), this);
        pm.registerEvents(new Skeletor(), this);
        pm.registerEvents(new Slingshot(), this);
        pm.registerEvents(new ButchersAxe(), this);
        pm.registerEvents(new Blazer(), this);
        pm.registerEvents(new ItemEnchantmentsListener(), this);
        pm.registerEvents(new EntitySpawnEvent(), this);
        if(config.getBoolean("Items.DisableRepairAndEnchant"))pm.registerEvents(new AntiRepairListener(), this);

        Objects.requireNonNull(getCommand("boss")).setExecutor(new Boss());
        Objects.requireNonNull(getCommand("vbh")).setExecutor(new VBHelp());
        Objects.requireNonNull(getCommand("items")).setExecutor(new Items());


        if(config.getBoolean("Items.slingshot.enableCraftingRecipe"))           CraftingRecipes.slingshotRecipe();          //enables Slingshot recipe
        if(config.getBoolean("Items.Skeletor.enableCraftingRecipe"))            CraftingRecipes.skeletorRecipe();           //enables skeletor recipe
        if(config.getBoolean("Items.cloakOfInvisibility.enableCraftingRecipe")) CraftingRecipes.cloakRecipe();              //enables the cloak recipe
        if(config.getBoolean("Items.SlimeBoots.enableCraftingRecipe"))          CraftingRecipes.slimeBootsRecipe();         //enables the slime boots recipe
        if(config.getBoolean("Items.Blazer.enableCraftingRecipe"))              CraftingRecipes.blazerRecipe();             //enables the blazer recipe

        final String version = this.getDescription().getVersion();

        if((!config.contains("Bosses.configVersion") || !config.getString("Bosses.configVersion").equals(version)) && !config.getBoolean("Bosses.resetConfig")){
            this.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Vanilla Bosses: The config seems to be out of date. This should not have any impact on gameplay if your plugin version is 1.4.2 or above. If any problems should occur anyway, consider updating it.");
            this.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "#Before updating# If you have customized the config I recommend saving it and copy/pasteing the parts you want to keep into the new config. \n If you want this message to stop appearing without updating the config, change the configVersion at the top of the config file to the current plugin version.");
        }

        if(config.getBoolean("Bosses.resetConfig")){                //resetting the config to the latest standard settings.
            configF = new File(getDataFolder(), "config.yml");
            saveResource("config.yml", true);
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "The configuration file has been reset to the Default values.");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "This happened because the 'resetConfig' setting in the configuration file was enabled. The setting has been reset to 'false' now");

        }

        Slingshot.removeNoFallDMGTagTimer();


        if(config.getBoolean("Items.cloakOfInvisibility.enableChecks")) InvisibilityCloak.invisCloakTimer();        // enable the check timer for the cloak of invisibility

        int pluginId = 12433;
        Metrics metrics = new Metrics(this, pluginId);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, RespawningBosses::spawnMobs, 5 * 20);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, RespawningBosses::bossBarTimer, 7 * 20);

        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "The Vanilla Bosses plugin is enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        if(config.getBoolean("Items.slingshot.enableCraftingRecipe"))           Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "blazer"));
        if(config.getBoolean("Items.Skeletor.enableCraftingRecipe"))            Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "slimeboots"));
        if(config.getBoolean("Items.cloakOfInvisibility.enableCraftingRecipe")) Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "invisibilityCloak"));
        if(config.getBoolean("Items.SlimeBoots.enableCraftingRecipe"))          Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "skeletor"));
        if(config.getBoolean("Items.Blazer.enableCraftingRecipe"))              Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "slingshotRecipe"));


        for (World w : getServer().getWorlds()
        ) {
            for (Entity e : w.getEntities()
            ) {
                if (e.getScoreboardTags().contains("removeInvulnerableOnDisable") && e.isInvulnerable())    //removing all entities marked for termination on plugin disable
                    e.setInvulnerable(false);
                if (e.getScoreboardTags().contains("removeOnDisable")) {
                    if(RespawningBosses.bossBarHashMap.keySet().contains(e.getUniqueId())){

                        RespawningBosses.bossBarHashMap.get(e.getUniqueId()).removeAll();

                    }

                    e.remove();
                }
            }
        }
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "The Vanilla Bosses plugin is disabled");
    }

    public static Main getInstance() {
        return instance;
    }

    public void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }

    private File configF;
    private FileConfiguration config;

    private void createFiles() {

        configF = new File(getDataFolder(), "config.yml");

        if (!configF.exists()) {
            configF.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(configF);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
