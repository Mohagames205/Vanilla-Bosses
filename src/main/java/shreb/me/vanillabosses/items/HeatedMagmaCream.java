package shreb.me.vanillabosses.items;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

public class HeatedMagmaCream implements Listener {
    ItemStack magmaCream;
    int level;
    /**
     *
     * @param level has to be 1, 2 or 3 to indicate the level of the created HeatedMagmaCream
     * @throws IllegalArgumentException is thrown when values except 1, 2 or 3 are entered
     */
    public HeatedMagmaCream(int level) throws IllegalArgumentException {

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
        cream.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "MagmaCreamLevel"), PersistentDataType.INTEGER, level);

        magmaCream = cream;
        this.level = level;
    }

    public HeatedMagmaCream() {

    }

    public ItemStack getMagmaCream() {
        return magmaCream;
    }

    public int getLevel() {
        return level;
    }

    @EventHandler
    public void onMagmaCreamUse(PlayerInteractEvent event){

        if(event.getItem() == null || event.getHand() == null) return;

        if(event.getItem().getType() == Material.MAGMA_CREAM
        && event.getItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "MagmaCreamLevel"), PersistentDataType.INTEGER)
        ){

            if( event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getInstance(), "MagmaCreamLevel"), PersistentDataType.INTEGER) == null) return;

            ItemStack cream = event.getItem();
            int level = 0;
            Player player = event.getPlayer();
            Location loc = event.getPlayer().getLocation();
            int radius;

            try{
            level = cream.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getInstance(), "MagmaCreamLevel"), PersistentDataType.INTEGER);
            } catch(NullPointerException e){
                e.printStackTrace();
                Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "VanillaBosses: Error 1 on HeatedMagmaCream use. Please notify the Author of the plugin about this");
            }

            switch (level){

                case 1:
                    radius = 3;
                    break;

                case 2:
                    radius = 5;
                    break;

                case 3:
                    radius = 7;
                    break;

                default:
                    Main.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "VanillaBosses: Error 2 on HeatedMagmaCream use. Please notify the Author of the plugin about this");
                    return;
            }

            Methods.spawnParticles(Particle.SMALL_FLAME, player.getWorld(), loc,  radius, radius, radius, 100, 3);

            for(Entity e: player.getWorld().getNearbyEntities(loc, radius, radius, radius, n -> n instanceof LivingEntity)){
                e.setFireTicks(40 * level);
            }
        }
    }
}
