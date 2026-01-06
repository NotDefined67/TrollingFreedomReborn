package me.leodev.trollingfreedomreborn.commands;

import me.leodev.trollingfreedomreborn.main.Core;
import me.leodev.trollingfreedomreborn.trolls.Beds.BedExplosion;
import me.leodev.trollingfreedomreborn.trolls.Beds.BedNight;
import me.leodev.trollingfreedomreborn.trolls.chat.*;
import me.leodev.trollingfreedomreborn.trolls.classics.*;
import me.leodev.trollingfreedomreborn.trolls.explosion.KittyCannon;
import me.leodev.trollingfreedomreborn.trolls.explosion.TNTPlace;
import me.leodev.trollingfreedomreborn.trolls.inventory.InventoryRave;
import me.leodev.trollingfreedomreborn.trolls.inventory.InventoryStop;
import me.leodev.trollingfreedomreborn.trolls.inventory.RandomInv;
import me.leodev.trollingfreedomreborn.trolls.movement.*;
import me.leodev.trollingfreedomreborn.trolls.random.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.io.IOException;
import java.util.*;

//ArgsCmd also implements CommandInterface
public class UnTroll implements CommandExecutor, TabCompleter {

    private final String[] TROLL_KEYWORDS = {
            "annoy", "anvildrop", "aquaphobia",
            "bedexplosion","stopblockbreakplace",
            "cage", "cavesounds", "randomchat",
            "coffindance", "entitymultiply","deafen",
            "forcejump", "freeze","hideallplayers",
            "instatoolbreak", "inventorystop",
            "kittycannon", "lag", "lightning",
            "lockinventory", "nick", "burn",
            "potato", "pumpkin", "rainitems",
            "randominv", "randomparticle",
            "rickroll", "silverfish", "slenderman",
            "slipperyhands", "sneakdestroy","randomcraft",
            "spin", "starve", "skyflash", "ghastsound",
            "tntplace", "vomit","explodeonchat",
            "invert", "inventoryrave","stopsleep",
            "reversemessage","poop","control", "ringoffire"
    };
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            completions.add("all");
            // Add online players to the list
            for (Player p : Bukkit.getOnlinePlayers()) {
                completions.add(p.getName());
            }
            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        }

        if (args.length == 2) {
            // Second argument suggests the specific troll to stop
            final List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1], Arrays.asList(TROLL_KEYWORDS), completions);
            Collections.sort(completions);
            return completions;
        }

        return Collections.emptyList();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!sender.hasPermission("trollingfreedom.untroll")) {
            String message2 = (String) Core.instance.getConfig().get("no-perms");
            sender.sendMessage(message2.replace("&", "§").replace("%player%", sender.getName()));
            return true;
        }

        // Safety: identify if sender is a player or console
        Player sndrPlayer = (sender instanceof Player) ? (Player) sender : null;

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) Core.instance.getConfig().get("menu.select-player")).replace("%player%", sender.getName()));
            return true;
        }

        // Format 1: /untroll all
        if (args[0].equalsIgnoreCase("all")) {
            for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                try {
                    StopTrolls(all, sndrPlayer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            sender.sendMessage("§b§lTFR §8| §7Stopped all trolls for §fEVERYONE");
            return true;
        }

        Player t = Bukkit.getServer().getPlayer(args[0]);
        if (t == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) Core.instance.getConfig().get("not-online")).replace("%player%", args[0]));
            return true;
        }

        // Format 2: /untroll <player> <troll>
        if (args.length == 2) {
            stopSpecificTroll(t, args[1], sndrPlayer);
            return true;
        }

        // Format 3: /untroll <player> (Stops everything)
        try {
            StopTrolls(t, sndrPlayer);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) Core.instance.getConfig().get("untrolled")).replace("%player%", t.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void StopTrolls(Player t, Player sndr) throws IOException {
        //remove all trolls
        UUID uuid = t.getUniqueId();

        // Clear all tracked tasks in the new map
        if (Core.instance.individualTasks.containsKey(uuid)) {
            Map<String, List<Integer>> playerMap = Core.instance.individualTasks.get(uuid);
            for (List<Integer> tasks : playerMap.values()) {
                for (int id : tasks) Bukkit.getScheduler().cancelTask(id);
            }
            Core.instance.individualTasks.remove(uuid);
        }
        if (Potato.Break1.contains(t.getPlayer().getName())) {
            Potato a = new Potato();
            a.unpotato(t);
        }
        if (RandomCrafts.randomcraft.contains(t.getPlayer().getName())) {
            RandomCrafts a = new RandomCrafts();
            a.unCraftTroll(t);
        }
        if (Break.Break1.contains(t.getPlayer().getName())) {
            Break b = new Break();
            b.unBreak(t);
        }
        if (Spin.spin1.contains(t.getPlayer().getName())) {
            Spin sp = new Spin();
            sp.StopSpin(t);
        }
        if (Freeze.frozen.contains(t.getPlayer().getName())) {
            Freeze c = new Freeze();
            c.Unfreeze(t);
        }
        if (Silverfish.Fish1.contains(t.getPlayer().getName())) {
            Silverfish silverfish = new Silverfish();
            silverfish.UnFish(t);
        }
        if (Vomit.Vomit1.contains(t.getPlayer().getName())) {
            Vomit d = new Vomit();
            d.UnVomit(t);
        }
        if (Pumpkin.Pumpkin1.contains(t.getPlayer().getName())) {
            Pumpkin g = new Pumpkin();
            g.unPumpkin(t);
        }
        if (InventoryStop.InvStop1.contains(t.getPlayer().getName())) {
            InventoryStop j = new InventoryStop();
            j.UnInventoryStop(t);
        }
        if (Slenderman.Slender1.contains(t.getPlayer().getName())) {
            Slenderman k = new Slenderman();
            k.UnEnderman(t);
        }
        if (CreeperAwMan.Creeper1.contains(t.getPlayer().getName())) {
            CreeperAwMan cr = new CreeperAwMan();
            cr.UnCreeper(t);
        }
        if (Cage.Cage1.contains(t.getPlayer().getName())) {
            Cage n = new Cage();
            n.UnCage(t);
        }
        if (Starve.starve1.contains(t.getPlayer().getName())) {
            Starve l = new Starve();
            l.UnStarve(t);
        }
        if (ChatChange.Chat1.contains(t.getPlayer().getName())) {
            ChatChange m = new ChatChange();
            m.UnChatChange(t);
        }
        if (Lightning.Lightning1.contains(t.getPlayer().getName())) {
            Lightning r = new Lightning();
            r.UnLightning(t);
        }
        if (HideAllPlayers.hide1.contains(t.getPlayer().getName())) {
            HideAllPlayers u = new HideAllPlayers();
            u.UnHideAll(t);
        }
        if (TimeFlash.flash1.contains(t.getPlayer().getName())) {
            TimeFlash v = new TimeFlash();
            v.UnSkyFlash(t);
        }
        if (TNTPlace.Fireball1.contains(t.getPlayer().getName())) {
            TNTPlace w = new TNTPlace();
            w.UnTNTPlace(t);
        }
        if (RainItems.Rain1.contains(t.getPlayer().getName())) {
            RainItems x = new RainItems();
            x.UnRainItem(t);
        }
        if (Aquaphobia.Aqua1.contains(t.getPlayer().getName())) {
            Aquaphobia y = new Aquaphobia();
            y.unAqua(t);
        }
        if (SneakDestroy.Sneak1.contains(t.getPlayer().getName())) {
            SneakDestroy four = new SneakDestroy();
            four.UnSneakDestroy(t);
        }
        if (InstaToolBreak.InstaToolBreak1.contains(t.getPlayer().getName())) {
            InstaToolBreak five = new InstaToolBreak();
            five.UnInstaToolBreak(t);
        }
        if (EntityMultiply.EntityMultiply1.contains(t.getPlayer().getName())) {
            EntityMultiply six = new EntityMultiply();
            six.UnEntityMultiply(t);
        }
        if (BedExplosion.Explode1.contains(t.getPlayer().getName())) {
            BedExplosion seven = new BedExplosion();
            seven.UnBedExplosion(t);
        }
        if (RickRoll.Rick1.contains(t.getPlayer().getName())) {
            RickRoll twelve = new RickRoll();
            twelve.UnRickRoll(t);
        }
        if (RandomInv.RandomInv1.contains(t.getPlayer().getName())) {
            RandomInv thirteen = new RandomInv();
            thirteen.UnRandomInv(t);
        }
        if (RandomParticle.RandomParticle1.contains(t.getPlayer().getName())) {
            RandomParticle fourteen = new RandomParticle();
            fourteen.UnRandomParticle(t);
        }
        if (ForceJump.Jump1.contains(t.getPlayer().getName())) {
            ForceJump sixteen = new ForceJump();
            sixteen.UnJump(t);
        }
        if (Deafen.Deaf1.contains(t.getPlayer().getName())) {
            Deafen seventeen = new Deafen();
            Deafen.UnDeafen(t);
        }
        if (Nick.Nick1.contains(t.getPlayer().getName())) {
            NickWithoutEss eighteen = new NickWithoutEss();
            eighteen.UnNick(t);
        }
        if (KittyCannon.Kitty1.contains(t.getPlayer().getName())) {
            KittyCannon nineteen = new KittyCannon();
            nineteen.UnKittyCannon(t);
        }
        if (ExplodeOnChat.Chat1.contains(t.getPlayer().getName())) {
            ExplodeOnChat twenty = new ExplodeOnChat();
            ExplodeOnChat.UnChat(t);
        }
        if (InventoryRave.Rave1.contains(t.getPlayer().getName())) {
            InventoryRave twentyone = new InventoryRave();
            twentyone.UnInvRave(t);
        }
        if (InvertWalk.Invert1.contains(t.getPlayer().getName())) {
            InvertWalk twentytwo = new InvertWalk();
            InvertWalk.UnInvert(t);
        }
        if (BedNight.Bed1.contains(t.getPlayer().getName())) {
            BedNight twentythree = new BedNight();
            twentythree.UnStopSleep(t);
        }
        if (ReverseMessage.Reverse1.contains(t.getPlayer().getName())) {
            ReverseMessage twentyfour = new ReverseMessage();
            twentyfour.UnReverse(t);
        }
        if (Poop.Poop1.contains(t.getPlayer().getName())) {
            Poop twentyfive = new Poop();
            twentyfive.UnPoop(t);
        }
        if (RingOfFire.nuke1.contains(t.getPlayer().getName())) {
            RingOfFire twentyseven = new RingOfFire();
            twentyseven.UnNuke(t);
        }
        if (sndr != null) {
            if (Control.controlled1.contains(t.getPlayer().getName())) {
                sndr.performCommand("control stop");
            }
        }
        //next troll here
    }
    public void stopSpecificTroll(Player victim, String trollName, Player sndr) {
        UUID uuid = victim.getUniqueId();
        String name = trollName.toLowerCase().replaceAll(" ", "");

        // 1. SURGICAL TASK CLEANUP
        // This cancels background tasks (sounds, movement, repeating logic)
        if (Core.instance.individualTasks.containsKey(uuid)) {
            Map<String, List<Integer>> playerMap = Core.instance.individualTasks.get(uuid);

            // Check for the name passed from the GUI or Command keywords
            if (playerMap.containsKey(trollName)) {
                for (int taskId : playerMap.get(trollName)) {
                    Bukkit.getScheduler().cancelTask(taskId);
                }
                playerMap.remove(trollName);
            }
        }

        // 2. SURGICAL LIST/LISTENER CLEANUP
        // Handles removing players from ArrayLists or reversing state changes
        switch (name) {
            // --- Page 1 ---
            case "annoy": Annoy.stopAnnoy(victim); break; // Repeating task handled in Step 1
            case "anvildrop": break; // Repeating task handled in Step 1
            case "aquaphobia": new Aquaphobia().unAqua(victim); break;
            case "bedexplosion": new BedExplosion().UnBedExplosion(victim); break;
            case "stopblockbreakplace": new Break().unBreak(victim); break;
            case "cage": new Cage().UnCage(victim); break;
            case "cavesounds": break; // Repeating task handled in Step 1
            case "randomchat": new ChatChange().UnChatChange(victim); break;
            case "coffindance": new Coffin().chk(new org.bukkit.entity.ArmorStand[0]); break;
            case "entitymultiply": new EntityMultiply().UnEntityMultiply(victim); break;
            case "deafen": Deafen.UnDeafen(victim); break;
            case "randomcraft": new RandomCrafts().unCraftTroll(victim); break;

            // --- Page 2 ---
            case "forcejump": new ForceJump().UnJump(victim); break;
            case "freeze": new Freeze().Unfreeze(victim); break;
            case "hideallplayers": new HideAllPlayers().UnHideAll(victim); break;
            case "instatoolbreak": new InstaToolBreak().UnInstaToolBreak(victim); break;
            case "inventorystop": new InventoryStop().UnInventoryStop(victim); break;
            case "kittycannon": new KittyCannon().UnKittyCannon(victim); break;
            case "lag": break; // Repeating task handled in Step 1
            case "lightning": new Lightning().UnLightning(victim); break;
            case "lockinventory": break;
            case "nick": new NickWithoutEss().UnNick(victim); break;

            // --- Page 3 ---
            case "burn": victim.setFireTicks(0); break;
            case "potato": new Potato().unpotato(victim); break;
            case "pumpkin": new Pumpkin().unPumpkin(victim); break;
            case "rainitems": new RainItems().UnRainItem(victim); break;
            case "randominv": new RandomInv().UnRandomInv(victim); break;
            case "randomparticle": new RandomParticle().UnRandomParticle(victim); break;
            case "rickroll": new RickRoll().UnRickRoll(victim); break;
            case "silverfish": new Silverfish().UnFish(victim); break;
            case "slenderman": new Slenderman().UnEnderman(victim); break;
            case "slipperyhands": break; // Repeating task handled in Step 1
            case "sneakdestroy": new SneakDestroy().UnSneakDestroy(victim); break;
            case "spin": new Spin().StopSpin(victim); break;
            case "starve": new Starve().UnStarve(victim); break;
            case "skyflash": new TimeFlash().UnSkyFlash(victim); break;

            // --- Page 4 ---
            case "ghastsound": break; // Repeating task handled in Step 1
            case "tntplace": new TNTPlace().UnTNTPlace(victim); break;
            case "vomit": new Vomit().UnVomit(victim); break;
            case "explodeonchat": ExplodeOnChat.UnChat(victim); break;
            case "inventoryrave": try { new InventoryRave().UnInvRave(victim); } catch (Exception ignored) {} break;
            case "invert": InvertWalk.UnInvert(victim); break;
            case "stopsleep": new BedNight().UnStopSleep(victim); break;
            case "reversemessage": new ReverseMessage().UnReverse(victim); break;
            case "poop": new Poop().UnPoop(victim); break;
            case "control": if (sndr != null){sndr.performCommand("control stop");} break;
            case "ringoffire": new RingOfFire().UnNuke(victim); break;

            default:
                senderFeedback(sndr, ChatColor.RED + "Troll '" + trollName + "' not recognized.");
                break;
        }
        senderFeedback(sndr, "§b§lTFR §8| §7Successfully stopped troll §f" + trollName + " §7for §b" + victim.getName());


    }
    private void senderFeedback(CommandSender sndr, String message) {
        if (sndr != null) {
            sndr.sendMessage(message);
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(message));
        }
    }
}