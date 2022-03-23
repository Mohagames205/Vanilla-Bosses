package shreb.me.vanillabosses.bossclasses;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shreb.me.vanillabosses.items.Skeletor;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

public class BossSkeleton implements Listener {

    static Configuration config = Main.getInstance().getConfig();

    /**
     * spawns the boss as a new Entity
     * @param location specifies the location in which the boss will spawn
     */

    public static Skeleton makeBossSkeleton(Location location, World w) {

        Skeleton skeleton = (Skeleton) w.spawnEntity(location, EntityType.SKELETON);

        skeleton.addScoreboardTag("BossSkeleton");
        skeleton.getScoreboardTags().add("VanillaBossesDamageTracker");
        skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.SKELETON.health);
        skeleton.setHealth(Bosses.SKELETON.health);
        skeleton.addScoreboardTag("removeInvulnerableOnDisable");
        skeleton.setCustomName(ChatColor.of(Bosses.SKELETON.nameColor) + Bosses.SKELETON.displayName);
        skeleton.setCustomNameVisible(config.getBoolean("Bosses.SkeletonBoss.showDisplayNameAlways"));
        EntityEquipment equipment = skeleton.getEquipment();
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack bow = Skeletor.makeSkeletor();

        int minProt = config.getInt("Bosses.SkeletonBoss.ProtectionMin");
        int maxProt = config.getInt("Bosses.SkeletonBoss.ProtectionMax");

        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Methods.randomNumber(minProt, maxProt));
        helmet.addUnsafeEnchantment(Enchantment.DURABILITY, Methods.randomNumber(10, 15));

        chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Methods.randomNumber(minProt, maxProt));
        chest.addUnsafeEnchantment(Enchantment.DURABILITY, Methods.randomNumber(5, 15));

        legs.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Methods.randomNumber(minProt, maxProt));
        legs.addUnsafeEnchantment(Enchantment.DURABILITY, Methods.randomNumber(5, 15));

        boots.addUnsafeEnchantment(Enchantment.DURABILITY, Methods.randomNumber(5, 15));
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Methods.randomNumber(minProt, maxProt));
        boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, Methods.randomNumber(1, 3));

        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, Methods.randomNumber(5, 10));
        bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, Methods.randomNumber(5, 10));
        bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, Methods.randomNumber(3, 10));

        equipment.setHelmet(helmet);
        equipment.setChestplate(chest);
        equipment.setLeggings(legs);
        equipment.setBoots(boots);
        equipment.setItemInMainHand(bow);

        float f;
        f = (float) config.getDouble("Bosses.SkeletonBoss.dropArmorChance");

        equipment.setBootsDropChance(f);
        equipment.setLeggingsDropChance(f);
        equipment.setChestplateDropChance(f);
        equipment.setHelmetDropChance(f);
        equipment.setItemInMainHandDropChance( (float) config.getDouble("Items.Skeletor.dropChance"));


        int rn = Methods.randomNumber(0, 100);

        if (rn <= 20) {
            skeleton.getEquipment().setItemInOffHand(new ItemStack(Material.NETHERITE_INGOT, Methods.randomNumber(1, 2)));
        }

        if (rn <= 40 && rn > 20) {
            skeleton.getEquipment().setItemInOffHand(new ItemStack(Material.EXPERIENCE_BOTTLE, 64));
        }

        if (rn <= 60 && rn > 40) {
            skeleton.getEquipment().setItemInOffHand(new ItemStack(Material.DIAMOND, Methods.randomNumber(8, 16)));
        }
        if (rn <= 80 && rn > 60) {

            ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
            axe.addEnchantment(Enchantment.DURABILITY, 3);
            axe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 7);
            axe.addEnchantment(Enchantment.DAMAGE_ALL, 5);

            skeleton.getEquipment().setItemInOffHand(axe);

        }
        f = (float) config.getDouble("Bosses.SkeletonBoss.dropOffHandChance");

        skeleton.getEquipment().setItemInOffHandDropChance(f);

        if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            skeleton.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

        return skeleton;
    }


    /**
     * edits an existing mob into the boss version.
     * @param skeleton the entity which to edit into the boss version.
     */


    public static void editToBossSkeleton(Skeleton skeleton) {


        skeleton.addScoreboardTag("BossSkeleton");
        skeleton.getScoreboardTags().add("VanillaBossesDamageTracker");
        skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.SKELETON.health);
        skeleton.setHealth(Bosses.SKELETON.health);
        skeleton.addScoreboardTag("removeInvulnerableOnDisable");
        skeleton.setCustomName(net.md_5.bungee.api.ChatColor.of(Bosses.SKELETON.nameColor) + Bosses.SKELETON.displayName);
        skeleton.setCustomNameVisible(config.getBoolean("Bosses.SkeletonBoss.showDisplayNameAlways"));

        EntityEquipment equipment = skeleton.getEquipment();

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack bow = Skeletor.makeSkeletor();

        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Methods.randomNumber(5, 15));
        helmet.addUnsafeEnchantment(Enchantment.DURABILITY, Methods.randomNumber(10, 15));

        chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Methods.randomNumber(5, 15));
        chest.addUnsafeEnchantment(Enchantment.DURABILITY, Methods.randomNumber(5, 15));

        legs.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Methods.randomNumber(5, 15));
        legs.addUnsafeEnchantment(Enchantment.DURABILITY, Methods.randomNumber(5, 15));

        boots.addUnsafeEnchantment(Enchantment.DURABILITY, Methods.randomNumber(5, 15));
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Methods.randomNumber(5, 15));
        boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, Methods.randomNumber(1, 3));

        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, Methods.randomNumber(5, 10));
        bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, Methods.randomNumber(5, 10));
        bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, Methods.randomNumber(3, 10));

        equipment.setHelmet(helmet);
        equipment.setChestplate(chest);
        equipment.setLeggings(legs);
        equipment.setBoots(boots);
        equipment.setItemInMainHand(bow);

        float f;
        f = (float) config.getDouble("Bosses.SkeletonBoss.dropArmorChance");

        equipment.setBootsDropChance(f);
        equipment.setLeggingsDropChance(f);
        equipment.setChestplateDropChance(f);
        equipment.setHelmetDropChance(f);
        equipment.setItemInMainHandDropChance( (float) config.getDouble("Items.Skeletor.dropChance"));

        int rn = Methods.randomNumber(0, 100);

        if (rn <= 20) {
            skeleton.getEquipment().setItemInOffHand(new ItemStack(Material.NETHERITE_INGOT, Methods.randomNumber(1, 2)));
        }

        if (rn <= 40 && rn > 20) {
            skeleton.getEquipment().setItemInOffHand(new ItemStack(Material.EXPERIENCE_BOTTLE, 64));
        }

        if (rn <= 60 && rn > 40) {
            skeleton.getEquipment().setItemInOffHand(new ItemStack(Material.DIAMOND, Methods.randomNumber(8, 16)));
        }
        if (rn <= 80 && rn > 60) {

            ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
            axe.addEnchantment(Enchantment.DURABILITY, 3);
            axe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 7);
            axe.addEnchantment(Enchantment.DAMAGE_ALL, 5);

            skeleton.getEquipment().setItemInOffHand(axe);

        }
        f = (float) config.getDouble("Bosses.SkeletonBoss.dropOffHandChance");

        skeleton.getEquipment().setItemInOffHandDropChance(f);

        if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            skeleton.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

    }
}


















