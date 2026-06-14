package com.leomadrassi.trollingfreedomreborn.trolls.chat

import com.earth2me.essentials.Essentials
import com.earth2me.essentials.User
import com.leomadrassi.trollingfreedomreborn.main.Core
import com.leomadrassi.trollingfreedomreborn.other.Mode
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class Nick : Listener {
    companion object {
        val Nick1 = mutableListOf<String>()
    }

    fun NickName(p: Player) {
        val ess = Bukkit.getServer().pluginManager.getPlugin("Essentials") as Essentials
        val user = ess.userMap.getUser(p.name)
        Nick1.add(p.name)
        val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            val randomString1 = Mode.getString(8, Mode.ALPHANUMERIC)
            user.nickname = randomString1
        }, 10L, 10L)
        Core.instance.addTask(p, "nick", id)
    }

    fun UnNick(p: Player) {
        val ess = Bukkit.getServer().pluginManager.getPlugin("Essentials") as Essentials
        val user = ess.userMap.getUser(p.name)
        if (Nick1.contains(p.name)) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "nick ${p.name} off")
            Nick1.remove(p.name)
        }
    }
}
