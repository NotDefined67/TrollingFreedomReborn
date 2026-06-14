package com.leomadrassi.trollingfreedomreborn.trolls.classics

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class Pumpkin : Listener {

    companion object {
        val Pumpkin1 = mutableListOf<String>()
    }

    fun Pumpkin(p: Player) {
        if (p == null) return
        val p2 = p.getPlayer()!!
        val helmet = p.inventory.helmet

        if (helmet != null && helmet.type != Material.AIR) {
            p.world.dropItem(p.location, helmet)
        }

        Pumpkin1.add(p2.name)

        val taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            if (!p.isOnline || !Pumpkin1.contains(p.name)) return@Runnable

            val stack = ItemStack(Material.CARVED_PUMPKIN)
            p.inventory.setHelmet(stack)
        }, 10L, 5L)

        Core.instance.addTask(p, "pumpkin", taskId)
    }

    fun unPumpkin(p: Player) {
        val p2 = p.getPlayer()!!
        if (Pumpkin1.contains(p.name)) {
            Pumpkin1.remove(p2.name)
            if (p.inventory.helmet == null) return
            if (p.inventory.helmet == ItemStack(Material.CARVED_PUMPKIN)) {
                p.inventory.setHelmet(ItemStack(Material.AIR))
            }
        }
    }

    @EventHandler
    fun onPumpkinMove(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (Pumpkin1.contains(player.name)) {
            val clicked = event.currentItem
            if (clicked!!.type == Material.CARVED_PUMPKIN) {
                event.isCancelled = true
            }
        }
    }
}
