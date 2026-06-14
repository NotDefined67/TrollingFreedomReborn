package com.leomadrassi.trollingfreedomreborn.trolls.inventory

import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

class DropAll : Listener {
    companion object {
        @JvmStatic
        fun DropAll(p: Player) {
            for (i in p.inventory.contents) {
                if (i != null) {
                    p.world.dropItemNaturally(p.location, i)
                    p.inventory.remove(i)
                }
            }
        }

        @JvmStatic
        fun UnDropAll(p: Player) {
            p.isInvisible = false
        }
    }
}
