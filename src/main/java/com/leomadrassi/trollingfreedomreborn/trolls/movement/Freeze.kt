package com.leomadrassi.trollingfreedomreborn.trolls.movement

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerMoveEvent

class Freeze : Listener {
    companion object {
        val frozen: MutableList<String> = ArrayList()
    }

    fun Freeze(p: Player) {
        frozen.add(p.name)
    }

    fun Unfreeze(p: Player) {
        frozen.remove(p.name)
    }

    @EventHandler
    fun onPlayerMove(e: PlayerMoveEvent) {
        val p = e.player
        if (frozen.contains(p.name)) {
            e.setTo(e.from)
        }
    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val p = e.player
        if (frozen.contains(p.name)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        val p = e.player
        if (frozen.contains(p.name)) {
            e.isCancelled = true
        }
    }
}
