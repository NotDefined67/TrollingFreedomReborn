package com.leomadrassi.trollingfreedomreborn.trolls.inventory

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import java.util.Random

class RandomInv : Listener {
    companion object {
        val RandomInv1 = ArrayList<String>()
    }

    fun RandomInv(p: Player) {
        val p2 = p.player!!
        RandomInv1.add(p2.name)
    }

    fun UnRandomInv(p: Player) {
        val p2 = p.player!!
        RandomInv1.remove(p2.name)
    }

    @EventHandler
    fun onOpenInv(e: InventoryCloseEvent) {
        val p = e.player as Player
        if (RandomInv1.contains(p.name)) {
            val rnd = Random()
            val rand = rnd.nextInt(7)
            when (rand) {
                0 -> {
                    val inv1 = Bukkit.createInventory(p, InventoryType.WORKBENCH)
                    p.openInventory(inv1)
                }
                1 -> {
                    val inv2 = Bukkit.createInventory(p, InventoryType.BARREL)
                    p.openInventory(inv2)
                }
                3 -> {
                    val inv3 = Bukkit.createInventory(p, InventoryType.FURNACE)
                    p.openInventory(inv3)
                }
                4 -> {
                    val inv4 = Bukkit.createInventory(p, InventoryType.ENCHANTING)
                    p.openInventory(inv4)
                }
                5 -> {
                    val inv5 = Bukkit.createInventory(p, InventoryType.DISPENSER)
                    p.openInventory(inv5)
                }
                6 -> {
                    val inv6 = Bukkit.createInventory(p, InventoryType.LECTERN)
                    p.openInventory(inv6)
                }
                7 -> {
                    val inv7 = Bukkit.createInventory(p, InventoryType.STONECUTTER)
                    p.openInventory(inv7)
                }
                8 -> {
                    val inv8 = Bukkit.createInventory(p, InventoryType.MERCHANT)
                    p.openInventory(inv8)
                }
                9 -> {
                    val inv9 = Bukkit.createInventory(p, InventoryType.HOPPER)
                    p.openInventory(inv9)
                }
                10 -> {
                    val inv10 = Bukkit.createInventory(p, InventoryType.GRINDSTONE)
                    p.openInventory(inv10)
                }
            }
        }
    }
}
