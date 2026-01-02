package me.leodev.trollingfreedomreborn.commands;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class SlipperyHands implements Listener {


    public static void SlipperyHands(Player p) {
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, new Runnable() {
            public void run() {
                ItemStack item = p.getInventory().getItemInMainHand();
                if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                    p.getWorld().dropItemNaturally(p.getLocation(), item);
                    p.getInventory().remove(item);
                }
            }
        }, 5L, 40L);
        Core.instance.addTask(p, "slipperyhands", id);
    }
}


