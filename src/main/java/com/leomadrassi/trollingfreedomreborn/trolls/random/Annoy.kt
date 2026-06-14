package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import java.util.ArrayList

class Annoy : Listener {
    companion object {
        @JvmField
        val Annoy1 = ArrayList<String>()

        fun Annoy(p: Player) {
            if (Annoy1.contains(p.name)) return

            Annoy1.add(p.name)

            val taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
                if (!p.isOnline || !Annoy1.contains(p.name)) {
                    stopAnnoy(p)
                    return@Runnable
                }

                p.playSound(p.location, Sound.ENTITY_VILLAGER_AMBIENT, 100f, 1f)
                p.playSound(p.location, Sound.ENTITY_VILLAGER_CELEBRATE, 100f, 1f)
                p.playSound(p.location, Sound.ENTITY_VILLAGER_NO, 100f, 1f)
            }, 10L, 5L)

            Core.instance.addTask(p, "annoy", taskId)
        }

        fun stopAnnoy(p: Player) {
            Annoy1.remove(p.name)
        }
    }
}
