package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

class SlipperyHands : Listener {
    companion object {
        fun SlipperyHands(p: Player) {
            val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
                val item = p.inventory.itemInMainHand
                if (p.inventory.itemInMainHand != null && p.inventory.itemInMainHand.type != Material.AIR) {
                    p.world.dropItemNaturally(p.location, item)
                    p.inventory.remove(item)
                }
            }, 5L, 40L)
            Core.instance.addTask(p, "slipperyhands", id)
        }
    }
}
