package com.leomadrassi.trollingfreedomreborn.ui

import com.leomadrassi.trollingfreedomreborn.commands.UnTroll
import com.leomadrassi.trollingfreedomreborn.main.Core
import com.leomadrassi.trollingfreedomreborn.trolls.chat.NickWithoutEss
import com.leomadrassi.trollingfreedomreborn.trolls.classics.Herobrine
import com.leomadrassi.trollingfreedomreborn.trolls.classics.OP
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.ExplodingChicken
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.ExplosiveSheep
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.KittyCannon
import com.leomadrassi.trollingfreedomreborn.trolls.fakestuff.FakeKicks
import com.leomadrassi.trollingfreedomreborn.trolls.fakestuff.FakeReload
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.InventoryStop
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.Invsee
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.LockInventory
import com.leomadrassi.trollingfreedomreborn.trolls.movement.ForceJump
import com.leomadrassi.trollingfreedomreborn.trolls.movement.Freeze
import com.leomadrassi.trollingfreedomreborn.trolls.movement.Lag
import com.leomadrassi.trollingfreedomreborn.trolls.movement.Launch
import com.leomadrassi.trollingfreedomreborn.trolls.movement.Lightning
import com.leomadrassi.trollingfreedomreborn.trolls.random.HideAllPlayers
import com.leomadrassi.trollingfreedomreborn.trolls.random.InstaToolBreak
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
import org.bukkit.inventory.meta.SkullMeta

class TrollInventory2(vic: Player) : Listener, InventoryHolder {

    companion object {
        private var main: TrollInventory2? = null

        fun getGUI(): TrollInventory2? = main

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
    val mainPage = createGuiItem(XMaterial.REDSTONE_BLOCK, true, Core.getPathCC("items.Playerselector-name"), Core.getPathCC("items.Playerselector-lore"))
    val unTroll = createGuiItem(XMaterial.BARRIER, true, Core.getPathCC("items.Untroll-name"), Core.getPathCC("items.Untroll-lore"))
    val secondPage = createGuiItem(XMaterial.ARROW, true, Core.getPathCC("items.nextpage-name"), Core.getPathCC("items.nextpage-lore"))
    val backPage = createGuiItem(XMaterial.ARROW, true, Core.getPathCC("items.backpage-name"), Core.getPathCC("items.backpage-lore"))

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
        inv.setItem(14, createGuiItem(XMaterial.LINGERING_POTION, Core.isTrollActive(VictimPlayer, "forcejump"), Core.tcc(Core.instance.pluginConfig.getString("items.forcejump-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.forcejump-lore")!!)))
        inv.setItem(15, createGuiItem(XMaterial.SOUL_SAND, Core.isTrollActive(VictimPlayer, "freeze"), Core.tcc(Core.instance.pluginConfig.getString("items.freeze-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.freeze-lore")!!)))
        inv.setItem(19, createGuiItem(XMaterial.PLAYER_HEAD, Core.isTrollActive(VictimPlayer, "hideallplayers"), Core.tcc(Core.instance.pluginConfig.getString("items.hideallplayers-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.hideallplayers-lore")!!)))
        inv.setItem(20, createGuiItem(XMaterial.GOLDEN_PICKAXE, Core.isTrollActive(VictimPlayer, "instatoolbreak"), Core.tcc(Core.instance.pluginConfig.getString("items.instatoolbreak-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.instatoolbreak-lore")!!)))
        inv.setItem(21, createGuiItem(XMaterial.CHEST, Core.isTrollActive(VictimPlayer, "inventorystop"), Core.tcc(Core.instance.pluginConfig.getString("items.inventorystop-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.inventorystop-lore")!!)))
        inv.setItem(23, createGuiItem(XMaterial.CAT_SPAWN_EGG, Core.isTrollActive(VictimPlayer, "kittycannon"), Core.tcc(Core.instance.pluginConfig.getString("items.kittycannon-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.kittycannon-lore")!!)))
        inv.setItem(29, createGuiItem(XMaterial.FLINT_AND_STEEL, Core.isTrollActive(VictimPlayer, "lightning"), Core.tcc(Core.instance.pluginConfig.getString("items.lightning-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.lightning-lore")!!)))
        inv.setItem(31, createGuiItem(XMaterial.NAME_TAG, Core.isTrollActive(VictimPlayer, "nick"), Core.tcc(Core.instance.pluginConfig.getString("items.nick-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.nick-lore")!!)))
        inv.setItem(0, mainPage)
        inv.setItem(10, createGuiItem(XMaterial.CHICKEN, false, Core.tcc(Core.instance.pluginConfig.getString("items.explodingchicken-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.explodingchicken-lore")!!)))
        inv.setItem(11, createGuiItem(XMaterial.MUTTON, false, Core.tcc(Core.instance.pluginConfig.getString("items.explosivesheep-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.explosivesheep-lore")!!)))
        inv.setItem(12, createGuiItem(XMaterial.TNT_MINECART, false, Core.tcc(Core.instance.pluginConfig.getString("items.fakecrash-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.fakecrash-lore")!!)))
        inv.setItem(13, createGuiItem(XMaterial.DEBUG_STICK, false, Core.tcc(Core.instance.pluginConfig.getString("items.fakereload-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.fakereload-lore")!!)))

        val herobrineHead = ItemStack(XMaterial.PLAYER_HEAD.parseMaterial()!!, 1)
        val herobrineMeta = herobrineHead.itemMeta as SkullMeta?
        if (herobrineMeta != null) {
            herobrineMeta.setDisplayName(Core.tcc(Core.instance.pluginConfig.getString("items.herobrine-name")!!))
            val herobrineLore = mutableListOf<String>()
            val configLore = Core.instance.pluginConfig.getString("items.herobrine-lore")!!
            if (configLore != null) {
                herobrineLore.add(Core.tcc(configLore))
            }
            herobrineLore.add(" ")
            herobrineLore.add("§eLeft Click to enable")
            herobrineLore.add("§dRight Click to disable")
            herobrineLore.add(" ")
            herobrineLore.add("§eStop all troll in case of any bug")
            herobrineMeta.lore = herobrineLore
            herobrineMeta.setOwner("her0brine")
            herobrineHead.itemMeta = herobrineMeta
        }
        inv.setItem(16, herobrineHead)
        inv.setItem(22, createGuiItem(XMaterial.CHEST, false, Core.tcc(Core.instance.pluginConfig.getString("items.invsee-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.invsee-lore")!!)))
        inv.setItem(24, createGuiItem(XMaterial.COBWEB, false, Core.tcc(Core.instance.pluginConfig.getString("items.lag-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.lag-lore")!!)))
        inv.setItem(25, createGuiItem(XMaterial.WATER_BUCKET, false, Core.tcc(Core.instance.pluginConfig.getString("items.launch-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.launch-lore")!!)))
        inv.setItem(30, createGuiItem(XMaterial.CHEST, false, Core.tcc(Core.instance.pluginConfig.getString("items.lockinventory-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.lockinventory-lore")!!)))
        inv.setItem(32, createGuiItem(XMaterial.DIAMOND_BLOCK, false, Core.tcc(Core.instance.pluginConfig.getString("items.op-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.op-lore")!!)))
        inv.setItem(33, createGuiItem(XMaterial.COAL_BLOCK, false, Core.tcc(Core.instance.pluginConfig.getString("items.unop-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.unop-lore")!!)))
        inv.setItem(40, unTroll)
        inv.setItem(36, backPage)
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
        val needsConfirm = listOf(19)

        if (VictimPlayer != null) {
            if (e.slot < 45) {
                val stoptroll = UnTroll()
                if (e.isLeftClick) {
                    if (e.rawSlot < 36 && e.rawSlot != 0 && e.rawSlot !in needsConfirm) {
                        notifyTroller(p, clickedItem)
                    }
                    when (e.rawSlot) {
                        10 -> ExplodingChicken.Chicken(VictimPlayer)
                        11 -> ExplosiveSheep().Sheep(VictimPlayer)
                        12 -> FakeKicks.FakeCrash(VictimPlayer)
                        13 -> FakeReload().Reload(VictimPlayer)
                        14 -> ForceJump().Jump(VictimPlayer)
                        15 -> Freeze().Freeze(VictimPlayer)
                        16 -> {
                            if (Bukkit.getServer().pluginManager.getPlugin("Citizens") != null) {
                                Herobrine.Herobrine(VictimPlayer)
                            } else {
                                p.sendMessage("§3TFR§8: §7You need Citizens installed for this to work")
                                p.sendMessage("§3TFR§8: §7§nhttps://ci.citizensnpcs.co/job/citizens2/")
                            }
                        }
                        19 -> {
                            p.sendMessage("§b§lTFR §8| §7Really §3§lhide all players §7from ${VictimPlayer.name}?")
                            ConfirmIH(
                                p, "§7Confirm §3§lHide All Players §7On §l${VictimPlayer.name}", Material.TNT, true,
                                { player, bool ->
                                    if (bool) {
                                        HideAllPlayers().HideAll(VictimPlayer)
                                        player.closeInventory()
                                        notifyTroller(player, clickedItem)
                                    } else {
                                        player.closeInventory()
                                    }
                                },
                                { player ->
                                    val sp2 = TrollInventory4(VictimPlayer.player!!)
                                    sp2.openInventory(player)
                                },
                                "§b§lTFR §8| §7Confirm §3§lHide All", Core.instance
                            )
                        }
                        20 -> InstaToolBreak().InstaToolBreak(VictimPlayer)
                        21 -> InventoryStop().InventoryStop(VictimPlayer)
                        22 -> Invsee.Invsee(VictimPlayer)
                        23 -> KittyCannon().KittyCannon(VictimPlayer)
                        24 -> Lag.Lagg(VictimPlayer)
                        25 -> Launch.Launch(VictimPlayer)
                        29 -> Lightning().Lightning(VictimPlayer)
                        30 -> LockInventory.Lock(VictimPlayer)
                        31 -> {
                            if (Bukkit.getServer().pluginManager.getPlugin("Essentials") != null) {
                                NickWithoutEss().NickName(VictimPlayer)
                            } else {
                                p.sendMessage("§3TFR§8: §7You need Essentials installed for this to work")
                                p.sendMessage("§3TFR§8: §7§nhttps://www.spigotmc.org/resources/essentialsx.9089/")
                            }
                        }
                        32 -> OP.FakeOP(VictimPlayer)
                        33 -> OP.FakeDeOP(VictimPlayer)
                        36 -> {
                            val sp1 = TrollInventory(VictimPlayer.player!!)
                            sp1.openInventory(p)
                        }
                        40 -> {
                            stoptroll.StopTrolls(VictimPlayer, p)
                            val message2 = Core.instance.pluginConfig.get("untrolled") as String
                            val replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.player!!.name)
                            p.sendMessage(replaced2)
                        }
                        44 -> {
                            val sp = TrollInventory3(VictimPlayer.player!!)
                            sp.openInventory(p)
                        }
                        0 -> {
                            val ps = PlayerSelectorInventory()
                            ps.openSel(p)
                        }
                    }
                } else if (e.isRightClick) {
                    when (e.rawSlot) {
                        14 -> stoptroll.stopSpecificTroll(VictimPlayer, "forcejump", p)
                        15 -> stoptroll.stopSpecificTroll(VictimPlayer, "freeze", p)
                        19 -> stoptroll.stopSpecificTroll(VictimPlayer, "hideallplayers", p)
                        20 -> stoptroll.stopSpecificTroll(VictimPlayer, "instatoolbreak", p)
                        21 -> stoptroll.stopSpecificTroll(VictimPlayer, "inventorystop", p)
                        23 -> stoptroll.stopSpecificTroll(VictimPlayer, "kittycannon", p)
                        24 -> stoptroll.stopSpecificTroll(VictimPlayer, "lag", p)
                        29 -> stoptroll.stopSpecificTroll(VictimPlayer, "lightning", p)
                        30 -> stoptroll.stopSpecificTroll(VictimPlayer, "lockinventory", p)
                        31 -> stoptroll.stopSpecificTroll(VictimPlayer, "nick", p)
                        36 -> {
                            val sp1 = TrollInventory(VictimPlayer.player!!)
                            sp1.openInventory(p)
                        }
                        40 -> {
                            stoptroll.StopTrolls(VictimPlayer, p)
                            val message2 = Core.instance.pluginConfig.get("untrolled") as String
                            val replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.player!!.name)
                            p.sendMessage(replaced2)
                        }
                        44 -> {
                            val sp = TrollInventory3(VictimPlayer.player!!)
                            sp.openInventory(p)
                        }
                        0 -> {
                            val ps = PlayerSelectorInventory()
                            ps.openSel(p)
                        }
                    }
                }
            }
            initializeItems()
            p.updateInventory()
        }
    }
}
