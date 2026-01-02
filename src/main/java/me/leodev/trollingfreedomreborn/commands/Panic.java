package me.leodev.trollingfreedomreborn.commands;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashSet;
import java.util.UUID;

public class Panic implements CommandExecutor {
    private final HashSet<UUID> confirmationPending = new HashSet<>();
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("trollingfreedom.panic")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }

        UUID uuid = (sender instanceof Player) ? ((Player) sender).getUniqueId() : UUID.nameUUIDFromBytes("console".getBytes());

        // FIRST RUN
        if (!confirmationPending.contains(uuid)) {
            confirmationPending.add(uuid);
            sender.sendMessage("§4§l[WARNING] §cYou are about to stop ALL plugin tasks.");
            sender.sendMessage("§crun the command again within 10 seconds to confirm.");

            // Remove from list after 5 seconds if they don't run it again
            Bukkit.getScheduler().runTaskLater(Core.instance, () -> confirmationPending.remove(uuid), 200L); // 100 ticks = 5 seconds
            return true;
        }

        // SECOND RUN (Within 5 seconds)
        confirmationPending.remove(uuid);

        // --- THE NUCLEAR OPTION ---
        Bukkit.getScheduler().cancelTasks(Core.instance);
        Core.instance.individualTasks.clear();

        Player sndrPlayer = (sender instanceof Player) ? (Player) sender : null;
        // Mass cleanup of lists/states
        for (Player all : Bukkit.getOnlinePlayers()) {
            try {
                new UnTroll().StopTrolls(all, sndrPlayer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sender.sendMessage(ChatColor.DARK_RED + "§l[PANIC] §fAll tasks and trolls force-stopped.");
        Bukkit.broadcast("§c§l[TFR] §7Panic switch activated by " + sender.getName(), "trollingfreedom.admin");

        return true;
    }
}
