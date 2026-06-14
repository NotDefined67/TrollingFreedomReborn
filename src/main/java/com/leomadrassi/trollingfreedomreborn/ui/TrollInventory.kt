package com.leomadrassi.trollingfreedomreborn.ui

import com.leomadrassi.trollingfreedomreborn.commands.UnTroll
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedExplosion
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedMissing
import com.leomadrassi.trollingfreedomreborn.trolls.chat.ChatChange
import com.leomadrassi.trollingfreedomreborn.trolls.chat.Deafen
import com.leomadrassi.trollingfreedomreborn.trolls.classics.AnvilDrop
import com.leomadrassi.trollingfreedomreborn.trolls.classics.Coffin
import com.leomadrassi.trollingfreedomreborn.trolls.classics.CreeperAwMan
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.DropAll
import com.leomadrassi.trollingfreedomreborn.trolls.movement.AFK
import com.leomadrassi.trollingfreedomreborn.trolls.movement.Cage
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Credits
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Demo
import com.leomadrassi.trollingfreedomreborn.trolls.random.AllEntitiesDie
import com.leomadrassi.trollingfreedomreborn.trolls.random.Annoy
import com.leomadrassi.trollingfreedomreborn.trolls.random.Aquaphobia
import com.leomadrassi.trollingfreedomreborn.trolls.random.Break
import com.leomadrassi.trollingfreedomreborn.trolls.random.EntityMultiply
import com.leomadrassi.trollingfreedomreborn.trolls.random.Sounds
import com.leomadrassi.trollingfreedomreborn.main.Core
import com.cryptomorin.xseries.XMaterial
import com.cryptomorin.xseries.XSound
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
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

class TrollInventory(vic: Player) : Listener, InventoryHolder {

    companion object {
        private var main: TrollInventory? = null

        fun getGUI(): TrollInventory? = main

        fun getMaps(maps: String): HashMap<String, String>? {
            return when (maps) {
                "LP" -> getGUI()?.launchedPlayers as? HashMap<String, String>
                "cFP" -> getGUI()?.clearedFPlayers as? HashMap<String, String>
                else -> null
            }
        }
    }

    private val inv: Inventory
    val launchedPlayers = HashMap<String, String>()
    val clearedFPlayers = HashMap<String, String>()
    var VictimPlayer: Player = vic
    val backPage = createGuiItem(XMaterial.ARROW, true, Core.getPathCC("items.backpage-name"), Core.getPathCC("items.backpage-lore"))
    val mainPage = createGuiItem(XMaterial.REDSTONE_BLOCK, true, Core.getPathCC("items.Playerselector-name"), Core.getPathCC("items.Playerselector-lore"))
    val unTroll = createGuiItem(XMaterial.BARRIER, true, Core.getPathCC("items.Untroll-name"), Core.getPathCC("items.Untroll-lore"))
    val secondPage = createGuiItem(XMaterial.ARROW, true, Core.getPathCC("items.nextpage-name"), Core.getPathCC("items.nextpage-lore"))

    init {
        main = this
        Bukkit.getPluginManager().registerEvents(this, Core.instance)
        inv = Bukkit.createInventory(this, 45, centerTitle(Core.getPathCC("menu.menu-title") + " - " + vic.name))
        initializeItems()
    }

    fun getNOU(p: Player): String {
        return if (Core.uid()) p.name else p.uniqueId.toString()
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
        val plc = createGuiItem(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, false, " ")
        val meta = plc.itemMeta
        meta?.setDisplayName(" ")
        plc.itemMeta = meta
        for (i in 0 until 45) {
            inv.setItem(i, plc)
        }
        inv.setItem(13, createGuiItem(XMaterial.VILLAGER_SPAWN_EGG, Core.isTrollActive(VictimPlayer, "annoy"), Core.tcc(Core.instance.pluginConfig.getString("items.annoy-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.annoy-lore")!!)))
        inv.setItem(15, createGuiItem(XMaterial.WATER_BUCKET, Core.isTrollActive(VictimPlayer, "aquaphobia"), Core.tcc(Core.instance.pluginConfig.getString("items.aquaphobia-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.aquaphobia-lore")!!)))
        inv.setItem(16, createGuiItem(XMaterial.WHITE_BED, Core.isTrollActive(VictimPlayer, "bedexplosion"), Core.tcc(Core.instance.pluginConfig.getString("items.bedexplosion-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.bedexplosion-lore")!!)))
        inv.setItem(20, createGuiItem(XMaterial.WOODEN_PICKAXE, Core.isTrollActive(VictimPlayer, "stopblockbreakplace"), Core.tcc(Core.instance.pluginConfig.getString("items.break-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.break-lore")!!)))
        inv.setItem(21, createGuiItem(XMaterial.GLASS, Core.isTrollActive(VictimPlayer, "cage"), Core.tcc(Core.instance.pluginConfig.getString("items.cage-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.cage-lore")!!)))
        inv.setItem(23, createGuiItem(XMaterial.PAPER, Core.isTrollActive(VictimPlayer, "randomchat"), Core.tcc(Core.instance.pluginConfig.getString("items.chatchange-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.chatchange-lore")!!)))
        inv.setItem(29, createGuiItem(XMaterial.CARROT, Core.isTrollActive(VictimPlayer, "entitymultiply"), Core.tcc(Core.instance.pluginConfig.getString("items.entitymultiply-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.entitymultiply-lore")!!)))
        inv.setItem(30, createGuiItem(XMaterial.CREEPER_HEAD, Core.isTrollActive(VictimPlayer, "creeperawman"), Core.tcc(Core.instance.pluginConfig.getString("items.creeperawman-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.creeperawman-lore")!!)))
        inv.setItem(31, createGuiItem(XMaterial.PAPER, Core.isTrollActive(VictimPlayer, "deafen"), Core.tcc(Core.instance.pluginConfig.getString("items.deafen-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.deafen-lore")!!)))
        inv.setItem(10, createGuiItem(XMaterial.WHITE_WOOL, false, Core.tcc(Core.instance.pluginConfig.getString("items.afk-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.afk-lore")!!)))
        inv.setItem(11, createGuiItem(XMaterial.RED_WOOL, false, Core.tcc(Core.instance.pluginConfig.getString("items.unafk-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.unafk-lore")!!)))
        inv.setItem(12, createGuiItem(XMaterial.DIAMOND_SWORD, false, Core.tcc(Core.instance.pluginConfig.getString("items.entitydie-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.entitydie-lore")!!)))
        inv.setItem(14, createGuiItem(XMaterial.ANVIL, false, Core.tcc(Core.instance.pluginConfig.getString("items.anvildrop-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.anvildrop-lore")!!)))
        inv.setItem(19, createGuiItem(XMaterial.RED_BED, false, Core.tcc(Core.instance.pluginConfig.getString("items.bedmissing-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.bedmissing-lore")!!)))
        inv.setItem(22, createGuiItem(XMaterial.STONE, false, Core.tcc(Core.instance.pluginConfig.getString("items.cavesounds-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.cavesounds-lore")!!)))
        inv.setItem(24, createGuiItem(XMaterial.WITHER_SKELETON_SKULL, false, Core.tcc(Core.instance.pluginConfig.getString("items.coffin-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.coffin-lore")!!)))
        inv.setItem(25, createGuiItem(XMaterial.IRON_BARS, false, Core.tcc(Core.instance.pluginConfig.getString("items.credits-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.credits-lore")!!)))
        inv.setItem(32, createGuiItem(XMaterial.IRON_BARS, false, Core.tcc(Core.instance.pluginConfig.getString("items.demo-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.demo-lore")!!)))
        inv.setItem(33, createGuiItem(XMaterial.ICE, false, Core.tcc(Core.instance.pluginConfig.getString("items.dropall-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.dropall-lore")!!)))
        inv.setItem(0, mainPage)
        inv.setItem(36, backPage)
        inv.setItem(40, unTroll)
        inv.setItem(44, secondPage)
    }

    protected fun createGuiItem(xmat: XMaterial, isEnchanted: Boolean, name: String, vararg lore: String): ItemStack {
        var material = xmat.parseMaterial()
        if (material == null) material = org.bukkit.Material.BARRIER

        val item = ItemStack(material, 1)
        val meta = item.itemMeta

        if (meta != null) {
            meta.setDisplayName(name)

            if (isEnchanted) {
                meta.addEnchant(Enchantment.UNBREAKING, 1, true)
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            }

            val itemLore = mutableListOf<String>()
            itemLore.addAll(listOf(*lore))

            itemLore.add(" ")
            itemLore.add(if (isEnchanted) "§a§lÃƒÂ¢Ã¢â‚¬â€œÃ‚Â¶ STATUS: ACTIVE" else "§eLeft Click to enable")
            itemLore.add("§dRight Click to disable")
            itemLore.add(" ")
            itemLore.add("§8§oStop all trolls if bugs occur")

            meta.lore = itemLore
            item.itemMeta = meta
        }
        return item
    }

    fun openInventory(ent: HumanEntity) {
        ent.openInventory(inv)
    }

    private fun notifyTroller(troller: Player, clickedItem: ItemStack) {
        if (clickedItem == null || !clickedItem.hasItemMeta()) return
        val type = clickedItem.type
        if (type == Material.LIGHT_GRAY_STAINED_GLASS_PANE || type == Material.CYAN_STAINED_GLASS_PANE || type == Material.ARROW || type == Material.BARRIER || type == Material.REDSTONE_BLOCK) return

        val trollName = clickedItem.itemMeta!!.displayName
        troller.sendMessage("§b§lTFR §8| §7Sent §b$trollName §7to §f${VictimPlayer.name}")
        troller.playSound(troller.location, XSound.BLOCK_NOTE_BLOCK_CHIME.parseSound()!!, 1f, 2f)
    }

    private fun notifyUnTroller(untroller: Player, clickedItem: ItemStack) {
        if (clickedItem == null || !clickedItem.hasItemMeta()) return
        val type = clickedItem.type
        if (type == Material.LIGHT_GRAY_STAINED_GLASS_PANE || type == Material.CYAN_STAINED_GLASS_PANE || type == Material.ARROW || type == Material.BARRIER || type == Material.REDSTONE_BLOCK) return

        val trollName = clickedItem.itemMeta!!.displayName
        untroller.sendMessage("§b§lTFR §8| §7§cStopped §b$trollName §7for §f${VictimPlayer.name}")
        untroller.playSound(untroller.location, XSound.BLOCK_NOTE_BLOCK_CHIME.parseSound()!!, 1f, 2f)
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.holder !== this) return
        e.isCancelled = true

        val clickedItem = e.currentItem ?: return
        if (clickedItem.type == XMaterial.AIR.parseMaterial()!!) return

        val p = e.whoClicked as Player

        if (VictimPlayer != null) {
            if (e.slot < 45) {
                val stoptroll = UnTroll()
                if (e.isLeftClick) {
                    if (e.rawSlot < 36) {
                        notifyTroller(p, clickedItem)
                    }
                    when (e.rawSlot) {
                        10 -> AFK.FakeAFK(VictimPlayer)
                        11 -> AFK.FakeUnAFK(VictimPlayer)
                        12 -> AllEntitiesDie.EntityDie(VictimPlayer)
                        13 -> Annoy.Annoy(VictimPlayer)
                        14 -> AnvilDrop.Anvil(VictimPlayer)
                        15 -> Aquaphobia().Aqua(VictimPlayer)
                        16 -> BedExplosion().BedExplosion(VictimPlayer)
                        19 -> BedMissing().BedMissing(VictimPlayer)
                        20 -> Break().Break(VictimPlayer)
                        21 -> Cage().Cage(VictimPlayer)
                        22 -> Sounds.CaveSound(VictimPlayer)
                        23 -> ChatChange().ChatChange(VictimPlayer)
                        24 -> Coffin().CoffinStart(VictimPlayer)
                        25 -> Credits().Credits(VictimPlayer)
                        29 -> EntityMultiply().EntityMultiply(VictimPlayer)
                        30 -> CreeperAwMan.Creeper(VictimPlayer)
                        31 -> Deafen().Deafen(VictimPlayer)
                        32 -> {
                            if (Bukkit.getVersion().contains("1.18")) {
                                Demo().DemoMenu(VictimPlayer)
                            } else {
                                p.sendMessage("§3TFR§8: §7If this troll does nothing")
                                p.sendMessage("§3TFR§8: §7make sure youre on the latest Minecraft version")
                                Demo().DemoMenu(VictimPlayer)
                            }
                        }
                        33 -> DropAll.DropAll(VictimPlayer)
                        0 -> {
                            val ps = PlayerSelectorInventory()
                            ps.openSel(p)
                        }
                        40 -> {
                            stoptroll.StopTrolls(VictimPlayer, p)
                            val message2 = Core.instance.pluginConfig.get("untrolled") as String
                            val replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.player!!.name)
                            p.sendMessage(replaced2)
                        }
                        44 -> {
                            val sp = TrollInventory2(VictimPlayer.player!!)
                            sp.openInventory(p)
                        }
                        36 -> {
                            val sp2 = TrollInventory(VictimPlayer.player!!)
                            sp2.openInventory(p)
                        }
                    }
                } else if (e.isRightClick) {
                    when (e.rawSlot) {
                        13 -> stoptroll.stopSpecificTroll(VictimPlayer, "annoy", p)
                        14 -> stoptroll.stopSpecificTroll(VictimPlayer, "anvildrop", p)
                        15 -> stoptroll.stopSpecificTroll(VictimPlayer, "aquaphobia", p)
                        16 -> stoptroll.stopSpecificTroll(VictimPlayer, "bedexplosion", p)
                        20 -> stoptroll.stopSpecificTroll(VictimPlayer, "stopblockbreakplace", p)
                        21 -> stoptroll.stopSpecificTroll(VictimPlayer, "cage", p)
                        22 -> stoptroll.stopSpecificTroll(VictimPlayer, "cavesounds", p)
                        23 -> stoptroll.stopSpecificTroll(VictimPlayer, "randomchat", p)
                        24 -> stoptroll.stopSpecificTroll(VictimPlayer, "coffindance", p)
                        31 -> stoptroll.stopSpecificTroll(VictimPlayer, "deafen", p)
                        29 -> stoptroll.stopSpecificTroll(VictimPlayer, "entitymultiply", p)
                        0 -> {
                            val ps = PlayerSelectorInventory()
                            ps.openSel(p)
                        }
                        40 -> {
                            stoptroll.StopTrolls(VictimPlayer, p)
                            val message2 = Core.instance.pluginConfig.get("untrolled") as String
                            val replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.player!!.name)
                            p.sendMessage(replaced2)
                        }
                        44 -> {
                            val sp = TrollInventory2(VictimPlayer.player!!)
                            sp.openInventory(p)
                        }
                        36 -> {
                            val sp2 = TrollInventory(VictimPlayer.player!!)
                            sp2.openInventory(p)
                        }
                    }
                }
            }
            initializeItems()
            p.updateInventory()
        }
    }
}
