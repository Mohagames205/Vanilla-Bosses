package shreb.me.vanillabosses.items;

import org.bukkit.NamespacedKey;
import shreb.me.vanillabosses.main.Main;

public enum Items {

    BASEBALLBAT("BaseballBat",
            new NamespacedKey(Main.getInstance(), "ConcussI")),

    BLAZER("Blazer",
            new NamespacedKey(Main.getInstance(), "BlazerOnHit")),

    BUTCHERSAXE("ButchersAxe",
            new NamespacedKey(Main.getInstance(), "BindII")),

    HEATEDMAGMACREAM1("HeatedMagmaCream",
            new NamespacedKey(Main.getInstance(), "MagmaCreamLevel"),
            Main.getInstance().getConfig().getInt("Items.HeatedMagmaCream.Level1.radius"),
            Main.getInstance().getConfig().getInt("Items.HeatedMagmaCream.Level1.burnTime"),
            1),

    HEATEDMAGMACREAM2("HeatedMagmaCream",
            new NamespacedKey(Main.getInstance(), "MagmaCreamLevel"),
            Main.getInstance().getConfig().getInt("Items.HeatedMagmaCream.Level2.radius"),
            Main.getInstance().getConfig().getInt("Items.HeatedMagmaCream.Level2.burnTime"),
            2),

    HEATEDMAGMACREAM3("HeatedMagmaCream",
            new NamespacedKey(Main.getInstance(), "MagmaCreamLevel"),
            Main.getInstance().getConfig().getInt("Items.HeatedMagmaCream.Level3.radius"),
            Main.getInstance().getConfig().getInt("Items.HeatedMagmaCream.Level3.burnTime"),
            3),

    INVISIBILITYCLOAK("cloakOfInvisibility",
            new NamespacedKey(Main.getInstance(), "CloakOfInvisibility")),

    SKELETOR("Skeletor",
            new NamespacedKey(Main.getInstance(), "shootsTnT")),

    SLIMEBOOTS("SlimeBoots",
            new NamespacedKey(Main.getInstance(), "slimeBoots")),

    BOUNCYSLIME("SlimeBoots.BouncySlime",
            new NamespacedKey(Main.getInstance(), "bouncySlime")),

    SLINGSHOT("slingshot",
            new NamespacedKey(Main.getInstance(), "Slingshot"));

    public String configSection;
    public NamespacedKey identifyingPDCKey;
    public int radius;
    public int time;
    public int level;


    Items(String configSection, NamespacedKey identifyingPDCKey){
        this.configSection = configSection;
        this.identifyingPDCKey = identifyingPDCKey;
    }

    Items(String configSection, NamespacedKey identifyingPDCKey, int radius, int time, int level){
        this.configSection = configSection;
        this.identifyingPDCKey = identifyingPDCKey;
        this.radius = radius;
        this.time = time;
        this.level = level;
    }

}
