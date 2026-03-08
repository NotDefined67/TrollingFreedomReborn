package com.leomadrassi.trollingfreedomreborn.commands;

import com.leomadrassi.trollingfreedomreborn.main.Core;
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedExplosion;
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedMissing;
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedNight;
import com.leomadrassi.trollingfreedomreborn.trolls.chat.*;
import com.leomadrassi.trollingfreedomreborn.trolls.classics.*;
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.*;
import com.leomadrassi.trollingfreedomreborn.trolls.fakestuff.FakeKicks;
import com.leomadrassi.trollingfreedomreborn.trolls.fakestuff.FakeReload;
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.*;
import com.leomadrassi.trollingfreedomreborn.trolls.movement.*;
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Credits;
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Demo;
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Guardian;
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.WorldLoading;
import com.leomadrassi.trollingfreedomreborn.trolls.random.Void;
import com.leomadrassi.trollingfreedomreborn.trolls.random.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrollWithCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        // 1. SAFETY: Check if the user provided enough arguments BEFORE accessing the array
        if (args.length < 2) {
            String message1 = Core.instance.getConfig().getString("trollf-usage");
            if (message1 == null) message1 = "&cUsage: /" + commandLabel + " <player> <troll>";
            String replaced1 = message1.replace("&", "§").replace("%player%", sender.getName());
            sender.sendMessage(replaced1);
            return true;
        }

        // 2. PERMISSION CHECK
        if (!sender.hasPermission("trollingfreedom.trollf")) {
            String message2 = Core.instance.getConfig().getString("no-perms");
            if (message2 == null) message2 = "&cNo permission!";
            String replaced2 = message2.replace("&", "§").replace("%player%", sender.getName());
            sender.sendMessage(replaced2);
            return true;
        }

        // 3. TARGET PLAYER CHECK
        String pl = args[0];
        Player t = Bukkit.getPlayer(pl);

        if (t == null) {
            String message4 = Core.instance.getConfig().getString("not-online");
            if (message4 == null) message4 = "&cPlayer %player% is not online.";
            String replaced4 = message4.replace("&", "§").replace("%player%", args[0]);
            sender.sendMessage(replaced4);
            return true;
        }

        // 4. PROTECTION CHECK
        if (!Core.canTroll(t)) {
            sender.sendMessage("§c§l[TFR] §7This player is §4protected §7from being trolled.");
            return true;
        }

        // 5. EXECUTION LOGIC
        String trollName = args[1].toLowerCase();

        switch (trollName) {
            case "afk":
                AFK.FakeAFK(t);
                break;
            case "unafk":
                AFK.FakeUnAFK(t);
                break;
            case "entitydie":
                AllEntitiesDie.EntityDie(t);
                break;
            case "annoy":
                Annoy.Annoy(t);
                break;
            case "anvildrop":
                AnvilDrop.Anvil(t);
                break;
            case "aquaphobia":
                new Aquaphobia().Aqua(t);
                break;
            case "bedexplosion":
                new BedExplosion().BedExplosion(t);
                break;
            case "bedmissing":
                new BedMissing().BedMissing(t);
                break;
            case "stopblockbreakplace":
                new Break().Break(t);
                break;
            case "cage":
                new Cage().Cage(t);
                break;
            case "cavesounds":
                Sounds.CaveSound(t);
                break;
            case "randomcraft":
                new RandomCrafts().craftTroll(t);
                break;
            case "randomchat":
                new ChatChange().ChatChange(t);
                break;
            case "coffindance":
                new Coffin().CoffinStart(t);
                break;
            case "credits":
                new Credits().Credits(t);
                break;
            case "entitymultiply":
                new EntityMultiply().EntityMultiply(t);
                break;
            case "creeperawman":
                try {
                    CreeperAwMan.Creeper(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "deafen":
                Deafen.Deafen(t);
                break;
            case "demo":
                new Demo().DemoMenu(t);
                break;
            case "dropall":
                DropAll.DropAll(t);
                break;
            case "explodingchicken":
                ExplodingChicken.Chicken(t);
                break;
            case "explosivesheep":
                new ExplosiveSheep().Sheep(t);
                break;
            case "fakecrash":
                FakeKicks.FakeCrash(t);
                break;
            case "fakereload":
                new FakeReload().Reload(t);
                break;
            case "forcejump":
                new ForceJump().Jump(t);
                break;
            case "freeze":
                new Freeze().Freeze(t);
                break;
            case "herobrine":
                Herobrine.Herobrine(t);
                break;
            case "hideallplayers":
                new HideAllPlayers().HideAll(t);
                break;
            case "instatoolbreak":
                new InstaToolBreak().InstaToolBreak(t);
                break;
            case "inventorystop":
                new InventoryStop().InventoryStop(t);
                break;
            case "invsee":
                Invsee.Invsee(t);
                break;
            case "kittycannon":
                new KittyCannon().KittyCannon(t);
                break;
            case "lag":
                Lag.Lagg(t);
                break;
            case "launch":
                Launch.Launch(t);
                break;
            case "lightning":
                new Lightning().Lightning(t);
                break;
            case "lockinventory":
                LockInventory.Lock(t);
                break;
            case "nick":
                new NickWithoutEss().NickName(t);
                break;
            case "fakeop":
                OP.FakeOP(t);
                break;
            case "fakeunop":
                OP.FakeDeOP(t);
                break;
            case "fakeclose":
                FakeKicks.FakeClosed(t);
                break;
            case "fakeban":
                FakeKicks.FakeBan(t);
                break;
            case "burn":
                Burn.Burn(t);
                break;
            case "potato":
                new Potato().potato(t);
                break;
            case "pumpkin":
                new Pumpkin().Pumpkin(t);
                break;
            case "rainitems":
                new RainItems().RainItem(t);
                break;
            case "randominv":
                new RandomInv().RandomInv(t);
                break;
            case "randomparticle":
                new RandomParticle().RandomParticle(t);
                break;
            case "randomtp":
                RandomTP.RandomTP(t);
                break;
            case "rickroll":
                new RickRoll().RickRoll(t);
                break;
            case "silverfish":
                Silverfish.Fish(t);
                break;
            case "slenderman":
                new Slenderman().Enderman(t);
                break;
            case "slipperyhands":
                SlipperyHands.SlipperyHands(t);
                break;
            case "sneakdestroy":
                new SneakDestroy().SneakDestroy(t);
                break;
            case "snowman":
                new Snowman().Snowman(t);
                break;
            case "spin":
                Spin.Spin(t);
                break;
            case "starve":
                new Starve().Starve(t);
                break;
            case "skyflash":
                new TimeFlash().SkyFlash(t);
                break;
            case "fakenuke":
                TNT.FakeNuke(t);
                break;
            case "ghastsound":
                Sounds.GhastSound(t);
                break;
            case "nuke":
                new TNT().Nuke(t);
                break;
            case "tntplace":
                new TNTPlace().TNTPlace(t);
                break;
            case "void":
                Void.Void(t);
                break;
            case "vomit":
                new Vomit().Vomit(t);
                break;
            case "worldloading":
                new WorldLoading().WorldLoading(t);
                break;
            case "explodeonchat":
                ExplodeOnChat.Chat(t);
                break;
            case "invert":
                InvertWalk.Invert(t);
                break;
            case "inventoryrave":
                try {
                    new InventoryRave().InvRave(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "bednight":
                new BedNight().BedNight(t);
                break;
            case "bedmonster":
                new BedNight().BedMonster(t);
                break;
            case "stopsleep":
                new BedNight().StopSleep(t);
                break;
            case "freefall":
                FreeFall.FreeFall(t);
                break;
            case "reversemessage":
                new ReverseMessage().Reverse(t);
                break;
            case "guardian":
                new Guardian().Guardian(t);
                break;
            case "poop":
                new Poop().Poop(t);
                break;
            case "control":
                if (t.equals(sender)) {
                    String msg = Core.instance.getConfig().getString("cannot-troll-yourself");
                    sender.sendMessage(msg != null ? msg.replace("&", "§") : "§cYou cannot troll yourself!");
                } else if (sender instanceof Player) {
                    ((Player) sender).performCommand("control " + t.getName());
                }
                break;
            case "ringoffire":
                new RingOfFire().Nuke(t);
                break;
            default:
                senderFeedback(sender, ChatColor.RED + "Troll '" + args[1] + "' not recognized.");
                return true;
        }

        senderFeedback(sender, "§b§lTFR §8| §7Successfully sent troll §f" + args[1] + " §7to §b" + t.getName());
        return true;
    }

    private void senderFeedback(CommandSender sndr, String message) {
        if (sndr instanceof Player) {
            sndr.sendMessage(message);
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(message));
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> completions = new ArrayList<>();
        ArrayList<String> commands = new ArrayList<>();

        if (args.length == 1) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                commands.add(p.getName());
            }
            StringUtil.copyPartialMatches(args[0], commands, completions);
        } else if (args.length == 2) {
            String[] trolls = {
                    "afk", "unafk", "entitydie", "annoy", "anvildrop", "aquaphobia", "bedexplosion", "bedmissing",
                    "stopblockbreakplace", "cage", "cavesounds", "randomchat", "coffindance", "credits", "entitymultiply",
                    "creeperawman", "deafen", "demo", "dropall", "explodingchicken", "explosivesheep", "fakecrash",
                    "fakereload", "forcejump", "freeze", "herobrine", "hideallplayers", "instatoolbreak", "inventorystop",
                    "invsee", "kittycannon", "lag", "launch", "lightning", "lockinventory", "nick", "fakeop", "fakeunop",
                    "fakeclose", "fakeban", "burn", "potato", "pumpkin", "rainitems", "randominv", "randomparticle",
                    "randomtp", "rickroll", "silverfish", "slenderman", "slipperyhands", "sneakdestroy", "snowman",
                    "spin", "starve", "skyflash", "fakenuke", "ghastsound", "nuke", "tntplace", "void", "vomit",
                    "worldloading", "explodeonchat", "invert", "inventoryrave", "bednight", "bedmonster", "stopsleep",
                    "freefall", "reversemessage", "guardian", "poop", "control", "ringoffire", "randomcraft"
            };
            Collections.addAll(commands, trolls);
            StringUtil.copyPartialMatches(args[1], commands, completions);
        }

        Collections.sort(completions);
        return completions;
    }
}