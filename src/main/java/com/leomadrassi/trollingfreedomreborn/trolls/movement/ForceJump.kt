package com.leomadrassi.trollingfreedomreborn.trolls.movement

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class ForceJump : Listener {
    companion object {
        val Jump1: MutableList<String> = ArrayList()
    }

    fun Jump(p: Player) {
        Jump1.add(p.name)
    }

    fun UnJump(p: Player) {
        Jump1.remove(p.name)
    }

    @EventHandler
    fun onWalk(e: PlayerMoveEvent) {
        val p = e.player
        if (Jump1.contains(p.name)) {
            if (!e.from.toVector().equals(e.to.toVector()))
                p.setVelocity(p.velocity.setY(1.0))
            val id = Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable { p.setVelocity(p.velocity.setY(-1.0)) }, 20L)
            Core.instance.addTask(p, "forcejump", id)
        }
    }
}
