package me.leodev.trollingfreedomreborn.commands;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryRave implements Listener {

    public static ArrayList<String> Rave1 = new ArrayList<>();
    private int r = 255, g = 0, b = 0;

    private int posRed, negRed;
    private int posGreen, negGreen;
    private int posBlue, negBlue;

    public void saveInventory(Player p) throws IOException {
        File f = new File(Core.instance.getDataFolder().getAbsolutePath(), p.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("inventory.armor", p.getInventory().getArmorContents());
        c.set("inventory.content", p.getInventory().getContents());
        c.save(f);
    }

    @SuppressWarnings("unchecked")
    public void restoreInventory(Player p) throws IOException {
        File f = new File(Core.instance.getDataFolder().getAbsolutePath(), p.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
    }

    public void deleteInventoryFile(Player p) throws IOException {
        File f = new File(Core.instance.getDataFolder().getAbsolutePath(), p.getName() + ".yml");
        f.delete();
    }

    public void InvRave(Player p) throws IOException {
        if (Rave1.contains(p.getName())) {
        } else {
            Rave1.add(p.getName());
            saveInventory(p);
            Rave(p);
            Rave2(p);
        }
    }

    public void UnInvRave(Player p) throws IOException {
        if (Rave1.contains(p.getName())) {
            Rave1.remove(p.getName());
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            restoreInventory(p);
            deleteInventoryFile(p);
        } else {
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (Rave1.contains(event.getPlayer().getName()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (Rave1.contains(event.getPlayer().getName()))
            event.setCancelled(true);
    }

    public void Rave(final Player p) {
        // List of modern colored panes
        final Material[] panes = {
                Material.WHITE_STAINED_GLASS_PANE, Material.ORANGE_STAINED_GLASS_PANE,
                Material.MAGENTA_STAINED_GLASS_PANE, Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                Material.YELLOW_STAINED_GLASS_PANE, Material.LIME_STAINED_GLASS_PANE,
                Material.PINK_STAINED_GLASS_PANE, Material.GRAY_STAINED_GLASS_PANE,
                Material.LIGHT_GRAY_STAINED_GLASS_PANE, Material.CYAN_STAINED_GLASS_PANE,
                Material.PURPLE_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE,
                Material.BROWN_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE,
                Material.RED_STAINED_GLASS_PANE, Material.BLACK_STAINED_GLASS_PANE
        };

        int taskId = new BukkitRunnable() {
            public void run() {
                if (!p.isOnline() || !Rave1.contains(p.getName())) {
                    this.cancel();
                    return;
                }
                p.getInventory().clear();
                Random r = new Random();
                for (int counter = 1; counter <= 36; counter++) {
                    p.getInventory().setItem(p.getInventory().firstEmpty(), new ItemStack(panes[r.nextInt(panes.length)]));
                }
            }
        }.runTaskTimer(Core.instance, 1L, 5L).getTaskId();
        Core.instance.addTask(p, "inventoryrave", taskId);
    }

    public void Rave2(final Player p) {
        // List of modern colored glass blocks
        final Material[] blocks = {
                Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS,
                Material.MAGENTA_STAINED_GLASS, Material.LIGHT_BLUE_STAINED_GLASS,
                Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS,
                Material.PINK_STAINED_GLASS, Material.GRAY_STAINED_GLASS
        };

        // Reuse the panes list from above for the off-hand
        final Material[] panes = { Material.WHITE_STAINED_GLASS_PANE, Material.RED_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE };

        p.getInventory().setArmorContents(null);
        int taskId = new BukkitRunnable() {
            public void run() {
                if (!p.isOnline() || !Rave1.contains(p.getName())) {
                    this.cancel();
                    return;
                }
                Color color = nextRGB();
                Random rnd = new Random();

                p.getInventory().setHelmet(new ItemStack(blocks[rnd.nextInt(blocks.length)]));
                p.getInventory().setChestplate(getItemStack(Material.LEATHER_CHESTPLATE, color));
                p.getInventory().setLeggings(getItemStack(Material.LEATHER_LEGGINGS, color));
                p.getInventory().setBoots(getItemStack(Material.LEATHER_BOOTS, color));
                p.getInventory().setItemInOffHand(new ItemStack(panes[rnd.nextInt(panes.length)]));
            }
        }.runTaskTimer(Core.instance, 1L, 1L).getTaskId();
        Core.instance.addTask(p, "inventoryrave", taskId);
    }

    public ItemStack getItemStack(Material material, Color color) {
        // Remove the , (byte) 0
        ItemStack itemStack = new ItemStack(material, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        ((LeatherArmorMeta) itemMeta).setColor(color);
        itemMeta.setDisplayName("§4R§ca§6v§ee§a!");
        itemMeta.addItemFlags(ItemFlag.values());

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getItemStack(Material material) {
        return getItemStack(material, Color.fromRGB(r, g, b));
    }

    private Color nextRGB() {
        int increment = 15;
        int max = 255 / increment;

        if (posGreen <= max) {
            ++this.posGreen;
            this.g = (posGreen - 1) * increment;
        } else if (negRed <= max) {
            this.negRed++;
            this.r = 255 - increment * (negRed - 1);
        } else if (posBlue <= max) {
            ++this.posBlue;
            this.b = (posBlue - 1) * increment;
        } else if (negGreen <= max) {
            ++this.negGreen;
            this.g = 255 - increment * (negGreen - 1);
        } else if (posRed <= max) {
            ++this.posRed;
            this.r = (posRed - 1) * increment;
        } else if (negBlue <= max) {
            ++this.negBlue;
            this.b = 255 - increment * (negBlue - 1);
        } else {
            this.posRed = 0;
            this.negRed = 0;
            this.posGreen = 0;
            this.negGreen = 0;
            this.posBlue = 0;
            this.negBlue = 0;
        }

        return Color.fromRGB(r, g, b);
    }
}
