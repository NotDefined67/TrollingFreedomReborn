package com.leomadrassi.trollingfreedomreborn.trolls.classics

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class OP : Listener {

    companion object {
        fun FakeOP(p: Player) {
            val p2 = p.name
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o[Server: Made $p2 a server operator]"))
        }

        fun FakeDeOP(p: Player) {
            val p2 = p.name
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o[Server: Made $p2 no longer a server operator]"))
        }
    }
}
