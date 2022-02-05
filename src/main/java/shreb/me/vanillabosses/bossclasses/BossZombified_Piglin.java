package shreb.me.vanillabosses.bossclasses;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import shreb.me.vanillabosses.items.ButchersAxe;
import shreb.me.vanillabosses.main.Main;

public class BossZombified_Piglin  implements Listener {

    static Configuration config = Main.getInstance().getConfig();

    /**
     * spawns the boss as a new Entity
     * @param location specifies the location in which the boss will spawn
     */

    public static PigZombie makeBossZombified_Piglin(Location location, World w) {

        PigZombie pigzombie = (PigZombie) w.spawnEntity(location, EntityType.ZOMBIFIED_PIGLIN);

        pigzombie.addScoreboardTag("BossZombified_Piglin");
        pigzombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(config.getDouble("Bosses.Zombified_PiglinBoss.health"));
        pigzombie.setHealth(pigzombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        pigzombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(pigzombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() * config.getDouble("Bosses.Zombified_PiglinBoss.DamageModifier"));
        pigzombie.setCustomName(config.getString("Bosses.Zombified_PiglinBoss.displayName"));
        pigzombie.setCustomNameVisible(config.getBoolean("Bosses.Zombified_PiglinBoss.showDisplayNameAlways"));

        pigzombie.getEquipment().setItemInMainHand(ButchersAxe.makeButchersAxe());


        pigzombie.setAngry(true);
        pigzombie.setAnger(Integer.MAX_VALUE);

        pigzombie.getEquipment().setItemInMainHandDropChance((float) config.getDouble("Items.ButchersAxe.dropChance"));

        ItemStack helmet = new ItemStack(Material.GOLDEN_HELMET);
        ItemStack chest = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);

        ItemStack[] armor = {boots, legs, chest, helmet};

        for (ItemStack itemStack : armor) {
            itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
            itemStack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        }


        pigzombie.getEquipment().setArmorContents(armor);
        return pigzombie;
    }

    /**
     * edits an existing mob into the boss version.
     * @param pigzombie the entity which to edit into the boss version.
     */

    public static void editToBossZombified_Piglin(PigZombie pigzombie) {


        pigzombie.addScoreboardTag("BossZombified_Piglin");
        pigzombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(config.getDouble("Bosses.Zombified_PiglinBoss.health"));
        pigzombie.setHealth(pigzombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        pigzombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(pigzombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() * config.getDouble("Bosses.Zombified_PiglinBoss.DamageModifier"));
        pigzombie.setCustomName(config.getString("Bosses.Zombified_PiglinBoss.displayName"));
        pigzombie.setCustomNameVisible(config.getBoolean("Bosses.Zombified_PiglinBoss.showDisplayNameAlways"));

        pigzombie.getEquipment().setItemInMainHand(ButchersAxe.makeButchersAxe());


        pigzombie.setAngry(true);
        pigzombie.setAnger(Integer.MAX_VALUE);

        pigzombie.getEquipment().setItemInMainHandDropChance((float) config.getDouble("Items.ButchersAxe.dropChance"));

        ItemStack helmet = new ItemStack(Material.GOLDEN_HELMET);
        ItemStack chest = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);

        ItemStack[] armor = {boots, legs, chest, helmet};

        for (ItemStack itemStack : armor) {
            itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
            itemStack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        }

        pigzombie.getEquipment().setArmorContents(armor);
    }
}
