package me.leodev.trollingfreedomreborn.trolls.random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCrafts implements Listener {

    // Changed to List<String> to store player names
    public static List<String> randomcraft = new ArrayList<>();
    private final Random random = new Random();
    private final Material[] materials = Material.values();

    public void craftTroll(Player target) {
        // Use getName() instead of getUniqueId()
        String name = target.getName();

        if (!randomcraft.contains(name)) {
            randomcraft.add(name);
        }
    }

    public void unCraftTroll(Player target) {
        String name = target.getName();
        // remove() works directly with the name string
        randomcraft.remove(name);
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        if (!(e.getView().getPlayer() instanceof Player)) return;
        Player p = (Player) e.getView().getPlayer();

        // 1. Check if the player is being trolled
        if (randomcraft.contains(p.getName())) {

            // 2. CHECK: Only show a result if there is a VALID recipe in the grid
            // This prevents "free items" from an empty or random grid.
            if (e.getRecipe() == null || e.getRecipe().getResult().getType() == Material.AIR) {
                return; // Do nothing, keep the result slot empty
            }

            // 3. Swap the valid result for a random item
            Material randomMat;
            do {
                randomMat = materials[random.nextInt(materials.length)];
            } while (randomMat.isAir() || !randomMat.isItem());

            e.getInventory().setResult(new ItemStack(randomMat, 1));
        }
    }
}