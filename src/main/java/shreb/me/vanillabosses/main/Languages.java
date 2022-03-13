package shreb.me.vanillabosses.main;

public enum Languages {

    EN (
        " was slain by ",

        "Not enough Arguments!",

        "Bad Argument, please try again.",

        "You do not have the necessary Permissions!",

        "An Error has ocurred, please ",

            "This command is disabled.",

            "The targeted Inventory seems to be full.",

            "Possible commands:\n" +
                    " - /vbh discord (this will send a link to the plugin discord)\n" +
                    " - /vbh  (shows this help message)\n" +
                    " - /vbh <EntityType>  (gives you information about the boss of the specified type. type '/boss list' to see all the available bosses!)\n" +
                    " - /vbh info  (gives you information about the plugin)\n",

            "A normal blaze which has been studying the art of not caring what it has for projectiles.\n" +
                    "This blaze has more health than a normal one, but can be cleansed by spectral arrows, doing more damage than usual.\n" +
                    "Upon being hit with a spectral arrow the blaze may drop an item (if not disabled in the config)",

            "Bomby is a Creeper with great anger management\n" +
                    "He still gets really angry when he is lit on fire by anything tho.\n" +
                    "He lets out his anger in the form of 8 primed blocks of TNT which fly in all directions when he is about to explode\n" +
                    "Bomby does a lot of damage when he gets angry but can also be defeated by making him angry in quick succession",

            "An enderman with a slight drinking problem. \n" +
                    "If you hit this enderman it may just become even more angry than a normal enderman would be.\n" +
                    "Whenever hit, there is a chance that the enderman gains configurable potion effects.\n" +
                    "When this enderman teleports, it will spawn endermites (if not disabled in the config)\n" +
                    "Drops the Cloak of Invisibility",

            "Always wants to cuddle people!\n" +
                    "Can get really angry when hit and set everything around it on fire.\n" +
                    "Doesn't want to be the bad guy but cuddling people when youre as hot as lava hurts slightly\n" +
                    "Has a Chance of dropping each of the 3 levels of Heated Magma Cream.",

            "The Skeleton King was once the regent of a prosperous nation\n" +
                    "He died alongside his army and nation in a final stand in front of his castles walls\n" +
                    "With his trusty Skeletor bow made from Deerbones he does a lot of damage and summons the power of TNT upon his enemies\n" +
                    "When hit he can reflect the damage, become invulnerable for a certain amount of time or summon a part of his once great army to serve him once more\n" +
                    "When he came back to the world of the living he was able to take a small amount of treasure with him which he may drop on being killed\n" +
                    "Since his bow is in a rather bad condition it may not be salvaged upon death\n" +
                    "Drops the Skeletor",

            "The Slimiest and bounciest out there!\n" +
                    "When hit it can redirect the knockback downwards to boost itself into the air, then crashing into the ground at a high speed.\n" +
                    "Any Player touching the ground at the moment the slime crashes into the ground will be flung away!\n" +
                    "Drops the Slime Boots and Bouncy Slime",

            "Dolores is a Spider.\n" +
                    "If you hit her, she can become invisible and gain buffs in addition to teleporting behind you\n" +
                    "additionally she has a powerful jump she can use to get close to you\n" +
                    "Drops the Slingshot",

            "This Witch is an overachiever.\n" +
                    "She has figured out how to make potions which are much better than those they teach at the academy.\n" +
                    "Experimenting with ingredients she has also made some forgotten potions, but sadly she will not tell us what ingredients she used...",

            "The Boss wither is a rather unfortunate Experiment\n" +
                    "Trying to make the antichrist has not ended well for anyone. ever.\n" +
                    "But here you are. Combining the 2 things that remind people the most about the nether.\n" +
                    "It is spawned by building a standard Wither directly on top of a Netherite Block. The Netherite Block will disappear if not changed in the config.\n" +
                    "On death he (or she?) will drop a wither egg.\n" +
                    "To hatch it, you have to follow the instructions written in black underneath the name of the egg",

            "Formerly known as 'Bob' in some parts, this Zombie always has his gang around to help out.\n" +
                    "With Bob wearing fully enchanted Iron armor and having a good amount of HP the gang doesn't have much to do tho.\n" +
                    "Drops the Baseball Bat",

            "One of the few Pigmen who were able to mine gold and enchant their armor!\n" +
                    "The Butchers axe this Pigman is holding has a chance to cripple anyone unfortunate enough to be hit by it.\n" +
                    "Drops the Butchers Axe",

            "The Vanilla Bosses plugin was made by Shreb (On Spigot)\n" +
                    "Please report any attempts of copying my content to me via Spigot or discord\n" +
                    "If you're enjoying my plugin, please do leave a rating and tell your friends :D\n" +
                    "In Case you have any ideas about new bosses or items and would like to see them in the plugin, please shoot me a message.\n" +
                    "Have fun playing and stay healthy :)"
    );

    public String killedByMessage;
    public String notEnoughArguments;
    public String badArgument;
    public String badPermissions;
    public String errorMessage;
    public String commandDisabled;
    public String inventoryFull;
    public String vbh0;
    public String vbhBlaze;
    public String vbhCreeper;
    public String vbhEnderman;
    public String vbhMagmaCube;
    public String vbhSkeleton;
    public String vbhSlime;
    public String vbhSpider;
    public String vbhWitch;
    public String vbhWither;
    public String vbhZombie;
    public String vbhPiglin;
    public String vbhInfo;


    Languages(String killedByMessage, String notEnoughArguments, String badArgument, String badPermissions, String errorMessage, String commandDisabled, String inventoryFull, String vbh0, String vbhBlaze, String vbhCreeper, String vbhEnderman, String vbhMagmaCube, String vbhSkeleton, String vbhSlime, String vbhSpider, String vbhWitch, String vbhWither, String vbhZombie, String vbhPiglin, String vbhInfo) {
        this.killedByMessage = killedByMessage;
        this.notEnoughArguments = notEnoughArguments;
        this.badArgument = badArgument;
        this.badPermissions = badPermissions;
        this.errorMessage = errorMessage;
        this.commandDisabled = commandDisabled;
        this.inventoryFull = inventoryFull;
        this.vbh0 = vbh0;
        this.vbhBlaze = vbhBlaze;
        this.vbhCreeper = vbhCreeper;
        this.vbhEnderman = vbhEnderman;
        this.vbhMagmaCube = vbhMagmaCube;
        this.vbhSkeleton = vbhSkeleton;
        this.vbhSlime = vbhSlime;
        this.vbhSpider = vbhSpider;
        this.vbhWitch = vbhWitch;
        this.vbhWither = vbhWither;
        this.vbhZombie = vbhZombie;
        this.vbhPiglin = vbhPiglin;
        this.vbhInfo = vbhInfo;
    }
}
