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
    /**
     * level of MagmaCream is saved inside the PDC, accessible by the PDC.get() method.
     */

    HEATEDMAGMACREAM1("HeatedMagmaCream",
            new NamespacedKey(Main.getInstance(), "MagmaCreamLevel")),
    /**
     * level of MagmaCream is saved inside the PDC, accessible by the PDC.get() method.
     */

    HEATEDMAGMACREAM2("HeatedMagmaCream",
            new NamespacedKey(Main.getInstance(), "MagmaCreamLevel")),
    /**
     * level of MagmaCream is saved inside the PDC, accessible by the PDC.get() method.
     */

    HEATEDMAGMACREAM3("HeatedMagmaCream",
            new NamespacedKey(Main.getInstance(), "MagmaCreamLevel")),

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


    Items(String configSection, NamespacedKey identifyingPDCKey){
        this.configSection = configSection;
        this.identifyingPDCKey = identifyingPDCKey;
    }


}
