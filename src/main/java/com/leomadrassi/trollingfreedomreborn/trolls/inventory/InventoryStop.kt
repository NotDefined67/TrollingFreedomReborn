package com.leomadrassi.trollingfreedomreborn.trolls.inventory

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryOpenEvent

class InventoryStop : CommandExecutor, Listener {
    companion object {
        val InvStop1 = ArrayList<String>()
    }

    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (commandLabel.equals("unchesttroll", ignoreCase = true)) {
            val p = sender as Player
            UnInventoryStop(p)
        }
        return false
    }

    fun InventoryStop(p: Player) {
        val p2 = p.player!!
        InvStop1.add(p2.name)
    }

    fun UnInventoryStop(p: Player) {
        val p2 = p.player!!
        InvStop1.remove(p2.name)
    }

    @EventHandler
    fun onInvOpen(e: InventoryOpenEvent) {
        val p = e.player as Player
        if (InvStop1.contains(p.name)) {
            e.isCancelled = true
        }
    }
}
