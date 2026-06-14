package com.leomadrassi.trollingfreedomreborn.trolls.random

import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import java.util.ArrayList

class EntityMultiply : Listener {
    companion object {
        @JvmField
        val EntityMultiply1 = ArrayList<String>()
    }

    fun EntityMultiply(p: Player) {
        val p2 = p.name
        EntityMultiply1.add(p.name)
    }

    fun UnEntityMultiply(p: Player) {
        val p2 = p.name
        EntityMultiply1.remove(p.name)
    }

    @EventHandler
    fun onKill(event: EntityDeathEvent) {
        val entity = event.entity
        if (entity.killer == null) return
        val p = entity.killer
        if (EntityMultiply1.contains(p!!.name)) {
            val l = entity.location
            l.world.spawnEntity(l, entity.type)
            l.world.spawnEntity(l, entity.type)
        }
    }
}
