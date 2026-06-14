package com.leomadrassi.trollingfreedomreborn.trolls.chat

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

class ChatChange : Listener {
    companion object {
        val Chat1 = mutableListOf<String>()
    }

    fun ChatChange(p: Player) {
        Chat1.add(p.name)
    }

    fun UnChatChange(p: Player) {
        Chat1.remove(p.name)
    }

    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val p = event.player
        if (Chat1.contains(p.name)) {
            val messages = Core.instance.pluginConfig.getStringList("troll-config.randomchat")
            val randomitem = (0 until messages.size).random()
            val message = messages[randomitem]
            val replaced = message.replace("&", "§")
            val component = LegacyComponentSerializer.legacySection().deserialize(replaced)
            event.message(component)
        }
    }
}
