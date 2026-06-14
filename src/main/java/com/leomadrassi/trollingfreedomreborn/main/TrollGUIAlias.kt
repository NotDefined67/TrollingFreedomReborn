package com.leomadrassi.trollingfreedomreborn.main

import com.leomadrassi.trollingfreedomreborn.ui.PlayerSelectorInventory
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TrollGUIAlias : CommandExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§cOnly players can open the GUI.")
            return true
        }

        val p = sender

        if (!p.hasPermission("trollingfreedom.open")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Core.instance!!.pluginConfig.get("no-perms") as String))
            return true
        }

        if (args.isNotEmpty()) {
            val target = Bukkit.getPlayer(args[0])
            if (target != null) {
                if (!Core.canTroll(target)) {
                    p.sendMessage("§c§l[TFR] §7That player is §4blocked §7from trolling.")
                    return true
                }
            }
        }

        PlayerSelectorInventory().openSel(p)
        return true
    }
}
