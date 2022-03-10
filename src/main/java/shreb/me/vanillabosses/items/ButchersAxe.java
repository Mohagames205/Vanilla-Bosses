package shreb.me.vanillabosses.items;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;

public class ButchersAxe implements Listener {

    static Configuration config = Main.getInstance().getConfig();


    public static ItemStack makeButchersAxe() {

        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);

        axe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        axe.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        axe.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);

        ItemMeta meta = axe.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(Items.BUTCHERSAXE.identifyingPDCKey, PersistentDataType.STRING, "Bind II");

        ArrayList<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.DARK_RED + "The Butchers Axe");
        lore.add("Bind II");
        lore.addAll(config.getStringList("Items.ButchersAxe.Lore"));
        meta.setLore(lore);
        axe.setItemMeta(meta);

        return axe;

    }

}
