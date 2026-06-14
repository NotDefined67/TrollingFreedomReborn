package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import java.util.ArrayList

class Break : Listener {
    companion object {
        @JvmField
        val Break1 = ArrayList<String>()
    }

    fun Break(p: Player) {
        val p2 = p.player
        Break1.add(p2!!.name)
    }

    fun unBreak(p: Player) {
        val p2 = p.player
        Break1.remove(p2!!.name)
    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val p = e.player
        if (Break1.contains(p.name)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        val p = e.player
        if (Break1.contains(p.name)) {
            e.isCancelled = true
        }
    }
}
