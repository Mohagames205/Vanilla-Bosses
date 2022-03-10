package shreb.me.vanillabosses.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;


public class BaseballBat {

    public static ItemStack makeBaseballBat(){

        ItemStack baseballBat = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = baseballBat.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Baseball Bat");

        PersistentDataContainer container = meta.getPersistentDataContainer();

        container.set(Items.BASEBALLBAT.identifyingPDCKey, PersistentDataType.STRING, "Concuss I");

        lore.add("Concuss I");
        lore.addAll(Main.getInstance().getConfig().getStringList("Items.BaseballBat.Lore"));

        meta.setLore(lore);

        baseballBat.setItemMeta(meta);

        baseballBat.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
        baseballBat.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        baseballBat.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);

        return baseballBat;
    }
}
