package shreb.me.vanillabosses.bossclasses;

import shreb.me.vanillabosses.main.Main;

//This class is not yet used, but a preparation for future changes to the code

public enum Bosses {

    BLAZE(Main.getInstance().getConfig().getInt("Bosses.BlazeBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.BlazeBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.BlazeBoss.DamageModifier"),
            "BlazeBoss",
            "BossBlaze",
            Main.getInstance().getConfig().getString("Bosses.BlazeBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.BlazeBoss.displayNameColor")),

    CREEPER(Main.getInstance().getConfig().getInt("Bosses.CreeperBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.CreeperBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.CreeperBoss.DamageModifier"),
            "CreeperBoss",
            "BossCreeper",
            Main.getInstance().getConfig().getString("Bosses.CreeperBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.CreeperBoss.displayNameColor")),

    ENDERMAN(Main.getInstance().getConfig().getInt("Bosses.EndermanBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.EndermanBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.EndermanBoss.DamageModifier"),
            "EndermanBoss",
            "BossEnderman",
            Main.getInstance().getConfig().getString("Bosses.EndermanBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.EndermanBoss.displayNameColor")),

    SKELETON(Main.getInstance().getConfig().getInt("Bosses.SkeletonBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.SkeletonBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.SkeletonBoss.DamageModifier"),
            "SkeletonBoss",
            "BossSkeleton",
            Main.getInstance().getConfig().getString("Bosses.SkeletonBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.SkeletonBoss.displayNameColor")),

    SLIME(Main.getInstance().getConfig().getInt("Bosses.SlimeBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.SlimeBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.SlimeBoss.DamageModifier"),
            "SlimeBoss",
            "BossSlime",
            Main.getInstance().getConfig().getString("Bosses.SlimeBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.SlimeBoss.displayNameColor")),

    SPIDER(Main.getInstance().getConfig().getInt("Bosses.SpiderBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.SpiderBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.SpiderBoss.DamageModifier"),
            "SpiderBoss",
            "BossSpider",
            Main.getInstance().getConfig().getString("Bosses.SpiderBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.SpiderBoss.displayNameColor")),

    WITCH(Main.getInstance().getConfig().getInt("Bosses.WitchBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.WitchBoss.health"),
            1,                                             //damageModifier of 1, since there is no benefit in changing it for a witch.
            "WitchBoss",
            "BossWitch",
            Main.getInstance().getConfig().getString("Bosses.WitchBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.WitchBoss.displayNameColor")),

    WITHER(0,                                               //Witherboss spawnchance of 0 since it doesnt spawn naturally
            Main.getInstance().getConfig().getDouble("Bosses.WitherBoss.Health"),
            Main.getInstance().getConfig().getDouble("Bosses.WitherBoss.DamageModifier"),
            "WitherBoss",
            "BossWither",
            Main.getInstance().getConfig().getString("Bosses.WitherBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.WitherBoss.displayNameColor")),

    ZOMBIE(Main.getInstance().getConfig().getInt("Bosses.ZombieBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.ZombieBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.ZombieBoss.DamageModifier"),
            "ZombieBoss",
            "BossZombie",
            Main.getInstance().getConfig().getString("Bosses.ZombieBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.ZombieBoss.displayNameColor")),

    ZOMBIFIED_PIGLIN(Main.getInstance().getConfig().getInt("Bosses.Zombified_PiglinBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.Zombified_PiglinBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.Zombified_PiglinBoss.DamageModifier"),
            "Zombified_PiglinBoss",
            "BossZombified_Piglin",
            Main.getInstance().getConfig().getString("Bosses.Zombified_PiglinBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.Zombified_PiglinBoss.displayNameColor")),

    MAGMA_CUBE(Main.getInstance().getConfig().getInt("Bosses.Magma_cubeBoss.spawnChance"),
            Main.getInstance().getConfig().getDouble("Bosses.Magma_cubeBoss.health"),
            Main.getInstance().getConfig().getDouble("Bosses.Magma_cubeBoss.DamageModifier"),
            "MagmacubeBoss",
            "BossMagmacube",
            Main.getInstance().getConfig().getString("Bosses.Magma_cubeBoss.displayName"),
            Main.getInstance().getConfig().getString("Bosses.Magma_cubeBoss.displayNameColor"));


    public int spawnChance; //out of 1000
    public double health;
    public double damageModifier;
    public String configSectionName;
    public String scoreboardBossTag;
    public String displayName;
    public String nameColor;


    Bosses(int spawnChance, double health, double damageModifier, String configSectionName, String scoreboardBossTag, String displayName, String nameColor){
        this.spawnChance = spawnChance;
        this.health = health;
        this.damageModifier = damageModifier;
        this.configSectionName = configSectionName;
        this.scoreboardBossTag = scoreboardBossTag;
        this.displayName = displayName;
        this.nameColor = nameColor;

    }
}
