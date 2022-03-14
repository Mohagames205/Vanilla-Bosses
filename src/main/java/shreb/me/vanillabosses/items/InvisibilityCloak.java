package shreb.me.vanillabosses.items;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;
import java.util.Objects;

public class InvisibilityCloak {


    public static void invisCloakTimer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {

            for (Player p : Main.getInstance().getServer().getOnlinePlayers()) {

                ItemStack[] armor = p.getEquipment().getArmorContents();
                if (armor[2] != null && p.getEquipment().getChestplate().hasItemMeta()) {
                    if (Objects.requireNonNull(p.getEquipment().getItem(EquipmentSlot.CHEST).getItemMeta()).getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "CloakOfInvisibility"), PersistentDataType.INTEGER)) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, (Main.getInstance().getConfig().getInt("Items.cloakOfInvisibility.delayBetweenChecks") + 1) * 20, 0));
                        if (Main.getInstance().getConfig().getBoolean("Items.cloakOfInvisibility.enableDamageCloakEachCheck") && Main.getInstance().getConfig().getInt("Items.cloakOfInvisibility.damagePerCheckAmount") > 0) {

                            ItemMeta meta = p.getEquipment().getChestplate().getItemMeta();
                            int nextDamage = ((Damageable) meta).getDamage() + Main.getInstance().getConfig().getInt("Items.cloakOfInvisibility.damagePerCheckAmount");
                            ((Damageable) meta).setDamage(nextDamage);
                            p.getEquipment().getItem(EquipmentSlot.CHEST).setItemMeta(meta);

                            if (((Damageable) meta).getDamage() > p.getEquipment().getChestplate().getType().getMaxDurability()) {      //Item breaking upon reaching 0 Durability
                                p.getEquipment().setChestplate(new ItemStack(Material.AIR));
                                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                            }

                        }
                    }
                }
            }
        }, 50L, 20L * Main.getInstance().getConfig().getInt("Items.cloakOfInvisibility.delayBetweenChecks"));
    }

    public static ItemStack makeCloak() {

        ItemStack cloak = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta meta = cloak.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.addAll(Main.getInstance().getConfig().getStringList("Items.cloakOfInvisibility.Lore"));
        meta.setDisplayName(ChatColor.GRAY + Main.getCurrentLanguage().itemInvisibilityCloakName);
        meta.setLore(lore);
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(Items.INVISIBILITYCLOAK.identifyingPDCKey, PersistentDataType.INTEGER, 1);

        cloak.setItemMeta(meta);

        return cloak;
    }
}
