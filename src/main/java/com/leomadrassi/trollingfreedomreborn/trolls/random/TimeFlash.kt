package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.ArrayList

class TimeFlash : Listener {
    companion object {
        @JvmField
        val flash1 = ArrayList<String>()
    }

    fun SkyFlash(p: Player) {
        flash1.add(p.name)
        p.setPlayerTime(0, true)
        val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            p.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 30, 100))
        }, 10L, 30L)
        Core.instance.addTask(p, "skyflash", id)
    }

    fun UnSkyFlash(p: Player) {
        val p2 = p.name
        p.removePotionEffect(PotionEffectType.BLINDNESS)
        p.resetPlayerTime()
        flash1.remove(p.name)
    }
}
