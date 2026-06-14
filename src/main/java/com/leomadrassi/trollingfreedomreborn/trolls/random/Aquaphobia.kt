package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.Effect
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.ArrayList

class Aquaphobia : Listener {
    companion object {
        @JvmField
        val Aqua1 = ArrayList<String>()
    }

    fun Aqua(p: Player) {
        val p2 = p.player
        Aqua1.add(p2!!.name)
    }

    fun unAqua(p: Player) {
        val p2 = p.player
        Aqua1.remove(p2!!.name)
    }

    @EventHandler
    fun onPlayerMove(e: PlayerMoveEvent) {
        val m = e.player.location.block.type
        val p = e.player
        if (Aqua1.contains(p.name)) {
            if (m == Material.WATER) {
                p.gameMode = GameMode.SURVIVAL
                p.addPotionEffect(PotionEffect(PotionEffectType.POISON, 1, 5))
                p.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 1, 1))
                p.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 1, 2))
                p.world.playEffect(p.eyeLocation, Effect.MOBSPAWNER_FLAMES, 2)
            }
        }
    }
}
