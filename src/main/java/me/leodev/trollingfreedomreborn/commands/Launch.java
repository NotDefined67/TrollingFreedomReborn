package me.leodev.trollingfreedomreborn.commands;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class Launch implements Listener {

    public static void Launch(Player p) {
        if (p == null) return;

        // A Y-vector of 150 is extremely high.
        // In most Spigot versions, this will instantly kick the player for flying.
        // Try 5.0 or 10.0 for a very high but "visible" launch.
        Vector velocity = new Vector(0, 5.0, 0);

        p.setVelocity(velocity);

        // Play the sound at the player's location
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_UP, 5.0F, 2.0F);

        // Spawn particles (Cloud)
        p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 100, 0.5, 0.5, 0.5, 0.1);
    }
}
