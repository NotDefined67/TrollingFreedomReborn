package com.leomadrassi.trollingfreedomreborn.trolls.explosion

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameRule
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class TNT : Listener {

    companion object {
        fun FakeNuke(p: Player) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5May death rain upon them."))
            p.playSound(p.location, Sound.ENTITY_TNT_PRIMED, 100f, 1f)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(1.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(2.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(3.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(5.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 1.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 2.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 3.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 4.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 5.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(1.0, 5.0, 1.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(2.0, 5.0, 2.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(3.0, 5.0, 3.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(4.0, 5.0, 4.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(5.0, 5.0, 5.0), EntityType.TNT)
            val wasFeedbackEnabled = p.world.getGameRuleValue(GameRule.SEND_COMMAND_FEEDBACK) ?: false
            if (wasFeedbackEnabled) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule send_command_feedback false")
            }
            Bukkit.getLogger().setFilter { record -> !record.message.lowercase().startsWith("/minecraft:kill") }
            Bukkit.getLogger().setFilter { record -> !ChatColor.stripColor(record.message)!!.lowercase().startsWith("killed primed tnt") }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:kill @e[type=tnt]")
            }, 60L)
        }
    }

    fun Nuke(p: Player) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5May death rain upon them."))
        for (x in 0 until 20) {
            p.playSound(p.location, Sound.ENTITY_TNT_PRIMED, 100f, 1f)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(1.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(2.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(3.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(5.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 1.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 2.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 3.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 4.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 5.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(0.0, 5.0, 0.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(1.0, 5.0, 1.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(2.0, 5.0, 2.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(3.0, 5.0, 3.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(4.0, 5.0, 4.0), EntityType.TNT)
            p.location.world.spawnEntity(p.location.add(5.0, 5.0, 5.0), EntityType.TNT)
        }
    }
}
