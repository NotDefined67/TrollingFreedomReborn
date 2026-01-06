package me.leodev.trollingfreedomreborn.trolls.random;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Burn implements Listener{
    public static void Burn(Player p) {
        String p2 = p.getName();
        p.setFireTicks(1000000000);
    }
}
