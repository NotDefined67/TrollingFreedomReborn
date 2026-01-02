package me.leodev.trollingfreedomreborn.commands;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class AnvilDrop implements Listener {

    public static void Anvil(final Player p) {
        // 1. Capture the task ID (int)
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, new Runnable() {
            public void run() {
                if (p.isOnline()) {
                    Location loc1 = p.getLocation().add(p.getLocation().getDirection());
                    Block block1 = p.getWorld().getBlockAt(loc1.add(0.0D, 20.0D, 0.0D)); //MIDDLE
                    block1.setType(Material.DAMAGED_ANVIL);
                }
            }
        }, 40L, 10L);

        // 2. Register it with the "Anvil Drop" key
        // Make sure this name matches the item name in your GUI
        Core.instance.addTask(p, "anvildrop", taskId);
    }

    // You can remove UnAnvil(Plugin plugin) entirely now because
    // UnTroll.java will handle cancellation via the taskId map!
}