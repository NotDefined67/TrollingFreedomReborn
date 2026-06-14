package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.ArrayList

class RingOfFire : Listener {
    companion object {
        @JvmField
        val nuke1 = ArrayList<String>()
    }

    fun Nuke(p: Player) {
        nuke1.add(p.name)
        particle(p)
        extra(p)
        p.location.world.strikeLightning(p.location)
    }

    fun UnNuke(p: Player) {
        p.fireTicks = 0
        p.isVisualFire = false
        nuke1.remove(p.name)
    }

    private fun extra(p: Player) {
        p.gameMode = GameMode.SURVIVAL
        p.isFlying = false
    }

    private fun particle(p: Player) {
        p.playSound(p.location, Sound.ENTITY_GENERIC_BURN, 100f, 1f)
        p.world.playSound(p.location, Sound.BLOCK_BELL_RESONATE, 100f, 1f)
        p.world.spawnParticle(Particle.EXPLOSION, p.location, 1)
        p.world.spawnParticle(Particle.FALLING_LAVA, p.location, 10)
        p.world.spawnParticle(Particle.FIREWORK, p.location, 10)
    }

    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        val p = e.entity.player
        if (nuke1.contains(p!!.name)) {
            UnNuke(p)
        }
    }

    @EventHandler
    fun onWalk(e: PlayerMoveEvent) {
        val p = e.player
        if (nuke1.contains(p.name)) {
            if (p.location.block.type != Material.WATER) {
                createFlameRings(p)
            }
        }
    }

    private fun createFlameRings(p: Player) {
        val id = object : BukkitRunnable() {
            var alpha = 0.0

            override fun run() {
                alpha += Math.PI / 16
                val loc = p.location
                val firstLocation = loc.clone().add(Math.cos(alpha), Math.sin(alpha) + 1, Math.sin(alpha))
                val secondLocation = loc.clone().add(Math.cos(alpha + Math.PI), Math.sin(alpha) + 1, Math.sin(alpha + Math.PI))
                p.spawnParticle(Particle.FLAME, firstLocation, 0, 0.0, 0.0, 0.0, 0.0)
                p.spawnParticle(Particle.FLAME, secondLocation, 0, 0.0, 0.0, 0.0, 0.0)
                p.fireTicks = 1000
            }
        }.runTaskTimer(JavaPlugin.getPlugin(Core::class.java), 0, 1).taskId
        Core.instance.addTask(p, "ringoffire", id)
    }
}
