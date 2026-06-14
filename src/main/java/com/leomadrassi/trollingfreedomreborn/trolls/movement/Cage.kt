package com.leomadrassi.trollingfreedomreborn.trolls.movement

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class Cage : Listener {
    companion object {
        val Cage1: MutableList<String> = ArrayList()
    }

    fun Cage(p: Player) {
        Cage1.add(p.name)
        val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            if (!Cage1.contains(p.name)) {
                return@Runnable
            }
            buildIronCageAround(p, 3, 2, true)
        }, 5L, 5L)
        Core.instance.addTask(p, "cage", id)
    }

    fun UnCage(p: Player) {
        Cage1.remove(p.name)
    }

    @EventHandler
    fun onPlayerMove(e: PlayerMoveEvent) {
        val p = e.player
        if (Cage1.contains(p.name)) {
            if (!e.from.toVector().equals(e.to.toVector()))
                e.isCancelled = true
        }
    }

    fun buildIronCageAround(ent: Entity, sideLength: Int, height: Int, wantRoof: Boolean) {
        val fence = Material.GLASS
        val roof = Material.GLASS
        val entLoc = ent.location
        if (sideLength < 3 || sideLength % 2 == 0) {
            throw IllegalArgumentException("You must enter an odd number greater than 3 for the side length")
        } else if (height == 0) {
            throw IllegalArgumentException("Height must be greater than 0.")
        }
        val delta = sideLength / 2
        val corner1 = Location(entLoc.world, (entLoc.blockX + delta).toDouble(), (entLoc.blockY + 1).toDouble(), (entLoc.blockZ - delta).toDouble())
        val corner2 = Location(entLoc.world, (entLoc.blockX - delta).toDouble(), (entLoc.blockY + 1).toDouble(), (entLoc.blockZ + delta).toDouble())
        val minX = Math.min(corner1.blockX, corner2.blockX)
        val maxX = Math.max(corner1.blockX, corner2.blockX)
        val minZ = Math.min(corner1.blockZ, corner2.blockZ)
        val maxZ = Math.max(corner1.blockZ, corner2.blockZ)
        for (x in minX..maxX) {
            for (y in 0 until height) {
                for (z in minZ..maxZ) {
                    if (x == minX || x == maxX || z == minZ || z == maxZ) {
                        val b = corner1.world.getBlockAt(x, entLoc.blockY + y, z)
                        b.setType(fence)
                    }
                    if (y == height - 1 && wantRoof) {
                        val b = corner1.world.getBlockAt(x, entLoc.blockY + y + 1, z)
                        b.setType(roof)
                    }
                }
            }
        }
    }
}
