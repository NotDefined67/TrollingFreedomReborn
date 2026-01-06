package me.leodev.trollingfreedomreborn.trolls.explosion;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class KittyCannon implements Listener {

    public static ArrayList<String> Kitty1 = new ArrayList();

    public void KittyCannon(final Player p) {
        String p2 = p.getName();


        Kitty1.add(p.getName());
        int repeat = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, new Runnable() {
            public void run() {
                EntityType cat = EntityType.OCELOT;
                Location twoBlocksAway = p.getEyeLocation().add(p.getEyeLocation().getDirection().multiply(10));
                final Ocelot ocelot = (Ocelot) p.getWorld().spawn(twoBlocksAway, cat.getEntityClass());
                ocelot.setVelocity(twoBlocksAway.getDirection().multiply(-2));
                p.getWorld().createExplosion(ocelot.getLocation(), 0F);
                p.playSound(ocelot.getLocation(), Sound.ENTITY_OCELOT_HURT, 100F, 100F);
                Location loc = ocelot.getLocation();
            }
        }, 10L, 10L);
        int delay = Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, new Runnable() {
            public void run() {
                for (Entity entity : p.getNearbyEntities(100, 100, 100)) {
                    if (entity instanceof Ocelot)
                        entity.remove();
                }
            }
        }, 60L); // 600L (ticks) is equal to 30 seconds (20 ticks = 1 second)
        Core.instance.addTask(p, "kittycannon", repeat);
        Core.instance.addTask(p, "kittycannon", delay);
    }

    public void UnKittyCannon(Player p) {

        // String nick = user.getNick();
        if (Kitty1.contains(p.getName())) {
            for (Entity entity : p.getNearbyEntities(100, 100, 100)) {
                if (entity instanceof Ocelot)
                    entity.remove();
            }
            Kitty1.remove(p.getName());
        } else {
        }
    }
}