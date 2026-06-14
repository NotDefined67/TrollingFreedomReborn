package com.leomadrassi.trollingfreedomreborn.trolls.explosion

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Entity
import org.bukkit.entity.Ocelot
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class KittyCannon : Listener {

    companion object {
        val Kitty1 = ArrayList<String>()
    }

    fun KittyCannon(p: Player) {
        Kitty1.add(p.name)
                    val repeat = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            val twoBlocksAway = p.eyeLocation.add(p.eyeLocation.direction.multiply(10))
            val ocelot = p.world.spawn(twoBlocksAway, Ocelot::class.java)
            ocelot.setVelocity(twoBlocksAway.direction.multiply(-2))
            p.world.createExplosion(ocelot.location, 0f)
            p.playSound(ocelot.location, Sound.ENTITY_OCELOT_HURT, 100f, 100f)
        }, 10L, 10L)
        val delay = Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable {
            for (entity in p.getNearbyEntities(100.0, 100.0, 100.0)) {
                if (entity is Ocelot) {
                    entity.remove()
                }
            }
        }, 60L)
        Core.instance.addTask(p, "kittycannon", repeat)
        Core.instance.addTask(p, "kittycannon", delay)
    }

    fun UnKittyCannon(p: Player) {
        if (Kitty1.contains(p.name)) {
            for (entity in p.getNearbyEntities(100.0, 100.0, 100.0)) {
                if (entity is Ocelot) {
                    entity.remove()
                }
            }
            Kitty1.remove(p.name)
        }
    }
}
