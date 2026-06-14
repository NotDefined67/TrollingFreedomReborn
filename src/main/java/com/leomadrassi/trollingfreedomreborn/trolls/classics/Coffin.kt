package com.leomadrassi.trollingfreedomreborn.trolls.classics

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.util.EulerAngle

class Coffin : Listener {

    var procedure = 0

    fun chk(a: Array<ArmorStand?>): Boolean {
        for (i in 0 until 6) {
            if (a[i]!!.isDead) {
                for (entity in a) {
                    if (entity is ArmorStand) {
                        entity.remove()
                    }
                }
                return true
            }
        }
        return false
    }

    fun p1(a: Array<ArmorStand?>) {
        for (i in 0 until 3) {
            a[i]!!.setHeadPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setLeftLegPose(EulerAngle(Math.toRadians(30.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setRightLegPose(EulerAngle(Math.toRadians(330.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setLeftArmPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(330.0)))
            a[i]!!.setRightArmPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(0.0)))
        }
        for (i in 3 until 6) {
            a[i]!!.setHeadPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setLeftLegPose(EulerAngle(Math.toRadians(30.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setRightLegPose(EulerAngle(Math.toRadians(330.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setLeftArmPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setRightArmPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(30.0)))
        }
    }

    fun p2(a: Array<ArmorStand?>) {
        for (i in 0 until 3) {
            a[i]!!.setHeadPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(30.0)))
            a[i]!!.setLeftLegPose(EulerAngle(Math.toRadians(330.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setRightLegPose(EulerAngle(Math.toRadians(30.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setLeftArmPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setRightArmPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(0.0)))
        }
        for (i in 3 until 6) {
            a[i]!!.setHeadPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(330.0)))
            a[i]!!.setLeftLegPose(EulerAngle(Math.toRadians(330.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setRightLegPose(EulerAngle(Math.toRadians(30.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setLeftArmPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(0.0)))
            a[i]!!.setRightArmPose(EulerAngle(Math.toRadians(0.0), Math.toRadians(0.0), Math.toRadians(0.0)))
        }
    }

    fun CoffinStart(p: Player): Boolean {
        val p2 = p.name

        val boots = ItemStack(Material.LEATHER_BOOTS)
        val meta = boots.itemMeta as LeatherArmorMeta
        meta.setColor(Color.BLACK)
        boots.itemMeta = meta

        val leggings = ItemStack(Material.LEATHER_LEGGINGS)
        val meta3 = leggings.itemMeta as LeatherArmorMeta
        meta3.setColor(Color.BLACK)
        leggings.itemMeta = meta3

        val chestplate = ItemStack(Material.LEATHER_CHESTPLATE)
        val meta4 = chestplate.itemMeta as LeatherArmorMeta
        meta4.setColor(Color.BLACK)
        chestplate.itemMeta = meta4

        val head = ItemStack(Material.PLAYER_HEAD)
        val metaref16 = head.itemMeta
        val lore16 = ArrayList<String>()

        val meta1 = head.itemMeta as SkullMeta
        meta1.setOwningPlayer(Bukkit.getOfflinePlayer("Pallbearers"))
        head.itemMeta = meta1

        val a = arrayOfNulls<ArmorStand>(6)
        val loc = p.location.clone()
        loc.setX(Math.round(loc.x) + 0.5)
        loc.setY(Math.floor(loc.y))
        loc.setZ(Math.round(loc.z) + 0.5)

        val cb = arrayOf<Block>(
            loc.add(3.0, 2.0, 0.0).block,
            loc.add(1.0, 0.0, 0.0).block,
            loc.add(1.0, 0.0, 0.0).block,
            loc.add(0.0, 0.0, 1.0).block,
            loc.add(-1.0, 0.0, 0.0).block,
            loc.add(-1.0, 0.0, 0.0).block
        )

        a[0] = p.world.spawnEntity(loc.add(0.0, -2.0, 0.0), EntityType.ARMOR_STAND) as ArmorStand
        a[1] = p.world.spawnEntity(loc.add(1.0, 0.0, 0.0), EntityType.ARMOR_STAND) as ArmorStand
        a[2] = p.world.spawnEntity(loc.add(1.0, 0.0, 0.0), EntityType.ARMOR_STAND) as ArmorStand
        a[3] = p.world.spawnEntity(loc.add(0.0, 0.0, -1.0), EntityType.ARMOR_STAND) as ArmorStand
        a[4] = p.world.spawnEntity(loc.add(-1.0, 0.0, 0.0), EntityType.ARMOR_STAND) as ArmorStand
        a[5] = p.world.spawnEntity(loc.add(-1.0, 0.0, 0.0), EntityType.ARMOR_STAND) as ArmorStand

        for (i in 0 until 6) {
            a[i]!!.setBasePlate(false)
            a[i]!!.setArms(true)
            a[i]!!.setBoots(boots)
            a[i]!!.setLeggings(leggings)
            a[i]!!.setChestplate(chestplate)
            a[i]!!.setHelmet(head)
            a[i]!!.setItemInHand(ItemStack(Material.PAPER))
            a[i]!!.setRotation(90.0f, 0.0f)
        }

        for (i in 0 until 6) {
            if (cb[i].type == Material.AIR) {
                cb[i].setType(Material.DARK_OAK_SLAB)
            }
        }

        val id = Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(Core.instance, Runnable {
            chk(a)

            if (procedure % 4 == 1 || procedure % 4 == 2) {
                p1(a)
            } else {
                p2(a)
            }

            when (procedure % 32) {
                0 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.587f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                2 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0f, 1.0f)
                }
                3 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.498f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                4 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.334f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.587f)
                }
                5 -> p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                6 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.189f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0f, 1.0f)
                }
                8 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.122f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.782f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                10 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.122f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0f, 1.0f)
                }
                11 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.189f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                12 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.334f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.782f)
                }
                13 -> p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                14 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.189f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0f, 1.0f)
                }
                15 -> p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.122f)
                16 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                18 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0f, 1.0f)
                }
                19 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.189f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                20 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.122f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f)
                }
                21 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.189f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                22 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.122f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0f, 1.0f)
                }
                23 -> p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.189f)
                24 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                26 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0f, 1.0f)
                }
                27 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.189f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                28 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.122f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f)
                }
                29 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.189f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.0f, 1.0f)
                }
                30 -> {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.122f)
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0f, 1.0f)
                }
                31 -> p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.189f)
            }
            procedure++
        }, 5L, 5L)
        Core.instance.addTask(p, "coffindance", id)

        return true
    }
}
