package shreb.me.vanillabosses.bossclasses;

import org.bukkit.configuration.Configuration;
import shreb.me.vanillabosses.main.Main;

//This class is not yet used, but a preparation for future changes to the code

public enum Bosses {

    BLAZE(Main.getInstance().getConfig().getInt("Bosses.BlazeBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.BlazeBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.BlazeBoss.DamageModifier"),
            "BlazeBoss",
            "BossBlaze"),

    CREEPER(Main.getInstance().getConfig().getInt("Bosses.CreeperBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.CreeperBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.CreeperBoss.DamageModifier"),
            "CreeperBoss",
            "BossCreeper"),

    ENDERMAN(Main.getInstance().getConfig().getInt("Bosses.EndermanBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.EndermanBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.EndermanBoss.DamageModifier"),
            "EndermanBoss",
            "BossEnderman"),

    SKELETON(Main.getInstance().getConfig().getInt("Bosses.SkeletonBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.SkeletonBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.SkeletonBoss.DamageModifier"),
            "SkeletonBoss",
            "BossSkeleton"),

    SLIME(Main.getInstance().getConfig().getInt("Bosses.SlimeBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.SlimeBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.SlimeBoss.DamageModifier"),
            "SlimeBoss",
            "BossSlime"),

    SPIDER(Main.getInstance().getConfig().getInt("Bosses.SpiderBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.SpiderBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.SpiderBoss.DamageModifier"),
            "SpiderBoss",
            "BossSpider"),

    WITCH(Main.getInstance().getConfig().getInt("Bosses.WitchBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.WitchBoss.health"),
            1,                                             //damageModifier of 1, since there is no benefit in changing it for a witch.
            "WitchBoss",
            "BossWitch"),

    WITHER(0,                                               //Witherboss spawnchance of 0 since it doesnt spawn naturally
            Main.getInstance().getConfig().getDouble("Bosses.WitherBoss.Health"),
            Main.getInstance().getConfig().getDouble("Bosses.WitherBoss.DamageModifier"),
            "WitherBoss",
            "BossWither"),

    ZOMBIE(Main.getInstance().getConfig().getInt("Bosses.ZombieBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.ZombieBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.ZombieBoss.DamageModifier"),
            "ZombieBoss",
            "BossZombie"),

    ZOMBIFIED_PIGLIN(Main.getInstance().getConfig().getInt("Bosses.Zombified_PiglinBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.Zombified_PiglinBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.Zombified_PiglinBoss.DamageModifier"),
            "Zombified_PiglinBoss",
            "BossZombified_Piglin"),

    MAGMACUBE(Main.getInstance().getConfig().getInt("Bosses.MagmacubeBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.MagmacubeBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.MagmacubeBoss.DamageModifier"),
            "MagmacubeBoss",
            "BossMagmacube");


    public int spawnChance; //out of 1000
    public double health;
    public double damageModifier;
    public String configSectionName;
    public String scoreboardBossTag;



    Bosses(int spawnChance, double health, double damageModifier, String configSectionName, String scoreboardBossTag){
        this.spawnChance = spawnChance;
        this.health = health;
        this.damageModifier = damageModifier;
        this.configSectionName = configSectionName;
        this.scoreboardBossTag = scoreboardBossTag;
    }
}
