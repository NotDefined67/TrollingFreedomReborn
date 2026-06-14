package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.GameMode
import org.bukkit.entity.Creature
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.ArrayList

class Silverfish : Listener {
    companion object {
        @JvmField
        val Fish1 = ArrayList<String>()

        fun Fish(p: Player) {
            val p2 = p.name
            Fish1.add(p.name)
            p.gameMode = GameMode.SURVIVAL
            p.isFlying = false
            for (x in 0 until 50) {
                val silverfish = p.world.spawnEntity(p.location, EntityType.SILVERFISH)
                val livingSilverfish = silverfish as LivingEntity
                val creatureSilverfish = livingSilverfish as Creature
                creatureSilverfish.isInvulnerable = true
                creatureSilverfish.addPotionEffect(
                    PotionEffect(PotionEffectType.WEAKNESS, 1000000000, 5, false, false),
                    true
                )
                creatureSilverfish.target = p
            }
        }
    }

    fun UnFish(p: Player) {
        val p2 = p.name
        if (Fish1.contains(p.name)) {
            Fish1.remove(p.name)
            for (entity in p.getNearbyEntities(100.0, 100.0, 100.0)) {
                if (entity is org.bukkit.entity.Silverfish) entity.remove()
                else return
            }
        }
    }
}
