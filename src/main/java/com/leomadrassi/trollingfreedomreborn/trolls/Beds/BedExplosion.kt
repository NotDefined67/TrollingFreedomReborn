package com.leomadrassi.trollingfreedomreborn.trolls.Beds

import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent

class BedExplosion : Listener {
    companion object {
        val Explode1 = mutableListOf<String>()
    }

    fun BedExplosion(p: Player) {
        Explode1.add(p.name)
    }

    fun UnBedExplosion(p: Player) {
        Explode1.remove(p.name)
    }

    @EventHandler
    fun onSex(event: PlayerBedEnterEvent) {
        val p = event.player
        if (Explode1.contains(p.name)) {
            val w = p.world
            w.createExplosion(event.bed.location, 3.0f, true)
        }
    }
}
