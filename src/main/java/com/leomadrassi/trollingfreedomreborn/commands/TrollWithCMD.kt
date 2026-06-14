package com.leomadrassi.trollingfreedomreborn.commands

import com.leomadrassi.trollingfreedomreborn.main.Core
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedExplosion
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedMissing
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedNight
import com.leomadrassi.trollingfreedomreborn.trolls.chat.*
import com.leomadrassi.trollingfreedomreborn.trolls.classics.*
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.*
import com.leomadrassi.trollingfreedomreborn.trolls.fakestuff.FakeKicks
import com.leomadrassi.trollingfreedomreborn.trolls.fakestuff.FakeReload
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.*
import com.leomadrassi.trollingfreedomreborn.trolls.movement.*
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Credits
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Demo
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Guardian
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.WorldLoading
import com.leomadrassi.trollingfreedomreborn.trolls.random.*
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.util.StringUtil
import java.io.IOException

class TrollWithCMD : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<out String>): Boolean {
        if (args.size < 2) {
            var message1 = Core.instance.config.getString("trollf-usage")
            if (message1 == null) message1 = "&cUsage: /$commandLabel <player> <troll>"
            sender.sendMessage(message1.replace("&", "§").replace("%player%", sender.name))
            return true
        }

        if (!sender.hasPermission("trollingfreedom.trollf")) {
            var message2 = Core.instance.config.getString("no-perms")
            if (message2 == null) message2 = "&cNo permission!"
            sender.sendMessage(message2.replace("&", "§").replace("%player%", sender.name))
            return true
        }

        val pl = args[0]
        val t = Bukkit.getPlayer(pl)

        if (t == null) {
            var message4 = Core.instance.config.getString("not-online")
            if (message4 == null) message4 = "&cPlayer %player% is not online."
            sender.sendMessage(message4.replace("&", "§").replace("%player%", args[0]))
            return true
        }

        if (!Core.canTroll(t)) {
            sender.sendMessage("§c§l[TFR] §7That player is §4blocked §7from being trolled.")
            return true
        }

        val trollName = args[1].lowercase()

        when (trollName) {
            "afk" -> AFK.FakeAFK(t)
            "unafk" -> AFK.FakeUnAFK(t)
            "entitydie" -> AllEntitiesDie.EntityDie(t)
            "annoy" -> Annoy.Annoy(t)
            "anvildrop" -> AnvilDrop.Anvil(t)
            "aquaphobia" -> Aquaphobia().Aqua(t)
            "bedexplosion" -> BedExplosion().BedExplosion(t)
            "bedmissing" -> BedMissing().BedMissing(t)
            "stopblockbreakplace" -> Break().Break(t)
            "cage" -> Cage().Cage(t)
            "cavesounds" -> Sounds.CaveSound(t)
            "randomcraft" -> RandomCrafts().craftTroll(t)
            "randomchat" -> ChatChange().ChatChange(t)
            "coffindance" -> Coffin().CoffinStart(t)
            "credits" -> Credits().Credits(t)
            "entitymultiply" -> EntityMultiply().EntityMultiply(t)
            "creeperawman" -> try { CreeperAwMan.Creeper(t) } catch (e: IOException) { e.printStackTrace() }
            "deafen" -> Deafen().Deafen(t)
            "demo" -> Demo().DemoMenu(t)
            "dropall" -> DropAll.DropAll(t)
            "explodingchicken" -> ExplodingChicken.Chicken(t)
            "explosivesheep" -> ExplosiveSheep().Sheep(t)
            "fakecrash" -> FakeKicks.FakeCrash(t)
            "fakereload" -> FakeReload().Reload(t)
            "forcejump" -> ForceJump().Jump(t)
            "freeze" -> Freeze().Freeze(t)
            "herobrine" -> Herobrine.Herobrine(t)
            "hideallplayers" -> HideAllPlayers().HideAll(t)
            "instatoolbreak" -> InstaToolBreak().InstaToolBreak(t)
            "inventorystop" -> InventoryStop().InventoryStop(t)
            "invsee" -> Invsee.Invsee(t)
            "kittycannon" -> KittyCannon().KittyCannon(t)
            "lag" -> Lag.Lagg(t)
            "launch" -> Launch.Launch(t)
            "lightning" -> Lightning().Lightning(t)
            "lockinventory" -> LockInventory.Lock(t)
            "nick" -> NickWithoutEss().NickName(t)
            "fakeop" -> OP.FakeOP(t)
            "fakeunop" -> OP.FakeDeOP(t)
            "fakeclose" -> FakeKicks.FakeClosed(t)
            "fakeban" -> FakeKicks.FakeBan(t)
            "burn" -> Burn.Burn(t)
            "potato" -> Potato().potato(t)
            "pumpkin" -> Pumpkin().Pumpkin(t)
            "rainitems" -> RainItems().RainItem(t)
            "randominv" -> RandomInv().RandomInv(t)
            "randomparticle" -> RandomParticle().RandomParticle(t)
            "randomtp" -> RandomTP.RandomTP(t)
            "rickroll" -> RickRoll().RickRoll(t)
            "silverfish" -> Silverfish.Fish(t)
            "slenderman" -> Slenderman().Enderman(t)
            "slipperyhands" -> SlipperyHands.SlipperyHands(t)
            "sneakdestroy" -> SneakDestroy().SneakDestroy(t)
            "snowman" -> Snowman().Snowman(t)
            "spin" -> Spin.Spin(t)
            "starve" -> Starve().Starve(t)
            "skyflash" -> TimeFlash().SkyFlash(t)
            "fakenuke" -> TNT.FakeNuke(t)
            "ghastsound" -> Sounds.GhastSound(t)
            "nuke" -> TNT().Nuke(t)
            "tntplace" -> TNTPlace().TNTPlace(t)
            "void" -> com.leomadrassi.trollingfreedomreborn.trolls.random.Void.Void(t)
            "vomit" -> Vomit().Vomit(t)
            "worldloading" -> WorldLoading.WorldLoading(t)
            "explodeonchat" -> ExplodeOnChat().Chat(t)
            "invert" -> InvertWalk.Invert(t)
            "inventoryrave" -> try { InventoryRave().InvRave(t) } catch (e: IOException) { e.printStackTrace() }
            "bednight" -> BedNight().BedNight(t)
            "bedmonster" -> BedNight().BedMonster(t)
            "stopsleep" -> BedNight().StopSleep(t)
            "freefall" -> FreeFall.FreeFall(t)
            "reversemessage" -> ReverseMessage().Reverse(t)
            "guardian" -> Guardian().Guardian(t)
            "poop" -> Poop().Poop(t)
            "control" -> {
                if (t == sender) {
                    val msg = Core.instance.config.getString("cannot-troll-yourself")
                    sender.sendMessage(if (msg != null) msg.replace("&", "§") else "§cYou cannot troll yourself!")
                } else if (sender is Player) {
                    sender.performCommand("control " + t.name)
                }
            }
            "ringoffire" -> RingOfFire().Nuke(t)
            else -> {
                senderFeedback(sender, ChatColor.RED.toString() + "I dont know a troll called '" + args[1] + "'.")
                return true
            }
        }

        senderFeedback(sender, "§b§lTFR §8| §7Sent §f${args[1]} §7to §b${t.name}")
        return true
    }

    private fun senderFeedback(sndr: CommandSender, message: String) {
        if (sndr is Player) {
            sndr.sendMessage(message)
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(message)!!)
        }
    }

    override fun onTabComplete(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): MutableList<String> {
        val completions = mutableListOf<String>()
        val commands = mutableListOf<String>()

        if (args.size == 1) {
            for (p in Bukkit.getOnlinePlayers()) {
                commands.add(p.name)
            }
            StringUtil.copyPartialMatches(args[0], commands, completions)
        } else if (args.size == 2) {
            val trolls = arrayOf(
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
            )
            commands.addAll(trolls)
            StringUtil.copyPartialMatches(args[1], commands, completions)
        }

        completions.sort()
        return completions
    }
}
