package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerAnimationEvent
import org.bukkit.inventory.ItemStack
import java.util.ArrayList

class InstaToolBreak : Listener {
    companion object {
        @JvmField
        val InstaToolBreak1 = ArrayList<String>()
    }

    fun InstaToolBreak(p: Player) {
        val p2 = p.name
        InstaToolBreak1.add(p.name)
    }

    fun UnInstaToolBreak(p: Player) {
        val p2 = p.name
        InstaToolBreak1.remove(p.name)
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val p = event.player
        if (InstaToolBreak1.contains(p.name)) {
            val item = p.itemInHand
            val block = event.block
            if (block != null && item != null && (
                        item.type.name.contains("PICKAXE") || item.type.name.contains("AXE") ||
                        item.type.name.contains("SHOVEL") || item.type.name.contains("SWORD") ||
                        item.type.name.contains("SPADE"))
            ) {
                item.durability = 12000
                p.playSound(p.location, Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f)
            }
        }
    }

    @EventHandler
    fun onEntityPunch(event: PlayerAnimationEvent) {
        val p = event.player
        if (InstaToolBreak1.contains(p.name)) {
            val item = p.itemInHand
            if (item != null && (
                        item.type.name.contains("PICKAXE") || item.type.name.contains("AXE") ||
                        item.type.name.contains("SHOVEL") || item.type.name.contains("SWORD") ||
                        item.type.name.contains("SPADE"))
            ) {
                item.durability = 12000
                p.inventory.removeItem(item)
                p.playSound(p.location, Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f)
            }
        }
    }
}
