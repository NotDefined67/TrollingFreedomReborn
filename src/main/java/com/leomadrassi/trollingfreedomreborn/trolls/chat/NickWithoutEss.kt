package com.leomadrassi.trollingfreedomreborn.trolls.chat

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class NickWithoutEss : Listener {
    fun NickName(p: Player) {
        if (Bukkit.getServer().pluginManager.getPlugin("Essentials") != null) {
            val eighteen = Nick()
            eighteen.NickName(p)
        }
    }

    fun UnNick(p: Player) {
        if (Bukkit.getServer().pluginManager.getPlugin("Essentials") != null) {
            val eighteen = Nick()
            eighteen.UnNick(p)
        }
    }
}
