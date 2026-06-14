package com.leomadrassi.trollingfreedomreborn.ui

import com.leomadrassi.trollingfreedomreborn.main.Core
import com.cryptomorin.xseries.XEnchantment
import com.cryptomorin.xseries.XMaterial
import com.cryptomorin.xseries.XSound
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
import org.bukkit.inventory.meta.SkullMeta

class PlayerSelectorInventory : InventoryHolder, Listener {

    companion object {
        private var main: PlayerSelectorInventory? = null

        fun getPS(): PlayerSelectorInventory? = main
    }

    private val inv: Inventory
    private val inv2: Inventory
    val nextPage = createGuiItem(XMaterial.REDSTONE_BLOCK, true, Core.getPathCC("items.nextpage-name"), Core.getPathCC("items.nextpage-lore"))
    val mainPage = createGuiItem(XMaterial.REDSTONE_BLOCK, true, Core.getPathCC("items.mainpage-name"), Core.getPathCC("items.mainpage-lore"))
    val untrollall = createGuiItem(XMaterial.BEACON, false, Core.tcc(Core.instance.pluginConfig.getString("items.Untrollall-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.Untrollall-lore")!!))
    var playersdone = 0

    init {
        Bukkit.getPluginManager().registerEvents(this, Core.instance)
        inv = Bukkit.createInventory(this, 54, centerTitle(Core.instance.getP() + Core.tcc(Core.instance.pluginConfig.getString("menu.select-player")!!)))
        inv2 = Bukkit.createInventory(this, 54, centerTitle(Core.instance.getP() + Core.tcc(Core.instance.pluginConfig.getString("menu.select-player")!!)))
        main = this
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

    protected fun createGuiItem(xmat: XMaterial, isEnchanted: Boolean, name: String, vararg lore: String): ItemStack {
        var material = xmat.parseMaterial()
        if (material == null) {
            Bukkit.getLogger().warning("[TFR] Material not found for: ${xmat.name}. Using BARRIER.")
            material = org.bukkit.Material.BARRIER
        }

        val item = ItemStack(material, 1)
        val meta = item.itemMeta

        if (meta != null) {
            meta.setDisplayName(name)

            if (isEnchanted) {
                var dur = org.bukkit.enchantments.Enchantment.UNBREAKING
                if (XEnchantment.UNBREAKING.enchant != null) {
                    dur = XEnchantment.UNBREAKING.enchant
                }
                meta.addEnchant(dur, 1, true)
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            }

            if (lore.isNotEmpty()) {
                meta.lore = listOf(*lore)
            }

            item.itemMeta = meta
        }

        return item
    }

    fun initializeItems() {
        Bukkit.getOnlinePlayers().forEach { o ->
            val item = ItemStack(XMaterial.PLAYER_HEAD.parseMaterial()!!, 1)
            val skullMeta = item.itemMeta as SkullMeta
            skullMeta.setOwner(o.name)
            skullMeta.setDisplayName(o.name)
            val lore = mutableListOf<String>()

            if (o.isOp) {
                lore.add(Core.getPathCC("items.messages.isOP"))
                if (!Core.canTroll(o)) {
                    lore.add(" ")
                    lore.add("§c§lPROTECTED")
                    lore.add("§7This player is blocked.")
                } else {
                    lore.add(" ")
                    lore.add("§a§lVULNERABLE")
                    lore.add("§7Click to open troll menu.")
                }
            } else {
                if (!Core.canTroll(o)) {
                    lore.add("§c§lPROTECTED")
                    lore.add("§7This player is blocked.")
                } else {
                    lore.add("§a§lVULNERABLE")
                    lore.add("§7Click to open troll menu.")
                }
            }

            skullMeta.lore = lore
            item.itemMeta = skullMeta

            if (playersdone > inv.size - 7) {
                inv2.addItem(item)
                inv2.setItem(49, untrollall)
            } else {
                inv.addItem(item)
                inv.setItem(49, untrollall)
                playersdone++
            }
        }

        if (Bukkit.getOnlinePlayers().size > inv.size - 7) {
            inv.setItem(53, nextPage)
        }
        if (Bukkit.getOnlinePlayers().size > inv2.size - 7) {
            inv2.setItem(53, mainPage)
        }
    }

    fun openUniInv(ent: HumanEntity, inv: Inventory) {
        ent.openInventory(inv)
    }

    fun openSel(ent: HumanEntity) {
        ent.openInventory(inv)
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.holder !== this) return
        e.isCancelled = true

        val clickedItem = e.currentItem ?: return
        if (clickedItem.type == XMaterial.AIR.parseMaterial()!!) return

        val p = e.whoClicked as Player

        if (clickedItem == untrollall) {
            p.performCommand("untroll all")
        }

        when {
            clickedItem == nextPage -> {
                p.closeInventory()
                openUniInv(p, inv2)
            }
            clickedItem == mainPage -> {
                p.closeInventory()
                openUniInv(p, inv)
            }
        }

        val Vic = Bukkit.getPlayerExact(clickedItem.itemMeta?.displayName ?: return)

        if (!Core.canTroll(Vic!!)) {
            p.sendMessage("§cThat player is blocked from trolling.")
            p.closeInventory()
            return
        }

        if (Vic != null) {
            p.sendMessage("§b§lTFR §8| §7Picked §b${Vic.name} §7to troll.")
            p.playSound(p.location, XSound.BLOCK_NOTE_BLOCK_CHIME.parseSound()!!, 1f, 2f)
            val gt = TrollInventory(Vic)
            gt.openInventory(p)
        }
    }
}
