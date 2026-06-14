package com.leomadrassi.trollingfreedomreborn.trolls.movement

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class Lightning : Listener {
    companion object {
        val Lightning1: MutableList<String> = ArrayList()
    }

    fun Lightning(p: Player) {
        Lightning1.add(p.name)
    }

    fun UnLightning(p: Player) {
        Lightning1.remove(p.name)
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val p = event.player
        if (Lightning1.contains(p.name)) {
            if (!event.from.toVector().equals(event.to.toVector())) {
                val world = p.world
                world.strikeLightning(p.location)
            }
        }
    }
}
