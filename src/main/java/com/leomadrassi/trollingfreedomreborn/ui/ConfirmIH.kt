package com.leomadrassi.trollingfreedomreborn.ui

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.plugin.Plugin
import java.util.function.BiConsumer
import java.util.function.Consumer

class ConfirmIH : InventoryHolder, Listener {

    private var p: Player? = null
    private var backButton = false
    private var bi: BiConsumer<Player, Boolean>? = null
    private var c: Consumer<Player>? = null
    private var question: String? = null
    private var title: String? = null
    private var material: Material? = null

    constructor()

    constructor(p: Player, question: String, questio: Material, backButton: Boolean,
                true_false: BiConsumer<Player, Boolean>, backbutton: Consumer<Player>, title: String, plugin: Plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin)
        this.p = p
        this.backButton = backButton
        bi = true_false
        c = backbutton
        this.question = question
        this.title = title
        material = questio
        p.openInventory(inventory)
    }

    override fun getInventory(): Inventory {
        val inv = Bukkit.createInventory(this, 45, title!!)

        val questionItem = ItemStack(material!!)
        var m = questionItem.itemMeta
        m!!.setDisplayName(ChatColor.GOLD.toString() + question)
        questionItem.itemMeta = m

        val true_ = ItemStack(Material.GREEN_STAINED_GLASS)
        m = true_.itemMeta
        m!!.setDisplayName("${ChatColor.GREEN}Yes")
        true_.itemMeta = m

        val false_ = ItemStack(Material.RED_STAINED_GLASS)
        m = false_.itemMeta
        m!!.setDisplayName("${ChatColor.RED}No")
        false_.itemMeta = m

        val back = ItemStack(Material.BARRIER)
        m = back.itemMeta
        m!!.setDisplayName("${ChatColor.YELLOW}Go back")
        back.itemMeta = m

        inv.setItem(13, questionItem)
        inv.setItem(29, false_)
        inv.setItem(33, true_)
        if (backButton) inv.setItem(44, back)

        return inv
    }

    @EventHandler
    private fun onClick(e: InventoryClickEvent) {
        if (e.view.topInventory.holder !== this) return
        e.isCancelled = true

        if (e.clickedInventory == null || e.currentItem == null || e.currentItem!!.type == Material.AIR) return
        if (e.clickedInventory!!.holder !== this) return

        when (e.slot) {
            29 -> bi!!.accept(p!!, false)
            33 -> bi!!.accept(p!!, true)
            44 -> c!!.accept(p!!)
        }
    }

    @EventHandler
    private fun onClose(e: InventoryCloseEvent) {
        if (e.inventory.holder === this) {
            InventoryClickEvent.getHandlerList().unregister(this)
            InventoryCloseEvent.getHandlerList().unregister(this)
        }
    }
}
