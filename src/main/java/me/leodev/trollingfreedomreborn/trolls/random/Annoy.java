package me.leodev.trollingfreedomreborn.trolls.random;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Annoy implements Listener {

    // Keep track of players currently being annoyed to prevent double-trolling
    // and to allow the GUI/UnTroll system to know who is active.
    public static List<String> Annoy1 = new ArrayList<>();

    public static void Annoy(Player p) {
        // Prevent starting multiple tasks for the same player
        if (Annoy1.contains(p.getName())) {
            return;
        }

        Annoy1.add(p.getName());

        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, () -> {
            // Check if player is still online or if they were removed from the list
            if (!p.isOnline() || !Annoy1.contains(p.getName())) {
                stopAnnoy(p);
                return;
            }

            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 100, 1);
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 100, 1);
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 1);
        }, 10L, 5L);

        // Register the task in the Core instance so it can be managed/cancelled
        Core.instance.addTask(p, "annoy", taskId);
    }

    /**
     * Stops the annoying villager sounds for the specified player.
     * Use this in your UnTroll class or stopSpecificTroll method.
     */
    public static void stopAnnoy(Player p) {
        Annoy1.remove(p.getName());

        // This will cancel the task ID associated with "annoy" for this player in your Core manager
        // Ensure your Core.java has a way to cancel tasks by name.
    }
}