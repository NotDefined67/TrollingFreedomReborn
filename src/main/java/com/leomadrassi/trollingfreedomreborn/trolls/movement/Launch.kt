package com.leomadrassi.trollingfreedomreborn.trolls.movement

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.util.Vector

class Launch : Listener {
    companion object {
        @JvmStatic
        fun Launch(p: Player) {
            if (p == null) return
            val velocity = Vector(0.0, 5.0, 0.0)
            p.setVelocity(velocity)
            p.world.playSound(p.location, Sound.ENTITY_PUFFER_FISH_BLOW_UP, 5.0f, 2.0f)
            p.world.spawnParticle(Particle.CLOUD, p.location, 100, 0.5, 0.5, 0.5, 0.1)
        }
    }
}
