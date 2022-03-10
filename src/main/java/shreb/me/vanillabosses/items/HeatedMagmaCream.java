package shreb.me.vanillabosses.items;

import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

public class HeatedMagmaCream implements Listener {

    Configuration config = Main.getInstance().getConfig();

    /**
     *
     * @param level has to be 1, 2 or 3 to indicate the level of the created HeatedMagmaCream
     * @throws IllegalArgumentException is thrown when values except 1, 2 or 3 are entered
     */
    public static ItemStack makeHeatedMagmaCream(int level) throws IllegalArgumentException {

        ItemStack cream = new ItemStack(Material.MAGMA_CREAM);


        switch (level) {

            case 1:
                cream.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
                break;

            case 2:
                cream.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
                break;

            case 3:
                cream.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 3);
                break;

            default:
                throw new IllegalArgumentException(ChatColor.RED + "VanillaBosses: Error with HeatedMagmaCream. Please notify the Author of the plugin about this Error.");
        }
        ItemMeta meta = cream.getItemMeta();
        meta.setDisplayName("Heated Magma Cream Lv." + level);
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(Items.HEATEDMAGMACREAM1.identifyingPDCKey, PersistentDataType.INTEGER, level);
        cream.setItemMeta(meta);

        return cream;
    }

    @EventHandler
    public void onMagmaCreamUse(PlayerInteractEvent event){

        if(event.getItem() == null || event.getHand() == null) return;
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if(event.getItem().getType() == Material.MAGMA_CREAM
        && (event.getItem().getItemMeta().getPersistentDataContainer().has(Items.HEATEDMAGMACREAM1.identifyingPDCKey, PersistentDataType.INTEGER)
        || event.getItem().getItemMeta().getPersistentDataContainer().has(Items.HEATEDMAGMACREAM2.identifyingPDCKey, PersistentDataType.INTEGER)
        || event.getItem().getItemMeta().getPersistentDataContainer().has(Items.HEATEDMAGMACREAM3.identifyingPDCKey, PersistentDataType.INTEGER)
        )){

            ItemStack cream = event.getItem();
            int level;
            Player player = event.getPlayer();
            Location loc = event.getPlayer().getLocation();
            int radius;
            int time;


            if(cream.getItemMeta().getPersistentDataContainer().has(Items.HEATEDMAGMACREAM1.identifyingPDCKey, PersistentDataType.INTEGER)) level = cream.getItemMeta().getPersistentDataContainer().get(Items.HEATEDMAGMACREAM1.identifyingPDCKey, PersistentDataType.INTEGER);
            else if(cream.getItemMeta().getPersistentDataContainer().has(Items.HEATEDMAGMACREAM2.identifyingPDCKey, PersistentDataType.INTEGER)) level = cream.getItemMeta().getPersistentDataContainer().get(Items.HEATEDMAGMACREAM2.identifyingPDCKey, PersistentDataType.INTEGER);
            else if(cream.getItemMeta().getPersistentDataContainer().has(Items.HEATEDMAGMACREAM3.identifyingPDCKey, PersistentDataType.INTEGER)) level = cream.getItemMeta().getPersistentDataContainer().get(Items.HEATEDMAGMACREAM3.identifyingPDCKey, PersistentDataType.INTEGER);
            else{
                return;
            }

            switch (level){

                case 1:
                    radius = Items.HEATEDMAGMACREAM1.radius;
                    time = Items.HEATEDMAGMACREAM1.time;
                    break;

                case 2:
                    radius = Items.HEATEDMAGMACREAM2.radius;
                    time = Items.HEATEDMAGMACREAM2.time;
                    break;

                case 3:
                    radius = Items.HEATEDMAGMACREAM3.radius;
                    time = Items.HEATEDMAGMACREAM3.time;
                    break;

                default:
                    Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "VanillaBosses: Error 2 on HeatedMagmaCream use. Please notify the Author of the plugin about this");
                    return;
            }

            Methods.spawnParticles(Particle.SMALL_FLAME, player.getWorld(), loc,  radius, radius, radius, 150, 3);

            player.getWorld().playSound(loc, Sound.ENTITY_SLIME_SQUISH, 1.0f, 1.0f);

            for(Entity e: player.getWorld().getNearbyEntities(loc, radius, radius, radius, n -> n instanceof LivingEntity && n != player)){
                e.setFireTicks(20 * time);
            }

            if(player.getGameMode() == GameMode.SURVIVAL){
                event.getItem().setAmount(event.getItem().getAmount() - 1);
            }

        }
    }
}
