package com.leomadrassi.trollingfreedomreborn.trolls.explosion

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class TNTPlace : Listener {

    companion object {
        val Fireball1 = ArrayList<String>()
    }

    fun TNTPlace(p: Player) {
        val p2 = p.player!!
        Fireball1.add(p2.name)
    }

    fun UnTNTPlace(p: Player) {
        val p2 = p.player!!
        Fireball1.remove(p2.name)
    }

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        val p = e.player
        if (Fireball1.contains(p.name)) {
            p.location.world.spawnEntity(e.block.location, EntityType.TNT)
            e.blockPlaced.breakNaturally()
        }
    }
}
