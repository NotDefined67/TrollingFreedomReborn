package com.leomadrassi.trollingfreedomreborn.trolls.movement

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class Lag : Listener {
    companion object {
        @JvmStatic
        fun Lagg(p: Player) {
            val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
                val loc1 = p.location
                p.teleport(loc1)
            }, 8L, 5L)
            Core.instance.addTask(p, "lag", id)
        }
    }
}
