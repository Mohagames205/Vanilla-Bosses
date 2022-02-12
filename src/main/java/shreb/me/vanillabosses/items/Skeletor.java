package shreb.me.vanillabosses.items;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.main.Main;

import java.util.ArrayList;
import java.util.Objects;

public class Skeletor implements Listener {

    @EventHandler
    public void onBossArrowHit(ProjectileHitEvent event) {

        if (event.getHitEntity() == null) return;

        if (Main.getInstance().getConfig().getBoolean("Items.Skeletor.TNTOnArrowHit.enable") && (event.getEntity() instanceof Arrow)) {

            if (event.getEntity() instanceof Arrow && event.getEntity().getScoreboardTags().contains("ArrowExplode")) {

                if (event.getHitEntity().getType().equals(EntityType.ENDERMAN)) return;
                if(!(event.getHitEntity() instanceof LivingEntity)) return;

                Entity tempTNT = event.getHitEntity().getWorld().spawnEntity(event.getHitEntity().getLocation(), EntityType.PRIMED_TNT);
                event.getEntity().remove();

                ((TNTPrimed) tempTNT).setFuseTicks(Main.getInstance().getConfig().getInt("Items.Skeletor.TNTOnArrowHit.TNTTimer"));
                ((TNTPrimed) tempTNT).setYield(Main.getInstance().getConfig().getInt("Items.Skeletor.TNTOnArrowHit.TNTYield"));

                if (event.getEntity().getShooter() instanceof Player && Main.getInstance().getConfig().getBoolean("Items.Skeletor.TNTOnArrowHit.TNTDoesNoBlockDamage")) {
                    tempTNT.addScoreboardTag("CancelOnExplode");
                    tempTNT.addScoreboardTag("dontBlowUpItems");
                } else if (event.getEntity().getShooter() instanceof Skeleton && ((Skeleton)event.getEntity().getShooter()).getScoreboardTags().contains("BossSkeleton") && !Main.getInstance().getConfig().getBoolean("Bosses.SkeletonBoss.summonedTNTDoesBlockDamage")){
                    tempTNT.addScoreboardTag("CancelOnExplode");
                    tempTNT.addScoreboardTag("dontBlowUpItems");
                }
            }
        }
    }

    @EventHandler
    public void onBossBowShoot(ProjectileLaunchEvent event) {

        if (!(event.getEntity() instanceof Arrow)) return;
        if(!(event.getEntity().getShooter() instanceof Entity)) return;

        Entity entity = (Entity) event.getEntity().getShooter();
        if(entity == null) return;

        if (entity instanceof Player && Objects.requireNonNull(((Player) entity).getEquipment()).getItemInOffHand().getType().equals(Material.TNT)) {

            if (Main.getInstance().getConfig().getBoolean("Items.Skeletor.ShootTNTFromOffHand.enabled")) {

                if (Objects.requireNonNull(Objects.requireNonNull(((Player) entity).getEquipment()).getItemInMainHand().getItemMeta().getPersistentDataContainer()).has(new NamespacedKey(Main.getInstance(), "shootsTnT"), PersistentDataType.STRING)) {
                    Player p = (Player) entity;
                    if(p.getGameMode().equals(GameMode.SURVIVAL))Objects.requireNonNull(p.getEquipment()).getItemInOffHand().setAmount(p.getEquipment().getItemInOffHand().getAmount() - 1);
                    Vector v = event.getEntity().getVelocity();
                    Entity TNTProjectile = event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
                    ((TNTPrimed) TNTProjectile).setFuseTicks(Main.getInstance().getConfig().getInt("Items.Skeletor.ShootTNTFromOffHand.TNTTimer"));
                    ((TNTPrimed) TNTProjectile).setYield(Main.getInstance().getConfig().getInt("Items.Skeletor.ShootTNTFromOffHand.TNTYield"));
                    TNTProjectile.setVelocity(v);
                    if(Main.getInstance().getConfig().getBoolean("Items.Skeletor.ShootTNTFromOffHand.TNTDoesNoBlockDamage")) TNTProjectile.addScoreboardTag("CancelOnExplode");
                    event.getEntity().remove();
                }
            }
        }

        if(Objects.requireNonNull(((LivingEntity) entity).getEquipment()).getItemInMainHand().getItemMeta() == null) return;

        if (Objects.requireNonNull(Objects.requireNonNull(((LivingEntity) entity).getEquipment()).getItemInMainHand().getItemMeta().getPersistentDataContainer()).has(new NamespacedKey(Main.getInstance(), "shootsTnT"), PersistentDataType.STRING)) {
            event.getEntity().addScoreboardTag("ArrowExplode");
            if(entity instanceof Skeleton) event.getEntity().addScoreboardTag("applyModifier");
        }
    }

    public static ItemStack makeSkeletor(){

        ItemStack bow = new ItemStack(Material.BOW);

        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Skeletor");
        ArrayList<String> lore = new ArrayList<>();

        for(String s: Main.getInstance().getConfig().getStringList("Items.Skeletor.Lore")){
            if(Main.getInstance().getConfig().getStringList("Items.Skeletor.Lore").indexOf(s) == 0){
                lore.add(ChatColor.DARK_RED + "" + ChatColor.BOLD + s);
            } else {
                lore.add(s);
            }
        }
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(new NamespacedKey(Main.getInstance(), "shootsTnT"), PersistentDataType.STRING, "Shoots TNT");
        meta.setLore(lore);
        bow.setItemMeta(meta);

        return bow;
    }

}
