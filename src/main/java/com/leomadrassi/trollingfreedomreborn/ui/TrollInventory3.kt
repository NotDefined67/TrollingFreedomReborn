package com.leomadrassi.trollingfreedomreborn.ui

import com.leomadrassi.trollingfreedomreborn.commands.UnTroll
import com.leomadrassi.trollingfreedomreborn.main.Core
import com.leomadrassi.trollingfreedomreborn.trolls.classics.Pumpkin
import com.leomadrassi.trollingfreedomreborn.trolls.classics.RickRoll
import com.leomadrassi.trollingfreedomreborn.trolls.classics.Slenderman
import com.leomadrassi.trollingfreedomreborn.trolls.classics.Spin
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.Snowman
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.TNT
import com.leomadrassi.trollingfreedomreborn.trolls.fakestuff.FakeKicks
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.RandomInv
import com.leomadrassi.trollingfreedomreborn.trolls.movement.SneakDestroy
import com.leomadrassi.trollingfreedomreborn.trolls.random.Burn
import com.leomadrassi.trollingfreedomreborn.trolls.random.Potato
import com.leomadrassi.trollingfreedomreborn.trolls.random.RainItems
import com.leomadrassi.trollingfreedomreborn.trolls.random.RandomParticle
import com.leomadrassi.trollingfreedomreborn.trolls.random.RandomTP
import com.leomadrassi.trollingfreedomreborn.trolls.random.Silverfish
import com.leomadrassi.trollingfreedomreborn.trolls.random.SlipperyHands
import com.leomadrassi.trollingfreedomreborn.trolls.random.Starve
import com.leomadrassi.trollingfreedomreborn.trolls.random.TimeFlash
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

class TrollInventory3(vic: Player) : Listener, InventoryHolder {

    companion object {
        private var main: TrollInventory3? = null

        fun getGUI(): TrollInventory3? = main

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

        inv.setItem(0, mainPage)
        inv.setItem(12, createGuiItem(XMaterial.FLINT_AND_STEEL, Core.isTrollActive(VictimPlayer, "burn"), Core.tcc(Core.instance.pluginConfig.getString("items.burn-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.burn-lore")!!)))
        inv.setItem(13, createGuiItem(XMaterial.POTATO, Core.isTrollActive(VictimPlayer, "potato"), Core.tcc(Core.instance.pluginConfig.getString("items.potato-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.potato-lore")!!)))
        inv.setItem(14, createGuiItem(XMaterial.CARVED_PUMPKIN, Core.isTrollActive(VictimPlayer, "pumpkin"), Core.tcc(Core.instance.pluginConfig.getString("items.pumpkin-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.pumpkin-lore")!!)))
        inv.setItem(15, createGuiItem(XMaterial.DIRT, Core.isTrollActive(VictimPlayer, "rainitems"), Core.tcc(Core.instance.pluginConfig.getString("items.rainitems-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.rainitems-lore")!!)))
        inv.setItem(16, createGuiItem(XMaterial.CHEST, Core.isTrollActive(VictimPlayer, "randominv"), Core.tcc(Core.instance.pluginConfig.getString("items.randominv-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.randominv-lore")!!)))
        inv.setItem(19, createGuiItem(XMaterial.REDSTONE, Core.isTrollActive(VictimPlayer, "randomparticle"), Core.tcc(Core.instance.pluginConfig.getString("items.randomparticle-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.randomparticle-lore")!!)))
        inv.setItem(21, createGuiItem(XMaterial.ENCHANTED_GOLDEN_APPLE, Core.isTrollActive(VictimPlayer, "rickroll"), Core.tcc(Core.instance.pluginConfig.getString("items.rickroll-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.rickroll-lore")!!)))
        inv.setItem(22, createGuiItem(XMaterial.SILVERFISH_SPAWN_EGG, Core.isTrollActive(VictimPlayer, "silverfish"), Core.tcc(Core.instance.pluginConfig.getString("items.silverfish-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.silverfish-lore")!!)))
        inv.setItem(23, createGuiItem(XMaterial.ENDERMAN_SPAWN_EGG, Core.isTrollActive(VictimPlayer, "slenderman"), Core.tcc(Core.instance.pluginConfig.getString("items.slenderman-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.slenderman-lore")!!)))
        inv.setItem(25, createGuiItem(XMaterial.DIAMOND_PICKAXE, Core.isTrollActive(VictimPlayer, "sneakdestroy"), Core.tcc(Core.instance.pluginConfig.getString("items.sneakdestroy-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.sneakdestroy-lore")!!)))
        inv.setItem(30, createGuiItem(XMaterial.POTION, Core.isTrollActive(VictimPlayer, "spin"), Core.tcc(Core.instance.pluginConfig.getString("items.spin-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.spin-lore")!!)))
        inv.setItem(31, createGuiItem(XMaterial.POISONOUS_POTATO, Core.isTrollActive(VictimPlayer, "starve"), Core.tcc(Core.instance.pluginConfig.getString("items.starve-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.starve-lore")!!)))
        inv.setItem(32, createGuiItem(XMaterial.CLOCK, Core.isTrollActive(VictimPlayer, "skyflash"), Core.tcc(Core.instance.pluginConfig.getString("items.timeflash-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.timeflash-lore")!!)))

        inv.setItem(10, createGuiItem(XMaterial.OAK_DOOR, false, Core.tcc(Core.instance.pluginConfig.getString("items.fakeclose-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.fakeclose-lore")!!)))
        inv.setItem(11, createGuiItem(XMaterial.IRON_DOOR, false, Core.tcc(Core.instance.pluginConfig.getString("items.fakeban-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.fakeban-lore")!!)))
        inv.setItem(20, createGuiItem(XMaterial.ENDER_PEARL, false, Core.tcc(Core.instance.pluginConfig.getString("items.randomtp-name")!! + "§o§1 WARNING: MIGHT CAUSE THE SERVER TO LAG!"), Core.tcc(Core.instance.pluginConfig.getString("items.randomtp-lore")!!)))
        inv.setItem(24, createGuiItem(XMaterial.PACKED_ICE, false, Core.tcc(Core.instance.pluginConfig.getString("items.slipperyhands-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.slipperyhands-lore")!!)))
        inv.setItem(29, createGuiItem(XMaterial.PUMPKIN, false, Core.tcc(Core.instance.pluginConfig.getString("items.snowman-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.snowman-lore")!!)))
        inv.setItem(33, createGuiItem(XMaterial.TNT, false, Core.tcc(Core.instance.pluginConfig.getString("items.tnt-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.tnt-lore")!!)))

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

        if (VictimPlayer != null) {
            if (e.slot < 45) {
                val stoptroll = UnTroll()
                if (e.isLeftClick) {
                    if (e.rawSlot < 36) {
                        notifyTroller(p, clickedItem)
                    }
                    when (e.rawSlot) {
                        10 -> FakeKicks.FakeClosed(VictimPlayer)
                        11 -> FakeKicks.FakeBan(VictimPlayer)
                        12 -> Burn.Burn(VictimPlayer)
                        13 -> Potato().potato(VictimPlayer)
                        14 -> Pumpkin().Pumpkin(VictimPlayer)
                        15 -> RainItems().RainItem(VictimPlayer)
                        16 -> RandomInv().RandomInv(VictimPlayer)
                        19 -> RandomParticle().RandomParticle(VictimPlayer)
                        20 -> RandomTP.RandomTP(VictimPlayer)
                        21 -> RickRoll().RickRoll(VictimPlayer)
                        22 -> Silverfish.Fish(VictimPlayer)
                        23 -> Slenderman().Enderman(VictimPlayer)
                        24 -> SlipperyHands.SlipperyHands(VictimPlayer)
                        0 -> {
                            val ps = PlayerSelectorInventory()
                            ps.openSel(p)
                        }
                        25 -> SneakDestroy().SneakDestroy(VictimPlayer)
                        29 -> Snowman().Snowman(VictimPlayer)
                        30 -> Spin.Spin(VictimPlayer)
                        31 -> Starve().Starve(VictimPlayer)
                        32 -> TimeFlash().SkyFlash(VictimPlayer)
                        33 -> TNT.FakeNuke(VictimPlayer)
                        36 -> {
                            val sp = TrollInventory2(VictimPlayer.player!!)
                            sp.openInventory(p)
                        }
                        40 -> {
                            stoptroll.StopTrolls(VictimPlayer, p)
                            val message2 = Core.instance.pluginConfig.get("untrolled") as String
                            val replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.player!!.name)
                            p.sendMessage(replaced2)
                        }
                        44 -> {
                            val sp2 = TrollInventory4(VictimPlayer.player!!)
                            sp2.openInventory(p)
                        }
                    }
                } else if (e.isRightClick) {
                    when (e.rawSlot) {
                        12 -> stoptroll.stopSpecificTroll(VictimPlayer, "burn", p)
                        13 -> stoptroll.stopSpecificTroll(VictimPlayer, "potato", p)
                        14 -> stoptroll.stopSpecificTroll(VictimPlayer, "pumpkin", p)
                        15 -> stoptroll.stopSpecificTroll(VictimPlayer, "rainitems", p)
                        16 -> stoptroll.stopSpecificTroll(VictimPlayer, "randominv", p)
                        19 -> stoptroll.stopSpecificTroll(VictimPlayer, "randomparticle", p)
                        21 -> stoptroll.stopSpecificTroll(VictimPlayer, "rickroll", p)
                        22 -> stoptroll.stopSpecificTroll(VictimPlayer, "silverfish", p)
                        23 -> stoptroll.stopSpecificTroll(VictimPlayer, "slenderman", p)
                        24 -> stoptroll.stopSpecificTroll(VictimPlayer, "slipperyhands", p)
                        0 -> {
                            val ps = PlayerSelectorInventory()
                            ps.openSel(p)
                        }
                        25 -> stoptroll.stopSpecificTroll(VictimPlayer, "sneakdestroy", p)
                        30 -> stoptroll.stopSpecificTroll(VictimPlayer, "spin", p)
                        31 -> stoptroll.stopSpecificTroll(VictimPlayer, "starve", p)
                        32 -> stoptroll.stopSpecificTroll(VictimPlayer, "skyflash", p)
                        36 -> {
                            val sp = TrollInventory2(VictimPlayer.player!!)
                            sp.openInventory(p)
                        }
                        40 -> {
                            stoptroll.StopTrolls(VictimPlayer, p)
                            val message2 = Core.instance.pluginConfig.get("untrolled") as String
                            val replaced2 = message2.replace("&", "§").replace("%player%", VictimPlayer.player!!.name)
                            p.sendMessage(replaced2)
                        }
                        44 -> {
                            val sp2 = TrollInventory4(VictimPlayer.player!!)
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
