package me.leodev.trollingfreedomreborn.trolls.inventory;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class LockInventory implements Listener {


    public static void Lock(Player p) {
        // 1. Define the BukkitRunnable separately
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (!p.isOnline()) {
                    this.cancel();
                    return;
                }

                p.closeInventory();

                // Note: If you want UnTroll to handle the stopping,
                // you usually don't need the sneaking check here.
                if (p.isSneaking()) {
                    this.cancel();
                }
            }
        };

        // 2. Start the task using the Runnable's own method
        // This returns a BukkitTask object, from which we get the ID
        int taskId = runnable.runTaskTimer(Core.instance, 1L, 1L).getTaskId();

        // 3. Register the task for your surgical UnTroll system
        Core.instance.addTask(p, "lockinventory", taskId);
    }
}


