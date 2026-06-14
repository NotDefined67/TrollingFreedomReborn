package com.leomadrassi.trollingfreedomreborn.trolls.classics

import com.leomadrassi.trollingfreedomreborn.main.Core
import net.citizensnpcs.api.CitizensAPI
import net.citizensnpcs.api.npc.NPC
import net.citizensnpcs.trait.SkinTrait
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockSpreadEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Herobrine : Listener {

    companion object {
        var herobrineActive = false

        fun Herobrine(p: Player) {
            herobrineActive = true

            val loc2 = p.location.add(
                p.location.direction.setY(0.0).normalize().multiply(-5.0)
            )
            val barrierLoc = p.location.clone().add(0.0, 4.0, 0.0)
            barrierLoc.block.setType(Material.BARRIER)
            p.walkSpeed = 0f
            p.flySpeed = 0f
            loc2.setX(loc2.blockX + 0.5)
            loc2.setZ(loc2.blockZ + 0.5)

            p.world.getBlockAt(loc2.clone()).setType(Material.GOLD_BLOCK)
            p.world.getBlockAt(loc2.clone().add(0.0, 1.0, 0.0)).setType(Material.NETHERRACK)
            p.world.getBlockAt(loc2.clone().add(0.0, 2.0, 0.0)).setType(Material.FIRE)

            val npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "")

            val npcLoc = Location(
                loc2.world,
                loc2.blockX + 0.5,
                loc2.blockY + 3.0,
                loc2.blockZ + 0.5
            )
            npc.getTrait(SkinTrait::class.java).setSkinName("her0brine")
            npc.data().set("nameplate-visible", false)
            Bukkit.getScheduler().runTaskLater(Core.instance, Runnable {
                npcLoc.setYaw(p.location.yaw + 180f)
                npc.spawn(npcLoc)
                npc.faceLocation(p.location)
            }, 10L)

            var trackTask = 0
            trackTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
                if (npc.isSpawned) npc.faceLocation(p.location)
            }, 1L, 1L)

            Bukkit.getScheduler().runTaskLater(Core.instance, Runnable {
                p.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 200, 0))
                p.setPlayerTime(15000L, true)
                p.playSound(p.location, Sound.ENTITY_GHAST_AMBIENT, 50f, 1f)
                for (i in 0 until 3) {
                    p.playSound(p.location, Sound.AMBIENT_CAVE, 100f, 1f)
                }
                levo(p)
            }, 10L)

            Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, Runnable {
                Bukkit.getScheduler().cancelTask(trackTask)
                npc.destroy()

                barrierLoc.block.setType(Material.AIR)
                p.walkSpeed = 0.2f
                p.flySpeed = 0.1f
                p.playSound(p.location, Sound.ENTITY_GHAST_AMBIENT, 50f, 1f)

                for (i in 0 until 4) p.world.strikeLightningEffect(loc2)
                p.world.spawnParticle(Particle.DRAGON_BREATH, loc2, 300, 0.5, 0.5, 0.5, 0.05, 1.0f)

                p.world.getBlockAt(loc2.clone()).setType(Material.AIR)
                p.world.getBlockAt(loc2.clone().add(0.0, 1.0, 0.0)).setType(Material.AIR)
                p.world.getBlockAt(loc2.clone().add(0.0, 2.0, 0.0)).setType(Material.AIR)
                p.world.getBlockAt(loc2.clone().add(0.0, 0.0, 0.0)).setType(Material.REDSTONE_TORCH)
                p.resetPlayerTime()

                Bukkit.getScheduler().runTaskLater(Core.instance, Runnable {
                    CitizensAPI.getNPCRegistry().deregister(npc)
                    herobrineActive = false
                }, 100L)

            }, 200L)
        }

        private fun levo(p: Player) {
            if (Core.instance.config.getBoolean("troll-config.herobrine-levitation")) {
                p.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 200, 0))
            }
        }
    }

    @EventHandler
    fun onFireSpread(e: BlockSpreadEvent) {
        if (!herobrineActive) return
        if (e.source.type == Material.FIRE || e.newState.type == Material.FIRE) {
            e.isCancelled = true
        }
    }
}
