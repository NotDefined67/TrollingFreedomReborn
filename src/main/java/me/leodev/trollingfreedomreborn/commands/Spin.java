package me.leodev.trollingfreedomreborn.commands;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class Spin implements Listener {

    public static ArrayList<String> spin1 = new ArrayList<>();


    public static void Spin(final Player p) {
        spin1.add(p.getName());
        final Location tp = p.getLocation();
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, new Runnable() {
            public void run() {
                tp.setYaw(tp.getYaw() + 90.0F);
                p.teleport(tp);
            }
        }, 10L, 5L);
        Core.instance.addTask(p,"spin",id);
    }

    public void StopSpin(Player p) {
        spin1.remove(p.getName());
    }
}
