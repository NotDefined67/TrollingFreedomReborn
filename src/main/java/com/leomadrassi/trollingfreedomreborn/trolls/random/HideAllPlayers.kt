package com.leomadrassi.trollingfreedomreborn.trolls.random

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import java.util.ArrayList

class HideAllPlayers : Listener {
    companion object {
        @JvmField
        val hide1 = ArrayList<String>()
    }

    fun HideAll(p: Player) {
        hide1.add(p.name)
        for (online in Bukkit.getOnlinePlayers()) {
            p.hidePlayer(Core.instance, online)
        }
    }

    fun UnHideAll(p: Player) {
        hide1.remove(p.name)
        for (online in Bukkit.getOnlinePlayers()) {
            p.showPlayer(Core.instance, online)
        }
    }
}
