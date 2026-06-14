package com.leomadrassi.trollingfreedomreborn.trolls.classics

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class Spin : Listener {

    companion object {
        val spin1 = mutableListOf<String>()

        fun Spin(p: Player) {
            spin1.add(p.name)
            val tp = p.location
            val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
                tp.setYaw(tp.yaw + 90.0f)
                p.teleport(tp)
            }, 10L, 5L)
            Core.instance.addTask(p, "spin", id)
        }
    }

    fun StopSpin(p: Player) {
        spin1.remove(p.name)
    }
}
