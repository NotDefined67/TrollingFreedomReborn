package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.inventory.ItemStack
import java.util.ArrayList

class Potato : Listener {
    companion object {
        @JvmField
        val Break1 = ArrayList<String>()
    }

    fun potato(p: Player) {
        val p2 = p.player
        p.isInvisible = true
        val tp = p.location
        Break1.add(p2!!.name)
        val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            val item1 = ItemStack(Material.POTATO)
            p.world.dropItemNaturally(p.location.add(0.0, 0.0, 0.0), item1)
            p.world.dropItemNaturally(p.location.add(0.0, 0.0, 0.0), item1)
            p.world.dropItemNaturally(p.location.add(0.0, 0.0, 0.0), item1)
        }, 10L, 5L)
        Core.instance.addTask(p, "potato", id)
    }

    fun unpotato(p: Player) {
        if (Break1.contains(p.name)) {
            p.isInvisible = false
            p.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { entstream -> entstream is Item }
                .map { it as Item }
                .filter { item -> item.itemStack.type == Material.POTATO }
                .forEach { obj: Entity -> obj.remove() }
            Break1.remove(p.name)
        }
    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val p = e.player
        if (Break1.contains(p.name)) e.isCancelled = true
    }

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        val p = e.player
        if (Break1.contains(p.name)) e.isCancelled = true
    }

    @EventHandler
    fun onPickUp(e: EntityPickupItemEvent) {
        if (Break1.isNotEmpty()) {
            val item = e.item.itemStack
            if (item.type == Material.POTATO) {
                e.isCancelled = true
                e.item.remove()
            }
        }
    }
}
