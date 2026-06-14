package com.leomadrassi.trollingfreedomreborn.trolls.explosion

import com.leomadrassi.trollingfreedomreborn.main.Core
import com.leomadrassi.trollingfreedomreborn.other.MathUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.entity.Sheep
import org.bukkit.event.Listener
import org.bukkit.util.Vector
import java.util.Random

class ExplosiveSheep : Listener {

    private val sheepArrayList = ArrayList<Sheep>()

    fun Sheep(p: Player) {
        val s = p.world.spawn(p.location, Sheep::class.java)

        s.isInvulnerable = true
        s.noDamageTicks = 20
        s.customName = "${ChatColor.RED}Explosive Sheep"
        s.isCustomNameVisible = false
        s.color = DyeColor.RED
        s.isSheared = false
        val scheduler = Bukkit.getScheduler()
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.WHITE
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 40L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.RED
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 10f)
        }, 80L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.WHITE
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 124L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.RED
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 10f)
        }, 144L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.WHITE
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 164L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.RED
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 10f)
        }, 184L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.WHITE
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 200L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.RED
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 10f)
        }, 216L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.WHITE
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 240L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.RED
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 256L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.WHITE
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 10f)
        }, 268L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.RED
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 280L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.WHITE
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 10f)
        }, 292L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.RED
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 304L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.WHITE
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 10f)
        }, 312L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.RED
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 320L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.WHITE
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 10f)
        }, 328L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.color = DyeColor.RED
            p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_HARP, 10f, 5f)
        }, 336L)
        scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            s.remove()
            s.world.spawnParticle(Particle.EXPLOSION, s.location, 2, 2.0, 2.0, 2.0)
            p.playSound(p.location, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 10f, 1f)
        }, 338L)
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable {
            for (x in 0 until 150) {
                val sheep = p.player!!.world.spawn(s.location, Sheep::class.java)

                try {
                    sheep.color = DyeColor.values()[MathUtils.randomRangeInt(0, 15)]
                } catch (exc: Exception) {
                    exc.printStackTrace()
                }
                val r = Random()
                MathUtils.applyVelocity(sheep, Vector(r.nextDouble() - 0.5, r.nextDouble() / 2.0, r.nextDouble() - 0.5).multiply(2).add(Vector(0.0, 0.8, 0.0)))
                sheep.setBaby()
                sheep.isInvulnerable = true
                sheep.ageLock = true
                sheep.isAware = true
                sheep.noDamageTicks = 120
                sheepArrayList.add(sheep)
                p.playSound(p.location, Sound.ENTITY_SHEEP_AMBIENT, 100f, 1f)
                Bukkit.getScheduler().runTaskLater(Core.instance, Runnable {
                    p.world.spawnParticle(Particle.LAVA, sheep.location, 5)
                    p.playSound(p.location, Sound.BLOCK_NOTE_BLOCK_PLING, 10f, 10f)
                    sheep.remove()
                }, 110)
                sheepArrayList.remove(s)
                s.remove()
            }
        }, 342L)
    }
}
