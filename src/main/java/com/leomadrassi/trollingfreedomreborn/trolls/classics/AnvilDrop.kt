package com.leomadrassi.trollingfreedomreborn.trolls.classics

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class AnvilDrop : Listener {

    companion object {
        fun Anvil(p: Player) {
            val taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
                if (p.isOnline) {
                    val loc1 = p.location.add(p.location.direction)
                    val block1 = p.world.getBlockAt(loc1.add(0.0, 20.0, 0.0))
                    block1.setType(Material.DAMAGED_ANVIL)
                }
            }, 40L, 10L)
            Core.instance.addTask(p, "anvildrop", taskId)
        }
    }
}
