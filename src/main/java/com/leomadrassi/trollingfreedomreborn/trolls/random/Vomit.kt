package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.inventory.ItemStack
import java.util.ArrayList

class Vomit : Listener {
    companion object {
        @JvmField
        val Vomit1 = ArrayList<String>()
    }

    fun Vomit(p: Player) {
        val p2 = p.name
        Vomit1.add(p.name)
        val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            val item = ItemStack(Material.WHITE_WOOL)
            val item2 = ItemStack(Material.LIGHT_GRAY_WOOL)
            val item3 = ItemStack(Material.GRAY_WOOL)
            val item4 = ItemStack(Material.BLACK_WOOL)
            val item5 = ItemStack(Material.BROWN_WOOL)
            val item6 = ItemStack(Material.RED_WOOL)
            val item7 = ItemStack(Material.ORANGE_WOOL)
            val item8 = ItemStack(Material.YELLOW_WOOL)
            val item9 = ItemStack(Material.LIME_WOOL)
            val item10 = ItemStack(Material.GREEN_WOOL)
            val item11 = ItemStack(Material.CYAN_WOOL)
            val item12 = ItemStack(Material.LIGHT_BLUE_WOOL)
            val item13 = ItemStack(Material.BLUE_WOOL)
            val item14 = ItemStack(Material.PURPLE_WOOL)
            val item15 = ItemStack(Material.MAGENTA_WOOL)
            val item16 = ItemStack(Material.PINK_WOOL)

            val dropped = p.location.world.dropItem(p.eyeLocation, item)!!
            dropped.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped2 = p.location.world.dropItem(p.eyeLocation, item2)!!
            dropped2.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped3 = p.location.world.dropItem(p.eyeLocation, item3)!!
            dropped3.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped4 = p.location.world.dropItem(p.eyeLocation, item4)!!
            dropped4.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped5 = p.location.world.dropItem(p.eyeLocation, item5)!!
            dropped5.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped6 = p.location.world.dropItem(p.eyeLocation, item6)!!
            dropped6.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped7 = p.location.world.dropItem(p.eyeLocation, item7)!!
            dropped7.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped8 = p.location.world.dropItem(p.eyeLocation, item8)!!
            dropped8.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped9 = p.location.world.dropItem(p.eyeLocation, item9)!!
            dropped9.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped10 = p.location.world.dropItem(p.eyeLocation, item10)!!
            dropped10.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped11 = p.location.world.dropItem(p.eyeLocation, item11)!!
            dropped11.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped12 = p.location.world.dropItem(p.eyeLocation, item12)!!
            dropped12.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped13 = p.location.world.dropItem(p.eyeLocation, item13)!!
            dropped13.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped14 = p.location.world.dropItem(p.eyeLocation, item14)!!
            dropped14.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped15 = p.location.world.dropItem(p.eyeLocation, item15)!!
            dropped15.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
            val dropped16 = p.location.world.dropItem(p.eyeLocation, item16)!!
            dropped16.velocity = p.eyeLocation.direction.normalize().multiply(0.3)
        }, 5L, 5L)
        Core.instance.addTask(p, "vomit", id)
    }

    fun UnVomit(p: Player) {
        if (Vomit1.contains(p.name)) {
            val p2 = p.name
            Vomit1.remove(p.name)
            val player = p
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.WHITE_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.CYAN_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.LIGHT_GRAY_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.GRAY_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.BLACK_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.RED_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.ORANGE_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.BROWN_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.YELLOW_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.LIME_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.GREEN_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.CYAN_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.BLUE_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.LIGHT_BLUE_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.PURPLE_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.MAGENTA_WOOL }.forEach { it.remove() }
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { it is Item }.map { it as Item }
                .filter { it.itemStack.type == Material.PINK_WOOL }.forEach { it.remove() }
        }
    }

    @EventHandler
    fun PlayerItemPickup(e: EntityPickupItemEvent) {
        if (Vomit1.isNotEmpty()) {
            val item = e.item.itemStack
            val type = item.type
            if (type == Material.WHITE_WOOL || type == Material.CYAN_WOOL || type == Material.LIGHT_GRAY_WOOL ||
                type == Material.GRAY_WOOL || type == Material.BLACK_WOOL || type == Material.RED_WOOL ||
                type == Material.ORANGE_WOOL || type == Material.BROWN_WOOL || type == Material.YELLOW_WOOL ||
                type == Material.LIME_WOOL || type == Material.GREEN_WOOL || type == Material.BLUE_WOOL ||
                type == Material.LIGHT_BLUE_WOOL || type == Material.PURPLE_WOOL || type == Material.MAGENTA_WOOL ||
                type == Material.PINK_WOOL
            ) {
                e.isCancelled = true
                e.item.remove()
            }
        }
    }

    @EventHandler
    fun InventoryItemPickup(e: InventoryPickupItemEvent) {
        if (Vomit1.isNotEmpty()) {
            val item = e.item.itemStack
            val type = item.type
            if (type == Material.WHITE_WOOL || type == Material.CYAN_WOOL || type == Material.LIGHT_GRAY_WOOL ||
                type == Material.GRAY_WOOL || type == Material.BLACK_WOOL || type == Material.RED_WOOL ||
                type == Material.ORANGE_WOOL || type == Material.BROWN_WOOL || type == Material.YELLOW_WOOL ||
                type == Material.LIME_WOOL || type == Material.GREEN_WOOL || type == Material.BLUE_WOOL ||
                type == Material.LIGHT_BLUE_WOOL || type == Material.PURPLE_WOOL || type == Material.MAGENTA_WOOL ||
                type == Material.PINK_WOOL
            ) {
                e.isCancelled = true
                e.item.remove()
            }
        }
    }
}
