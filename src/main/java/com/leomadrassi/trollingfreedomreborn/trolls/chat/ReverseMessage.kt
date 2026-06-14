package com.leomadrassi.trollingfreedomreborn.trolls.chat

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

class ReverseMessage : Listener {
    companion object {
        val Reverse1 = mutableListOf<String>()
    }

    fun reverseMessage(i: String): String {
        val res = StringBuilder()
        val length = i.length
        for (i1 in length - 1 downTo 0) {
            res.append(i[i1])
        }
        return res.toString()
    }

    fun Reverse(p: Player) {
        Reverse1.add(p.name)
    }

    fun UnReverse(p: Player) {
        Reverse1.remove(p.name)
    }

    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val p = event.player
        if (Reverse1.contains(p.name)) {
            val msg = reverseMessage(PlainTextComponentSerializer.plainText().serialize(event.message()))
            event.message(Component.text(msg))
        }
    }
}
