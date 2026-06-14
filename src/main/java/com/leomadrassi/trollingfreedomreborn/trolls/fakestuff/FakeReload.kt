package com.leomadrassi.trollingfreedomreborn.trolls.fakestuff

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class FakeReload : Listener {
    fun Reload(p: Player) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o[CONSOLE: &cPlease note that this command is not supported and may cause issues when using some plugins.&7&o]"))
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable { p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o[CONSOLE: &cIf you encounter any issues please use the /stop command to restart your server.&7&o]")) }, 30L)
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable { p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o[CONSOLE: &aReload complete.&7&o]")) }, 100L)
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable {
            for (x in 0 until 1500) {
                val loc1 = p.location
                p.teleport(loc1)
            }
        }, 95L)
    }
}
