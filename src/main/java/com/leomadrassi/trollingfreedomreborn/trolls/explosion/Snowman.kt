package com.leomadrassi.trollingfreedomreborn.trolls.explosion

import com.leomadrassi.trollingfreedomreborn.main.Core
import com.leomadrassi.trollingfreedomreborn.other.MathUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.util.Vector
import java.util.Random

class Snowman : Listener {

    private val sheepArrayList = ArrayList<org.bukkit.entity.Snowman>()

    fun Snowman(p: Player) {
        val s = p.world.spawn(p.location, org.bukkit.entity.Snowman::class.java)

        s.isInvulnerable = true
        s.noDamageTicks = 20
        s.customName = "${ChatColor.RED}Explosive Snowman"
        s.isCustomNameVisible = false

        val scheduler = Bukkit.getScheduler()
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 40L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 50f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 80L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 124L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 50f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 144L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 164L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 50f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 184L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 200L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 50f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 216L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 240L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 256L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 50f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 268L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 280L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 50f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 292L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 304L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 50f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 312L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 320L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 50f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 328L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_CHIME, 10f, 5f)
            s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
        }, 336L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.remove()
            s.world.spawnParticle(Particle.FIREWORK, s.location, 2, 2.0, 2.0, 2.0)
            p.playSound(p.location, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 10f, 1f)
        }, 338L)
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable {
            for (x in 0 until 250) {
                s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 50.0)
                val snowman = p.player!!.world.spawn(s.location, org.bukkit.entity.Snowman::class.java)
                val r = Random()
                MathUtils.applyVelocity(snowman, Vector(r.nextDouble() - 0.5, r.nextDouble() / 2.0, r.nextDouble() - 0.5).multiply(2).add(Vector(0.0, 0.8, 0.0)))
                snowman.isInvulnerable = true
                snowman.isAware = true
                snowman.noDamageTicks = 120
                sheepArrayList.add(snowman)
                Bukkit.getScheduler().runTaskLater(Core.instance, Runnable {
                    s.world.spawnParticle(Particle.FIREWORK, s.location, 1, 10.0, 1.0, 100.0)
                    p.world.spawnParticle(Particle.FLAME, snowman.location, 5)
                    p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_PLING, 10f, 10f)
                    snowman.remove()
                }, 110)
                sheepArrayList.remove(s)
                s.remove()
            }
        }, 352L)
    }
}
