package me.leodev.trollingfreedomreborn.main;

import me.leodev.trollingfreedomreborn.commands.*;
import me.leodev.trollingfreedomreborn.other.*;
import com.cryptomorin.xseries.XEnchantment;
import me.leodev.trollingfreedomreborn.other.EventListener;
import me.leodev.trollingfreedomreborn.trolls.Beds.BedExplosion;
import me.leodev.trollingfreedomreborn.trolls.Beds.BedMissing;
import me.leodev.trollingfreedomreborn.trolls.Beds.BedNight;
import me.leodev.trollingfreedomreborn.trolls.random.*;
import me.leodev.trollingfreedomreborn.trolls.chat.ChatChange;
import me.leodev.trollingfreedomreborn.trolls.chat.Deafen;
import me.leodev.trollingfreedomreborn.trolls.chat.ExplodeOnChat;
import me.leodev.trollingfreedomreborn.trolls.chat.ReverseMessage;
import me.leodev.trollingfreedomreborn.trolls.classics.*;
import me.leodev.trollingfreedomreborn.trolls.explosion.*;
import me.leodev.trollingfreedomreborn.trolls.fakestuff.FakeKicks;
import me.leodev.trollingfreedomreborn.trolls.fakestuff.FakeReload;
import me.leodev.trollingfreedomreborn.trolls.inventory.*;
import me.leodev.trollingfreedomreborn.trolls.movement.*;
import me.leodev.trollingfreedomreborn.trolls.packettrolls.Credits;
import me.leodev.trollingfreedomreborn.trolls.packettrolls.Demo;
import me.leodev.trollingfreedomreborn.trolls.packettrolls.WorldLoading;
import me.leodev.trollingfreedomreborn.trolls.random.Void;
import me.leodev.trollingfreedomreborn.ui.ConfirmIH;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;


public class Core extends JavaPlugin implements Listener {

    public static Core instance;
    static Boolean usingUUID = false;
    public FileConfiguration config = getConfig();
    private final File file = null;

    public Core() {

        if ((instance) == null)
            instance = this;

    }


    public static String tcc(String i) {
        i = ChatColor.translateAlternateColorCodes('&', i);
        return i;
    }

    public static Boolean uid() {
        return usingUUID;
    }

    public static String getPathCC(String path) {
        String c = Core.instance.getConfig().getString(path);
        assert c != null;
        c = ChatColor.translateAlternateColorCodes('&', c);
        return c;
    }

    public static Boolean advCheck(String perm, Player p) {
        if (instance.getConfig().getBoolean("values.advanced-perms.enabled")) {
            if (usingUUID) {
                OfflinePlayer pp = Bukkit.getPlayerExact(instance.getConfig().getString("values.advanced-perms.playername"));
                if (pp.isOnline() && pp.hasPlayedBefore()) {
                    return p.getUniqueId().equals(pp.getUniqueId());
                }
            } else {
                return p.getName().equals(instance.getConfig().getString("values.advanced-perms.playername"));
            }
        } else {
            return p.hasPermission(perm);
        }
        return false;
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        saveDefaultConfig();
        config = getConfig();
        config.options().copyDefaults(true);
    }

    public ItemStack getSkull() {
        ItemStack skull = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGY0YzY3MzZjYjFjNjg4MGQ4MGEyMDYyZDdhYTRhYWIxZWEwYzU1YmYxNDJhZDMwZmQ1MmM1NzUxNWYwYzJkMSJ9fX0=");
        ItemMeta skullmeta = skull.getItemMeta();
        skullmeta.setDisplayName("§b§lTrolling§3§lFreedomReborn §7| §bTroll GUI");
        skullmeta.setLore(Arrays.asList("§0§k000000000", "§7§k777777", "§9§k999999", "§a§k§l133742069YEET!"));
        skullmeta.addEnchant(XEnchantment.UNBREAKING.getEnchant(), 1, false);
        skullmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        skull.setItemMeta(skullmeta);
        return skull;
    }

    public static boolean canTroll(Player target) {
        // 1. Check the allow-troll-op setting
        boolean allowOp = Core.instance.getConfig().getBoolean("allow-troll-op", false);
        if (target.isOp() && !allowOp) {
            return false;
        }

        // 2. Check the blocklist
        List<String> blocklist = Core.instance.getConfig().getStringList("blocklist");
        if (blocklist.contains(target.getName())) {
            return false;
        }

        return true;
    }

    @Override
    public void onEnable() {
        setupConfig();

        if (getConfig().getBoolean("values.dependency-downloader")) {
            try {
                DependencyChecker chk = new DependencyChecker();
                DependencyChecker.DependencyChecker();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!getConfig().getBoolean("values.dependency-downloader")) {
                return;
            }
        }

        getCommand("trollingfreedom").setExecutor(new Help());
        getCommand("untroll").setExecutor(new UnTroll());
        getCommand("untroll").setTabCompleter(new UnTroll());

        getCommand("control").setExecutor(new Control());

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);
        getCommand("trollf").setExecutor(new TrollWithCMD());
        getCommand("panicstoptroll").setExecutor(new Panic());
        getCommand("trollf").setTabCompleter(new TrollWithCMD());


        getCommand("trollgui").setExecutor(new TrollGUIAlias());

        getServer().getPluginManager().registerEvents(new AFK(), this);
        getServer().getPluginManager().registerEvents(new OP(), this);
        getServer().getPluginManager().registerEvents(new Demo(), this);
        getServer().getPluginManager().registerEvents(new TNT(), this);
        getServer().getPluginManager().registerEvents(new Credits(), this);
        getServer().getPluginManager().registerEvents(new Freeze(), this);
        getServer().getPluginManager().registerEvents(new Spin(), this);
        getServer().getPluginManager().registerEvents(new Launch(), this);
        getServer().getPluginManager().registerEvents(new Herobrine(), this);
        getServer().getPluginManager().registerEvents(new FakeKicks(), this);
        getServer().getPluginManager().registerEvents(new Break(), this);
        getServer().getPluginManager().registerEvents(new Potato(), this);
        getServer().getPluginManager().registerEvents(new Void(), this);
        getServer().getPluginManager().registerEvents(new Annoy(), this);
        getServer().getPluginManager().registerEvents(new Vomit(), this);
        getServer().getPluginManager().registerEvents(new WorldLoading(), this);
        getServer().getPluginManager().registerEvents(new Pumpkin(), this);
        getServer().getPluginManager().registerEvents(new Sounds(), this);
        getServer().getPluginManager().registerEvents(new AnvilDrop(), this);
        getServer().getPluginManager().registerEvents(new InventoryStop(), this);
        getServer().getPluginManager().registerEvents(new Slenderman(), this);
        getServer().getPluginManager().registerEvents(new CreeperAwMan(), this);
        getServer().getPluginManager().registerEvents(new Coffin(), this);
        getServer().getPluginManager().registerEvents(new DropAll(), this);
        getServer().getPluginManager().registerEvents(new Cage(), this);
        getServer().getPluginManager().registerEvents(new Starve(), this);
        getServer().getPluginManager().registerEvents(new ChatChange(), this);
        getServer().getPluginManager().registerEvents(new Invsee(), this);
        getServer().getPluginManager().registerEvents(new RandomTP(), this);
        getServer().getPluginManager().registerEvents(new RandomCrafts(), this);
        getServer().getPluginManager().registerEvents(new Lightning(), this);
        getServer().getPluginManager().registerEvents(new HideAllPlayers(), this);
        getServer().getPluginManager().registerEvents(new TimeFlash(), this);
        getServer().getPluginManager().registerEvents(new TNTPlace(), this);
        getServer().getPluginManager().registerEvents(new Aquaphobia(), this);
        getServer().getPluginManager().registerEvents(new SlipperyHands(), this);
        getServer().getPluginManager().registerEvents(new LockInventory(), this);
        getServer().getPluginManager().registerEvents(new SneakDestroy(), this);
        getServer().getPluginManager().registerEvents(new InstaToolBreak(), this);
        getServer().getPluginManager().registerEvents(new Burn(), this);
        getServer().getPluginManager().registerEvents(new EntityMultiply(), this);
        getServer().getPluginManager().registerEvents(new BedExplosion(), this);
        getServer().getPluginManager().registerEvents(new Lag(), this);
        getServer().getPluginManager().registerEvents(new FakeReload(), this);
        getServer().getPluginManager().registerEvents(new ExplodingChicken(), this);
        getServer().getPluginManager().registerEvents(new ExplosiveSheep(), this);
        getServer().getPluginManager().registerEvents(new MathUtils(), this);
        getServer().getPluginManager().registerEvents(new RickRoll(), this);
        getServer().getPluginManager().registerEvents(new RandomInv(), this);
        getServer().getPluginManager().registerEvents(new RandomParticle(), this);
        getServer().getPluginManager().registerEvents(new AllEntitiesDie(), this);
        getServer().getPluginManager().registerEvents(new ForceJump(), this);
        getServer().getPluginManager().registerEvents(new Deafen(), this);
        getServer().getPluginManager().registerEvents(new KittyCannon(), this);
        getServer().getPluginManager().registerEvents(new BedMissing(), this);
        getServer().getPluginManager().registerEvents(new ExplodeOnChat(), this);
        getServer().getPluginManager().registerEvents(new InventoryRave(), this);
        getServer().getPluginManager().registerEvents(new InvertWalk(), this);
        getServer().getPluginManager().registerEvents(new UntrollOnQuit(), this);
        getServer().getPluginManager().registerEvents(new BedNight(), this);
        getServer().getPluginManager().registerEvents(new ReverseMessage(), this);
        getServer().getPluginManager().registerEvents(new RainItems(), this);
        getServer().getPluginManager().registerEvents(new Poop(), this);
        getServer().getPluginManager().registerEvents(new ConfirmIH(), this);
        getServer().getPluginManager().registerEvents(new Control(), this);
        getServer().getPluginManager().registerEvents(new RingOfFire(), this);

        super.onEnable();
        reloadConfig();
        usingUUID = getConfig().getBoolean("values.using-uuid");
        try {

            Field f = null;
            f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap commandMap = (CommandMap) f.get(Bukkit.getServer());

            new TrollCommand(commandMap, this);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            getLogger().severe(e.getLocalizedMessage());
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
        usingUUID = getServer().getOnlineMode();


        new UpdateChecker(this, 131388).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                String message1 = (String) Core.instance.getConfig().get("trollingfreedom-console-no-update");
                String replaced1 = message1.replace("&", "§");
                getServer().getConsoleSender().sendMessage(replaced1);
            } else {
                getServer().getConsoleSender().sendMessage(ChatColor.RED + "=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                getServer().getConsoleSender().sendMessage(ChatColor.RED + "There is an update to Trolling Freedom Reborn!");
                getServer().getConsoleSender().sendMessage(ChatColor.RED + "Please check spigot page, modrinth page or github for updates for §bTrolling§3§lFreedomReborn");
                getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Github: §nhttps://github.com/NotDefined67/TrollingFreedomReborn");
                getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Spigot: §nhttps://www.spigotmc.org/resources/.131388/");
                getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Modrinth: §nhttps://modrinth.com/plugin/trollingfreedomreborn");
                getServer().getConsoleSender().sendMessage(ChatColor.RED + "=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).forEach(op -> {
                    op.sendMessage(ChatColor.RED + "=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    op.sendMessage(ChatColor.RED + "There is an update to Trolling Freedom Reborn!");
                    op.sendMessage(ChatColor.RED + "Please check spigot page, modrinth page or github for updates for §bTrolling§3§lFreedomReborn");
                    op.sendMessage(ChatColor.LIGHT_PURPLE + "Github: §nhttps://github.com/NotDefined67/TrollingFreedomReborn");
                    op.sendMessage(ChatColor.LIGHT_PURPLE + "Spigot: §nhttps://www.spigotmc.org/resources/.131388/");
                    op.sendMessage(ChatColor.LIGHT_PURPLE + "Modrinth: §nhttps://modrinth.com/plugin/trollingfreedomreborn");
                    op.sendMessage(ChatColor.RED + "=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                });
            }
        });
    }
    public final Map<UUID, Map<String, List<Integer>>> individualTasks = new HashMap<>();

    public void addTask(Player player, String trollName, int taskId) {
        UUID uuid = player.getUniqueId();
        individualTasks.computeIfAbsent(uuid, k -> new HashMap<>());
        individualTasks.get(uuid).computeIfAbsent(trollName, k -> new ArrayList<>()).add(taskId);
    }

    public String getP() {
        return tcc(this.config.getString("prefix"));
    }

    public String getPrefixedMessage(String configPath) {
        return tcc(this.config.getString("prefix") + this.config.get(configPath));
    }

    @Override
    public void onDisable() {

        this.getServer().getLogger().info("§b§lTrolling§3§lFreedomReborn §7| §cDisabling");
        this.getServer().getLogger().info("§c§lDisabled");

    }
    public void setupConfig() {
        // 1. Load the existing config
        FileConfiguration config = getConfig();

        // 2. Define the defaults
        List<String> defaultBlocklist = Arrays.asList("LeoMadrassiDev", "Herobrine");

        // 3. Add defaults if they don't exist
        config.addDefault("blocklist", defaultBlocklist);
        config.addDefault("allow-troll-op", false);

        // 4. Copy defaults so they are physically written to the file
        config.options().copyDefaults(true);
        saveConfig();

        // 5. Add the Comments (Spigot/Paper 1.18.2+)
        // This makes the config easy for admins to understand
        config.setComments("blocklist", Collections.singletonList("Players in this list cannot be trolled"));
        config.setComments("allow-troll-op", Collections.singletonList("If true, Operators can be trolled. If false, they are protected."));

        // Save again to write the comments
        saveConfig();
    }
    public static boolean isTrollActive(Player t, String trollKey) {
        if (t == null) return false;
        String name = t.getName();
        switch (trollKey.toLowerCase()) {
            case "annoy": return me.leodev.trollingfreedomreborn.trolls.random.Annoy.Annoy1.contains(name);
            case "aquaphobia": return me.leodev.trollingfreedomreborn.trolls.random.Aquaphobia.Aqua1.contains(name);
            case "bedexplosion": return me.leodev.trollingfreedomreborn.trolls.Beds.BedExplosion.Explode1.contains(name);
            case "stopblockbreakplace": return me.leodev.trollingfreedomreborn.trolls.random.Break.Break1.contains(name);
            case "cage": return me.leodev.trollingfreedomreborn.trolls.movement.Cage.Cage1.contains(name);
            case "randomchat": return me.leodev.trollingfreedomreborn.trolls.chat.ChatChange.Chat1.contains(name);
            case "entitymultiply": return me.leodev.trollingfreedomreborn.trolls.random.EntityMultiply.EntityMultiply1.contains(name);
            case "creeperawman": return me.leodev.trollingfreedomreborn.trolls.classics.CreeperAwMan.Creeper1.contains(name);
            case "deafen": return me.leodev.trollingfreedomreborn.trolls.chat.Deafen.Deaf1.contains(name);
            case "forcejump": return me.leodev.trollingfreedomreborn.trolls.movement.ForceJump.Jump1.contains(name);
            case "freeze": return me.leodev.trollingfreedomreborn.trolls.movement.Freeze.frozen.contains(name);
            case "hideallplayers": return me.leodev.trollingfreedomreborn.trolls.random.HideAllPlayers.hide1.contains(name);
            case "instatoolbreak": return me.leodev.trollingfreedomreborn.trolls.random.InstaToolBreak.InstaToolBreak1.contains(name);
            case "inventorystop": return me.leodev.trollingfreedomreborn.trolls.inventory.InventoryStop.InvStop1.contains(name);
            case "kittycannon": return me.leodev.trollingfreedomreborn.trolls.explosion.KittyCannon.Kitty1.contains(name);
            case "lightning": return me.leodev.trollingfreedomreborn.trolls.movement.Lightning.Lightning1.contains(name);
            case "nick": return me.leodev.trollingfreedomreborn.trolls.chat.Nick.Nick1.contains(name);
            case "burn": return t.getFireTicks() > 0;
            case "potato": return me.leodev.trollingfreedomreborn.trolls.random.Potato.Break1.contains(name);
            case "pumpkin": return me.leodev.trollingfreedomreborn.trolls.classics.Pumpkin.Pumpkin1.contains(name);
            case "rainitems": return me.leodev.trollingfreedomreborn.trolls.random.RainItems.Rain1.contains(name);
            case "randominv": return me.leodev.trollingfreedomreborn.trolls.inventory.RandomInv.RandomInv1.contains(name);
            case "randomparticle": return me.leodev.trollingfreedomreborn.trolls.random.RandomParticle.RandomParticle1.contains(name);
            case "rickroll": return me.leodev.trollingfreedomreborn.trolls.classics.RickRoll.Rick1.contains(name);
            case "silverfish": return me.leodev.trollingfreedomreborn.trolls.random.Silverfish.Fish1.contains(name);
            case "slenderman": return me.leodev.trollingfreedomreborn.trolls.classics.Slenderman.Slender1.contains(name);
            case "sneakdestroy": return me.leodev.trollingfreedomreborn.trolls.movement.SneakDestroy.Sneak1.contains(name);
            case "spin": return me.leodev.trollingfreedomreborn.trolls.classics.Spin.spin1.contains(name);
            case "starve": return me.leodev.trollingfreedomreborn.trolls.random.Starve.starve1.contains(name);
            case "skyflash": return me.leodev.trollingfreedomreborn.trolls.random.TimeFlash.flash1.contains(name);
            case "tntplace": return me.leodev.trollingfreedomreborn.trolls.explosion.TNTPlace.Fireball1.contains(name);
            case "vomit": return me.leodev.trollingfreedomreborn.trolls.random.Vomit.Vomit1.contains(name);
            case "explodeonchat": return me.leodev.trollingfreedomreborn.trolls.chat.ExplodeOnChat.Chat1.contains(name);
            case "inventoryrave": return me.leodev.trollingfreedomreborn.trolls.inventory.InventoryRave.Rave1.contains(name);
            case "invert": return me.leodev.trollingfreedomreborn.trolls.movement.InvertWalk.Invert1.contains(name);
            case "stopsleep": return me.leodev.trollingfreedomreborn.trolls.Beds.BedNight.Bed1.contains(name);
            case "reversemessage": return me.leodev.trollingfreedomreborn.trolls.chat.ReverseMessage.Reverse1.contains(name);
            case "poop": return me.leodev.trollingfreedomreborn.trolls.random.Poop.Poop1.contains(name);
            case "ringoffire": return me.leodev.trollingfreedomreborn.trolls.random.RingOfFire.nuke1.contains(name);
            case "randomcraft": return me.leodev.trollingfreedomreborn.trolls.random.RandomCrafts.randomcraft.contains(name);
            default: return false;
        }
    }
}