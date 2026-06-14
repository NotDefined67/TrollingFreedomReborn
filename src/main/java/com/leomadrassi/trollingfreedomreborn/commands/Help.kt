package com.leomadrassi.trollingfreedomreborn.commands

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.util.StringUtil
import java.lang.reflect.Field

class Help : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<out String>): Boolean {
        if (args.isNotEmpty() && args[0].equals("reload", ignoreCase = true)) {
            if (!sender.hasPermission("trollingfreedom.reload")) {
                sender.sendMessage(ChatColor.RED.toString() + "You dont have permission to reload the plugin.")
                return true
            }

            Core.instance.reloadConfig()

            try {
                val f: Field = org.bukkit.Bukkit.getServer().javaClass.getDeclaredField("commandMap")
                f.isAccessible = true
                val commandMap = f.get(org.bukkit.Bukkit.getServer()) as org.bukkit.command.CommandMap
                Core.instance.registerCustomAliases(commandMap)
            } catch (e: Exception) {
                sender.sendMessage(ChatColor.RED.toString() + "Config reloaded but couldnt refresh aliases.")
                e.printStackTrace()
                return true
            }

            sender.sendMessage(ChatColor.AQUA.toString() + "TFR " + ChatColor.GRAY + "| " + ChatColor.GREEN + "Config and aliases reloaded.")
            return true
        }

        Help(sender)
        return true
    }

    override fun onTabComplete(sender: CommandSender, cmd: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (args.size == 1) {
            val options = mutableListOf<String>()
            if (sender.hasPermission("trollingfreedom.reload")) {
                options.add("reload")
            }
            options.add("help")
            return StringUtil.copyPartialMatches(args[0], options, mutableListOf())
        }
        return mutableListOf()
    }

    fun Help(s: CommandSender) {
        val pdf = Core.instance.description
        val messages = Core.instance.config.getStringList("trollingfreedom-help")

        if (messages == null || messages.isEmpty()) {
            s.sendMessage(ChatColor.RED.toString() + "Help menu not found in config.yml.")
            return
        }

        for (msg in messages) {
            val replaced = msg.replace("%version%", pdf.version)
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', replaced))
        }
    }
}
