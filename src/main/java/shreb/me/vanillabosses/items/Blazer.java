package shreb.me.vanillabosses.items;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

import java.util.ArrayList;

public class Blazer implements Listener {

    public static ItemStack makeBlazer(){

        if(Material.getMaterial(Main.getInstance().getConfig().getString("Items.Blazer.Chestplate").toUpperCase()) == null) {

            Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "VanillaBosses Error: Blazer Chestplate type could not be found");
            return null;
        }

        ItemStack blazer = new ItemStack(Material.getMaterial(Main.getInstance().getConfig().getString("Items.Blazer.Chestplate").toUpperCase()));

        if(blazer.getType() == Material.LEATHER_CHESTPLATE){
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) blazer.getItemMeta();
            leatherArmorMeta.setColor(Color.BLUE);
            blazer.setItemMeta(leatherArmorMeta);
        }

        ItemMeta armorMeta = blazer.getItemMeta();
        PersistentDataContainer container = armorMeta.getPersistentDataContainer();
        container.set(new NamespacedKey(Main.getInstance(), "BlazerOnHit"), PersistentDataType.INTEGER, 1);

        armorMeta.setDisplayName(ChatColor.DARK_BLUE + "Blazer");
        ArrayList<String> lore = new ArrayList<>();
        for(String s: Main.getInstance().getConfig().getStringList("Items.Blazer.Lore")){
            if(Main.getInstance().getConfig().getStringList("Items.Blazer.Lore").indexOf(s) == 0){
                lore.add(ChatColor.DARK_RED + "" + ChatColor.BOLD + s);
            } else {
                lore.add(s);
            }
        }
        armorMeta.setLore(lore);

        blazer.setItemMeta(armorMeta);

        return blazer;
    }

    @EventHandler
    public void onBlazerHit(EntityDamageByEntityEvent event){

        if(event.getEntityType().isAlive() && event.getDamager().getType().isAlive()){

            LivingEntity dEntity = (LivingEntity) event.getDamager();
            LivingEntity entity = (LivingEntity) event.getEntity();
            if(entity.getEquipment().getChestplate() != null){
                if(!entity.getEquipment().getChestplate().hasItemMeta()) return;
                if(!entity.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "BlazerOnHit"), PersistentDataType.INTEGER)) return;

                int rn = Methods.randomNumber(0,100);
                int chance = Main.getInstance().getConfig().getInt("Items.Blazer.chanceToCombust");

                if(rn < chance) {

                    dEntity.setFireTicks(Main.getInstance().getConfig().getInt("Items.Blazer.ticksOfFire"));

                }
            }
        }
    }
}
