package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.entity.Player
import org.bukkit.event.Listener

class Void : Listener {
    companion object {
        fun Void(p: Player) {
            val p2 = p.name
            p.teleport(p.location.add(0.0, -600.0, 0.0))
            p.isFlying = false
        }
    }
}
