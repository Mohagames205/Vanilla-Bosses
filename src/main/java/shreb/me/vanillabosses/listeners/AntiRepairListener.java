package shreb.me.vanillabosses.listeners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.items.Items;

import java.util.ArrayList;

public class AntiRepairListener implements Listener {

    static ArrayList<NamespacedKey> keys = new ArrayList<>();

    static {
        keys.add(Items.BASEBALLBAT.identifyingPDCKey);
        keys.add(Items.BLAZER.identifyingPDCKey);
        keys.add(Items.BUTCHERSAXE.identifyingPDCKey);
        keys.add(Items.INVISIBILITYCLOAK.identifyingPDCKey);
        keys.add(Items.SKELETOR.identifyingPDCKey);
        keys.add(Items.SLIMEBOOTS.identifyingPDCKey);
        keys.add(Items.SLINGSHOT.identifyingPDCKey);
    }

    @EventHandler
    public void onAnvilRepairPrep(PrepareAnvilEvent event) {

        ItemStack item1 = event.getInventory().getContents()[0];
        ItemStack item2 = event.getInventory().getContents()[1];

        if (item1 == null && item2 == null) return;

//if item1 and item2 aren't null
        if (item1 != null && item1.hasItemMeta() && item2 != null && (

                //and item1 is a plugin item
                item1.getItemMeta().getPersistentDataContainer().has(Items.BASEBALLBAT.identifyingPDCKey, PersistentDataType.STRING) ||

                        item1.getItemMeta().getPersistentDataContainer().has(Items.BLAZER.identifyingPDCKey, PersistentDataType.INTEGER) ||

                        item1.getItemMeta().getPersistentDataContainer().has(Items.BUTCHERSAXE.identifyingPDCKey, PersistentDataType.STRING) ||

                        item1.getItemMeta().getPersistentDataContainer().has(Items.INVISIBILITYCLOAK.identifyingPDCKey, PersistentDataType.INTEGER) ||

                        item1.getItemMeta().getPersistentDataContainer().has(Items.SKELETOR.identifyingPDCKey, PersistentDataType.STRING) ||

                        item1.getItemMeta().getPersistentDataContainer().has(Items.SLIMEBOOTS.identifyingPDCKey, PersistentDataType.STRING) ||

                        item1.getItemMeta().getPersistentDataContainer().has(Items.SLINGSHOT.identifyingPDCKey, PersistentDataType.INTEGER))

                &&

                //and item2 is not an enchanted book
                item2.getType() != Material.ENCHANTED_BOOK

                &&

                !(
                item2.getItemMeta().getPersistentDataContainer().has(Items.BASEBALLBAT.identifyingPDCKey, PersistentDataType.STRING) ||

                item2.getItemMeta().getPersistentDataContainer().has(Items.BLAZER.identifyingPDCKey, PersistentDataType.INTEGER) ||

                item2.getItemMeta().getPersistentDataContainer().has(Items.BUTCHERSAXE.identifyingPDCKey, PersistentDataType.STRING) ||

                item2.getItemMeta().getPersistentDataContainer().has(Items.INVISIBILITYCLOAK.identifyingPDCKey, PersistentDataType.INTEGER) ||

                item2.getItemMeta().getPersistentDataContainer().has(Items.SKELETOR.identifyingPDCKey, PersistentDataType.STRING) ||

                item2.getItemMeta().getPersistentDataContainer().has(Items.SLIMEBOOTS.identifyingPDCKey, PersistentDataType.STRING) ||

                item2.getItemMeta().getPersistentDataContainer().has(Items.SLINGSHOT.identifyingPDCKey, PersistentDataType.INTEGER))


        ) {
            // prevent anvil action
            event.getInventory().setRepairCost(0);
            event.setResult(null);
        }

    }
}
