package me.leodev.trollingfreedomreborn.ui;

import me.leodev.trollingfreedomreborn.commands.*;
import me.leodev.trollingfreedomreborn.main.Core;
import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import me.leodev.trollingfreedomreborn.trolls.chat.NickWithoutEss;
import me.leodev.trollingfreedomreborn.trolls.classics.Herobrine;
import me.leodev.trollingfreedomreborn.trolls.classics.OP;
import me.leodev.trollingfreedomreborn.trolls.explosion.ExplodingChicken;
import me.leodev.trollingfreedomreborn.trolls.explosion.ExplosiveSheep;
import me.leodev.trollingfreedomreborn.trolls.explosion.KittyCannon;
import me.leodev.trollingfreedomreborn.trolls.fakestuff.FakeKicks;
import me.leodev.trollingfreedomreborn.trolls.fakestuff.FakeReload;
import me.leodev.trollingfreedomreborn.trolls.inventory.InventoryStop;
import me.leodev.trollingfreedomreborn.trolls.inventory.Invsee;
import me.leodev.trollingfreedomreborn.trolls.inventory.LockInventory;
import me.leodev.trollingfreedomreborn.trolls.movement.*;
import me.leodev.trollingfreedomreborn.trolls.random.HideAllPlayers;
import me.leodev.trollingfreedomreborn.trolls.random.InstaToolBreak;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class TrollInventory2 implements Listener, InventoryHolder {

    static TrollInventory2 main;
    //HashMaps for Toggabels
    private final Inventory inv;
    HashMap<String, String> launchedPlayers = new HashMap<String, String>();
    HashMap<String, String> clearedFPlayers = new HashMap<String, String>();
    Player VictimPlayer;
    ItemStack mainPage = createGuiItem(XMaterial.REDSTONE_BLOCK, true, Core.getPathCC("items.Playerselector-name"), Core.getPathCC("items.Playerselector-lore"));

    ItemStack unTroll = createGuiItem(XMaterial.BARRIER, true, Core.getPathCC("items.Untroll-name"), Core.getPathCC("items.Untroll-lore"));
    ItemStack secondPage = createGuiItem(XMaterial.ARROW, true, Core.getPathCC("items.nextpage-name"), Core.getPathCC("items.nextpage-lore"));
    ItemStack backPage = createGuiItem(XMaterial.ARROW, true, Core.getPathCC("items.backpage-name"), Core.getPathCC("items.backpage-lore"));

    public TrollInventory2(Player vic) {

        VictimPlayer = vic;
        main = this;
        Bukkit.getPluginManager().registerEvents(this, Core.instance);
        // Create a new inventory, with "this" owner for comparison with other inventories, a size of nine, called example
        inv = Bukkit.createInventory(this, 45, centerTitle(Core.getPathCC("menu.menu-title") + " - " + vic.getName()));
        // Put the items into the inventory
        initializeItems();

    }

    //To use method beyond this codeA
    public static TrollInventory2 getGUI() {
        return main;
    }

    public static HashMap getMaps(String maps) {
        switch (maps) {
            case "LP":
                return TrollInventory2.getGUI().launchedPlayers;
            case "cFP":
                return TrollInventory2.getGUI().clearedFPlayers;
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
        inv.setItem(14, createGuiItem(XMaterial.LINGERING_POTION, Core.isTrollActive(VictimPlayer, "forcejump"), Core.tcc(Core.instance.getConfig().getString("items.forcejump-name")), Core.tcc(Core.instance.getConfig().getString("items.forcejump-lore"))));
        inv.setItem(15, createGuiItem(XMaterial.SOUL_SAND, Core.isTrollActive(VictimPlayer, "freeze"), Core.tcc(Core.instance.getConfig().getString("items.freeze-name")), Core.tcc(Core.instance.getConfig().getString("items.freeze-lore"))));
        inv.setItem(19, createGuiItem(XMaterial.PLAYER_HEAD, Core.isTrollActive(VictimPlayer, "hideallplayers"), Core.tcc(Core.instance.getConfig().getString("items.hideallplayers-name")), Core.tcc(Core.instance.getConfig().getString("items.hideallplayers-lore"))));
        inv.setItem(20, createGuiItem(XMaterial.GOLDEN_PICKAXE, Core.isTrollActive(VictimPlayer, "instatoolbreak"), Core.tcc(Core.instance.getConfig().getString("items.instatoolbreak-name")), Core.tcc(Core.instance.getConfig().getString("items.instatoolbreak-lore"))));
        inv.setItem(21, createGuiItem(XMaterial.CHEST, Core.isTrollActive(VictimPlayer, "inventorystop"), Core.tcc(Core.instance.getConfig().getString("items.inventorystop-name")), Core.tcc(Core.instance.getConfig().getString("items.inventorystop-lore"))));
        inv.setItem(23, createGuiItem(XMaterial.CAT_SPAWN_EGG, Core.isTrollActive(VictimPlayer, "kittycannon"), Core.tcc(Core.instance.getConfig().getString("items.kittycannon-name")), Core.tcc(Core.instance.getConfig().getString("items.kittycannon-lore"))));
        inv.setItem(29, createGuiItem(XMaterial.FLINT_AND_STEEL, Core.isTrollActive(VictimPlayer, "lightning"), Core.tcc(Core.instance.getConfig().getString("items.lightning-name")), Core.tcc(Core.instance.getConfig().getString("items.lightning-lore"))));
        inv.setItem(31, createGuiItem(XMaterial.NAME_TAG, Core.isTrollActive(VictimPlayer, "nick"), Core.tcc(Core.instance.getConfig().getString("items.nick-name")), Core.tcc(Core.instance.getConfig().getString("items.nick-lore"))));
        inv.setItem(0,mainPage);
        inv.setItem(10, createGuiItem(XMaterial.CHICKEN, false, Core.tcc(Core.instance.getConfig().getString("items.explodingchicken-name")), Core.tcc(Core.instance.getConfig().getString("items.explodingchicken-lore"))));
        inv.setItem(11, createGuiItem(XMaterial.MUTTON, false, Core.tcc(Core.instance.getConfig().getString("items.explosivesheep-name")), Core.tcc(Core.instance.getConfig().getString("items.explosivesheep-lore"))));
        inv.setItem(12, createGuiItem(XMaterial.TNT_MINECART, false, Core.tcc(Core.instance.getConfig().getString("items.fakecrash-name")), Core.tcc(Core.instance.getConfig().getString("items.fakecrash-lore"))));
        inv.setItem(13, createGuiItem(XMaterial.DEBUG_STICK, false, Core.tcc(Core.instance.getConfig().getString("items.fakereload-name")), Core.tcc(Core.instance.getConfig().getString("items.fakereload-lore"))));
        // 1. Create a basic PLAYER_HEAD item
        ItemStack herobrineHead = new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial(), 1);
        SkullMeta herobrineMeta = (SkullMeta) herobrineHead.getItemMeta();

        if (herobrineMeta != null) {
            // 2. Set the Name and Lore manually
            herobrineMeta.setDisplayName(Core.tcc(Core.instance.getConfig().getString("items.herobrine-name")));

            // Using an ArrayList so we can add our custom Left/Right click lines
            java.util.List<String> herobrineLore = new java.util.ArrayList<>();
            String configLore = Core.instance.getConfig().getString("items.herobrine-lore");
            if (configLore != null) {
                herobrineLore.add(Core.tcc(configLore));
            }
            herobrineLore.add(" ");
            herobrineLore.add("§eLeft Click to enable");
            herobrineLore.add("§dRight Click to disable");
            herobrineLore.add(" "); 
            herobrineLore.add("§eStop all troll in case of any bug");
            herobrineMeta.setLore(herobrineLore);

            // 3. Set the specific Skin
            herobrineMeta.setOwner("her0brine");

            // 4. Apply meta back to the item
            herobrineHead.setItemMeta(herobrineMeta);
        }

// 5. Place it in the inventory
        inv.setItem(16, herobrineHead);
        inv.setItem(22, createGuiItem(XMaterial.CHEST, false, Core.tcc(Core.instance.getConfig().getString("items.invsee-name")), Core.tcc(Core.instance.getConfig().getString("items.invsee-lore"))));
        inv.setItem(24, createGuiItem(XMaterial.COBWEB, false, Core.tcc(Core.instance.getConfig().getString("items.lag-name")), Core.tcc(Core.instance.getConfig().getString("items.lag-lore"))));
        inv.setItem(25, createGuiItem(XMaterial.WATER_BUCKET, false, Core.tcc(Core.instance.getConfig().getString("items.launch-name")), Core.tcc(Core.instance.getConfig().getString("items.launch-lore"))));
        inv.setItem(30, createGuiItem(XMaterial.CHEST, false, Core.tcc(Core.instance.getConfig().getString("items.lockinventory-name")), Core.tcc(Core.instance.getConfig().getString("items.lockinventory-lore"))));
        inv.setItem(32, createGuiItem(XMaterial.DIAMOND_BLOCK, false, Core.tcc(Core.instance.getConfig().getString("items.op-name")), Core.tcc(Core.instance.getConfig().getString("items.op-lore"))));
        inv.setItem(33, createGuiItem(XMaterial.COAL_BLOCK, false, Core.tcc(Core.instance.getConfig().getString("items.unop-name")), Core.tcc(Core.instance.getConfig().getString("items.unop-lore"))));

        //inv.setItem(36, mainPage);

        inv.setItem(40, unTroll);

        inv.setItem(36, backPage);

        inv.setItem(44, secondPage);
    }

    protected ItemStack createGuiItem(final XMaterial xmat, final boolean isEnchanted, final String name, final String... lore) {
        org.bukkit.Material material = xmat.parseMaterial();
        if (material == null) material = org.bukkit.Material.BARRIER;

        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);

            if (isEnchanted) {
                meta.addEnchant(Enchantment.UNBREAKING, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }

            List<String> itemLore = new java.util.ArrayList<>();
            if (lore != null) {
                itemLore.addAll(Arrays.asList(lore));
            }

            itemLore.add(" ");
            itemLore.add(isEnchanted ? "§a§l▶ STATUS: ACTIVE" : "§eLeft Click to enable");
            itemLore.add("§dRight Click to disable");
            itemLore.add(" ");
            itemLore.add("§8§oStop all trolls if bugs occur");

            meta.setLore(itemLore);
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
        List<Integer> needsConfirm = Arrays.asList(19);


        if (VictimPlayer != null) {
            if (e.getSlot() < 45) {
                UnTroll stoptroll = new UnTroll();
                if (e.isLeftClick()){
                    if (e.getRawSlot() < 36 && e.getRawSlot() != 0 && !needsConfirm.contains(e.getRawSlot())) {
                        notifyTroller(p, clickedItem);
                    }
                    switch (e.getRawSlot()) {
                        case 10:
                            ExplodingChicken troll = new ExplodingChicken();
                            ExplodingChicken.Chicken(VictimPlayer);
                            break;
                        case 11:
                            ExplosiveSheep troll2 = new ExplosiveSheep();
                            troll2.Sheep(VictimPlayer);
                            break;
                        case 12:
                            FakeKicks troll3 = new FakeKicks();
                            FakeKicks.FakeCrash(VictimPlayer);
                            break;
                        case 13:
                            FakeReload troll4 = new FakeReload();
                            troll4.Reload(VictimPlayer);
                            break;
                        case 14:
                            ForceJump troll5 = new ForceJump();
                            troll5.Jump(VictimPlayer);
                            break;
                        case 15:
                            Freeze troll6 = new Freeze();
                            troll6.Freeze(VictimPlayer);
                            break;
                        case 16:
                            if (Bukkit.getServer().getPluginManager().getPlugin("Citizens") != null) {
                                Herobrine troll7 = new Herobrine();
                                Herobrine.Herobrine(VictimPlayer);
                            } else {
                                p.sendMessage("§3TFR§8: §7Please install Citizens for this troll to work");
                                p.sendMessage("§3TFR§8: §7§nhttps://ci.citizensnpcs.co/job/citizens2/");
                            }
                            break;
                        case 19:
                            p.sendMessage("§b§lTFR §8| §7Are you sure you want to §3§lhide all players §7from " + VictimPlayer.getName() + "?");

                            new ConfirmIH(p, "§7Confirm §3§lHide All Players §7On §l" + VictimPlayer.getName(), Material.TNT, true, new BiConsumer<Player, Boolean>() {

                                @Override
                                public void accept(Player p, Boolean bool) {

                                    if (bool) {

                                        //code to execute if bool is true

                                        //p.sendMessage("true");
                                        HideAllPlayers troll8 = new HideAllPlayers();
                                        troll8.HideAll(VictimPlayer);
                                        p.closeInventory();
                                        notifyTroller(p, clickedItem);
                                    } else {
                                        //p.sendMessage("false");

                                        //code to execute if bool is false
                                        p.closeInventory();

                                    }

                                }
                            }, player -> {
                                TrollInventory4 sp2 = new TrollInventory4(VictimPlayer.getPlayer());
                                sp2.openInventory(p);
                            }, "§b§lTFR §8| §7Confirm §3§lHide All", Core.instance);
                            break;
                        case 20:
                            InstaToolBreak troll9 = new InstaToolBreak();
                            troll9.InstaToolBreak(VictimPlayer);
                            break;
                        case 21:
                            InventoryStop troll10 = new InventoryStop();
                            troll10.InventoryStop(VictimPlayer);
                            break;
                        case 22:
                            Invsee troll11 = new Invsee();
                            Invsee.Invsee(VictimPlayer);
                            break;
                        case 23:
                            KittyCannon troll12 = new KittyCannon();
                            troll12.KittyCannon(VictimPlayer);
                            break;
                        case 24:
                            Lag troll13 = new Lag();
                            Lag.Lagg(VictimPlayer);
                            break;
                        case 25:
                            Launch troll14 = new Launch();
                            Launch.Launch(VictimPlayer);
                            break;
                        case 29:
                            Lightning troll15 = new Lightning();
                            troll15.Lightning(VictimPlayer);
                            break;
                        case 30:
                            LockInventory troll16 = new LockInventory();
                            LockInventory.Lock(VictimPlayer);
                            break;
                        case 31:
                            if (Bukkit.getServer().getPluginManager().getPlugin("Essentials") != null) {
                                NickWithoutEss troll17 = new NickWithoutEss();
                                troll17.NickName(VictimPlayer);
                            } else {
                                p.sendMessage("§3TFR§8: §7Please install Essentials for this troll to work");
                                p.sendMessage("§3TFR§8: §7§nhttps://www.spigotmc.org/resources/essentialsx.9089/");
                            }

                            break;
                        case 32:
                            OP troll18 = new OP();
                            OP.FakeOP(VictimPlayer);
                            break;
                        case 33:
                            OP troll19 = new OP();
                            OP.FakeDeOP(VictimPlayer);
                            break;
                        case 36:
                            TrollInventory sp1 = new TrollInventory(VictimPlayer.getPlayer());
                            sp1.openInventory(p);
                            break;
                        case 40:

                            stoptroll.StopTrolls(VictimPlayer, p);

                            String message2 = (String) Core.instance.getConfig().get("untrolled");
                            String replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.getPlayer().getName());
                            p.sendMessage(replaced2);
                            break;
                        case 44:
                            TrollInventory3 sp = new TrollInventory3(VictimPlayer.getPlayer());
                            sp.openInventory(p);
                            break;
                        case 0:
                            PlayerSelectorInventory ps = new PlayerSelectorInventory();
                            ps.openSel(p);
                            break;
                    }
                } else if (e.isRightClick()){
                    switch (e.getRawSlot()) {
                        case 14:
                            stoptroll.stopSpecificTroll(VictimPlayer, "forcejump", p);
                            break;
                        case 15:
                            stoptroll.stopSpecificTroll(VictimPlayer, "freeze", p);
                            break;
                        case 19:
                            stoptroll.stopSpecificTroll(VictimPlayer, "hideallplayers", p);
                            break;
                        case 20:
                            stoptroll.stopSpecificTroll(VictimPlayer, "instatoolbreak", p);
                            break;
                        case 21:
                            stoptroll.stopSpecificTroll(VictimPlayer, "inventorystop", p);
                            break;
                        case 23:
                            stoptroll.stopSpecificTroll(VictimPlayer, "kittycannon", p);
                            break;
                        case 24:
                            stoptroll.stopSpecificTroll(VictimPlayer, "lag", p);
                            break;
                        case 29:
                            stoptroll.stopSpecificTroll(VictimPlayer, "lightning", p);
                            break;
                        case 30:
                            stoptroll.stopSpecificTroll(VictimPlayer, "lockinventory", p);
                            break;
                        case 31:
                            stoptroll.stopSpecificTroll(VictimPlayer, "nick", p);
                            break;
                        case 36:
                            TrollInventory sp1 = new TrollInventory(VictimPlayer.getPlayer());
                            sp1.openInventory(p);
                            break;
                        case 40:

                            stoptroll.StopTrolls(VictimPlayer, p);

                            String message2 = (String) Core.instance.getConfig().get("untrolled");
                            String replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.getPlayer().getName());
                            p.sendMessage(replaced2);
                            break;
                        case 44:
                            TrollInventory3 sp = new TrollInventory3(VictimPlayer.getPlayer());
                            sp.openInventory(p);
                            break;
                        case 0:
                            PlayerSelectorInventory ps = new PlayerSelectorInventory();
                            ps.openSel(p);
                            break;
                    }
                }
            }
            initializeItems();

            // 2. Force the player's client to visual sync the changes
            p.updateInventory();
        }
    }
}