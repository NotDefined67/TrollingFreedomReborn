package com.leomadrassi.trollingfreedomreborn.trolls.Beds

import org.bukkit.entity.Player
import org.bukkit.event.Listener

class BedMissing : Listener {
    fun BedMissing(player: Player) {
        player.sendMessage("You have no home bed or charged respawn anchor, or it was obstructed")
    }
}
