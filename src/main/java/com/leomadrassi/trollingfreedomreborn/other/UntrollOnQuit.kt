package com.leomadrassi.trollingfreedomreborn.other

import com.leomadrassi.trollingfreedomreborn.commands.UnTroll
import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class UntrollOnQuit : Listener {

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        if (Core.instance.config.getBoolean("values.untroll-on-quit", true)) {
            val player = event.player
            try {
                UnTroll().StopTrolls(player, null)
                Bukkit.getConsoleSender().sendMessage("§b§lTFR §8| §7Auto-untrolled §f${player.name} §7due to disconnect.")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
