package com.leomadrassi.trollingfreedomreborn.trolls.explosion

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.Creature
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

class ExplodingChicken : Listener {

    companion object {
        fun Chicken(p: Player) {
            val enderman = p.world.spawnEntity(p.eyeLocation.add(3.0, -1.0, 0.0), EntityType.CHICKEN)
            val livingenderman = enderman as LivingEntity
            val creatureenderman = livingenderman as Creature
            creatureenderman.isInvulnerable = true
            creatureenderman.setAI(false)
            val loc = creatureenderman.location
            val fw = loc.world.spawnEntity(loc, EntityType.FIREWORK_ROCKET) as Firework
            val fwm = fw.fireworkMeta

            fwm.power = 5
            fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(false).build())

            fw.fireworkMeta = fwm
            fw.detonate()
            for (x in 0 until 50) {
                val fw2 = loc.world.spawnEntity(loc, EntityType.FIREWORK_ROCKET) as Firework
                fw2.fireworkMeta = fwm
                p.world.spawnParticle(Particle.SMOKE, loc, 5, 0.0, 0.0, 0.0)
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable {
                p.world.createExplosion(loc, 2.0f)
                val chicken = ItemStack(Material.CHICKEN)
                p.world.dropItemNaturally(loc, chicken)
                creatureenderman.health = 0.0
            }, 90L)
        }
    }
}
