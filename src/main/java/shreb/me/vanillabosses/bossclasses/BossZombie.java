package shreb.me.vanillabosses.bossclasses;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.items.BaseballBat;
import shreb.me.vanillabosses.listeners.EntitySpawnEvent;
import shreb.me.vanillabosses.main.Main;

import java.util.Objects;


public class BossZombie implements Listener {

    static Configuration config = Main.getInstance().getConfig();

    static ItemStack weapon = BaseballBat.makeBaseballBat();

    /**
     * spawns the boss as a new Entity
     *
     * @param location specifies the location in which the boss will spawn
     */

    public static Zombie makeBossZombie(Location location, World w) {

        Zombie zombie = (Zombie) w.spawnEntity(location, EntityType.ZOMBIE);

        zombie.addScoreboardTag("BossZombie");
        zombie.getScoreboardTags().add("VanillaBossesDamageTracker");
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.ZOMBIE.health);
        zombie.setHealth(Bosses.ZOMBIE.health);
        zombie.setCustomName(net.md_5.bungee.api.ChatColor.of(Bosses.ZOMBIE.nameColor) + Bosses.ZOMBIE.displayName);
        zombie.setCustomNameVisible(config.getBoolean("Bosses.ZombieBoss.showDisplayNameAlways"));

        zombie.getEquipment().setItemInMainHand(weapon);
        zombie.getEquipment().setItemInMainHandDropChance((float) config.getDouble("Items.BaseballBat.dropChance"));

        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);

        ItemStack[] armor = {boots, legs, chest, helmet};

        for (ItemStack itemStack : armor) {
            itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
            itemStack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        }

        zombie.getEquipment().setArmorContents(armor);

        zombie.getEquipment().setHelmetDropChance(0);
        zombie.getEquipment().setChestplateDropChance(0);
        zombie.getEquipment().setLeggingsDropChance(0);
        zombie.getEquipment().setBootsDropChance(0);
        if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

        return zombie;
    }

    /**
     * edits an existing mob into the boss version.
     *
     * @param zombie the entity which to edit into the boss version.
     */

    public static void editToBossZombie(Zombie zombie) {

        zombie.setAdult();

        zombie.addScoreboardTag("BossZombie");
        zombie.getScoreboardTags().add("VanillaBossesDamageTracker");
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.ZOMBIE.health);
        zombie.setHealth(Bosses.ZOMBIE.health);
        zombie.setCustomName(ChatColor.of(Bosses.ZOMBIE.nameColor) + Bosses.ZOMBIE.displayName);
        zombie.setCustomNameVisible(config.getBoolean("Bosses.ZombieBoss.showDisplayNameAlways"));

        zombie.getEquipment().setItemInMainHand(weapon);
        zombie.getEquipment().setItemInMainHandDropChance((float) config.getDouble("Items.BaseballBat.dropChance"));

        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);

        ItemStack[] armor = {boots, legs, chest, helmet};

        for (ItemStack itemStack : armor) {
            itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
            itemStack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        }

        zombie.getEquipment().setArmorContents(armor);

        zombie.getEquipment().setHelmetDropChance(0);
        zombie.getEquipment().setChestplateDropChance(0);
        zombie.getEquipment().setLeggingsDropChance(0);
        zombie.getEquipment().setBootsDropChance(0);
        if(config.getBoolean("Bosses.bossesGetGlowingPotionEffect")){
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
        }

    }


    public static void zombieHorde(int radius, int amountOfZombies, Location center) {

        Vector v = new Vector(radius, 0, 0);  //set the starting vector for spawning

        double degrees = 360.0 / amountOfZombies; //get the degrees every spawnpoint has to be different by to make a circle

        EntitySpawnEvent.spawn = false;

        for (int i = 0; i < amountOfZombies; i++) {

            center.add(v);

            Zombie zombie = (Zombie) Objects.requireNonNull(center.getWorld()).spawnEntity(center.add(0, 2, 0), EntityType.ZOMBIE);

            center.subtract(0, 2, 0);

            zombie.getScoreboardTags().add("NotABoss");

            zombie.setAdult();

            zombie.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            zombie.getEquipment().setHelmetDropChance(0);

            center.subtract(v);

            v.rotateAroundY(degrees);

        }
        EntitySpawnEvent.spawn = true;
    }
}
