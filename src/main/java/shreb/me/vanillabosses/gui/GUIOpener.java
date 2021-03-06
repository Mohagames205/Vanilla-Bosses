/**
package shreb.me.vanillabosses.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Wither;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import shreb.me.vanillabosses.bossclasses.BossWither;
import shreb.me.vanillabosses.items.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class GUIOpener {

    static HashSet<Inventory> guiInventories = new HashSet<>();
    static HashSet<Inventory> bossSettingInventories = new HashSet<>();

    public static ItemStack bossBlaze = createGuiItem(Material.BLAZE_ROD, "Boss Blaze", "Access the Blaze boss configuration");
    public static ItemStack bossCreeper = createGuiItem(Material.GUNPOWDER, "Boss Creeper", "Access the Creeper boss configuration");
    public static ItemStack bossEnderman = createGuiItem(Material.ENDER_PEARL, "Boss Enderman", "Access the Enderman boss configuration");
    public static ItemStack bossMagmacube = createGuiItem(Material.MAGMA_CREAM, "Boss Magmacube", "Access the Magmacube boss configuration");
    public static ItemStack bossSkeleton = createGuiItem(Material.SKELETON_SKULL, "Boss Skeleton", "Access the Skeleton boss configuration");
    public static ItemStack bossSlime = createGuiItem(Material.SLIME_BALL, "Boss Slime", "Access the Slime boss configuration");
    public static ItemStack bossSpider = createGuiItem(Material.SPIDER_EYE, "Boss Spider", "Access the Spider boss configuration");
    public static ItemStack bossWitch = createGuiItem(Material.STICK, "Boss Witch", "Access the Witch boss configuration");
    public static ItemStack bossWither = createGuiItem(Material.WITHER_ROSE, "Boss Wither", "Access the Wither boss configuration");
    public static ItemStack bossZombie = createGuiItem(Material.ROTTEN_FLESH, "Boss Zombie", "Access the Zombie boss configuration");
    public static ItemStack bossPigman = createGuiItem(Material.GOLD_NUGGET, "Boss Pigman", "Access the Zombified Piglin boss configuration");

    public static ItemStack bosses = createGuiItem(Material.SKELETON_SKULL, "Bosses", ChatColor.LIGHT_PURPLE + "Access the bosses section of the config");
    public static ItemStack items = createGuiItem(Material.BOW, "Items", ChatColor.LIGHT_PURPLE + "Access the Items section of the config.");
    public static ItemStack general = createGuiItem(Material.PAPER, "General Settings", "access general settings");

    public static ItemStack itemBaseballBat = BaseballBat.makeBaseballBat();
    public static ItemStack itemBlazer = Blazer.makeBlazer();
    public static ItemStack itemButchersAxe = ButchersAxe.makeButchersAxe();
    public static ItemStack itemHMC = HeatedMagmaCream.makeHeatedMagmaCream(1);
    public static ItemStack itemInvisibilityCloak = InvisibilityCloak.makeCloak();
    public static ItemStack itemSkeletor = Skeletor.makeSkeletor();
    public static ItemStack itemSlimeBoots = SlimeBoots.makeSlimeBoots();
    public static ItemStack itemBouncySlime = SlimeBoots.makeBouncySlime();
    public static ItemStack itemSlingShot = Slingshot.makeSlingshot();
    public static ItemStack itemWitherEgg = BossWither.makeWitherEgg();

    public static ItemStack cancelArrow = createGuiItem(Material.ARROW, "Cancel");
    public static ItemStack confirmEntry = createGuiItem(Material.PAPER, "Confirm entry", "Have a named item in your hand to set that number");
    public static ItemStack setTrue = createGuiItem(Material.GREEN_CONCRETE, "Set to True");
    public static ItemStack setFalse = createGuiItem(Material.RED_CONCRETE, "Set to False");

    static HashMap<ItemStack, Inventory> generalInventoryMap = new HashMap<>();
    static HashMap<ItemStack, Inventory> bossInventoryMap = new HashMap<>();
    static HashMap<ItemStack, Inventory> itemInventoryMap = new HashMap<>();
    static HashMap<ItemStack, Inventory> inventory1Map = new HashMap<>();

    private final Inventory itemWitherEggInv;
    private final Inventory itemBaseballbatInv;
    private final Inventory itemBlazerInv;
    private final Inventory itemButchersAxeInv;
    private final Inventory itemHMCInv;
    private final Inventory itemInvisCloakInv;
    private final Inventory itemSkeletorInv;
    private final Inventory itemSlimeBootsInv;
    private final Inventory itemBouncySlimeInv;
    private final Inventory itemSlingshotInv;

    private final Inventory trueFalse = Bukkit.createInventory(null, 9, "Boolean Entry");
    private final Inventory stringEntering = Bukkit.createInventory(null, 9, "String Entry");
    private final Inventory doubleEntering = Bukkit.createInventory(null, 9, "Number Entry");
    private final Inventory intEntering = Bukkit.createInventory(null, 9, "Number Entry");

    private static final Inventory inventoryBosses = Bukkit.createInventory(null, 18, "Section Selection");
    private static final Inventory inventoryItems = Bukkit.createInventory(null, 18, "Section Selection");
    private static final Inventory inventoryGeneral = Bukkit.createInventory(null, 18, "Section Selection");
    public static final Inventory inventory1 = Bukkit.createInventory(null, 9, "Section Selection");

    private static final Inventory bossBlazeBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossCreeperBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossEndermanBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossMagmacubeBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossSkeletonBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossSlimeBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossSpiderBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossWitchBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossWitherBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossZombieBasicSettings = Bukkit.createInventory(null, 9, "Settings");
    private static final Inventory bossPigmanBasicSettings = Bukkit.createInventory(null, 9, "Settings");

    public static ItemStack pluginLanguage = createGuiItem(Material.PAPER, "Language", "Change the plugin language");
    public static ItemStack blazesFromSpawners = createGuiItem(Material.BLAZE_ROD, "Spawners spawn Blaze bosses.", "Change to true to disable blaze bosses spawning on spawners.");

    public GUIOpener() {

        itemWitherEggInv = Bukkit.createInventory(null, 9, "Settings");
        itemBaseballbatInv = Bukkit.createInventory(null, 9, "Settings");
        itemBlazerInv = Bukkit.createInventory(null, 9, "Settings");
        itemButchersAxeInv = Bukkit.createInventory(null, 9, "Settings");
        itemHMCInv = Bukkit.createInventory(null, 9, "Settings");
        itemInvisCloakInv = Bukkit.createInventory(null, 9, "Settings");
        itemSkeletorInv = Bukkit.createInventory(null, 9, "Settings");
        itemSlimeBootsInv = Bukkit.createInventory(null, 9, "Settings");
        itemBouncySlimeInv = Bukkit.createInventory(null, 9, "Settings");
        itemSlingshotInv = Bukkit.createInventory(null, 9, "Settings");

        initializeInventories();
        initializeItems();
    }

    private void initializeItems() {

        inventory1Map.put(bosses, inventoryBosses);
        inventory1Map.put(items, inventoryItems);
        inventory1Map.put(general, inventoryGeneral);

        generalInventoryMap.put(pluginLanguage, stringEntering);
        generalInventoryMap.put(blazesFromSpawners, trueFalse);

        bossInventoryMap.put(bossBlaze, bossBlazeBasicSettings);
        bossInventoryMap.put(bossMagmacube, bossMagmacubeBasicSettings);
        bossInventoryMap.put(bossCreeper, bossCreeperBasicSettings);
        bossInventoryMap.put(bossPigman, bossPigmanBasicSettings);
        bossInventoryMap.put(bossEnderman, bossEndermanBasicSettings);
        bossInventoryMap.put(bossSkeleton, bossSkeletonBasicSettings);
        bossInventoryMap.put(bossSlime, bossSlimeBasicSettings);
        bossInventoryMap.put(bossWitch, bossWitchBasicSettings);
        bossInventoryMap.put(bossWither, bossWitherBasicSettings);
        bossInventoryMap.put(bossZombie, bossZombieBasicSettings);

        itemInventoryMap.put(itemWitherEgg, itemWitherEggInv);
        itemInventoryMap.put(itemBlazer, itemBlazerInv);
        itemInventoryMap.put(itemHMC, itemHMCInv);
        itemInventoryMap.put(itemBouncySlime, itemBouncySlimeInv);
        itemInventoryMap.put(itemButchersAxe, itemButchersAxeInv);
        itemInventoryMap.put(itemBaseballBat, itemBaseballbatInv);
        itemInventoryMap.put(itemSlingShot, itemSlingshotInv);
        itemInventoryMap.put(itemSlimeBoots, itemSlimeBootsInv);
        itemInventoryMap.put(itemInvisibilityCloak, itemInvisCloakInv);
        itemInventoryMap.put(itemSkeletor, itemSkeletorInv);


    }

    private void initializeInventories() {

        inventoryBosses.addItem(bossBlaze);
        inventoryBosses.addItem(bossCreeper);
        inventoryBosses.addItem(bossEnderman);
        inventoryBosses.addItem(bossMagmacube);
        inventoryBosses.addItem(bossSkeleton);
        inventoryBosses.addItem(bossSlime);
        inventoryBosses.addItem(bossSpider);
        inventoryBosses.addItem(bossWitch);
        inventoryBosses.addItem(bossWither);
        inventoryBosses.addItem(bossZombie);
        inventoryBosses.addItem(bossPigman);

        inventoryItems.addItem(itemBlazer);
        inventoryItems.addItem(itemHMC);
        inventoryItems.addItem(itemBaseballBat);
        inventoryItems.addItem(itemSkeletor);
        inventoryItems.addItem(itemButchersAxe);
        inventoryItems.addItem(itemBouncySlime);
        inventoryItems.addItem(itemInvisibilityCloak);
        inventoryItems.addItem(itemSlimeBoots);
        inventoryItems.addItem(itemSlingShot);
        inventoryItems.addItem(itemWitherEgg);

        inventoryGeneral.addItem(pluginLanguage);
        inventoryGeneral.addItem(blazesFromSpawners);

        //inventory1 init
        for (int i = 0; i < 2; i++) inventory1.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS));
        inventory1.setItem(2, bosses);
        inventory1.setItem(3, new ItemStack(Material.GRAY_STAINED_GLASS));
        inventory1.setItem(4, items);
        inventory1.setItem(5, new ItemStack(Material.GRAY_STAINED_GLASS));
        inventory1.setItem(6, general);
        for (int i = 7; i < 9; i++) inventory1.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS));

        trueFalse.addItem(setTrue);
        trueFalse.addItem(setFalse);
        trueFalse.addItem(cancelArrow);

        stringEntering.addItem(confirmEntry);
        stringEntering.addItem(cancelArrow);

        intEntering.addItem(confirmEntry);
        intEntering.addItem(cancelArrow);

        doubleEntering.addItem(confirmEntry);
        doubleEntering.addItem(cancelArrow);

        guiInventories.add(itemWitherEggInv);
        guiInventories.add(itemBaseballbatInv);
        guiInventories.add(itemBlazerInv);
        guiInventories.add(itemButchersAxeInv);
        guiInventories.add(itemHMCInv);
        guiInventories.add(itemInvisCloakInv);
        guiInventories.add(itemSkeletorInv);
        guiInventories.add(itemSlimeBootsInv);
        guiInventories.add(itemBouncySlimeInv);
        guiInventories.add(itemSlingshotInv);

        guiInventories.add(trueFalse);
        guiInventories.add(stringEntering);
        guiInventories.add(doubleEntering);
        guiInventories.add(intEntering);

        guiInventories.add(inventoryBosses);
        guiInventories.add(inventoryItems);
        guiInventories.add(inventoryGeneral);
        guiInventories.add(inventory1);

        bossSettingInventories.add(bossBlazeBasicSettings);
        bossSettingInventories.add(bossCreeperBasicSettings);
        bossSettingInventories.add(bossEndermanBasicSettings);
        bossSettingInventories.add(bossMagmacubeBasicSettings);
        bossSettingInventories.add(bossSkeletonBasicSettings);
        bossSettingInventories.add(bossSlimeBasicSettings);
        bossSettingInventories.add(bossSpiderBasicSettings);
        bossSettingInventories.add(bossWitchBasicSettings);
        bossSettingInventories.add(bossWitherBasicSettings);
        bossSettingInventories.add(bossZombieBasicSettings);
        bossSettingInventories.add(bossPigmanBasicSettings);

        guiInventories.addAll(bossSettingInventories);



    }

    public static HashSet<Inventory> getGuiInventories() {
        return guiInventories;
    }

    // Nice little method to create a gui item with a custom name, and description
    protected static ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public static void openRelatedInventory(InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;

        if (!guiInventories.contains(e.getInventory())) {
            return;
        }

        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().equals(inventory1)) {

            if (e.getCurrentItem().equals(bosses)) {

                player.openInventory(inventoryBosses);

            } else if (e.getCurrentItem().equals(items)) {

                player.openInventory(inventoryItems);

            } else if (e.getCurrentItem().equals(general)) {

                player.openInventory(inventoryGeneral);

            }
        }
        if (e.getInventory().equals(inventoryItems)) {

            player.openInventory(itemInventoryMap.get(e.getCurrentItem()));

        } else if (e.getInventory().equals(inventoryBosses)) {

            if (itemInventoryMap.containsKey(e.getCurrentItem())) {
                player.openInventory(itemInventoryMap.get(e.getCurrentItem()));
            }

        } else if (e.getInventory().equals(inventoryGeneral)) {

            player.openInventory(itemInventoryMap.get(e.getCurrentItem()));

        } else if (e.getInventory().equals(bossBasicSettings)) {

            player.openInventory(itemInventoryMap.get(e.getCurrentItem()));

        }
    }
}
 */