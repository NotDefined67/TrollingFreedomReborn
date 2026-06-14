package com.leomadrassi.trollingfreedomreborn.trolls.packettrolls

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent
import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.HashSet
import java.util.UUID

class WorldLoading : Listener {
    companion object {
        @JvmField
        val loadingPlayers = HashSet<UUID>()

        fun WorldLoading(player: Player) {
            if (player == null || !player.isOnline) return
            if (loadingPlayers.contains(player.uniqueId)) return

            val originalLocation = player.location
            val currentWorld = player.world
            val originalMode = player.gameMode

            val targetWorld = Bukkit.getWorlds().stream()
                .filter { w -> w != currentWorld }
                .filter { w -> w.environment != World.Environment.NETHER }
                .findFirst()
                .orElse(null) ?: return

            loadingPlayers.add(player.uniqueId)
            player.gameMode = GameMode.SPECTATOR

            Bukkit.getScheduler().runTaskLater(Core.instance, Runnable {
                if (!player.isOnline) {
                    loadingPlayers.remove(player.uniqueId)
                    return@Runnable
                }

                val skyLoc = targetWorld.spawnLocation.clone().add(0.0, 500.0, 0.0)
                player.teleport(skyLoc)

                Bukkit.getScheduler().runTaskLater(Core.instance, Runnable {
                    if (player.isOnline) {
                        player.teleport(originalLocation)

                        Bukkit.getScheduler().runTaskLater(Core.instance, Runnable {
                            if (player.isOnline) {
                                player.gameMode = originalMode
                                loadingPlayers.remove(player.uniqueId)
                            }
                        }, 10L)
                    } else {
                        loadingPlayers.remove(player.uniqueId)
                    }
                }, 100L)
            }, 1L)
        }
    }

    @EventHandler
    fun onAdvancement(event: PlayerAdvancementCriterionGrantEvent) {
        if (loadingPlayers.contains(event.player.uniqueId)) {
            event.isCancelled = true
        }
    }
}
