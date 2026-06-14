package com.leomadrassi.trollingfreedomreborn.commands

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.IOException
import java.util.HashSet
import java.util.UUID

class Panic : CommandExecutor {
    private val confirmationPending = HashSet<UUID>()

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("trollingfreedom.panic")) {
            sender.sendMessage(ChatColor.RED.toString() + "You cant do that.")
            return true
        }

        val uuid = if (sender is Player) sender.uniqueId else UUID.nameUUIDFromBytes("console".toByteArray())

        if (!confirmationPending.contains(uuid)) {
            confirmationPending.add(uuid)
            sender.sendMessage("§4§l[WARNING] §cThis will kill ALL plugin tasks and untroll everyone.")
            sender.sendMessage("§cRun it again within 10 seconds to confirm.")

            Bukkit.getScheduler().runTaskLater(Core.instance, Runnable { confirmationPending.remove(uuid) }, 200L)
            return true
        }

        confirmationPending.remove(uuid)

        Bukkit.getScheduler().cancelTasks(Core.instance)
        Core.instance.individualTasks.clear()

        val sndrPlayer = if (sender is Player) sender else null
        for (all in Bukkit.getOnlinePlayers()) {
            try {
                UnTroll().StopTrolls(all, sndrPlayer)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        sender.sendMessage(ChatColor.DARK_RED.toString() + "§l[PANIC] §fEverything stopped.")
        Bukkit.broadcast("§c§l[TFR] §7Panic switch pulled by " + sender.name, "trollingfreedom.admin")

        return true
    }
}
