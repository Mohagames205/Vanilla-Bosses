package shreb.me.vanillabosses.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.main.Main;

public class AntiRepairListener implements Listener {

    @EventHandler
    public void onAnvilRepairPrep(PrepareAnvilEvent event) {

        ItemStack item1 = event.getInventory().getContents()[0];
        ItemStack item2 = event.getInventory().getContents()[1];

        if (item1 == null && item2 == null) return;

        if (item1 != null && item1.hasItemMeta() && (

                item1.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "ConcussI"), PersistentDataType.STRING) ||

                        item1.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "BlazerOnHit"), PersistentDataType.INTEGER) ||

                        item1.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "BindII"), PersistentDataType.STRING) ||

                        item1.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "CloakOfInvisibility"), PersistentDataType.INTEGER) ||

                        item1.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "shootsTnT"), PersistentDataType.STRING) ||

                        item1.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "slimeBoots"), PersistentDataType.STRING) ||

                        item1.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "Slingshot"), PersistentDataType.INTEGER))

        ) {
            event.getInventory().setRepairCost(0);
            event.setResult(null);
        }

        if (item2 != null && item2.hasItemMeta() && (

                item2.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "ConcussI"), PersistentDataType.STRING) ||

                        item2.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "BlazerOnHit"), PersistentDataType.INTEGER) ||

                        item2.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "BindII"), PersistentDataType.STRING) ||

                        item2.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "CloakOfInvisibility"), PersistentDataType.INTEGER) ||

                        item2.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "shootsTnT"), PersistentDataType.STRING) ||

                        item2.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "slimeBoots"), PersistentDataType.STRING) ||

                        item2.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "Slingshot"), PersistentDataType.INTEGER))
        ) {
            event.getInventory().setRepairCost(0);
            event.setResult(null);
        }
    }
}
