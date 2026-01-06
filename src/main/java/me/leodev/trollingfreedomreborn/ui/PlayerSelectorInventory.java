package me.leodev.trollingfreedomreborn.ui;

import me.leodev.trollingfreedomreborn.main.Core;
import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayerSelectorInventory implements InventoryHolder, Listener {

    static PlayerSelectorInventory main;
    private final Inventory inv;
    private final Inventory inv2;
    ItemStack nextPage = createGuiItem(XMaterial.REDSTONE_BLOCK, true, Core.getPathCC("items.nextpage-name"), Core.getPathCC("items.nextpage-lore"));
    ItemStack mainPage = createGuiItem(XMaterial.REDSTONE_BLOCK, true, Core.getPathCC("items.mainpage-name"), Core.getPathCC("items.mainpage-lore"));
    ItemStack untrollall = createGuiItem(XMaterial.BEACON, false, Core.tcc(Core.instance.getConfig().getString("items.Untrollall-name")), Core.tcc(Core.instance.getConfig().getString("items.Untrollall-lore")));
    // You can call this whenever you want to put the items in
    int playersdone = 0;

    public PlayerSelectorInventory() {
        Bukkit.getPluginManager().registerEvents(this, Core.instance);
        // Create a new inventory, with "this" owner for comparison with other inventories, a size of nine, called example
        inv = Bukkit.createInventory(this, 54, centerTitle(Core.instance.getP() + Core.tcc(Core.instance.getConfig().getString("menu.select-player"))));
        inv2 = Bukkit.createInventory(this, 54, centerTitle(Core.instance.getP() + Core.tcc(Core.instance.getConfig().getString("menu.select-player"))));
        // Put the items into the inventory
        main = this;
        initializeItems();
    }

    public static PlayerSelectorInventory getPS() {
        return main;
    }

    public String centerTitle(String title) {
        StringBuilder result = new StringBuilder();
        int spaces = (27 - ChatColor.stripColor(title).length());

        for (int i = 0; i < spaces; i++) {
            result.append(" ");
        }

        return result.append(title).toString();
    }

    @Override
    public @NotNull
    Inventory getInventory() {
        return inv;
    }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(final XMaterial xmat, final Boolean isEnchanted, final String name, final String... lore) {
        // 1. Convert XMaterial to a Bukkit Material
        org.bukkit.Material material = xmat.parseMaterial();

        // 2. Safety check: If XMaterial fails (common in new MC versions), use BARRIER
        if (material == null) {
            Bukkit.getLogger().warning("[TFR] Material not found for: " + xmat.name() + ". Using BARRIER.");
            material = org.bukkit.Material.BARRIER;
        }

        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);

            // 3. Handle Enchantment safely (Durability = Unbreaking)
            if (isEnchanted) {
                org.bukkit.enchantments.Enchantment dur = org.bukkit.enchantments.Enchantment.UNBREAKING;
                if (XEnchantment.UNBREAKING.getEnchant() != null) {
                    dur = XEnchantment.UNBREAKING.getEnchant();
                }
                meta.addEnchant(dur, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }

            // 4. Handle Lore safely
            if (lore != null) {
                meta.setLore(Arrays.asList(lore));
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    public void initializeItems() {

        Bukkit.getOnlinePlayers().forEach(o -> {

            final ItemStack item = new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial(), 1);

            // Set the lore of the item
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            skullMeta.setOwner(o.getName());
            skullMeta.setDisplayName(o.getName());
            List<String> lore = new ArrayList<>();
            if (o.isOp()) {

                lore.add(Core.getPathCC("items.messages.isOP"));
                if (!Core.canTroll(o)) {
                    lore.add(" ");
                    lore.add("§c§lPROTECTED");
                    lore.add("§7You cannot troll this player.");
                } else {
                    lore.add(" ");
                    lore.add("§a§lVULNERABLE");

                    lore.add("§7Click to open troll menu.");
                }
            } else {
                if (!Core.canTroll(o)) {
                    lore.add("§c§lPROTECTED");
                    lore.add("§7You cannot troll this player.");
                } else {
                    lore.add("§a§lVULNERABLE");
                    lore.add("§7Click to open troll menu.");
                }
            }


            // Optional: Keep your isOP check

            skullMeta.setLore(lore);
            item.setItemMeta(skullMeta);
            if (playersdone > inv.getSize() - 7) {
                inv2.addItem(item);
                inv2.setItem(49, untrollall);
            } else {
                inv.addItem(item);
                inv.setItem(49, untrollall);
                playersdone++;
            }


        });

        if (Bukkit.getOnlinePlayers().size() > inv.getSize() - 7) {
            inv.setItem(53, nextPage);
        }
        if (Bukkit.getOnlinePlayers().size() > inv2.getSize() - 7) {
            inv2.setItem(53, mainPage);
        }
    }

    // You can open the inventory with this
    public void openUniInv(final HumanEntity ent, Inventory inv) {
        ent.openInventory(inv);
    }

    public void openSel(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory().getHolder() != this) return;
        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == XMaterial.AIR.parseMaterial()) return;

        final Player p = (Player) e.getWhoClicked();

        //untroll all
        if (clickedItem.equals(untrollall)) {
            p.performCommand("untroll all");
            //
        }

        if (clickedItem.equals(nextPage)) {
            p.closeInventory();
            openUniInv(p, inv2);
        } else if (clickedItem.equals(mainPage)) {
            p.closeInventory();
            openUniInv(p, inv);
        }
        final Player Vic = Bukkit.getPlayerExact(clickedItem.getItemMeta().getDisplayName());
        if (!Core.canTroll(Vic)) {
            p.sendMessage("§cYou cannot troll this player!");
            p.closeInventory();
            return;
        }
        if (Vic != null) {
            if (Vic instanceof Player) {
                p.sendMessage("§b§lTFR §8| §7Selected §b" + Vic.getName() + " §7to troll.");
                p.playSound(p.getLocation(), com.cryptomorin.xseries.XSound.BLOCK_NOTE_BLOCK_CHIME.parseSound(), 1f, 2f);
                TrollInventory gt = new TrollInventory(Vic);
                gt.openInventory(p);
            }
        }
    }
}
