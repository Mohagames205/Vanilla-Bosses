package shreb.me.vanillabosses.bossclasses;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.main.Main;
import shreb.me.vanillabosses.main.Methods;

public class BossWitch implements Listener {

    static Configuration config = Main.getInstance().getConfig();

    static String path = "Bosses.WitchBoss.customThrownPotions.";

    static ItemStack damagePot = makeDamagePot();
    static ItemStack poisonPot = makePoisonPot();
    static ItemStack blindnessPot = makeBlindnessPot();
    static ItemStack witherPot = makeWitherPot();
    static ItemStack hungerPot = makeHungerPot();

    static int harmChance = config.getInt(path + "Harm.chance");
    static int poisonChance = config.getInt(path + "Poison.chance");
    static int blindChance = config.getInt(path + "Blindness.chance");
    static int witherChance = config.getInt(path + "Wither.chance");
    static int hungerChance = config.getInt(path + "Hunger.chance");


    @EventHandler
    public void onPotionThrow(ProjectileLaunchEvent event) {

        if(!(event.getEntity().getShooter() instanceof LivingEntity)) return;

        if(!((LivingEntity) event.getEntity().getShooter()).getType().equals(EntityType.WITCH)) return;

        if(!event.getEntity().getType().equals(EntityType.SPLASH_POTION)) return;

        Vector v = event.getEntity().getVelocity();
        Location loc = event.getLocation();

        int rn = Methods.randomNumber(0, 100);
        int currentChance = harmChance;

        if (rn < currentChance) {

            event.getEntity().remove();

            ThrownPotion e = (ThrownPotion) event.getEntity().getWorld().spawnEntity(loc, EntityType.SPLASH_POTION);

            e.setItem(damagePot);
            e.setVelocity(v);

            return;
        }

        currentChance += poisonChance;
        if (rn < currentChance) {
            event.getEntity().remove();

            ThrownPotion e = (ThrownPotion) event.getEntity().getWorld().spawnEntity(loc, EntityType.SPLASH_POTION);

            e.setItem(poisonPot);
            e.setVelocity(v);

            return;
        }

        currentChance += blindChance;
        if (rn < currentChance) {
            event.getEntity().remove();

            ThrownPotion e = (ThrownPotion) event.getEntity().getWorld().spawnEntity(loc, EntityType.SPLASH_POTION);

            e.setItem(blindnessPot);
            e.setVelocity(v);

            return;
        }

        currentChance += witherChance;
        if (rn < currentChance) {
            event.getEntity().remove();

            ThrownPotion e = (ThrownPotion) event.getEntity().getWorld().spawnEntity(loc, EntityType.SPLASH_POTION);

            e.setItem(witherPot);
            e.setVelocity(v);

            return;
        }

        currentChance += hungerChance;
        if (rn < currentChance) {
            event.getEntity().remove();

            ThrownPotion e = (ThrownPotion) event.getEntity().getWorld().spawnEntity(loc, EntityType.SPLASH_POTION);

            e.setItem(hungerPot);
            e.setVelocity(v);
        }
    }


    public static ItemStack makeDamagePot() {
        ItemStack damagePot = new ItemStack(Material.SPLASH_POTION);
        PotionMeta meta = (PotionMeta) damagePot.getItemMeta();
        meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 1, config.getInt(path + "Harm.amplifier") - 1), true);
        meta.setDisplayName(ChatColor.RED + "Potion of Harming");
        damagePot.setItemMeta(meta);
        return damagePot;
    }

    public static ItemStack makePoisonPot() {
        ItemStack poisonPot = new ItemStack(Material.SPLASH_POTION);
        PotionMeta meta = (PotionMeta) poisonPot.getItemMeta();
        meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 20 * config.getInt(path + "Poison.duration"), config.getInt(path + "Poison.amplifier") - 1), true);
        meta.setDisplayName(ChatColor.GREEN + "Potion of Poison");
        poisonPot.setItemMeta(meta);
        return poisonPot;
    }

    public static ItemStack makeBlindnessPot() {
        ItemStack blindnessPot = new ItemStack(Material.SPLASH_POTION);
        PotionMeta meta = (PotionMeta) blindnessPot.getItemMeta();
        meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * config.getInt(path + "Blindness.duration"), config.getInt(path + "Blindness.amplifier") - 1), true);
        meta.setDisplayName(ChatColor.BLACK + "Potion of Blindness");
        blindnessPot.setItemMeta(meta);
        return blindnessPot;
    }

    public static ItemStack makeWitherPot() {
        ItemStack poisonPot = new ItemStack(Material.SPLASH_POTION);
        PotionMeta meta = (PotionMeta) poisonPot.getItemMeta();
        meta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 20 * config.getInt(path + "Wither.duration"), config.getInt(path + "Wither.amplifier") - 1), true);
        meta.setDisplayName(ChatColor.GRAY + "Potion of Withering");
        poisonPot.setItemMeta(meta);
        return poisonPot;
    }

    public static ItemStack makeHungerPot() {
        ItemStack hungerPot = new ItemStack(Material.SPLASH_POTION);
        PotionMeta meta = (PotionMeta) hungerPot.getItemMeta();
        meta.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER, 20 * config.getInt(path + "Hunger.duration"), config.getInt(path + "Hunger.amplifier") - 1), true);
        meta.setDisplayName(ChatColor.DARK_GREEN + "Potion of Hunger");
        hungerPot.setItemMeta(meta);
        return hungerPot;
    }

    /**
     * spawns the boss as a new Entity
     * @param location specifies the location in which the boss will spawn
     */

    public static Witch makeBossWitch(Location location, World w){

        Witch witch = (Witch) w.spawnEntity(location, EntityType.WITCH);
        witch.addScoreboardTag("BossWitch");
        witch.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.WITCH.health);
        witch.setHealth(Bosses.WITCH.health);
        witch.setCustomName(config.getString("Bosses.WitchBoss.displayName"));
        witch.setCustomNameVisible(config.getBoolean("Bosses.WitchBoss.showDisplayNameAlways"));

        return witch;
    }

    /**
     * edits an existing mob into the boss version.
     * @param witch the entity which to edit into the boss version.
     */

    public static void editToBossWitch(Witch witch){

        witch.addScoreboardTag("BossWitch");
        witch.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Bosses.WITCH.health);
        witch.setHealth(Bosses.WITCH.health);
        witch.setCustomName(config.getString("Bosses.WitchBoss.displayName"));
        witch.setCustomNameVisible(config.getBoolean("Bosses.WitchBoss.showDisplayNameAlways"));
    }
}
