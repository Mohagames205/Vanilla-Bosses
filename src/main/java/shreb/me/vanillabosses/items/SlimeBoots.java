package shreb.me.vanillabosses.items;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;

public class SlimeBoots {

    public static ItemStack makeBouncySlime(){

        ItemStack bouncySlime = new ItemStack(Material.SLIME_BALL);
        ItemMeta meta = bouncySlime.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        ArrayList<String> lore = new ArrayList<>(Main.getInstance().getConfig().getStringList("Items.SlimeBoots.BouncySlime.Lore"));
        meta.setLore(lore);
        container.set(Items.BOUNCYSLIME.identifyingPDCKey, PersistentDataType.STRING, "bouncySlime");
        meta.setDisplayName(ChatColor.DARK_GREEN + Main.getCurrentLanguage().itemBouncySlimeName);
        bouncySlime.setItemMeta(meta);

        return bouncySlime;
    }

    public static ItemStack replaceBouncySlime(ItemStack stack){

        if(!stack.hasItemMeta() && !stack.getItemMeta().getPersistentDataContainer().has(Items.BOUNCYSLIME.identifyingPDCKey, PersistentDataType.STRING)) return stack;

        int amount = stack.getAmount();
        stack = makeBouncySlime();
        stack.setAmount(amount);
        return stack;

    }

    public static ItemStack makeSlimeBoots(){

        ItemStack slimeBoots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) slimeBoots.getItemMeta();
        meta.setColor(Color.GREEN);
        meta.setDisplayName(ChatColor.DARK_GREEN + Main.getCurrentLanguage().itemSlimeBootsName);
        ArrayList<String> lore = new ArrayList<>();
        lore.addAll(Main.getInstance().getConfig().getStringList("Items.SlimeBoots.Lore"));

        meta.setLore(lore);
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(Items.SLIMEBOOTS.identifyingPDCKey, PersistentDataType.STRING, "negateFallDamage");
        slimeBoots.setItemMeta(meta);

        return slimeBoots;
    }

}
