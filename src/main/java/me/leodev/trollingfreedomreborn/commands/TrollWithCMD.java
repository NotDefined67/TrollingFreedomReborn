package me.leodev.trollingfreedomreborn.commands;

import me.leodev.trollingfreedomreborn.main.Core;
import me.leodev.trollingfreedomreborn.trolls.Beds.BedExplosion;
import me.leodev.trollingfreedomreborn.trolls.Beds.BedMissing;
import me.leodev.trollingfreedomreborn.trolls.Beds.BedNight;
import me.leodev.trollingfreedomreborn.trolls.random.*;
import me.leodev.trollingfreedomreborn.trolls.chat.*;
import me.leodev.trollingfreedomreborn.trolls.classics.*;
import me.leodev.trollingfreedomreborn.trolls.explosion.*;
import me.leodev.trollingfreedomreborn.trolls.fakestuff.FakeKicks;
import me.leodev.trollingfreedomreborn.trolls.fakestuff.FakeReload;
import me.leodev.trollingfreedomreborn.trolls.inventory.*;
import me.leodev.trollingfreedomreborn.trolls.movement.*;
import me.leodev.trollingfreedomreborn.trolls.packettrolls.Credits;
import me.leodev.trollingfreedomreborn.trolls.packettrolls.Demo;
import me.leodev.trollingfreedomreborn.trolls.packettrolls.Guardian;
import me.leodev.trollingfreedomreborn.trolls.packettrolls.WorldLoading;
import me.leodev.trollingfreedomreborn.trolls.random.Void;
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
import java.util.Random;

public class TrollWithCMD
    implements CommandExecutor,
    TabCompleter {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        String ags;

        String pl = args[0];
        Player t = Bukkit.getPlayer(pl);
        if (!Core.canTroll(t)) {
            sender.sendMessage("§c§l[TFR] §7This player is §4protected §7from being trolled.");
            return true;
        }

        if (!sender.hasPermission("trollingfreedom.trollf")) {

            String message2 = (String) Core.instance.getConfig().get("no-perms");
            String replaced2 = message2.replace("&", "§").replace("%player%", sender.getName());
            sender.sendMessage(replaced2);
            return false;
        }
        if (args.length == 0) {
            String message1 = (String) Core.instance.getConfig().get("trollf-usage");
            String replaced1 = message1.replace("&", "§").replace("%player%", sender.getName());
            sender.sendMessage(replaced1);
            return false;
        }
        if (args.length == 1) {

            String message3 = (String) Core.instance.getConfig().get("trollf-usage");
            String replaced3 = message3.replace("&", "§").replace("%player%", sender.getName());
            sender.sendMessage(replaced3);
            return false;
        }
        //
        if (t == null) {
            String message4 = (String) Core.instance.getConfig().get("not-online");
            String replaced4 = message4.replace("&", "§").replace("%player%", args[0]);
            sender.sendMessage(replaced4);
            return false;
        }
        switch (ags = args.length > 1 ? args[1] : "") {
            case "afk": {
                AFK troll = new AFK();
                AFK.FakeAFK(t);
                break;
            }
            case "unafk": {
                AFK troll2 = new AFK();
                AFK.FakeUnAFK(t);
                break;
            }
            case "entitydie": {
                AllEntitiesDie troll3 = new AllEntitiesDie();
                AllEntitiesDie.EntityDie(t);
                break;
            }
            case "annoy": {
                Annoy troll4 = new Annoy();
                Annoy.Annoy(t);
                break;
            }
            case "anvildrop": {
                AnvilDrop troll5 = new AnvilDrop();
                AnvilDrop.Anvil(t);
                break;
            }
            case "aquaphobia": {
                Aquaphobia troll6 = new Aquaphobia();
                troll6.Aqua(t);
                break;
            }
            case "bedexplosion": {
                BedExplosion troll7 = new BedExplosion();
                troll7.BedExplosion(t);
                break;
            }
            case "bedmissing": {
                BedMissing troll8 = new BedMissing();
                troll8.BedMissing(t);
                break;
            }
            case "stopblockbreakplace": {
                Break troll9 = new Break();
                troll9.Break(t);
                break;
            }
            case "cage": {
                Cage troll10 = new Cage();
                troll10.Cage(t);
                break;
            }
            case "cavesounds": {
                Sounds troll11 = new Sounds();
                Sounds.CaveSound(t);
                break;
            }
            case "randomcraft": {
                RandomCrafts rc = new RandomCrafts();
                rc.craftTroll(t);
                break;
            }
            case "randomchat": {
                ChatChange troll12 = new ChatChange();
                troll12.ChatChange(t);
                break;
            }
            case "coffindance": {
                Coffin troll13 = new Coffin();
                troll13.CoffinStart(t);
                break;
            }
            case "credits": {
                Credits troll14 = new Credits();
                troll14.Credits(t);
                break;
            }
            case "entitymultiply": {
                EntityMultiply troll15 = new EntityMultiply();
                troll15.EntityMultiply(t);
                break;
            }
            case "creeperawman": {
                CreeperAwMan troll16 = new CreeperAwMan();
                try {
                    CreeperAwMan.Creeper(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "deafen": {
                Deafen troll17 = new Deafen();
                Deafen.Deafen(t);
                break;
            }
            case "demo": {
                Demo troll18 = new Demo();
                troll18.DemoMenu(t);
                break;
            }
            case "dropall": {
                DropAll troll19 = new DropAll();
                DropAll.DropAll(t);
                break;
            }
            case "explodingchicken": {
                ExplodingChicken trollfg2 = new ExplodingChicken();
                ExplodingChicken.Chicken(t);
                break;
            }
            case "explosivesheep": {
                ExplosiveSheep trollfg22 = new ExplosiveSheep();
                trollfg22.Sheep(t);
                break;
            }
            case "fakecrash": {
                FakeKicks trollfg23 = new FakeKicks();
                FakeKicks.FakeCrash(t);
                break;
            }
            case "fakereload": {
                FakeReload trollfg24 = new FakeReload();
                trollfg24.Reload(t);
                break;
            }
            case "forcejump": {
                ForceJump trollfg25 = new ForceJump();
                trollfg25.Jump(t);
                break;
            }
            case "freeze": {
                Freeze trollfg26 = new Freeze();
                trollfg26.Freeze(t);
                break;
            }
            case "herobrine": {
                Herobrine trollfg27 = new Herobrine();
                Herobrine.Herobrine(t);
                break;
            }
            case "hideallplayers": {
                HideAllPlayers trollfg28 = new HideAllPlayers();
                trollfg28.HideAll(t);
                break;
            }
            case "instatoolbreak": {
                InstaToolBreak trollfg29 = new InstaToolBreak();
                trollfg29.InstaToolBreak(t);
                break;
            }
            case "inventorystop": {
                InventoryStop trollfg210 = new InventoryStop();
                trollfg210.InventoryStop(t);
                break;
            }
            case "invsee": {
                Invsee trollfg211 = new Invsee();
                Invsee.Invsee(t);
                break;
            }
            case "kittycannon": {
                KittyCannon trollfg212 = new KittyCannon();
                trollfg212.KittyCannon(t);
                break;
            }
            case "lag": {
                Lag trollfg213 = new Lag();
                Lag.Lagg(t);
                break;
            }
            case "launch": {
                Launch trollfg214 = new Launch();
                Launch.Launch(t);
                break;
            }
            case "lightning": {
                Lightning trollfg215 = new Lightning();
                trollfg215.Lightning(t);
                break;
            }
            case "lockinventory": {
                LockInventory trollfg216 = new LockInventory();
                LockInventory.Lock(t);
                break;
            }
            case "nick": {
                NickWithoutEss trollfg217 = new NickWithoutEss();
                trollfg217.NickName(t);
                break;
            }
            case "fakeop": {
                OP trollfg218 = new OP();
                OP.FakeOP(t);
                break;
            }
            case "fakeunop": {
                OP trollfg219 = new OP();
                OP.FakeDeOP(t);
                break;
            }
            case "fakeclose": {
                FakeKicks trollfg3 = new FakeKicks();
                FakeKicks.FakeClosed(t);
                break;
            }
            case "fakeban": {
                FakeKicks trollfg32 = new FakeKicks();
                FakeKicks.FakeBan(t);
                break;
            }
            case "burn": {
                Burn trollfg33 = new Burn();
                Burn.Burn(t);
                break;
            }
            case "potato": {
                Potato trollfg34 = new Potato();
                trollfg34.potato(t);
                break;
            }
            case "pumpkin": {
                Pumpkin trollfg35 = new Pumpkin();
                trollfg35.Pumpkin(t);
                break;
            }
            case "rainitems": {
                RainItems trollfg36 = new RainItems();
                trollfg36.RainItem(t);
                break;
            }
            case "randominv": {
                RandomInv trollfg37 = new RandomInv();
                trollfg37.RandomInv(t);
                break;
            }
            case "randomparticle": {
                RandomParticle trollfg38 = new RandomParticle();
                trollfg38.RandomParticle(t);
                break;
            }
            case "randomtp": {
                RandomTP trollfg39 = new RandomTP();
                RandomTP.RandomTP(t);
                break;
            }
            case "rickroll": {
                RickRoll trollfg310 = new RickRoll();
                trollfg310.RickRoll(t);
                break;
            }
            case "silverfish": {
                Silverfish trollfg311 = new Silverfish();
                Silverfish.Fish(t);
                break;
            }
            case "slenderman": {
                Slenderman trollfg312 = new Slenderman();
                trollfg312.Enderman(t);
                break;
            }
            case "slipperyhands": {
                SlipperyHands trollfg313 = new SlipperyHands();
                SlipperyHands.SlipperyHands(t);
                break;
            }
            case "sneakdestroy": {
                SneakDestroy trollfg314 = new SneakDestroy();
                trollfg314.SneakDestroy(t);
                break;
            }
            case "snowman": {
                Snowman trollfg315 = new Snowman();
                trollfg315.Snowman(t);
                break;
            }
            case "spin": {
                Spin trollfg316 = new Spin();
                Spin.Spin(t);
                break;
            }
            case "starve": {
                Starve trollfg317 = new Starve();
                trollfg317.Starve(t);
                break;
            }
            case "skyflash": {
                TimeFlash trollfg318 = new TimeFlash();
                trollfg318.SkyFlash(t);
                break;
            }
            case "fakenuke": {
                TNT trollfg319 = new TNT();
                TNT.FakeNuke(t);
                break;
            }
            case "ghastsound": {
                Sounds trollfg4 = new Sounds();
                Sounds.GhastSound(t);
                break;
            }
            case "nuke": {
                TNT trollfg42 = new TNT();
                trollfg42.Nuke(t);
                break;
            }
            case "tntplace": {
                TNTPlace trollfg43 = new TNTPlace();
                trollfg43.TNTPlace(t);
                break;
            }
            case "void": {
                Void trollfg44 = new Void();
                Void.Void(t);
                break;
            }
            case "vomit": {
                Vomit trollfg45 = new Vomit();
                trollfg45.Vomit(t);
                break;
            }
            case "worldloading": {
                WorldLoading trollfg46 = new WorldLoading();
                trollfg46.WorldLoading(t);
                break;
            }
            case "explodeonchat": {
                ExplodeOnChat trollfg47 = new ExplodeOnChat();
                ExplodeOnChat.Chat(t);
                break;
            }
            case "invert": {
                InvertWalk trollfg48 = new InvertWalk();
                InvertWalk.Invert(t);
                break;
            }
            case "inventoryrave": {
                InventoryRave trollfg49 = new InventoryRave();
                try {
                    trollfg49.InvRave(t);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "bednight": {
                BedNight trollfg50 = new BedNight();
                trollfg50.BedNight(t);
                break;
            }
            case "bedmonster": {
                BedNight trollfg51 = new BedNight();
                trollfg51.BedMonster(t);
                break;
            }
            case "stopsleep": {
                BedNight trollfg52 = new BedNight();
                trollfg52.StopSleep(t);
                break;
            }
            case "freefall": {
                FreeFall trollfg53 = new FreeFall();
                FreeFall.FreeFall(t);
                break;
            }
            case "reversemessage": {
                ReverseMessage trollfg54 = new ReverseMessage();
                trollfg54.Reverse(t);
                break;
            }
            case "guardian": {
                Guardian trollfg56 = new Guardian();
                trollfg56.Guardian(t);
                break;
            }
            case "poop": {
                Poop trollfg57 = new Poop();
                trollfg57.Poop(t);
                break;
            }
            case "control": {
                String message2 = (String) Core.instance.getConfig().get("cannot-troll-yourself");
                String replaced2 = message2.replace("&", "§");
                if (t.equals(sender)) {
                    sender.sendMessage(replaced2);
                } else {
                    Player sendr = (Player) sender;
                    sendr.performCommand("control " + t.getName());
                }
                break;
            }
            case "ringoffire": {
                RingOfFire trollfg59 = new RingOfFire();
                trollfg59.Nuke(t);

                break;
            }
            default:
                // This runs if no case matches
                senderFeedback(sender, ChatColor.RED + "Troll '" + args[1] + "' not recognized.");
                return false;
        }
        senderFeedback(sender, "§b§lTFR §8| §7Successfully sent troll §f" + args[1] + " §7to §b" + t.getName());
        return true;
    }
    private void senderFeedback(CommandSender sndr, String message) {
        if (sndr instanceof Player) {
            sndr.sendMessage(message);
        } else {
            // Sends to console/rcon and strips colors
            Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(message));
        }
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> completions = new ArrayList<String>();
        ArrayList<String> commands = new ArrayList<String>();
        if (args.length == 1) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                commands.add(p.getName());
            }
            StringUtil.copyPartialMatches(args[0], commands, completions);
        } else if (args.length == 2) {
            if (!args[0].isEmpty()) {
                commands.add("afk");
                commands.add("unafk");
                commands.add("entitydie");
                commands.add("annoy");
                commands.add("anvildrop");
                commands.add("aquaphobia");
                commands.add("bedexplosion");
                commands.add("bedmissing");
                commands.add("stopblockbreakplace");
                commands.add("cage");
                commands.add("cavesounds");
                commands.add("randomchat");
                commands.add("coffindance");
                commands.add("credits");
                commands.add("entitymultiply");
                commands.add("creeperawman");
                commands.add("deafen");
                commands.add("demo");
                commands.add("dropall");
                commands.add("explodingchicken");
                commands.add("explosivesheep");
                commands.add("fakecrash");
                commands.add("fakereload");
                commands.add("forcejump");
                commands.add("freeze");
                commands.add("herobrine");
                commands.add("hideallplayers");
                commands.add("instatoolbreak");
                commands.add("inventorystop");
                commands.add("invsee");
                commands.add("kittycannon");
                commands.add("lag");
                commands.add("launch");
                commands.add("lightning");
                commands.add("lockinventory");
                commands.add("nick");
                commands.add("fakeop");
                commands.add("fakeunop");
                commands.add("fakeclose");
                commands.add("fakeban");
                commands.add("burn");
                commands.add("potato");
                commands.add("pumpkin");
                commands.add("rainitems");
                commands.add("randominv");
                commands.add("randomparticle");
                commands.add("randomtp");
                commands.add("rickroll");
                commands.add("silverfish");
                commands.add("slenderman");
                commands.add("slipperyhands");
                commands.add("sneakdestroy");
                commands.add("snowman");
                commands.add("spin");
                commands.add("starve");
                commands.add("skyflash");
                commands.add("fakenuke");
                commands.add("ghastsound");
                commands.add("nuke");
                commands.add("tntplace");
                commands.add("void");
                commands.add("vomit");
                commands.add("worldloading");
                commands.add("explodeonchat");
                commands.add("invert");
                commands.add("inventoryrave");
                commands.add("bednight");
                commands.add("bedmonster");
                commands.add("stopsleep");
                commands.add("freefall");
                commands.add("reversemessage");
                commands.add("guardian");
                commands.add("poop");
                commands.add("control");
                commands.add("ringoffire");
                commands.add("randomcraft");
            }
            StringUtil.copyPartialMatches(args[1], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}