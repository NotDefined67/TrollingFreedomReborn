package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class Sounds : Listener {
    companion object {
        fun CaveSound(p: Player) {
            val p2 = p.name
            val taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
                p.playSound(p.location, Sound.AMBIENT_CAVE, 100f, 1f)
            }, 10L, 5L)
            Core.instance.addTask(p, "cavesounds", taskId)
        }

        fun GhastSound(p: Player) {
            val p2 = p.name
            val taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
                p.playSound(p.location, Sound.ENTITY_GHAST_AMBIENT, 100f, 1f)
            }, 10L, 40L)
            Core.instance.addTask(p, "ghastsound", taskId)
        }
    }
}
