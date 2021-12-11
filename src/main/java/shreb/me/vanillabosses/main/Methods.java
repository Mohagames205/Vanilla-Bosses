package shreb.me.vanillabosses.main;


import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import shreb.me.vanillabosses.listeners.EntitySpawnEvent;

import java.util.*;

public class Methods {

    static Configuration config = Main.getInstance().getConfig();
    public static HashMap<UUID, Location> prevloc = new HashMap<>();
    public static HashMap<UUID, Integer> counter = new HashMap<>();

    public Methods() {

    }

    public static int randomNumber(int min, int max) {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);
        return (int) (Math.floor(Math.random() * (max - min)) + min);
    }

    /**
     * This method is meant to check wether a player is actually killing monsters legit or grinding them in a mobfarm with an auto clicker while afk
     *
     * @param event the event to be checked for afk players hitting mobs
     */

    static HashMap<UUID, Integer> timer1= new HashMap<>();
    static HashMap<UUID, Integer> timer2= new HashMap<>();

    public static void afkCheck(EntityDamageByEntityEvent event) {
        //AFKCHECK START

        if (config.getBoolean("Bosses.enableAntiAFKTeleports")) {

            if(!timer1.containsKey(event.getDamager().getUniqueId())){timer1.put(event.getDamager().getUniqueId(), 0);}
            if(!timer2.containsKey(event.getDamager().getUniqueId())){timer2.put(event.getDamager().getUniqueId(), 0);}

            if (prevloc.get(event.getDamager().getUniqueId()).equals(event.getDamager().getLocation())) { //check wether a players location or rotation is the same as the last time they hit a boss
                if(timer1.get(event.getDamager().getUniqueId()) == 0) {
                    counter.put(event.getDamager().getUniqueId(), counter.get(event.getDamager().getUniqueId()) + 1); // counter++ if the location hasnt changed
                    int taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> timer1.put(event.getDamager().getUniqueId(), 0), 5);
                    timer1.put(event.getDamager().getUniqueId(), taskID);
                }

                if(counter.get(event.getDamager().getUniqueId()) > config.getInt("Bosses.AntiAFKHitLimit") - 3){
                    if(timer2.get(event.getDamager().getUniqueId()) == 0){
                        event.getDamager().sendMessage(ChatColor.GRAY + config.getString("Bosses.AntiAFKWarningMessage"));
                        int taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> timer2.put(event.getDamager().getUniqueId(), 0), 100);
                        timer2.put(event.getDamager().getUniqueId(), taskID);
                    }
                }

            } else {
                counter.put(event.getDamager().getUniqueId(), 0);
                prevloc.put(event.getDamager().getUniqueId(), event.getDamager().getLocation()); //set the location to compare the next time the player hits a boss
            }

            if (counter.get(event.getDamager().getUniqueId()) > config.getInt("Bosses.AntiAFKHitLimit") &&
                    prevloc.get(event.getDamager().getUniqueId()).equals(event.getDamager().getLocation())) {
                //check wether the player has hit bosses more than the limit without changing location/moving their mouse
                int afkCheck = Methods.randomNumber(-1, 1000);
                //make a random number to compare against the chance in Bosses.AntiAFKChance

                if (afkCheck < config.getInt("Bosses.AntiAFKChance")) { //check wether it should teleport the boss onto the player
                    if(event.getEntity().getType() != EntityType.CREEPER){
                        event.getEntity().teleport(event.getDamager()); //teleport the mob onto the player who is afk grinding bosses
                        event.setCancelled(true);
                    }
                }
            }
        }
        //AFKCHECK END
    }


    public static ArrayList<ItemStack> addItemsFromConfig(List<String> str) {

        ArrayList<ItemStack> items = new ArrayList<>();

        Material mat;
        int min;
        int max;
        int chance;

        for (String s : str) {

            String[] strings = s.split(":");
            mat = Material.getMaterial(strings[0]);

            if (mat != null) {

                min = Integer.parseInt(strings[1]);
                max = Integer.parseInt(strings[2]);
                chance = Integer.parseInt(strings[3]);

                int amount = min;
                for (int i1 = min; i1 < max; i1++) {
                    if (Methods.randomNumber(0, 100) < chance) {
                        amount++;
                    }
                }
                ItemStack itemStack = new ItemStack(mat, amount);
                items.add(itemStack);
            }
        }
        return items;
    }

    public static void mobHorde(int radius, int amount, String type, Location center) {

        Vector v = new Vector(radius, 0, 0);  //set the starting vector for spawning

        double degrees = 360.0 / amount; //get the degrees every spawnpoint has to be different by to make a circle

        EntitySpawnEvent.spawn = false;

        for (int i = 0; i < amount; i++) {

            center.add(v);

            LivingEntity entity = (LivingEntity) Objects.requireNonNull(center.getWorld()).spawnEntity(center.add(0, 2, 0), EntityType.valueOf(type.toUpperCase()));

            center.subtract(0, 2, 0);

            entity.getScoreboardTags().add("NotABoss");

            entity.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            entity.getEquipment().setHelmetDropChance(0);

            center.subtract(v);

            v.rotateAroundY(degrees);

        }
        EntitySpawnEvent.spawn = true;
    }

    public static void spawnParticles(Particle particle, World world, Location loc, double offsetX, double offsetY, double offsetZ, int amount, int repeats) {

        for (int i = 0; i < repeats; i++) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                world.spawnParticle(particle, loc, amount, offsetX, offsetY, offsetZ);

            }, 10L * i);

        }
    }

}

