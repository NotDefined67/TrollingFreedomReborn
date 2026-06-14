package com.leomadrassi.trollingfreedomreborn.ui

import com.leomadrassi.trollingfreedomreborn.commands.UnTroll
import com.leomadrassi.trollingfreedomreborn.main.Core
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedNight
import com.leomadrassi.trollingfreedomreborn.trolls.chat.ExplodeOnChat
import com.leomadrassi.trollingfreedomreborn.trolls.chat.ReverseMessage
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.TNT
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.TNTPlace
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.InventoryRave
import com.leomadrassi.trollingfreedomreborn.trolls.movement.FreeFall
import com.leomadrassi.trollingfreedomreborn.trolls.movement.InvertWalk
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Guardian
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.WorldLoading
import com.leomadrassi.trollingfreedomreborn.trolls.random.Poop
import com.leomadrassi.trollingfreedomreborn.trolls.random.RandomCrafts
import com.leomadrassi.trollingfreedomreborn.trolls.random.RingOfFire
import com.leomadrassi.trollingfreedomreborn.trolls.random.Sounds
import com.leomadrassi.trollingfreedomreborn.trolls.random.Void
import com.leomadrassi.trollingfreedomreborn.trolls.random.Vomit
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

class TrollInventory4(vic: Player) : Listener, InventoryHolder {

    companion object {
        private var main: TrollInventory4? = null

        fun getGUI(): TrollInventory4? = main

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
        inv.setItem(12, createGuiItem(XMaterial.TNT, Core.isTrollActive(VictimPlayer, "tntplace"), Core.tcc(Core.instance.pluginConfig.getString("items.tntplace-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.tntplace-lore")!!)))
        inv.setItem(14, createGuiItem(XMaterial.GREEN_WOOL, Core.isTrollActive(VictimPlayer, "vomit"), Core.tcc(Core.instance.pluginConfig.getString("items.vomit-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.vomit-lore")!!)))
        inv.setItem(16, createGuiItem(XMaterial.PAPER, Core.isTrollActive(VictimPlayer, "explodeonchat"), Core.tcc(Core.instance.pluginConfig.getString("items.explodeonchat-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.explodeonchat-lore")!!)))
        inv.setItem(19, createGuiItem(XMaterial.RED_STAINED_GLASS_PANE, Core.isTrollActive(VictimPlayer, "inventoryrave"), Core.tcc(Core.instance.pluginConfig.getString("items.invrave-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.invrave-lore")!!)))
        inv.setItem(20, createGuiItem(XMaterial.LEATHER_BOOTS, Core.isTrollActive(VictimPlayer, "invert"), Core.tcc(Core.instance.pluginConfig.getString("items.invert-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.invert-lore")!!)))
        inv.setItem(23, createGuiItem(XMaterial.BLUE_BED, Core.isTrollActive(VictimPlayer, "stopsleep"), Core.tcc(Core.instance.pluginConfig.getString("items.stopsleep-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.stopsleep-lore")!!)))
        inv.setItem(25, createGuiItem(XMaterial.PAPER, Core.isTrollActive(VictimPlayer, "reversemessage"), Core.tcc(Core.instance.pluginConfig.getString("items.reversemessage-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.reversemessage-lore")!!)))
        inv.setItem(30, createGuiItem(XMaterial.COCOA_BEANS, Core.isTrollActive(VictimPlayer, "poop"), Core.tcc(Core.instance.pluginConfig.getString("items.poop-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.poop-lore")!!)))
        inv.setItem(32, createGuiItem(XMaterial.FLINT_AND_STEEL, Core.isTrollActive(VictimPlayer, "ringoffire"), Core.tcc(Core.instance.pluginConfig.getString("items.ringoffire-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.ringoffire-lore")!!)))
        inv.setItem(10, createGuiItem(XMaterial.GHAST_SPAWN_EGG, false, Core.tcc(Core.instance.pluginConfig.getString("items.ghastsound-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.ghastsound-lore")!!)))
        inv.setItem(11, createGuiItem(XMaterial.TNT, false, Core.tcc(Core.instance.pluginConfig.getString("items.nuke-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.nuke-lore")!!)))
        inv.setItem(13, createGuiItem(XMaterial.STRUCTURE_BLOCK, false, Core.tcc(Core.instance.pluginConfig.getString("items.void-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.void-lore")!!)))
        inv.setItem(15, createGuiItem(XMaterial.DIRT, false, Core.tcc(Core.instance.pluginConfig.getString("items.worldloading-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.worldloading-lore")!!)))
        inv.setItem(21, createGuiItem(XMaterial.BLACK_BED, false, Core.tcc(Core.instance.pluginConfig.getString("items.bednight-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.bednight-lore")!!)))
        inv.setItem(22, createGuiItem(XMaterial.GREEN_BED, false, Core.tcc(Core.instance.pluginConfig.getString("items.bedmonster-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.bedmonster-lore")!!)))
        inv.setItem(24, createGuiItem(XMaterial.WHITE_WOOL, false, Core.tcc(Core.instance.pluginConfig.getString("items.freefall-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.freefall-lore")!!)))
        inv.setItem(29, createGuiItem(XMaterial.ELDER_GUARDIAN_SPAWN_EGG, false, Core.tcc(Core.instance.pluginConfig.getString("items.guardian-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.guardian-lore")!!)))
        inv.setItem(31, createGuiItem(XMaterial.CARROT_ON_A_STICK, false, Core.tcc(Core.instance.pluginConfig.getString("items.control-name")!!), Core.tcc(Core.instance.pluginConfig.getString("items.control-lore")!!)))

        val rcName = Core.instance.pluginConfig.getString("items.randomcraft-name", "&eRandom Crafting")!!
        val rcLore = Core.instance.pluginConfig.getString("items.randomcraft-lore", "&7Change recipe of your victim to something random")!!

        inv.setItem(33, createGuiItem(XMaterial.CRAFTING_TABLE, Core.isTrollActive(VictimPlayer, "randomcraft"), Core.tcc(rcName), Core.tcc(rcLore)))
        inv.setItem(40, unTroll)
        inv.setItem(36, backPage)
        inv.setItem(44, secondPage)
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

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.holder !== this) return
        e.isCancelled = true

        val clickedItem = e.currentItem ?: return
        if (clickedItem.type == XMaterial.AIR.parseMaterial()!!) return

        val p = e.whoClicked as Player
        val needsConfirm = listOf(11, 15)

        if (VictimPlayer != null) {
            if (e.slot < 45) {
                val stoptroll = UnTroll()
                if (e.isLeftClick) {
                    if (e.rawSlot < 36 && e.rawSlot != 0 && e.rawSlot !in needsConfirm) {
                        notifyTroller(p, clickedItem)
                    }
                    when (e.rawSlot) {
                        10 -> Sounds.GhastSound(VictimPlayer)
                        11 -> {
                            p.sendMessage("§b§lTFR §8| §7Really §c§lNUKE §7${VictimPlayer.name}?")
                            p.sendMessage("§b§lTFR §8| §7This will blow a lot of stuff up!")
                            ConfirmIH(
                                p, "§7Confirm §c§lNUKE §7on §l${VictimPlayer.name}", Material.TNT, true,
                                { player, bool ->
                                    if (bool) {
                                        TNT().Nuke(VictimPlayer)
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
                                "§b§lTFR §8| §7Confirm §c§lNUKE", Core.instance
                            )
                        }
                        12 -> TNTPlace().TNTPlace(VictimPlayer)
                        13 -> Void.Void(VictimPlayer)
                        14 -> Vomit().Vomit(VictimPlayer)
                        15 -> {
                            p.sendMessage("§b§lTFR §8| §7Really start §7world loading §7on ${VictimPlayer.name}?")
                            p.sendMessage("§b§lTFR §8| §7They might not be able to escape without leaving the game")
                            ConfirmIH(
                                p, "§7Confirm §7world loading §7on §l${VictimPlayer.name}", Material.DIRT, true,
                                { player, bool ->
                                    if (bool) {
                                        WorldLoading.WorldLoading(VictimPlayer)
                                        notifyTroller(player, clickedItem)
                                    } else {
                                        player.closeInventory()
                                    }
                                },
                                { player ->
                                    val sp2 = TrollInventory4(VictimPlayer.player!!)
                                    sp2.openInventory(player)
                                },
                                "§b§lTFR §8| §7Confirm §7World Loading", Core.instance
                            )
                        }
                        16 -> ExplodeOnChat().Chat(VictimPlayer)
                        19 -> InventoryRave().InvRave(VictimPlayer)
                        20 -> InvertWalk.Invert(VictimPlayer)
                        21 -> BedNight().BedNight(VictimPlayer)
                        22 -> BedNight().BedMonster(VictimPlayer)
                        23 -> BedNight().StopSleep(VictimPlayer)
                        0 -> {
                            val ps = PlayerSelectorInventory()
                            ps.openSel(p)
                        }
                        24 -> FreeFall.FreeFall(VictimPlayer)
                        25 -> ReverseMessage().Reverse(VictimPlayer)
                        29 -> Guardian().Guardian(VictimPlayer)
                        30 -> Poop().Poop(VictimPlayer)
                        31 -> {
                            val message12 = Core.instance.pluginConfig.get("cannot-troll-yourself") as String
                            val replaced12 = message12.replace("&", "§")
                            if (p == VictimPlayer) {
                                p.sendMessage(replaced12)
                            } else {
                                p.performCommand("control ${VictimPlayer.name}")
                            }
                        }
                        32 -> RingOfFire().Nuke(VictimPlayer)
                        33 -> RandomCrafts().craftTroll(VictimPlayer)
                        36 -> {
                            val sp = TrollInventory3(VictimPlayer.player!!)
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
                        10 -> stoptroll.stopSpecificTroll(VictimPlayer, "ghastsound", p)
                        12 -> stoptroll.stopSpecificTroll(VictimPlayer, "tntplace", p)
                        14 -> stoptroll.stopSpecificTroll(VictimPlayer, "vomit", p)
                        16 -> stoptroll.stopSpecificTroll(VictimPlayer, "explodeonchat", p)
                        19 -> stoptroll.stopSpecificTroll(VictimPlayer, "inventoryrave", p)
                        20 -> stoptroll.stopSpecificTroll(VictimPlayer, "invert", p)
                        23 -> stoptroll.stopSpecificTroll(VictimPlayer, "stopsleep", p)
                        0 -> {
                            val ps = PlayerSelectorInventory()
                            ps.openSel(p)
                        }
                        25 -> stoptroll.stopSpecificTroll(VictimPlayer, "reversemessage", p)
                        30 -> stoptroll.stopSpecificTroll(VictimPlayer, "poop", p)
                        31 -> stoptroll.stopSpecificTroll(VictimPlayer, "control", p)
                        32 -> stoptroll.stopSpecificTroll(VictimPlayer, "ringoffire", p)
                        33 -> stoptroll.stopSpecificTroll(VictimPlayer, "randomcraft", p)
                        36 -> {
                            val sp = TrollInventory3(VictimPlayer.player!!)
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
