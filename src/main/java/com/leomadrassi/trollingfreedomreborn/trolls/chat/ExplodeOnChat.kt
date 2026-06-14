package com.leomadrassi.trollingfreedomreborn.trolls.chat

import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import io.papermc.paper.event.player.AsyncChatEvent

class ExplodeOnChat : Listener {
    companion object {
        val Chat1 = mutableListOf<String>()
    }

    fun Chat(p: Player) {
        Chat1.add(p.name)
    }

    fun UnChat(p: Player) {
        Chat1.remove(p.name)
    }

    @EventHandler
    fun onChat(e: AsyncChatEvent) {
        val p = e.player
        if (Chat1.contains(p.name)) {
            val w = p.world
            w.createExplosion(p.location, 5.0f, true)
        }
    }
}
