package com.leomadrassi.trollingfreedomreborn.trolls.inventory

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.scheduler.BukkitRunnable
import java.io.File
import java.io.IOException
import java.util.Random

class InventoryRave : Listener {
    companion object {
        val Rave1 = ArrayList<String>()
    }

    private var r = 255
    private var g = 0
    private var b = 0
    private var posRed = 0
    private var negRed = 0
    private var posGreen = 0
    private var negGreen = 0
    private var posBlue = 0
    private var negBlue = 0

    @Throws(IOException::class)
    fun saveInventory(p: Player) {
        val f = File(Core.instance.dataFolder.absolutePath, "${p.name}.yml")
        val c = YamlConfiguration.loadConfiguration(f)
        c.set("inventory.armor", p.inventory.armorContents)
        c.set("inventory.content", p.inventory.contents)
        c.save(f)
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(IOException::class)
    fun restoreInventory(p: Player) {
        val f = File(Core.instance.dataFolder.absolutePath, "${p.name}.yml")
        val c = YamlConfiguration.loadConfiguration(f)
        val content = (c.get("inventory.armor") as List<ItemStack>).toTypedArray()
        p.inventory.setArmorContents(content as Array<ItemStack?>)
        val content2 = (c.get("inventory.content") as List<ItemStack>).toTypedArray()
        p.inventory.setContents(content2 as Array<ItemStack?>)
    }

    @Throws(IOException::class)
    fun deleteInventoryFile(p: Player) {
        val f = File(Core.instance.dataFolder.absolutePath, "${p.name}.yml")
        f.delete()
    }

    @Throws(IOException::class)
    fun InvRave(p: Player) {
        if (!Rave1.contains(p.name)) {
            Rave1.add(p.name)
            saveInventory(p)
            Rave(p)
            Rave2(p)
        }
    }

    @Throws(IOException::class)
    fun UnInvRave(p: Player) {
        if (Rave1.contains(p.name)) {
            Rave1.remove(p.name)
            p.inventory.clear()
            p.inventory.setArmorContents(arrayOfNulls(4))
            restoreInventory(p)
            deleteInventoryFile(p)
        }
    }

    @EventHandler
    fun onDrop(event: PlayerDropItemEvent) {
        if (Rave1.contains(event.player.name))
            event.isCancelled = true
    }

    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        if (Rave1.contains(event.player.name))
            event.isCancelled = true
    }

    fun Rave(p: Player) {
        val panes = arrayOf(
            Material.WHITE_STAINED_GLASS_PANE, Material.ORANGE_STAINED_GLASS_PANE,
            Material.MAGENTA_STAINED_GLASS_PANE, Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Material.YELLOW_STAINED_GLASS_PANE, Material.LIME_STAINED_GLASS_PANE,
            Material.PINK_STAINED_GLASS_PANE, Material.GRAY_STAINED_GLASS_PANE,
            Material.LIGHT_GRAY_STAINED_GLASS_PANE, Material.CYAN_STAINED_GLASS_PANE,
            Material.PURPLE_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE,
            Material.BROWN_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE,
            Material.RED_STAINED_GLASS_PANE, Material.BLACK_STAINED_GLASS_PANE
        )

        val taskId = object : BukkitRunnable() {
            override fun run() {
                if (!p.isOnline || !Rave1.contains(p.name)) {
                    cancel()
                    return
                }
                p.inventory.clear()
                val rnd = Random()
                for (counter in 1..36) {
                    p.inventory.setItem(p.inventory.firstEmpty(), ItemStack(panes[rnd.nextInt(panes.size)]))
                }
            }
        }.runTaskTimer(Core.instance, 1L, 5L).taskId
        Core.instance.addTask(p, "inventoryrave", taskId)
    }

    fun Rave2(p: Player) {
        val blocks = arrayOf(
            Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS,
            Material.MAGENTA_STAINED_GLASS, Material.LIGHT_BLUE_STAINED_GLASS,
            Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS,
            Material.PINK_STAINED_GLASS, Material.GRAY_STAINED_GLASS
        )

        val panes = arrayOf(
            Material.WHITE_STAINED_GLASS_PANE, Material.RED_STAINED_GLASS_PANE,
            Material.BLUE_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE
        )

        p.inventory.setArmorContents(arrayOfNulls(4))
        val taskId = object : BukkitRunnable() {
            override fun run() {
                if (!p.isOnline || !Rave1.contains(p.name)) {
                    cancel()
                    return
                }
                val color = nextRGB()
                val rnd = Random()

                p.inventory.setHelmet(ItemStack(blocks[rnd.nextInt(blocks.size)]))
                p.inventory.setChestplate(getItemStack(Material.LEATHER_CHESTPLATE, color))
                p.inventory.setLeggings(getItemStack(Material.LEATHER_LEGGINGS, color))
                p.inventory.setBoots(getItemStack(Material.LEATHER_BOOTS, color))
                p.inventory.setItemInOffHand(ItemStack(panes[rnd.nextInt(panes.size)]))
            }
        }.runTaskTimer(Core.instance, 1L, 1L).taskId
        Core.instance.addTask(p, "inventoryrave", taskId)
    }

    private fun getItemStack(material: Material, color: Color): ItemStack {
        val itemStack = ItemStack(material, 1)
        val itemMeta = itemStack.itemMeta

        (itemMeta as LeatherArmorMeta).setColor(color)
        itemMeta.setDisplayName("§4R§ca§6v§ee§a!")
        itemMeta.addItemFlags(*ItemFlag.values())

        itemStack.setItemMeta(itemMeta)
        return itemStack
    }

    private fun getItemStack(material: Material): ItemStack {
        return getItemStack(material, Color.fromRGB(r, g, b))
    }

    private fun nextRGB(): Color {
        val increment = 15
        val max = 255 / increment

        if (posGreen <= max) {
            ++posGreen
            g = (posGreen - 1) * increment
        } else if (negRed <= max) {
            ++negRed
            r = 255 - increment * (negRed - 1)
        } else if (posBlue <= max) {
            ++posBlue
            b = (posBlue - 1) * increment
        } else if (negGreen <= max) {
            ++negGreen
            g = 255 - increment * (negGreen - 1)
        } else if (posRed <= max) {
            ++posRed
            r = (posRed - 1) * increment
        } else if (negBlue <= max) {
            ++negBlue
            b = 255 - increment * (negBlue - 1)
        } else {
            posRed = 0
            negRed = 0
            posGreen = 0
            negGreen = 0
            posBlue = 0
            negBlue = 0
        }

        return Color.fromRGB(r, g, b)
    }
}
