package com.leomadrassi.trollingfreedomreborn.trolls.movement

import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class InvertWalk : Listener {
    companion object {
        val Invert1: MutableList<String> = ArrayList()

        @JvmStatic
        fun Invert(p: Player) {
            Invert1.add(p.name)
        }

        @JvmStatic
        fun UnInvert(p: Player) {
            if (Invert1.contains(p.name)) {
                p.walkSpeed = 0.2f
                p.flySpeed = 0.1f
                Invert1.remove(p.name)
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onMove(move: PlayerMoveEvent) {
        if (Invert1.contains(move.player.name)) {
            move.player.flySpeed = 0.5f
            move.player.walkSpeed = 1.0f
            val from = move.from
            val to = move.to!!
            val xDiff = from.x - to.x
            val zDiff = from.z - to.z
            to.setX(from.x + xDiff)
            to.setZ(from.z + zDiff)
        }
    }
}
