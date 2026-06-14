package com.leomadrassi.trollingfreedomreborn.trolls.Beds

import net.kyori.adventure.text.Component
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent

class BedNight : Listener {
    companion object {
        val Bed1 = mutableListOf<String>()
    }

    fun BedNight(player: Player) {
        player.sendActionBar(Component.text("You can only sleep at night or during thunderstorms"))
    }

    fun BedMonster(player: Player) {
        player.sendActionBar(Component.text("You may not rest now; there are monsters nearby"))
        player.playSound(player.location, Sound.ENTITY_ZOMBIE_AMBIENT, 100f, 1f)
        player.playSound(player.location, Sound.ENTITY_ZOMBIE_AMBIENT, 100f, 1f)
    }

    fun StopSleep(player: Player) {
        Bed1.add(player.name)
        if (player.isSleeping) {
            player.wakeup(false)
        }
    }

    fun UnStopSleep(player: Player) {
        Bed1.remove(player.name)
    }

    @EventHandler
    fun onBedEnter(e: PlayerBedEnterEvent) {
        val p = e.player
        if (Bed1.contains(p.name)) {
            e.isCancelled = true
        }
    }
}
