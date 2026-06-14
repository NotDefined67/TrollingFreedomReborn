package com.leomadrassi.trollingfreedomreborn.trolls.movement

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class FreeFall : Listener {
    companion object {
        @JvmStatic
        fun FreeFall(p: Player) {
            val location = p.location
            location.setY(location.blockY + 1000.0)
            p.teleport(location)
            p.world.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 100.0f, 2.0f)
            p.world.spawnParticle(Particle.BUBBLE_POP, p.location, 100)
        }
    }
}
