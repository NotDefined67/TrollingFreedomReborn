package com.leomadrassi.trollingfreedomreborn.trolls.inventory

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitRunnable

class LockInventory : Listener {
    companion object {
        @JvmStatic
        fun Lock(p: Player) {
            val runnable = object : BukkitRunnable() {
                override fun run() {
                    if (!p.isOnline) {
                        cancel()
                        return
                    }

                    p.closeInventory()

                    if (p.isSneaking) {
                        cancel()
                    }
                }
            }

            val taskId = runnable.runTaskTimer(Core.instance, 1L, 1L).taskId
            Core.instance.addTask(p, "lockinventory", taskId)
        }
    }
}
