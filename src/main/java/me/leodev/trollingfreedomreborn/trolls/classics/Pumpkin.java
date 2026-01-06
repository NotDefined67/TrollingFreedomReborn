package me.leodev.trollingfreedomreborn.trolls.classics;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Pumpkin implements Listener {


    public static ArrayList<String> Pumpkin1 = new ArrayList<>();

    public void Pumpkin(Player p) {
        if (p == null) return;
        Player p2 = p.getPlayer();
        ItemStack helmet = p.getInventory().getHelmet();

        // Drop helmet if they have one
        if (helmet != null && helmet.getType() != Material.AIR) {
            p.getWorld().dropItem(p.getLocation(), helmet);
        }

        Pumpkin1.add(p2.getName());

        // Capture the Task ID
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, () -> {
            // Safety check: Stop if player leaves or is untrolled
            if (!p.isOnline() || !Pumpkin1.contains(p.getName())) return;

            ItemStack stack = new ItemStack(Material.CARVED_PUMPKIN);
            p.getInventory().setHelmet(stack);
        }, 10L, 5L);

        // Register to the individualTasks map
        Core.instance.addTask(p, "pumpkin", taskId);
    }

    public void unPumpkin(Player p) {
        Player p2 = p.getPlayer();
        if (Pumpkin1.contains(p.getName())) {
            Pumpkin1.remove(p2.getName());
            if (p.getInventory().getHelmet() == null) {
                return;
            }
            if (p.getInventory().getHelmet().equals(new ItemStack(Material.CARVED_PUMPKIN))) {
                ItemStack stack = new ItemStack(Material.AIR);
                p.getInventory().setHelmet(stack);
            }
        }
    }

    @EventHandler
    public void onPumpkinMove(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (Pumpkin1.contains(player.getName())) {
            ItemStack clicked = event.getCurrentItem();
            if (clicked.getType().equals(Material.CARVED_PUMPKIN)) {
                event.setCancelled(true);
            } else {
            }

        }
    }
}

