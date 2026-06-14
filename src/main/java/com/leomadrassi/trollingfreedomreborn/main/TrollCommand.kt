package com.leomadrassi.trollingfreedomreborn.main

import com.leomadrassi.trollingfreedomreborn.commands.Help
import com.leomadrassi.trollingfreedomreborn.ui.PlayerSelectorInventory
import com.leomadrassi.trollingfreedomreborn.ui.SettingsMenuInventory
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class TrollCommand(commandMap: CommandMap, plugin: JavaPlugin) : CommandHandler<JavaPlugin>(plugin, "troll") {

    val keys = Core.instance!!.pluginConfig.defaultSection!!.getKeys(true)
    var args: Array<String>? = null

    init {
        addPermission("trollingfreedom.open")
        registerCommand(commandMap)
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): MutableList<String> {
        if (args.size == 1) {
            val subcommands = listOf("reload", "giveskull", "contact", "help", "toggle-troll-op", "add-blocked", "remove-blocked")
            return subcommands.filter { it.lowercase().startsWith(args[0].lowercase()) }.toMutableList()
        }

        if (args.size == 2) {
            val sub = args[0].lowercase()

            if (sub == "add-blocked") {
                return Bukkit.getOnlinePlayers().stream()
                    .map { it.name }
                    .filter { it.lowercase().startsWith(args[1].lowercase()) }
                    .toList()
                    .toMutableList()
            }

            if (sub == "remove-blocked") {
                return Core.instance!!.pluginConfig.getStringList("blocklist").stream()
                    .filter { it.lowercase().startsWith(args[1].lowercase()) }
                    .toList()
                    .toMutableList()
            }

            return Bukkit.getOnlinePlayers().stream()
                .map { it.name }
                .filter { it.lowercase().startsWith(args[1].lowercase()) }
                .toList()
                .toMutableList()
        }

        return super.tabComplete(sender, alias, args)
    }

    override fun onCommand(sender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {
        val ags = if (strings.isNotEmpty()) strings[0] else ""
        if (sender is Player) {
            val p = sender
            if (Core.advCheck("trollingfreedom.open", p)) {
                when (ags) {
                    "reload" -> {
                        val sm = SettingsMenuInventory()
                        sm.openInventory(p)
                    }
                    "toggle-troll-op" -> {
                        val currentVal = Core.instance!!.pluginConfig.getBoolean("allow-troll-op", false)
                        Core.instance!!.pluginConfig.set("allow-troll-op", !currentVal)
                        Core.instance!!.saveConfig()
                        sender.sendMessage("${Core.instance!!.getP()}§7Trolling OPs is now ${if (!currentVal) "§aallowed" else "§cblocked"}")
                    }
                    "add-blocked" -> {
                        if (strings.size < 2) {
                            sender.sendMessage("${Core.instance!!.getP()}§cUsage: /troll add-blocked <player>")
                            return true
                        }
                        val toBlock = strings[1]
                        val blocklistAdd = Core.instance!!.pluginConfig.getStringList("blocklist")
                        if (!blocklistAdd.contains(toBlock)) {
                            blocklistAdd.add(toBlock)
                            Core.instance!!.pluginConfig["blocklist"] = blocklistAdd
                            Core.instance!!.saveConfig()
                            sender.sendMessage("${Core.instance!!.getP()}§b$toBlock §7has been added to the blocklist.")
                        } else {
                            sender.sendMessage("${Core.instance!!.getP()}§cThat player is already blocked.")
                        }
                    }
                    "remove-blocked" -> {
                        if (strings.size < 2) {
                            sender.sendMessage("${Core.instance!!.getP()}§cUsage: /troll remove-blocked <player>")
                            return true
                        }
                        val toRemove = strings[1]
                        val blocklistRem = Core.instance!!.pluginConfig.getStringList("blocklist")
                        if (blocklistRem.contains(toRemove)) {
                            blocklistRem.remove(toRemove)
                            Core.instance!!.pluginConfig["blocklist"] = blocklistRem
                            Core.instance!!.saveConfig()
                            sender.sendMessage("${Core.instance!!.getP()}§b$toRemove §7has been removed from the blocklist.")
                        } else {
                            sender.sendMessage("${Core.instance!!.getP()}§cThat player is not in the blocklist.")
                        }
                    }
                    "giveskull" -> {
                        p.inventory.addItem(Core.instance!!.getSkull())
                        p.sendMessage("${Core.instance!!.getP()}§bYou got the skull item.")
                    }
                    "help" -> {
                        val helpcmd = Help()
                        helpcmd.Help(p)
                    }
                    "contact" -> {
                        p.sendMessage("${Core.instance!!.getP()}§7Plugin Contact: ")
                        p.sendMessage("${Core.instance!!.getP()}§9Discord§7: §nhttps://discord.gg/???/")
                        p.sendMessage("${Core.instance!!.getP()}https://www.spigotmc.org/resources/?/")
                    }
                    "player" -> {
                        p.sendMessage("${Core.instance!!.getP()}§7For command line trolling:")
                        p.sendMessage("${Core.instance!!.getP()}§9/trollf <PLAYER> <TROLL>")
                        p.sendMessage("${Core.instance!!.getP()}")
                        p.sendMessage("${Core.instance!!.getP()}§7For GUI trolling:")
                        p.sendMessage("${Core.instance!!.getP()}§9/Troll")
                    }
                    else -> {
                        if (sender !is Player) {
                            sender.sendMessage("§cOnly players can open the selection GUI.")
                            return true
                        }
                        val ps = PlayerSelectorInventory()
                        ps.openSel(sender)
                    }
                }
            }
        } else {
            sender.sendMessage("§cOnly players can run this command.")
        }
        return true
    }

    companion object {
        fun convert(setOfString: Set<String>): Array<String> {
            val arrayOfString = arrayOfNulls<String>(setOfString.size)
            var index = 0
            for (str in setOfString) {
                arrayOfString[index++] = str
            }
            @Suppress("UNCHECKED_CAST")
            return arrayOfString as Array<String>
        }

        fun isNumeric(str: String?): Boolean {
            if (str == null || str.isEmpty()) return false
            return try {
                str.toDouble()
                true
            } catch (e: NumberFormatException) {
                false
            }
        }
    }
}
