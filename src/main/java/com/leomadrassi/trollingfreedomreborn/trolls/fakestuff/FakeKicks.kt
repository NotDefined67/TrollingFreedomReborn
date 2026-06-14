package com.leomadrassi.trollingfreedomreborn.trolls.fakestuff

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class FakeKicks : Listener {
    companion object {
        @JvmStatic
        fun FakeCrash(p: Player) {
            p.kickPlayer(ChatColor.translateAlternateColorCodes('&', "Internal exception: java.net.SocketException: Connection reset."))
        }

        @JvmStatic
        fun FakeClosed(p: Player) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o[Server: Server Stopping]"))
        }

        @JvmStatic
        fun FakeBan(p: Player) {
            p.kickPlayer(ChatColor.translateAlternateColorCodes('&', "You have been banned from this server!"))
        }
    }
}
