package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.inventory.ItemStack
import java.util.ArrayList

class Poop : Listener {
    companion object {
        @JvmField
        val Poop1 = ArrayList<String>()
    }

    fun Poop(p: Player) {
        val p2 = p.name
        Poop1.add(p.name)
    }

    fun UnPoop(p: Player) {
        if (Poop1.contains(p.name)) {
            val p2 = p.name
            Poop1.remove(p.name)
            val player = p
            player.getNearbyEntities(10.0, 10.0, 10.0).stream()
                .filter { entstream -> entstream is Item }
                .map { it as Item }
                .filter { item -> item.itemStack.type == Material.COCOA_BEANS }
                .forEach { obj: Entity -> obj.remove() }
        }
    }

    @EventHandler
    fun PlayerSneak(e: PlayerToggleSneakEvent) {
        val p = e.player
        if (Poop1.contains(p.name)) {
            val text = Poop1.toString()
            val result = text.replace("\\[".toRegex(), "").replace("\\]".toRegex(), "")
            val victim = Bukkit.getServer().getPlayer(result)
            val item = ItemStack(Material.COCOA_BEANS)
            victim!!.world.playSound(victim.location, Sound.ENTITY_FOX_AGGRO, 100.0f, 1.0f)
            val dropped = p.location.world.dropItem(p.eyeLocation.add(0.0, -1.3, 0.0), item)
            dropped!!.velocity = p.eyeLocation.direction.normalize().multiply(-0.3)
        }
    }

    @EventHandler
    fun PlayerItemPickup(e: EntityPickupItemEvent) {
        if (Poop1.isNotEmpty()) {
            val item = e.item.itemStack
            if (item.type == Material.COCOA_BEANS) {
                e.isCancelled = true
                e.item.remove()
            }
        }
    }

    @EventHandler
    fun InventoryItemPickup(e: InventoryPickupItemEvent) {
        if (Poop1.isNotEmpty()) {
            val item = e.item.itemStack
            if (item.type == Material.COCOA_BEANS) {
                e.isCancelled = true
                e.item.remove()
            }
        }
    }
}
