package com.leomadrassi.trollingfreedomreborn.trolls.classics

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Slenderman : Listener {

    companion object {
        val Slender1 = mutableListOf<String>()
    }

    fun Enderman(p: Player) {
        Slender1.add(p.name)
        p.setGameMode(GameMode.SURVIVAL)
        val enderman = p.world.spawnEntity(p.location, EntityType.ENDERMAN)
        val livingenderman = enderman as LivingEntity
        val creatureenderman = livingenderman as Creature
        creatureenderman.setInvulnerable(true)
        creatureenderman.setTarget(p)
        val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            p.playSound(p.location, Sound.ENTITY_ENDERMAN_AMBIENT, 50f, 1f)
            p.playSound(p.location, Sound.ENTITY_ENDERMAN_SCREAM, 100f, 1f)
            p.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 200, 1))
            p.addPotionEffect(PotionEffect(PotionEffectType.SLOWNESS, 200, 2))
        }, 10L, 5L)
        Core.instance.addTask(p, "slenderman", id)
    }

    fun UnEnderman(p: Player) {
        val p2 = p.getPlayer()!!
        if (Slender1.contains(p.name)) {
            Slender1.remove(p2.name)
            p.removePotionEffect(PotionEffectType.BLINDNESS)
            p.removePotionEffect(PotionEffectType.SLOWNESS)
            for (entity in p.getNearbyEntities(100.0, 100.0, 100.0)) {
                if (entity is Enderman) {
                    entity.remove()
                }
            }
        }
    }

    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        val p = e.entity
        if (Slender1.contains(p.name)) {
            UnEnderman(p)
        }
    }
}
