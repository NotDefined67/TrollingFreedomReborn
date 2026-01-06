package me.leodev.trollingfreedomreborn.ui;

import me.leodev.trollingfreedomreborn.commands.*;
import me.leodev.trollingfreedomreborn.main.Core;
import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import me.leodev.trollingfreedomreborn.trolls.classics.Pumpkin;
import me.leodev.trollingfreedomreborn.trolls.classics.RickRoll;
import me.leodev.trollingfreedomreborn.trolls.classics.Slenderman;
import me.leodev.trollingfreedomreborn.trolls.classics.Spin;
import me.leodev.trollingfreedomreborn.trolls.explosion.Snowman;
import me.leodev.trollingfreedomreborn.trolls.explosion.TNT;
import me.leodev.trollingfreedomreborn.trolls.fakestuff.FakeKicks;
import me.leodev.trollingfreedomreborn.trolls.inventory.RandomInv;
import me.leodev.trollingfreedomreborn.trolls.movement.SneakDestroy;
import me.leodev.trollingfreedomreborn.trolls.random.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TrollInventory3 implements Listener, InventoryHolder {

    static TrollInventory3 main;
    //HashMaps for Toggabels
    private final Inventory inv;
    HashMap<String, String> launchedPlayers = new HashMap<String, String>();
    HashMap<String, String> clearedFPlayers = new HashMap<String, String>();
    Player VictimPlayer;
    ItemStack mainPage = createGuiItem(XMaterial.REDSTONE_BLOCK, true, Core.getPathCC("items.Playerselector-name"), Core.getPathCC("items.Playerselector-lore"));

    ItemStack unTroll = createGuiItem(XMaterial.BARRIER, true, Core.getPathCC("items.Untroll-name"), Core.getPathCC("items.Untroll-lore"));
    ItemStack secondPage = createGuiItem(XMaterial.ARROW, true, Core.getPathCC("items.nextpage-name"), Core.getPathCC("items.nextpage-lore"));
    ItemStack backPage = createGuiItem(XMaterial.ARROW, true, Core.getPathCC("items.backpage-name"), Core.getPathCC("items.backpage-lore"));

    public TrollInventory3(Player vic) {

        VictimPlayer = vic;
        main = this;
        Bukkit.getPluginManager().registerEvents(this, Core.instance);
        // Create a new inventory, with "this" owner for comparison with other inventories, a size of nine, called example
        inv = Bukkit.createInventory(this, 45, centerTitle(Core.getPathCC("menu.menu-title") + " - " + vic.getName()));
        // Put the items into the inventory
        initializeItems();

    }

    //To use method beyond this codeA
    public static TrollInventory3 getGUI() {
        return main;
    }

    public static HashMap getMaps(String maps) {
        switch (maps) {
            case "LP":
                return TrollInventory3.getGUI().launchedPlayers;
            case "cFP":
                return TrollInventory3.getGUI().clearedFPlayers;
        }
        // FrZLayers... creative.
        return null;
    }

    // You can call this whenever you want to put the items in

    //  ItemStack mainPage = createGuiItem(XMaterial.REDSTONE_BLOCK, true, Core.getPathCC("items.Playerselector-name"), Core.getPathCC("items.Playerselector-lore"));

    public String getNOU(Player p) {
        return Core.uid() ? p.getName() : p.getUniqueId().toString();
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
    public Inventory getInventory() {
        return inv;
    }

    public void initializeItems() {
        ItemStack plc = createGuiItem(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, false, " ");
        ItemMeta meta = plc.getItemMeta();
        meta.setDisplayName(" ");
        plc.setItemMeta(meta);
        for (Integer i = 0; i < 45; i++) {
            inv.setItem(i, plc);
        }

        inv.setItem(0,mainPage);
        inv.setItem(10, createGuiItem(XMaterial.OAK_DOOR, false, Core.tcc(Core.instance.getConfig().getString("items.fakeclose-name")), Core.tcc(Core.instance.getConfig().getString("items.fakeclose-lore"))));
        inv.setItem(11, createGuiItem(XMaterial.IRON_DOOR, false, Core.tcc(Core.instance.getConfig().getString("items.fakeban-name")), Core.tcc(Core.instance.getConfig().getString("items.fakeban-lore"))));
        inv.setItem(12, createGuiItem(XMaterial.FLINT_AND_STEEL, false, Core.tcc(Core.instance.getConfig().getString("items.burn-name")), Core.tcc(Core.instance.getConfig().getString("items.burn-lore"))));
        inv.setItem(13, createGuiItem(XMaterial.POTATO, false, Core.tcc(Core.instance.getConfig().getString("items.potato-name")), Core.tcc(Core.instance.getConfig().getString("items.potato-lore"))));
        inv.setItem(14, createGuiItem(XMaterial.CARVED_PUMPKIN, false, Core.tcc(Core.instance.getConfig().getString("items.pumpkin-name")), Core.tcc(Core.instance.getConfig().getString("items.pumpkin-lore"))));
        inv.setItem(15, createGuiItem(XMaterial.DIRT, false, Core.tcc(Core.instance.getConfig().getString("items.rainitems-name")), Core.tcc(Core.instance.getConfig().getString("items.rainitems-lore"))));
        inv.setItem(16, createGuiItem(XMaterial.CHEST, false, Core.tcc(Core.instance.getConfig().getString("items.randominv-name")), Core.tcc(Core.instance.getConfig().getString("items.randominv-lore"))));
        inv.setItem(19, createGuiItem(XMaterial.REDSTONE, false, Core.tcc(Core.instance.getConfig().getString("items.randomparticle-name")), Core.tcc(Core.instance.getConfig().getString("items.randomparticle-lore"))));
        inv.setItem(20, createGuiItem(XMaterial.ENDER_PEARL, false, Core.tcc(Core.instance.getConfig().getString("items.randomtp-name") + "§o§1 WARNING: MIGHT CAUSE THE SERVER TO LAG!"), Core.tcc(Core.instance.getConfig().getString("items.randomtp-lore"))));
        inv.setItem(21, createGuiItem(XMaterial.ENCHANTED_GOLDEN_APPLE, false, Core.tcc(Core.instance.getConfig().getString("items.rickroll-name")), Core.tcc(Core.instance.getConfig().getString("items.rickroll-lore"))));
        inv.setItem(22, createGuiItem(XMaterial.SILVERFISH_SPAWN_EGG, false, Core.tcc(Core.instance.getConfig().getString("items.silverfish-name")), Core.tcc(Core.instance.getConfig().getString("items.silverfish-lore"))));
        inv.setItem(23, createGuiItem(XMaterial.ENDERMAN_SPAWN_EGG, false, Core.tcc(Core.instance.getConfig().getString("items.slenderman-name")), Core.tcc(Core.instance.getConfig().getString("items.slenderman-lore"))));
        inv.setItem(24, createGuiItem(XMaterial.PACKED_ICE, false, Core.tcc(Core.instance.getConfig().getString("items.slipperyhands-name")), Core.tcc(Core.instance.getConfig().getString("items.slipperyhands-lore"))));
        inv.setItem(25, createGuiItem(XMaterial.DIAMOND_PICKAXE, false, Core.tcc(Core.instance.getConfig().getString("items.sneakdestroy-name")), Core.tcc(Core.instance.getConfig().getString("items.sneakdestroy-lore"))));
        inv.setItem(29, createGuiItem(XMaterial.PUMPKIN, false, Core.tcc(Core.instance.getConfig().getString("items.snowman-name")), Core.tcc(Core.instance.getConfig().getString("items.snowman-lore"))));
        inv.setItem(30, createGuiItem(XMaterial.POTION, false, Core.tcc(Core.instance.getConfig().getString("items.spin-name")), Core.tcc(Core.instance.getConfig().getString("items.spin-lore"))));
        inv.setItem(31, createGuiItem(XMaterial.POISONOUS_POTATO, false, Core.tcc(Core.instance.getConfig().getString("items.starve-name")), Core.tcc(Core.instance.getConfig().getString("items.starve-lore"))));
        inv.setItem(32, createGuiItem(XMaterial.CLOCK, false, Core.tcc(Core.instance.getConfig().getString("items.timeflash-name")), Core.tcc(Core.instance.getConfig().getString("items.timeflash-lore"))));
        inv.setItem(33, createGuiItem(XMaterial.TNT, false, Core.tcc(Core.instance.getConfig().getString("items.tnt-name")), Core.tcc(Core.instance.getConfig().getString("items.tnt-lore"))));

        //inv.setItem(36, mainPage);

        inv.setItem(40, unTroll);

        inv.setItem(36, backPage);

        inv.setItem(44, secondPage);
    }

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
            List<String> itemLore = new java.util.ArrayList<>(); // Create the variable here

            if (lore != null) {
                itemLore.addAll(Arrays.asList(lore)); // Add the config lore to our new list
            }

            itemLore.add(" "); // Empty line
            itemLore.add("§eLeft Click to enable");
            itemLore.add("§dRight Click to disable");
            itemLore.add(" "); 
            itemLore.add("§eStop all troll in case of any bug");

            meta.setLore(itemLore); // Set the NEW list to the meta
            item.setItemMeta(meta);
        }

        return item;
    }

    // You can open the inventory with this
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }
    private void notifyTroller(Player troller, ItemStack clickedItem) {
        if (clickedItem == null || !clickedItem.hasItemMeta()) return;
        Material type = clickedItem.getType();
        if (type == Material.LIGHT_GRAY_STAINED_GLASS_PANE || type == Material.CYAN_STAINED_GLASS_PANE || type == Material.ARROW || type == Material.BARRIER || type == Material.REDSTONE_BLOCK) {
            return;
        }

        // Get the name of the item from the GUI (e.g., "Herobrine")
        String trollName = clickedItem.getItemMeta().getDisplayName();

        // Send the message to the troller (the person who clicked)
        troller.sendMessage("§b§lTFR §8| §7Successfully sent §b" + trollName + " §7to §f" + VictimPlayer.getName());
        troller.playSound(troller.getLocation(), com.cryptomorin.xseries.XSound.BLOCK_NOTE_BLOCK_CHIME.parseSound(), 1f, 2f);
    }
    private void notifyUnTroller(Player untroller, ItemStack clickedItem) {
        if (clickedItem == null || !clickedItem.hasItemMeta()) return;
        Material type = clickedItem.getType();
        if (type == Material.LIGHT_GRAY_STAINED_GLASS_PANE || type == Material.CYAN_STAINED_GLASS_PANE || type == Material.ARROW || type == Material.BARRIER || type == Material.REDSTONE_BLOCK) {
            return;
        }

        // Get the name of the item from the GUI (e.g., "Herobrine")
        String trollName = clickedItem.getItemMeta().getDisplayName();

        // Send the message to the troller (the person who clicked)
        untroller.sendMessage("§b§lTFR §8| §7Successfully §cstopped §b" + trollName + " §7for §f" + VictimPlayer.getName());
        untroller.playSound(untroller.getLocation(), com.cryptomorin.xseries.XSound.BLOCK_NOTE_BLOCK_CHIME.parseSound(), 1f, 2f);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) throws IOException {
        if (e.getInventory().getHolder() != this) return;
        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == XMaterial.AIR.parseMaterial()) return;

        final Player p = (Player) e.getWhoClicked();

        if (VictimPlayer != null) {
            if (e.getSlot() < 45) {
                UnTroll stoptroll = new UnTroll();
                if (e.isLeftClick()){
                    if (e.getRawSlot() < 36) {
                        notifyTroller(p, clickedItem);
                    }
                    switch (e.getRawSlot()) {
                        case 10:
                            FakeKicks troll = new FakeKicks();
                            FakeKicks.FakeClosed(VictimPlayer);
                            break;
                        case 11:
                            FakeKicks troll2 = new FakeKicks();
                            FakeKicks.FakeBan(VictimPlayer);
                            break;
                        case 12:
                            Burn troll3 = new Burn();
                            Burn.Burn(VictimPlayer);
                            break;
                        case 13:
                            Potato troll4 = new Potato();
                            troll4.potato(VictimPlayer);
                            break;
                        case 14:
                            Pumpkin troll5 = new Pumpkin();
                            troll5.Pumpkin(VictimPlayer);
                            break;
                        case 15:
                            RainItems troll6 = new RainItems();
                            troll6.RainItem(VictimPlayer);
                            break;
                        case 16:
                            RandomInv troll7 = new RandomInv();
                            troll7.RandomInv(VictimPlayer);
                            break;
                        case 19:
                            RandomParticle troll8 = new RandomParticle();
                            troll8.RandomParticle(VictimPlayer);
                            break;
                        case 20:
                            RandomTP troll9 = new RandomTP();
                            RandomTP.RandomTP(VictimPlayer);
                            break;
                        case 21:
                            RickRoll troll10 = new RickRoll();
                            troll10.RickRoll(VictimPlayer);
                            break;
                        case 22:
                            Silverfish troll11 = new Silverfish();
                            Silverfish.Fish(VictimPlayer);
                            break;
                        case 23:
                            Slenderman troll12 = new Slenderman();
                            troll12.Enderman(VictimPlayer);
                            break;
                        case 24:
                            SlipperyHands troll13 = new SlipperyHands();
                            SlipperyHands.SlipperyHands(VictimPlayer);
                            break;
                        case 0:
                            PlayerSelectorInventory ps = new PlayerSelectorInventory();
                            ps.openSel(p);
                            break;
                        case 25:
                            SneakDestroy troll14 = new SneakDestroy();
                            troll14.SneakDestroy(VictimPlayer);
                            break;
                        case 29:
                            Snowman troll15 = new Snowman();
                            troll15.Snowman(VictimPlayer);
                            break;
                        case 30:
                            Spin troll16 = new Spin();
                            Spin.Spin(VictimPlayer);
                            break;
                        case 31:
                            Starve troll17 = new Starve();
                            troll17.Starve(VictimPlayer);
                            break;
                        case 32:
                            TimeFlash troll18 = new TimeFlash();
                            troll18.SkyFlash(VictimPlayer);
                            break;
                        case 33:
                            TNT troll19 = new TNT();
                            TNT.FakeNuke(VictimPlayer);
                            break;
                        case 36:
                            TrollInventory2 sp = new TrollInventory2(VictimPlayer.getPlayer());
                            sp.openInventory(p);
                            break;
                        case 40:

                            stoptroll.StopTrolls(VictimPlayer, p);

                            String message2 = (String) Core.instance.getConfig().get("untrolled");
                            String replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.getPlayer().getName());
                            p.sendMessage(replaced2);
                            break;
                        case 44:
                            TrollInventory4 sp2 = new TrollInventory4(VictimPlayer.getPlayer());
                            sp2.openInventory(p);
                            break;
                    }
                }else if (e.isRightClick()){
                    switch (e.getRawSlot()) {
                        case 12:
                            stoptroll.stopSpecificTroll(VictimPlayer, "burn", p);
                            break;
                        case 13:
                            stoptroll.stopSpecificTroll(VictimPlayer, "potato", p);
                            break;
                        case 14:
                            stoptroll.stopSpecificTroll(VictimPlayer, "pumpkin", p);
                            break;
                        case 15:
                            stoptroll.stopSpecificTroll(VictimPlayer, "rainitems", p);
                            break;
                        case 16:
                            stoptroll.stopSpecificTroll(VictimPlayer, "randominv", p);
                            break;
                        case 19:
                            stoptroll.stopSpecificTroll(VictimPlayer, "randomparticle", p);
                            break;
                        case 21:
                            stoptroll.stopSpecificTroll(VictimPlayer, "rickroll", p);
                            break;
                        case 22:
                            stoptroll.stopSpecificTroll(VictimPlayer, "silverfish", p);
                            break;
                        case 23:
                            stoptroll.stopSpecificTroll(VictimPlayer, "slenderman", p);
                            break;
                        case 24:
                            stoptroll.stopSpecificTroll(VictimPlayer, "slipperyhands", p);
                            break;
                        case 0:
                            PlayerSelectorInventory ps = new PlayerSelectorInventory();
                            ps.openSel(p);
                            break;
                        case 25:
                            stoptroll.stopSpecificTroll(VictimPlayer, "sneakdestroy", p);
                            break;
                        case 30:
                            stoptroll.stopSpecificTroll(VictimPlayer, "spin", p);
                            break;
                        case 31:
                            stoptroll.stopSpecificTroll(VictimPlayer, "starve", p);
                            break;
                        case 32:
                            stoptroll.stopSpecificTroll(VictimPlayer, "skyflash", p);
                            break;
                        case 36:
                            TrollInventory2 sp = new TrollInventory2(VictimPlayer.getPlayer());
                            sp.openInventory(p);
                            break;
                        case 40:

                            stoptroll.StopTrolls(VictimPlayer, p);

                            String message2 = (String) Core.instance.getConfig().get("untrolled");
                            String replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.getPlayer().getName());
                            p.sendMessage(replaced2);
                            break;
                        case 44:
                            TrollInventory4 sp2 = new TrollInventory4(VictimPlayer.getPlayer());
                            sp2.openInventory(p);
                            break;
                    }
                }
            }
        }
    }
}

