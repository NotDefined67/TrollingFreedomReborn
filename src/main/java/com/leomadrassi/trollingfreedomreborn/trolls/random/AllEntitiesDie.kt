package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Entity
import org.bukkit.entity.Mob
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class AllEntitiesDie : Listener {
    companion object {
        fun EntityDie(p: Player) {
            val p2 = p.name
            for (entity in p.getNearbyEntities(10.0, 10.0, 10.0)) {
                if (entity is Mob) entity.remove()
                entity.world.spawnParticle(Particle.EXPLOSION, entity.location, 1)
                p.world.playSound(p.location, Sound.ENTITY_DONKEY_DEATH, 5.0f, 1.2f)
                if (p.getNearbyEntities(10.0, 10.0, 10.0).isEmpty()) {
                    return
                }
            }
        }
    }
}
