package me.leodev.trollingfreedomreborn.trolls.random;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Sounds implements Listener {


    public static void CaveSound(final Player p) {
        String p2 = p.getName();
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, new Runnable() {
            public void run() {
                p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 100, 1);
            }
        }, 10L, 5L);
        Core.instance.addTask(p, "cavesounds", taskId);
    }

    public static void GhastSound(final Player p) {
        String p2 = p.getName();
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, new Runnable() {
            public void run() {
                p.playSound(p.getLocation(), Sound.ENTITY_GHAST_AMBIENT, 100, 1);
            }
        }, 10L, 40L);
        Core.instance.addTask(p, "ghastsound", taskId);
    }
}