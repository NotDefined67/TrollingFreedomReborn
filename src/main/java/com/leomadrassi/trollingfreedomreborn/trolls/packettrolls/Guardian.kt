package com.leomadrassi.trollingfreedomreborn.trolls.packettrolls

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketContainer
import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import java.lang.reflect.Field

class Guardian : Listener {
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
            player.playSound(player.location, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 100f, 1f)
            player.playSound(player.location, Sound.ENTITY_ELDER_GUARDIAN_DEATH, 100f, 1f)
            player.playSound(player.location, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 100f, 1f)
            player.playSound(player.location, Sound.ENTITY_ELDER_GUARDIAN_AMBIENT, 100f, 1f)
            return true
        }
    }

    fun Guardian(player: Player) {
        if (field_spectatorMode == null) {
            Core.instance.logger.severe("Failed to show guardian to player. This server version is not compatible.")
            return
        }
        val packet = PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE)
        packet.gameStateIDs.write(0, 10)
        packet.float.write(0, 0f)
        sendPacket(player, packet)
    }
}
