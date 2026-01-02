package me.leodev.trollingfreedomreborn.commands;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;

import java.util.HashSet;
import java.util.UUID;

public class WorldLoading implements Listener {

    // This set acts as the "Toggle" for the listener
    public static HashSet<UUID> loadingPlayers = new HashSet<>();

    public static void WorldLoading(Player player) {
        if (player == null || !player.isOnline()) return;

        // Prevent double-triggering
        if (loadingPlayers.contains(player.getUniqueId())) return;

        final Location originalLocation = player.getLocation();
        final World currentWorld = player.getWorld();
        final GameMode originalMode = player.getGameMode();

        World targetWorld = Bukkit.getWorlds().stream()
                .filter(w -> !w.equals(currentWorld))
                .filter(w -> w.getEnvironment() != World.Environment.NETHER)
                .findFirst()
                .orElse(null);

        if (targetWorld == null) return;

        // 1. ADD TO LISTERNER BLOCKLIST
        loadingPlayers.add(player.getUniqueId());
        player.setGameMode(GameMode.SPECTATOR);

        // 2. Wait 1 tick, then teleport
        Bukkit.getScheduler().runTaskLater(Core.instance, () -> {
            if (!player.isOnline()) {
                loadingPlayers.remove(player.getUniqueId());
                return;
            }

            Location skyLoc = targetWorld.getSpawnLocation().clone().add(0, 500, 0);
            player.teleport(skyLoc);

            // 3. Keep them there for 5 seconds
            Bukkit.getScheduler().runTaskLater(Core.instance, () -> {
                if (player.isOnline()) {
                    player.teleport(originalLocation);

                    // 4. RESTORE AND REMOVE FROM LISTENER
                    Bukkit.getScheduler().runTaskLater(Core.instance, () -> {
                        if (player.isOnline()) {
                            player.setGameMode(originalMode);
                            loadingPlayers.remove(player.getUniqueId());
                        }
                    }, 10L);
                } else {
                    loadingPlayers.remove(player.getUniqueId());
                }
            }, 100L);
        }, 1L);
    }

    // THE LISTENER: Only blocks if the player is in the HashSet
    @EventHandler
    public void onAdvancement(PlayerAdvancementCriterionGrantEvent event) {
        if (loadingPlayers.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}