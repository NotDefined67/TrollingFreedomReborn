package com.leomadrassi.trollingfreedomreborn.commands

import com.leomadrassi.trollingfreedomreborn.main.Core
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedExplosion
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedNight
import com.leomadrassi.trollingfreedomreborn.trolls.chat.*
import com.leomadrassi.trollingfreedomreborn.trolls.classics.*
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.KittyCannon
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.TNTPlace
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.InventoryRave
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.InventoryStop
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.RandomInv
import com.leomadrassi.trollingfreedomreborn.trolls.movement.*
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

class UnTroll : CommandExecutor, TabCompleter {

    private val TROLL_KEYWORDS = arrayOf(
        "annoy", "anvildrop", "aquaphobia",
        "bedexplosion", "stopblockbreakplace",
        "cage", "cavesounds", "randomchat",
        "coffindance", "entitymultiply", "deafen",
        "forcejump", "freeze", "hideallplayers",
        "instatoolbreak", "inventorystop",
        "kittycannon", "lag", "lightning",
        "lockinventory", "nick", "burn",
        "potato", "pumpkin", "rainitems",
        "randominv", "randomparticle",
        "rickroll", "silverfish", "slenderman",
        "slipperyhands", "sneakdestroy", "randomcraft",
        "spin", "starve", "skyflash", "ghastsound",
        "tntplace", "vomit", "explodeonchat",
        "invert", "inventoryrave", "stopsleep",
        "reversemessage", "poop", "control", "ringoffire"
    )

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (args.size == 1) {
            val completions = mutableListOf<String>()
            completions.add("all")
            for (p in Bukkit.getOnlinePlayers()) {
                completions.add(p.name)
            }
            return StringUtil.copyPartialMatches(args[0], completions, mutableListOf())
        }

        if (args.size == 2) {
            val completions = mutableListOf<String>()
            StringUtil.copyPartialMatches(args[1], TROLL_KEYWORDS.toList(), completions)
            completions.sort()
            return completions
        }

        return mutableListOf()
    }

    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("trollingfreedom.untroll")) {
            val message2 = Core.instance.config.get("no-perms") as String
            sender.sendMessage(message2.replace("&", "§").replace("%player%", sender.name))
            return true
        }

        val sndrPlayer = if (sender is Player) sender else null

        if (args.isEmpty()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Core.instance.config.get("menu.select-player") as String).replace("%player%", sender.name))
            return true
        }

        if (args[0].equals("all", ignoreCase = true)) {
            for (all in Bukkit.getOnlinePlayers()) {
                try {
                    StopTrolls(all, sndrPlayer)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            sender.sendMessage("§b§lTFR §8| §7Untrolled §fEVERYONE")
            return true
        }

        val t = Bukkit.getPlayer(args[0])
        if (t == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Core.instance.config.get("not-online") as String).replace("%player%", args[0]))
            return true
        }

        if (args.size == 2) {
            stopSpecificTroll(t, args[1], sndrPlayer)
            return true
        }

        try {
            StopTrolls(t, sndrPlayer)
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Core.instance.config.get("untrolled") as String).replace("%player%", t.name))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return true
    }

    fun StopTrolls(t: Player, sndr: Player?) {
        val uuid = t.uniqueId

        if (Core.instance.individualTasks.containsKey(uuid)) {
            val playerMap = Core.instance.individualTasks[uuid]
            if (playerMap != null) {
                for (tasks in playerMap.values) {
                    for (id in tasks) Bukkit.getScheduler().cancelTask(id)
                }
            }
            Core.instance.individualTasks.remove(uuid)
        }
        if (Potato.Break1.contains(t.name)) {
            Potato().unpotato(t)
        }
        if (RandomCrafts.randomcraft.contains(t.name)) {
            RandomCrafts().unCraftTroll(t)
        }
        if (Break.Break1.contains(t.name)) {
            Break().unBreak(t)
        }
        if (Spin.spin1.contains(t.name)) {
            Spin().StopSpin(t)
        }
        if (Freeze.frozen.contains(t.name)) {
            Freeze().Unfreeze(t)
        }
        if (Silverfish.Fish1.contains(t.name)) {
            Silverfish().UnFish(t)
        }
        if (Vomit.Vomit1.contains(t.name)) {
            Vomit().UnVomit(t)
        }
        if (Pumpkin.Pumpkin1.contains(t.name)) {
            Pumpkin().unPumpkin(t)
        }
        if (InventoryStop.InvStop1.contains(t.name)) {
            InventoryStop().UnInventoryStop(t)
        }
        if (Slenderman.Slender1.contains(t.name)) {
            Slenderman().UnEnderman(t)
        }
        if (CreeperAwMan.Creeper1.contains(t.name)) {
            CreeperAwMan().UnCreeper(t)
        }
        if (Cage.Cage1.contains(t.name)) {
            Cage().UnCage(t)
        }
        if (Starve.starve1.contains(t.name)) {
            Starve().UnStarve(t)
        }
        if (ChatChange.Chat1.contains(t.name)) {
            ChatChange().UnChatChange(t)
        }
        if (Lightning.Lightning1.contains(t.name)) {
            Lightning().UnLightning(t)
        }
        if (HideAllPlayers.hide1.contains(t.name)) {
            HideAllPlayers().UnHideAll(t)
        }
        if (TimeFlash.flash1.contains(t.name)) {
            TimeFlash().UnSkyFlash(t)
        }
        if (TNTPlace.Fireball1.contains(t.name)) {
            TNTPlace().UnTNTPlace(t)
        }
        if (RainItems.Rain1.contains(t.name)) {
            RainItems().UnRainItem(t)
        }
        if (Aquaphobia.Aqua1.contains(t.name)) {
            Aquaphobia().unAqua(t)
        }
        if (SneakDestroy.Sneak1.contains(t.name)) {
            SneakDestroy().UnSneakDestroy(t)
        }
        if (InstaToolBreak.InstaToolBreak1.contains(t.name)) {
            InstaToolBreak().UnInstaToolBreak(t)
        }
        if (EntityMultiply.EntityMultiply1.contains(t.name)) {
            EntityMultiply().UnEntityMultiply(t)
        }
        if (BedExplosion.Explode1.contains(t.name)) {
            BedExplosion().UnBedExplosion(t)
        }
        if (RickRoll.Rick1.contains(t.name)) {
            RickRoll().UnRickRoll(t)
        }
        if (RandomInv.RandomInv1.contains(t.name)) {
            RandomInv().UnRandomInv(t)
        }
        if (RandomParticle.RandomParticle1.contains(t.name)) {
            RandomParticle().UnRandomParticle(t)
        }
        if (ForceJump.Jump1.contains(t.name)) {
            ForceJump().UnJump(t)
        }
        if (Deafen.Deaf1.contains(t.name)) {
            Deafen().UnDeafen(t)
        }
        if (Nick.Nick1.contains(t.name)) {
            NickWithoutEss().UnNick(t)
        }
        if (KittyCannon.Kitty1.contains(t.name)) {
            KittyCannon().UnKittyCannon(t)
        }
        if (ExplodeOnChat.Chat1.contains(t.name)) {
            ExplodeOnChat().UnChat(t)
        }
        if (InventoryRave.Rave1.contains(t.name)) {
            InventoryRave().UnInvRave(t)
        }
        if (InvertWalk.Invert1.contains(t.name)) {
            InvertWalk.UnInvert(t)
        }
        if (BedNight.Bed1.contains(t.name)) {
            BedNight().UnStopSleep(t)
        }
        if (ReverseMessage.Reverse1.contains(t.name)) {
            ReverseMessage().UnReverse(t)
        }
        if (Poop.Poop1.contains(t.name)) {
            Poop().UnPoop(t)
        }
        if (RingOfFire.nuke1.contains(t.name)) {
            RingOfFire().UnNuke(t)
        }
        if (sndr != null) {
            if (Control.controlled1.contains(t.name)) {
                sndr.performCommand("control stop")
            }
        }
    }

    fun stopSpecificTroll(victim: Player, trollName: String, sndr: Player?) {
        val uuid = victim.uniqueId
        val name = trollName.lowercase().replace(" ", "")

        if (Core.instance.individualTasks.containsKey(uuid)) {
            val playerMap = Core.instance.individualTasks[uuid]
            if (playerMap != null && playerMap.containsKey(trollName)) {
                for (taskId in playerMap[trollName]!!) {
                    Bukkit.getScheduler().cancelTask(taskId)
                }
                playerMap.remove(trollName)
            }
        }

        when (name) {
            "annoy" -> Annoy.stopAnnoy(victim)
            "anvildrop" -> {}
            "aquaphobia" -> Aquaphobia().unAqua(victim)
            "bedexplosion" -> BedExplosion().UnBedExplosion(victim)
            "stopblockbreakplace" -> Break().unBreak(victim)
            "cage" -> Cage().UnCage(victim)
            "cavesounds" -> {}
            "randomchat" -> ChatChange().UnChatChange(victim)
            "coffindance" -> Coffin().chk(emptyArray())
            "entitymultiply" -> EntityMultiply().UnEntityMultiply(victim)
            "deafen" -> Deafen().UnDeafen(victim)
            "randomcraft" -> RandomCrafts().unCraftTroll(victim)
            "forcejump" -> ForceJump().UnJump(victim)
            "freeze" -> Freeze().Unfreeze(victim)
            "hideallplayers" -> HideAllPlayers().UnHideAll(victim)
            "instatoolbreak" -> InstaToolBreak().UnInstaToolBreak(victim)
            "inventorystop" -> InventoryStop().UnInventoryStop(victim)
            "kittycannon" -> KittyCannon().UnKittyCannon(victim)
            "lag" -> {}
            "lightning" -> Lightning().UnLightning(victim)
            "lockinventory" -> {}
            "nick" -> NickWithoutEss().UnNick(victim)
            "burn" -> victim.fireTicks = 0
            "potato" -> Potato().unpotato(victim)
            "pumpkin" -> Pumpkin().unPumpkin(victim)
            "rainitems" -> RainItems().UnRainItem(victim)
            "randominv" -> RandomInv().UnRandomInv(victim)
            "randomparticle" -> RandomParticle().UnRandomParticle(victim)
            "rickroll" -> RickRoll().UnRickRoll(victim)
            "silverfish" -> Silverfish().UnFish(victim)
            "slenderman" -> Slenderman().UnEnderman(victim)
            "slipperyhands" -> {}
            "sneakdestroy" -> SneakDestroy().UnSneakDestroy(victim)
            "spin" -> Spin().StopSpin(victim)
            "starve" -> Starve().UnStarve(victim)
            "skyflash" -> TimeFlash().UnSkyFlash(victim)
            "ghastsound" -> {}
            "tntplace" -> TNTPlace().UnTNTPlace(victim)
            "vomit" -> Vomit().UnVomit(victim)
            "explodeonchat" -> ExplodeOnChat().UnChat(victim)
            "inventoryrave" -> try { InventoryRave().UnInvRave(victim) } catch (_: Exception) {}
            "invert" -> InvertWalk.UnInvert(victim)
            "stopsleep" -> BedNight().UnStopSleep(victim)
            "reversemessage" -> ReverseMessage().UnReverse(victim)
            "poop" -> Poop().UnPoop(victim)
            "control" -> sndr?.performCommand("control stop")
            "ringoffire" -> RingOfFire().UnNuke(victim)
            else -> senderFeedback(sndr, ChatColor.RED.toString() + "I don't know a troll called '$trollName'.")
        }
        senderFeedback(sndr, "§b§lTFR §8| §7Stopped §f$trollName §7for §b${victim.name}")
    }

    private fun senderFeedback(sndr: CommandSender?, message: String) {
        if (sndr != null) {
            sndr.sendMessage(message)
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(message)!!)
        }
    }
}
