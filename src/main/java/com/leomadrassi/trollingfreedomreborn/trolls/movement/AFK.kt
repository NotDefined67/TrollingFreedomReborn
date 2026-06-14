package com.leomadrassi.trollingfreedomreborn.trolls.movement

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class AFK : Listener {
    companion object {
        @JvmStatic
        fun FakeAFK(p: Player) {
            val p2 = p.name
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7* $p2 is now AFK."))
        }

        @JvmStatic
        fun FakeUnAFK(p: Player) {
            val p2 = p.name
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7* $p2 is no longer AFK."))
        }
    }
}
