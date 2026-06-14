package com.leomadrassi.trollingfreedomreborn.trolls.movement

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerToggleSneakEvent

class SneakDestroy : Listener {
    companion object {
        val Sneak1: MutableList<String> = ArrayList()
    }

    fun SneakDestroy(p: Player) {
        Sneak1.add(p.name)
    }

    fun UnSneakDestroy(p: Player) {
        Sneak1.remove(p.name)
        Sneak1.remove(p.name)
        Sneak1.remove(p.name)
    }

    @EventHandler
    fun onSneak(event: PlayerToggleSneakEvent) {
        val p = event.player
        if (Sneak1.contains(p.name)) {
            val loc = p.location.clone().add(0.0, -1.0, 0.0)
            loc.block.setType(Material.AIR)
        }
    }
}
