package com.leomadrassi.trollingfreedomreborn.trolls.chat

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import io.papermc.paper.event.player.AsyncChatEvent

class Deafen : Listener {
    companion object {
        val Deaf1 = mutableListOf<String>()
    }

    fun Deafen(p: Player) {
        Deaf1.add(p.name)
    }

    fun UnDeafen(p: Player) {
        Deaf1.remove(p.name)
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerChat(event: AsyncChatEvent) {
        val p = event.player
        if (Deaf1.contains(p.name)) {
            event.isCancelled = true
        }
    }
}
