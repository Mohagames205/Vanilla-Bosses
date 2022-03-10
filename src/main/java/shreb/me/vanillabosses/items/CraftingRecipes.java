package shreb.me.vanillabosses.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import shreb.me.vanillabosses.main.Main;

import java.util.HashMap;

public class CraftingRecipes {

    public static HashMap<String, ItemStack> initializeSpecialItems() {

        ItemStack potion = new ItemStack(Material.SPLASH_POTION);
        ItemMeta meta = potion.getItemMeta();
        PotionMeta potMeta = (PotionMeta) meta;
        PotionData data = new PotionData(PotionType.INVISIBILITY, true, false);
        potMeta.setBasePotionData(data);
        potion.setItemMeta(potMeta);

        HashMap<String, ItemStack> specialItems = new HashMap<>();
        specialItems.put("BASEBALLBAT", BaseballBat.makeBaseballBat());
        specialItems.put("BLAZER", Blazer.makeBlazer());
        specialItems.put("BUTCHERSAXE", ButchersAxe.makeButchersAxe());
        specialItems.put("INVISIBILITYCLOAK", InvisibilityCloak.makeCloak());
        specialItems.put("SKELETOR", Skeletor.makeSkeletor());
        specialItems.put("SLIMEBOOTS", SlimeBoots.makeSlimeBoots());
        specialItems.put("BOUNCYSLIME", SlimeBoots.makeBouncySlime());
        specialItems.put("SLINGSHOT", Slingshot.makeSlingshot());
        specialItems.put("POT", potion);
        specialItems.put("HeatedCream1", HeatedMagmaCream.makeHeatedMagmaCream(1));
        specialItems.put("HeatedCream2", HeatedMagmaCream.makeHeatedMagmaCream(2));
        specialItems.put("HeatedCream3", HeatedMagmaCream.makeHeatedMagmaCream(3));

        return specialItems;
    }

    private static final HashMap<String, ItemStack> specialItems = initializeSpecialItems();

    private static final FileConfiguration config = Main.getInstance().getConfig();

    public static void slingshotRecipe() {
        ItemStack slingshot = Slingshot.makeSlingshot();

        NamespacedKey key = new NamespacedKey(Main.getInstance(), "Slingshot");

        ShapedRecipe slingshotRecipe = new ShapedRecipe(key, slingshot);

        slingshotRecipe.shape(config.getStringList("Items.slingshot.recipeShape").get(0), config.getStringList("Items.slingshot.recipeShape").get(1), config.getStringList("Items.slingshot.recipeShape").get(2));

        for (String s : config.getStringList("Items.slingshot.recipeIngredients")) {

            RecipeChoice choice;

            String[] strings = s.split(":");
            char recipeKey = strings[0].charAt(0);
            String materialType = strings[1];

            if (specialItems.containsKey(materialType.toUpperCase())) {

                choice = new RecipeChoice.ExactChoice(specialItems.get(materialType.toUpperCase()));

            } else if (Material.getMaterial(materialType.toUpperCase()) != null) {

                choice = new RecipeChoice.MaterialChoice(Material.getMaterial(materialType.toUpperCase()));

            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage("Vanilla Bosses: Couldnt find recipeIngredient " + materialType);
                return;
            }

            slingshotRecipe.setIngredient(recipeKey, choice);
        }

        Bukkit.addRecipe(slingshotRecipe);
    }

    public static void skeletorRecipe() {

        ItemStack bow = Skeletor.makeSkeletor();

        NamespacedKey key = new NamespacedKey(Main.getInstance(), "Skeletor");

        ShapedRecipe skeletor = new ShapedRecipe(key, bow);

        skeletor.shape(config.getStringList("Items.Skeletor.recipeShape").get(0), config.getStringList("Items.Skeletor.recipeShape").get(1), config.getStringList("Items.Skeletor.recipeShape").get(2));

        for (String s : config.getStringList("Items.Skeletor.recipeIngredients")) {

            RecipeChoice choice;

            String[] strings = s.split(":");
            char recipeKey = strings[0].charAt(0);
            String materialType = strings[1];

            if (specialItems.containsKey(materialType.toUpperCase())) {

                choice = new RecipeChoice.ExactChoice(specialItems.get(materialType.toUpperCase()));

            } else if (Material.getMaterial(materialType.toUpperCase()) != null) {

                choice = new RecipeChoice.MaterialChoice(Material.getMaterial(materialType.toUpperCase()));

            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage("Vanilla Bosses: Couldnt find recipeIngredient " + materialType);
                return;
            }

            skeletor.setIngredient(recipeKey, choice);
        }
        Bukkit.addRecipe(skeletor);
    }

    public static void cloakRecipe() {

        ItemStack cloak = InvisibilityCloak.makeCloak();

        NamespacedKey key = new NamespacedKey(Main.getInstance(), "invisibilityCloak");

        ShapedRecipe invisibilityCloak = new ShapedRecipe(key, cloak);

        invisibilityCloak.shape(config.getStringList("Items.cloakOfInvisibility.recipeShape").get(0), config.getStringList("Items.cloakOfInvisibility.recipeShape").get(1), config.getStringList("Items.cloakOfInvisibility.recipeShape").get(2));

        for (String s : config.getStringList("Items.cloakOfInvisibility.recipeIngredients")) {

            RecipeChoice choice;

            String[] strings = s.split(":");
            char recipeKey = strings[0].charAt(0);
            String materialType = strings[1];

            if (specialItems.containsKey(materialType.toUpperCase())) {

                choice = new RecipeChoice.ExactChoice(specialItems.get(materialType.toUpperCase()));

            } else if (Material.getMaterial(materialType.toUpperCase()) != null) {

                choice = new RecipeChoice.MaterialChoice(Material.getMaterial(materialType.toUpperCase()));

            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage("Vanilla Bosses: Couldnt find recipeIngredient " + materialType);
                return;
            }

            invisibilityCloak.setIngredient(recipeKey, choice);

        }

        Bukkit.addRecipe(invisibilityCloak);

    }

    public static void slimeBootsRecipe() {

        ItemStack boots = SlimeBoots.makeSlimeBoots();
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "slimeboots");
        ShapedRecipe slimeBoots = new ShapedRecipe(key, boots);

        slimeBoots.shape(config.getStringList("Items.SlimeBoots.recipeShape").get(0), config.getStringList("Items.SlimeBoots.recipeShape").get(1), config.getStringList("Items.SlimeBoots.recipeShape").get(2));

        for (String s : config.getStringList("Items.SlimeBoots.recipeIngredients")) {

            RecipeChoice choice;

            String[] strings = s.split(":");
            char recipeKey = strings[0].charAt(0);
            String materialType = strings[1];

            if (specialItems.containsKey(materialType.toUpperCase())) {

                choice = new RecipeChoice.ExactChoice(specialItems.get(materialType.toUpperCase()));

            } else if (Material.getMaterial(materialType.toUpperCase()) != null) {

                choice = new RecipeChoice.MaterialChoice(Material.getMaterial(materialType.toUpperCase()));

            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage("Vanilla Bosses: Couldnt find recipeIngredient " + materialType);
                return;
            }

            slimeBoots.setIngredient(recipeKey, choice);
        }

        Bukkit.addRecipe(slimeBoots);
    }

    public static void blazerRecipe() {

        ItemStack blazerItem = Blazer.makeBlazer();
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "blazer");
        ShapedRecipe blazer = new ShapedRecipe(key, blazerItem);

        blazer.shape(config.getStringList("Items.Blazer.recipeShape").get(0), config.getStringList("Items.Blazer.recipeShape").get(1), config.getStringList("Items.Blazer.recipeShape").get(2));

        for (String s : config.getStringList("Items.Blazer.recipeIngredients")) {

            RecipeChoice choice;

            String[] strings = s.split(":");
            char recipeKey = strings[0].charAt(0);
            String materialType = strings[1];


            if (specialItems.containsKey(materialType.toUpperCase())) {

                choice = new RecipeChoice.ExactChoice(specialItems.get(materialType.toUpperCase()));

            } else if (Material.getMaterial(materialType.toUpperCase()) != null) {

                choice = new RecipeChoice.MaterialChoice(Material.getMaterial(materialType.toUpperCase()));

            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage("Vanilla Bosses: Couldnt find recipeIngredient " + materialType);
                return;
            }

            blazer.setIngredient(recipeKey, choice);
        }

        Bukkit.addRecipe(blazer);
    }

    public static void heatedMagmaCreamRecipeLv2() {

        NamespacedKey key = new NamespacedKey(Main.getInstance(), "heatedCreamFirst");
        ShapedRecipe heatedCreamFirst = new ShapedRecipe(key, HeatedMagmaCream.makeHeatedMagmaCream(2));

        heatedCreamFirst.shape("AAA", "   ", "   ");
        heatedCreamFirst.setIngredient('A',new RecipeChoice.ExactChoice(specialItems.get("HeatedCream1")));

        Bukkit.addRecipe(heatedCreamFirst);
    }

    public static void heatedMagmaCreamRecipeLv3() {

        NamespacedKey key = new NamespacedKey(Main.getInstance(), "heatedCreamSecond");
        ShapedRecipe heatedCreamSecond = new ShapedRecipe(key, HeatedMagmaCream.makeHeatedMagmaCream(3));

        heatedCreamSecond.shape("AAA", "   ", "   ");
        heatedCreamSecond.setIngredient('A',new RecipeChoice.ExactChoice(specialItems.get("HeatedCream2")));

        Bukkit.addRecipe(heatedCreamSecond);
    }
}
