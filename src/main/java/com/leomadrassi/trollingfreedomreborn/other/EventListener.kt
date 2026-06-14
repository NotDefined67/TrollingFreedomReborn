package com.leomadrassi.trollingfreedomreborn.other

import com.leomadrassi.trollingfreedomreborn.commands.UnTroll
import com.leomadrassi.trollingfreedomreborn.main.Core
import com.cryptomorin.xseries.XMaterial
import com.leomadrassi.trollingfreedomreborn.ui.PlayerSelectorInventory
import com.leomadrassi.trollingfreedomreborn.ui.TrollInventory
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.io.IOException

class EventListener : Listener {

    fun getNOU(p: Player): String {
        return if (Core.uid()) p.name else p.uniqueId.toString()
    }

    @EventHandler
    fun onPlayerFall(e: EntityDamageEvent) {
        if (e.entity is Player) {
            val player = e.entity as Player
            try {
                if (e.cause == EntityDamageEvent.DamageCause.FALL) {
                    val key = if (Core.instance.config.getBoolean("using-uuid")) player.name else player.uniqueId.toString()
                    if (TrollInventory.getMaps("LP")?.containsKey(key) == true) {
                        e.isCancelled = true
                        TrollInventory.getMaps("LP")?.remove(key)
                    }
                }
            } catch (_: Exception) {
            }
        }
    }

    @EventHandler
    fun onPlayerInteract(e: PlayerInteractEvent) {
        val p = e.player
        if (Core.advCheck("ms3.use", p)) {
            if (e.action == Action.RIGHT_CLICK_BLOCK || e.action == Action.RIGHT_CLICK_AIR) {
                if (p.inventory.itemInMainHand != null && p.inventory.itemInMainHand.type != XMaterial.AIR.parseMaterial()) {
                    if (p.inventory.itemInMainHand.isSimilar(Core.instance.getSkull())) {
                        PlayerSelectorInventory().openSel(p)
                        e.isCancelled = true
                    }
                }
            }
        }
    }

    @Throws(IOException::class)
    @EventHandler
    fun onPlayerDisconnect(e: PlayerQuitEvent) {
        val p = e.player
        UnTroll().StopTrolls(p, p)
    }

    @EventHandler
    fun onMove(e: PlayerMoveEvent) {
        if (e.player is Player) {
            val player = e.player
            try {
                val key = if (Core.uid()) player.name else player.uniqueId.toString()
                if (TrollInventory.getMaps("FR")?.containsKey(key) == true) {
                    e.isCancelled = true
                }
            } catch (_: Exception) {
            }
        }
    }

    @EventHandler
    fun onPlayerDrop(e: PlayerDropItemEvent) {
        val p = e.player
        if (Core.advCheck("trollingfreedom.troll", p)) {
            val droppedi = e.itemDrop
            if (droppedi.itemStack.isSimilar(Core.instance.getSkull())) {
                p.sendMessage(Core.instance.getP() + "You cant drop this item.")
                e.isCancelled = true
            }
        }
    }
}
