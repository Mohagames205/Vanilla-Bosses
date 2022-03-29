/**
package shreb.me.vanillabosses.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import shreb.me.vanillabosses.items.*;

import java.util.Arrays;

public class ConfigGUI implements Listener {

    public ConfigGUI() {

        new GUIOpener();
        // Put the items into the inventory
        initializeItems();
    }

    public void initializeItems() {


    }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    // You can open the inventory with this
    public static void openInventory(final HumanEntity ent) {
        ent.openInventory(GUIOpener.inventory1);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(GUIOpener.inventory1)
                && !GUIOpener.inventory1Map.containsValue(e.getInventory())
                && !GUIOpener.bossInventoryMap.containsValue(e.getInventory())
                && !GUIOpener.itemInventoryMap.containsValue(e.getInventory())
                && !GUIOpener.generalInventoryMap.containsValue(e.getInventory())
        ) return;

        if(GUIOpener.inventory1Map.containsValue(e.getInventory())) System.out.println("CP1");

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();

        final int clickedSlot = e.getRawSlot();

        GUIOpener.openRelatedInventory(e);
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (GUIOpener.inventory1Map.containsValue(e.getInventory())
                || GUIOpener.bossInventoryMap.containsValue(e.getInventory())
                || GUIOpener.itemInventoryMap.containsValue(e.getInventory())
                || GUIOpener.generalInventoryMap.containsValue(e.getInventory())) {
            e.setCancelled(true);
        }
    }
}
*/