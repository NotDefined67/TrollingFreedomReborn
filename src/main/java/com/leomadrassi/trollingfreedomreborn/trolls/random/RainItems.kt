package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.inventory.ItemStack
import java.util.ArrayList

class RainItems : Listener {
    companion object {
        @JvmField
        val Rain1 = ArrayList<String>()
    }

    fun RainItem(p: Player) {
        Rain1.add(p.name)
        val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            if (!p.isOnline || !Rain1.contains(p.name)) {
                return@Runnable
            }
            for (online in Bukkit.getOnlinePlayers()) {
                val item = ItemStack(
                    Material.matchMaterial(Core.instance.pluginConfig.getString("troll-config.rain-item-material")!!)!!
                )
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(1.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(2.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(3.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(4.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(5.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 1.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 2.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 3.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 4.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 5.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(1.0, 5.0, 1.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(2.0, 5.0, 2.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(3.0, 5.0, 3.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(4.0, 5.0, 4.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(5.0, 5.0, 5.0), item))
                online.inventory.remove(item)

                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-1.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-2.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-3.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-4.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-5.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 1.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 2.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 3.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 4.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 5.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-1.0, 5.0, 1.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-2.0, 5.0, 2.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-3.0, 5.0, 3.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-4.0, 5.0, 4.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-5.0, 5.0, 5.0), item))
                online.inventory.remove(item)

                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(1.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(2.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(3.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(4.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(5.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -1.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -2.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -3.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -4.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -5.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(1.0, 5.0, -1.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(2.0, 5.0, -2.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(3.0, 5.0, -3.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(4.0, 5.0, -4.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(5.0, 5.0, -5.0), item))
                online.inventory.remove(item)

                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-1.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-2.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-3.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-4.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-5.0, 5.0, 0.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -1.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -2.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -3.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -4.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(0.0, 5.0, -5.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-1.0, 5.0, -1.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-2.0, 5.0, -2.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-3.0, 5.0, -3.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-4.0, 5.0, -4.0), item))
                online.inventory.remove(item)
                p.setPassenger(p.location.world.dropItem(p.eyeLocation.add(-5.0, 5.0, -5.0), item))
                online.inventory.remove(item)
            }
        }, 5L, 5L)
        Core.instance.addTask(p, "rainitems", id)
    }

    fun UnRainItem(p: Player) {
        if (Rain1.contains(p.name)) {
            Rain1.remove(p.name)
            p.getNearbyEntities(20.0, 20.0, 20.0).stream()
                .filter { entstream -> entstream is Item }
                .map { it as Item }
                .filter { item ->
                    item.itemStack.type == Material.matchMaterial(
                        Core.instance.pluginConfig.getString("troll-config.rain-item-material")!!
                    )
                }
                .forEach { obj: Entity -> obj.remove() }
        }
    }

    @EventHandler
    fun onPickUp(e: EntityPickupItemEvent) {
        val p = e.entity as Player
        if (Rain1.isNotEmpty()) {
            val item = e.item.itemStack
            if (item.type == Material.matchMaterial(
                    Core.instance.pluginConfig.getString("troll-config.rain-item-material")!!
                )
            ) {
                e.isCancelled = true
                e.item.remove()
            }
        }
    }
}
