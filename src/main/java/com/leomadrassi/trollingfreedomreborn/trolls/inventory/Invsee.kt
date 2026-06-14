package com.leomadrassi.trollingfreedomreborn.trolls.inventory

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView

class Invsee : Listener {
    companion object {
        @JvmStatic
        fun Invsee(p: Player) {
            val inv: Inventory = p.inventory
            for (all in Core.instance.server.onlinePlayers.toList()) {
                all.openInventory(inv)
                val view = p.openInventory
                if (view.topInventory.toString().contains("Player")) {
                    view.close()
                }
            }
        }
    }
}
