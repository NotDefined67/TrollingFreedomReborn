package me.leodev.trollingfreedomreborn.other;

import me.leodev.trollingfreedomreborn.commands.UnTroll;
import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class UntrollOnQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        // Check if the feature is enabled in config.yml
        if (Core.instance.getConfig().getBoolean("values.untroll-on-quit", true)) {
            Player player = event.getPlayer();

            try {
                // Initialize UnTroll and stop tasks/lists for this specific player
                UnTroll untroll = new UnTroll();
                // Passing null for sender because the server/plugin is triggering it
                untroll.StopTrolls(player, null);
                Bukkit.getConsoleSender().sendMessage("§b§lTFR §8| §7Auto-untrolled §f" + player.getName() + " §7due to disconnect.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}