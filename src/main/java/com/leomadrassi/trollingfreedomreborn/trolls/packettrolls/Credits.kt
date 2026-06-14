package com.leomadrassi.trollingfreedomreborn.trolls.packettrolls

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketContainer
import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import java.lang.reflect.Field

class Credits : Listener {
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

    fun Credits(player: Player) {
        if (field_spectatorMode == null) {
            Core.instance.logger.severe("Failed to show credits screen to player. This server version is not compatible.")
            return
        }
        val packet = PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE)
        try {
            field_spectatorMode!!.get(null)
            packet.gameStateIDs.write(0, 4)
            packet.float.write(0, 1.0f)
            sendPacket(player, packet)
        } catch (e: IllegalAccessException) {
            Core.instance.logger.severe("Failed to show credits screen to player:")
            e.printStackTrace()
        }
    }
}
