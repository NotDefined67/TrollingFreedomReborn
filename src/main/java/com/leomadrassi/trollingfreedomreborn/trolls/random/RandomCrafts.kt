package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.inventory.ItemStack
import java.util.ArrayList
import java.util.Random

class RandomCrafts : Listener {
    companion object {
        @JvmField
        val randomcraft = ArrayList<String>()
    }

    private val random = Random()
    private val materials = Material.values()

    fun craftTroll(target: Player) {
        val name = target.name
        if (!randomcraft.contains(name)) {
            randomcraft.add(name)
        }
    }

    fun unCraftTroll(target: Player) {
        val name = target.name
        randomcraft.remove(name)
    }

    @EventHandler
    fun onCraft(e: PrepareItemCraftEvent) {
        if (e.view.player !is Player) return
        val p = e.view.player as Player
        if (randomcraft.contains(p.name)) {
            if (e.recipe == null || e.recipe!!.result.type == Material.AIR) {
                return
            }
            var randomMat: Material
            do {
                randomMat = materials[random.nextInt(materials.size)]
            } while (randomMat.isAir || !randomMat.isItem)
            e.inventory.setResult(ItemStack(randomMat, 1))
        }
    }
}
