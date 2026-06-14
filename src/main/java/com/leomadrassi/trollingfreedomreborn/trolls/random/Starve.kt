package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.ArrayList

class Starve : Listener {
    companion object {
        @JvmField
        val starve1 = ArrayList<String>()
    }

    fun Starve(p: Player) {
        val p2 = p.name
        starve1.add(p.name)
        p.gameMode = GameMode.SURVIVAL
        p.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 200000, 50))
    }

    fun UnStarve(p: Player) {
        val p2 = p.name
        p.removePotionEffect(PotionEffectType.HUNGER)
        p.foodLevel = 20
        starve1.remove(p.name)
    }
}
