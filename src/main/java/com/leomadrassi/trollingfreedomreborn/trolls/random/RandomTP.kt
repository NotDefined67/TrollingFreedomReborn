package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class RandomTP : Listener {
    companion object {
        fun RandomTP(p: Player) {
            val p2 = p.name
            val xk = (Math.random() * 1000 + 1).toInt()
            val yk = (Math.random() * 1000 + 1).toInt()
            val y = 255.0
            val x = xk.toDouble()
            val z = yk.toDouble()
            val w = p.world
            p.teleport(Location(w, x, y, z))
            teleportToTop(p)
        }

        fun teleportToTop(p: Player) {
            val l = p.location
            val b = p.world.getHighestBlockAt(l.blockX, l.blockZ)
            val l2 = Location(b.location.world, b.location.blockX.toDouble(), (b.location.blockY + 1).toDouble(), b.location.blockZ.toDouble())
            p.teleport(l2)
        }
    }
}
