package shreb.me.vanillabosses.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shreb.me.vanillabosses.bossclasses.Bosses;
import shreb.me.vanillabosses.bossclasses.RespawningBosses;
import shreb.me.vanillabosses.main.Main;

public class BossEggs implements Listener {

    public static final NamespacedKey bossEggIdentifier = new NamespacedKey(Main.getInstance(), "BossEgg");

    /**
     * This method will return an Item stack containing the specified amount of Bosseggs of the specified type.
     * It will name the Item to clarify that it is indeed a boss egg and add the BossEggs.bossEggIdentifier to the PDC of the ItemStack.
     * The PersistentDataType of this is String, the value is the specified type.toString().
     *
     * @param type The type of Boss the egg should summon
     * @param amount The amount of eggs in the ItemStack which is returned
     * @return an ItemStack with the specified amount of eggs with the specified type
     * @throws IllegalArgumentException if amount is not a number from 1-64 and if the type given is not a
     */

    @SuppressWarnings("ConstantConditions")
    public static ItemStack makeBossEgg(EntityType type, int amount) throws IllegalArgumentException{

        if(amount > 64 || amount < 1){
            throw new IllegalArgumentException("Cannot make " + amount + " Boss Eggs. Must be a number between 1 and 64.");
        }

        ItemStack egg;
        ChatColor color;

        switch (type) {

            case BLAZE:
                egg = new ItemStack(Material.BLAZE_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.BLAZE.nameColor);
                break;

            case CREEPER:
                egg = new ItemStack(Material.CREEPER_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.CREEPER.nameColor);
                break;

            case ENDERMAN:
                egg = new ItemStack(Material.ENDERMAN_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.ENDERMAN.nameColor);
                break;

            case MAGMA_CUBE:
                egg = new ItemStack(Material.MAGMA_CUBE_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.MAGMA_CUBE.nameColor);
                break;

            case SKELETON:
                egg = new ItemStack(Material.SKELETON_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.SKELETON.nameColor);
                break;

            case SLIME:
                egg = new ItemStack(Material.SLIME_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.SLIME.nameColor);
                break;

            case SPIDER:
                egg = new ItemStack(Material.SPIDER_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.BLAZE.nameColor);
                break;

            case WITCH:
                egg = new ItemStack(Material.WITCH_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.WITCH.nameColor);
                break;

            case ZOMBIE:
                egg = new ItemStack(Material.ZOMBIE_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.ZOMBIE.nameColor);
                break;

            case ZOMBIFIED_PIGLIN:
                egg = new ItemStack(Material.ZOMBIFIED_PIGLIN_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.ZOMBIFIED_PIGLIN.nameColor);
                break;

            case WITHER:
                egg = new ItemStack(Material.BAT_SPAWN_EGG, amount);
                color = ChatColor.valueOf(Bosses.WITHER.nameColor);
                ItemMeta meta = egg.getItemMeta();
                meta.setDisplayName("Wither Spawn Egg");
                egg.setItemMeta(meta);
                break;

            default:
                throw new IllegalArgumentException("Tried to make an unknown type of boss egg");
        }

        ItemMeta meta = egg.getItemMeta();
        meta.setDisplayName(meta.getDisplayName() + color + "{Boss}");
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(bossEggIdentifier, PersistentDataType.STRING, type.toString());
        egg.setItemMeta(meta);

        return egg;
    }


    @EventHandler
    public void onPlayerUseBossEgg(PlayerInteractEvent event){

        if(event.getAction() != Action.RIGHT_CLICK_BLOCK || !event.hasItem() || !event.hasBlock()) return;

        ItemStack itemStack = event.getItem();

        if(itemStack == null || !itemStack.hasItemMeta()) return;

        if(itemStack.getItemMeta().getPersistentDataContainer().has(bossEggIdentifier, PersistentDataType.STRING)){

            EntityType type = EntityType.valueOf(itemStack.getItemMeta().getPersistentDataContainer().get(bossEggIdentifier, PersistentDataType.STRING));

            if(event.getHand() == null || event.getPlayer().getEquipment() == null) return;

            switch(event.getBlockFace()){

                case UP:
                    RespawningBosses.bossSpawnMethod(type, event.getClickedBlock().getLocation().add(0,1,0), event.getClickedBlock().getWorld());
                    break;

                case DOWN:
                    RespawningBosses.bossSpawnMethod(type, event.getClickedBlock().getLocation().add(0,-2,0), event.getClickedBlock().getWorld());
                    break;

                case EAST:
                    RespawningBosses.bossSpawnMethod(type, event.getClickedBlock().getLocation().add(1,0,0), event.getClickedBlock().getWorld());
                    break;

                case WEST:
                    RespawningBosses.bossSpawnMethod(type, event.getClickedBlock().getLocation().add(-1,0,0), event.getClickedBlock().getWorld());
                    break;

                case NORTH:
                    RespawningBosses.bossSpawnMethod(type, event.getClickedBlock().getLocation().add(0,0,1), event.getClickedBlock().getWorld());
                    break;

                case SOUTH:
                    RespawningBosses.bossSpawnMethod(type, event.getClickedBlock().getLocation().add(0,0,-1), event.getClickedBlock().getWorld());
                    break;

                default:
                    event.getPlayer().sendMessage("This won't work...");
                    return;

            }

            event.getPlayer().getEquipment().getItem(event.getHand()).setAmount(event.getPlayer().getEquipment().getItem(event.getHand()).getAmount() - 1);
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.GREEN + "The Boss has been summoned! Good luck...");

        }

    }


}
