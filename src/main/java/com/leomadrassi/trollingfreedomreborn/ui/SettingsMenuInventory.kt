package com.leomadrassi.trollingfreedomreborn.ui

import com.leomadrassi.trollingfreedomreborn.main.Core
import com.cryptomorin.xseries.XEnchantment
import com.cryptomorin.xseries.XMaterial
import com.cryptomorin.xseries.XSound
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class SettingsMenuInventory : Listener, InventoryHolder {

    private val inv: Inventory

    init {
        Bukkit.getPluginManager().registerEvents(this, Core.instance)
        inv = Bukkit.createInventory(this, 9, centerTitle(Core.instance.getP() + Core.getPathCC("menu.settings")))
        initializeItems()
    }

    fun centerTitle(title: String): String {
        val result = StringBuilder()
        val spaces = 27 - ChatColor.stripColor(title)!!.length
        for (i in 0 until spaces) {
            result.append(" ")
        }
        return result.append(title).toString()
    }

    override fun getInventory(): Inventory = inv

    fun initializeItems() {
        val plc = ItemStack(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial()!!, 1)
        val meta2 = plc.itemMeta
        meta2?.setDisplayName(" ")
        plc.itemMeta = meta2
        for (i in 0 until inv.size) {
            inv.setItem(i, plc)
        }

        val restart = ItemStack(XMaterial.REDSTONE.parseMaterial()!!, 1)
        val meta = restart.itemMeta
        meta?.setDisplayName(Core.getPathCC("items.restart-name"))
        meta?.lore = listOf(Core.tcc(Core.getPathCC("items.restart-lore")))
        meta?.addEnchant(XEnchantment.SHARPNESS.getEnchant()!!, 1, true)
        meta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        restart.itemMeta = meta

        inv.setItem(4, restart)
    }

    fun openInventory(ent: HumanEntity) {
        ent.openInventory(inv)
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.holder !== this) return
        e.isCancelled = true

        val clickedItem = e.currentItem ?: return
        if (clickedItem.type == XMaterial.AIR.parseMaterial()!!) return

        val p = e.whoClicked as Player
        if (e.rawSlot == 4) {
            Core.instance.reloadConfig()
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, *TextComponent.fromLegacyText(Core.instance.getP() + Core.getPathCC("menu.messages.restart")))
            p.playSound(p.location, XSound.ENTITY_PLAYER_LEVELUP.parseSound()!!, 2f, 2f)
        }
    }
}
