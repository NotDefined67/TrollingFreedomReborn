package com.leomadrassi.trollingfreedomreborn.trolls.packettrolls

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketContainer
import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import java.lang.reflect.Field

class Demo : Listener {
    companion object {
        var field_spectatorMode: Field? = null

        init {
            try {
                field_spectatorMode = PacketType.Play.Server.GAME_STATE_CHANGE.packetClass.getDeclaredField("e")
            } catch (e: NoSuchFieldException) {
                Core.instance.logger.severe("Failed to initialise the troll. This server version is not compatible.")
                e.printStackTrace()
            }
        }

        fun sendPacket(player: Player, packet: PacketContainer): Boolean {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet)
            return true
        }
    }

    fun DemoMenu(player: Player) {
        if (field_spectatorMode == null) {
            Core.instance.logger.severe("Failed to show demo screen to player. This server version is not compatible.")
            return
        }
        val packet = PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE)
        packet.gameStateIDs.write(0, 5)
        packet.float.write(0, 0f)
        sendPacket(player, packet)
    }
}
