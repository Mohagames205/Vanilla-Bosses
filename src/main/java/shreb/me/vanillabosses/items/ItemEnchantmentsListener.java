package shreb.me.vanillabosses.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

import java.util.Objects;

public class ItemEnchantmentsListener implements Listener {

    static Configuration config = Main.getInstance().getConfig();

    @EventHandler
    public void onSpecialEnchantHit(EntityDamageByEntityEvent event) {


        if (!event.getDamager().getType().equals(EntityType.PLAYER)                 // excluding all mobs except the ones needed.
                && !event.getDamager().getType().equals(EntityType.ZOMBIFIED_PIGLIN)
                && !event.getDamager().getType().equals(EntityType.ZOMBIE)
        ) return;

//For Players to have the intended effects when hitting entities with custom enchants.

        if (event.getDamager() instanceof Player) {
            Player p = (Player) event.getDamager();

            if (Objects.requireNonNull(p.getEquipment()).getItemInMainHand().equals(new ItemStack(Material.AIR)))
                return;
            if (!p.getEquipment().getItemInMainHand().hasItemMeta()) return;
            if (!p.getEquipment().getItemInMainHand().getItemMeta().hasLore()) return;
            if (!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) return;
            if (!event.getEntityType().isAlive()) return;


            if (p.getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Bind II")) { //BindChance
                if (Methods.randomNumber(0, 100) < config.getInt("Items.ButchersAxe.ChanceToApplySlowness")) {
                    ((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 2, 3));
                    Methods.spawnParticles(Particle.FLASH, event.getEntity().getWorld(), event.getEntity().getLocation(), 0,0,0, 1, 1);
                }
            }


            if (p.getEquipment().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "ConcussI"), PersistentDataType.STRING)) {
                if (Methods.randomNumber(0, 100) < config.getInt("Items.BaseballBat.chanceToConcuss")) {
                    ((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * config.getInt("Items.BaseballBat.concussionDuration"), 0));
                    Methods.spawnParticles(Particle.FIREWORKS_SPARK, event.getEntity().getWorld(), event.getEntity().getLocation(), 0,0,0, 10, 3);
                }
            }

        }

//For the Zombified_PiglinBoss to have the same effect when wielding the Bind II axe!

        if (event.getDamager().getType().equals(EntityType.ZOMBIFIED_PIGLIN)) {
            PigZombie p = (PigZombie) event.getDamager();

            if (Objects.requireNonNull(p.getEquipment()).getItemInMainHand().equals(new ItemStack(Material.AIR)))
                return;
            if (!p.getEquipment().getItemInMainHand().hasItemMeta()) return;
            if (!p.getEquipment().getItemInMainHand().getItemMeta().hasLore()) return;
            if (!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) return;
            if (!event.getEntityType().isAlive()) return;

            if (p.getEquipment().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "BindII"), PersistentDataType.STRING)) { //BindChance
                if (Methods.randomNumber(0, 100) < config.getInt("Items.ButchersAxe.ChanceToApplySlowness")) {
                    ((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 2, 3));
                    Methods.spawnParticles(Particle.FLASH, event.getEntity().getWorld(), event.getEntity().getLocation(), 0,0,0, 10, 3);
                }
            }
        }

//For the Zombie to have the same onHit effect when holding the baseballbat.

        if (event.getDamager().getType().equals(EntityType.ZOMBIE)) {
            Zombie p = (Zombie) event.getDamager();

            if (Objects.requireNonNull(p.getEquipment()).getItemInMainHand().equals(new ItemStack(Material.AIR)))
                return;
            if (!p.getEquipment().getItemInMainHand().hasItemMeta()) return;
            if (!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) return;
            if (!event.getEntityType().isAlive()) return;

            if (p.getEquipment().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "ConcussI"), PersistentDataType.STRING)) {
                if (Methods.randomNumber(0, 100) < config.getInt("Items.BaseballBat.chanceToConcuss")) {
                    ((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * config.getInt("Items.BaseballBat.concussionDuration"), 0));
                }
            }
        }
    }
}