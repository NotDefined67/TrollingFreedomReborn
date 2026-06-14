package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import java.util.ArrayList
import java.util.Random

class RandomParticle : Listener {
    companion object {
        @JvmField
        val RandomParticle1 = ArrayList<String>()
    }

    fun RandomParticle(p: Player) {
        val p2 = p.player
        RandomParticle1.add(p2!!.name)
    }

    fun UnRandomParticle(p: Player) {
        val p2 = p.player
        RandomParticle1.remove(p2!!.name)
    }

    @EventHandler
    fun onWalk(e: PlayerMoveEvent) {
        val p = e.player
        if (RandomParticle1.contains(p.name)) {
            val rnd = Random()
            val rand = rnd.nextInt(11)
            when (rand) {
                0 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.CLOUD, p.location, 10)
                }, 100L)
                1 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.LAVA, p.location, 10)
                }, 100L)
                3 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.SMOKE, p.location, 10)
                }, 100L)
                4 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.EXPLOSION, p.location, 10)
                }, 100L)
                5 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.PORTAL, p.location, 10)
                }, 100L)
                6 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.BUBBLE_POP, p.location, 10)
                }, 100L)
                7 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.CRIT, p.location, 10)
                }, 100L)
                8 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.DOLPHIN, p.location, 10)
                }, 100L)
                9 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.ENCHANT, p.location, 10)
                }, 100L)
                10 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.HEART, p.location, 10)
                }, 100L)
                11 -> Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.EXPLOSION, p.location, 10)
                }, 100L)
            }
        }
    }
}
