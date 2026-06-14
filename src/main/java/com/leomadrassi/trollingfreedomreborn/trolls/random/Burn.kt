package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.entity.Player
import org.bukkit.event.Listener

class Burn : Listener {
    companion object {
        fun Burn(p: Player) {
            val p2 = p.name
            p.fireTicks = 1000000000
        }
    }
}
