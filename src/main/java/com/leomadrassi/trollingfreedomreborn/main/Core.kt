package com.leomadrassi.trollingfreedomreborn.main

import com.leomadrassi.trollingfreedomreborn.commands.Help
import com.leomadrassi.trollingfreedomreborn.commands.Panic
import com.leomadrassi.trollingfreedomreborn.commands.TrollWithCMD
import com.leomadrassi.trollingfreedomreborn.commands.UnTroll
import com.leomadrassi.trollingfreedomreborn.other.*
import com.leomadrassi.trollingfreedomreborn.other.EventListener
import com.leomadrassi.trollingfreedomreborn.trolls.chat.ChatChange
import com.leomadrassi.trollingfreedomreborn.trolls.chat.Deafen
import com.leomadrassi.trollingfreedomreborn.trolls.chat.ExplodeOnChat
import com.leomadrassi.trollingfreedomreborn.trolls.chat.Nick
import com.leomadrassi.trollingfreedomreborn.trolls.chat.ReverseMessage
import com.leomadrassi.trollingfreedomreborn.trolls.classics.*
import com.leomadrassi.trollingfreedomreborn.trolls.explosion.*
import com.leomadrassi.trollingfreedomreborn.trolls.fakestuff.FakeKicks
import com.leomadrassi.trollingfreedomreborn.trolls.fakestuff.FakeReload
import com.leomadrassi.trollingfreedomreborn.trolls.inventory.*
import com.leomadrassi.trollingfreedomreborn.trolls.movement.*
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Credits
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.Demo
import com.leomadrassi.trollingfreedomreborn.trolls.packettrolls.WorldLoading
import com.leomadrassi.trollingfreedomreborn.trolls.random.*
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedExplosion
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedMissing
import com.leomadrassi.trollingfreedomreborn.trolls.Beds.BedNight
import com.leomadrassi.trollingfreedomreborn.ui.ConfirmIH
import com.cryptomorin.xseries.XEnchantment
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandMap
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.permissions.ServerOperator
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.lang.reflect.Field
import java.util.*

class Core : JavaPlugin(), Listener {
    companion object {
        lateinit var instance: Core
        @JvmField
        var usingUUID = false

        @JvmStatic
        fun tcc(i: String): String = ChatColor.translateAlternateColorCodes('&', i)

        @JvmStatic
        fun uid(): Boolean = usingUUID

        @JvmStatic
        fun getPathCC(path: String): String {
            var c = instance.pluginConfig.getString(path)
            c = ChatColor.translateAlternateColorCodes('&', c!!)
            return c
        }

        @JvmStatic
        fun advCheck(perm: String, p: Player): Boolean {
            if (instance.pluginConfig.getBoolean("values.advanced-perms.enabled")) {
                if (usingUUID) {
                    val pp = Bukkit.getPlayerExact(instance.pluginConfig.getString("values.advanced-perms.playername")!!)
                    if (pp != null && pp.isOnline() && pp.hasPlayedBefore()) {
                        return p.uniqueId == pp.uniqueId
                    }
                } else {
                    return p.name == instance.pluginConfig.getString("values.advanced-perms.playername")
                }
            } else {
                return p.hasPermission(perm)
            }
            return false
        }

        @JvmStatic
        fun canTroll(target: Player): Boolean {
            val allowOp = instance.pluginConfig.getBoolean("allow-troll-op", false)
            if (target.isOp() && !allowOp) return false
            val blocklist = instance.pluginConfig.getStringList("blocklist")
            return !blocklist.contains(target.name)
        }

        @JvmStatic
        fun isTrollActive(t: Player?, trollKey: String): Boolean {
            if (t == null) return false
            val name = t.name
            return when (trollKey.lowercase()) {
                "annoy" -> Annoy.Annoy1.contains(name)
                "aquaphobia" -> Aquaphobia.Aqua1.contains(name)
                "bedexplosion" -> BedExplosion.Explode1.contains(name)
                "stopblockbreakplace" -> Break.Break1.contains(name)
                "cage" -> Cage.Cage1.contains(name)
                "randomchat" -> ChatChange.Chat1.contains(name)
                "entitymultiply" -> EntityMultiply.EntityMultiply1.contains(name)
                "creeperawman" -> CreeperAwMan.Creeper1.contains(name)
                "deafen" -> Deafen.Deaf1.contains(name)
                "forcejump" -> ForceJump.Jump1.contains(name)
                "freeze" -> Freeze.frozen.contains(name)
                "hideallplayers" -> HideAllPlayers.hide1.contains(name)
                "instatoolbreak" -> InstaToolBreak.InstaToolBreak1.contains(name)
                "inventorystop" -> InventoryStop.InvStop1.contains(name)
                "kittycannon" -> KittyCannon.Kitty1.contains(name)
                "lightning" -> Lightning.Lightning1.contains(name)
                "nick" -> Nick.Nick1.contains(name)
                "burn" -> t.fireTicks > 0
                "potato" -> Potato.Break1.contains(name)
                "pumpkin" -> Pumpkin.Pumpkin1.contains(name)
                "rainitems" -> RainItems.Rain1.contains(name)
                "randominv" -> RandomInv.RandomInv1.contains(name)
                "randomparticle" -> RandomParticle.RandomParticle1.contains(name)
                "rickroll" -> RickRoll.Rick1.contains(name)
                "silverfish" -> Silverfish.Fish1.contains(name)
                "slenderman" -> Slenderman.Slender1.contains(name)
                "sneakdestroy" -> SneakDestroy.Sneak1.contains(name)
                "spin" -> Spin.spin1.contains(name)
                "starve" -> Starve.starve1.contains(name)
                "skyflash" -> TimeFlash.flash1.contains(name)
                "tntplace" -> TNTPlace.Fireball1.contains(name)
                "vomit" -> Vomit.Vomit1.contains(name)
                "explodeonchat" -> ExplodeOnChat.Chat1.contains(name)
                "inventoryrave" -> InventoryRave.Rave1.contains(name)
                "invert" -> InvertWalk.Invert1.contains(name)
                "stopsleep" -> BedNight.Bed1.contains(name)
                "reversemessage" -> ReverseMessage.Reverse1.contains(name)
                "poop" -> Poop.Poop1.contains(name)
                "ringoffire" -> RingOfFire.nuke1.contains(name)
                "randomcraft" -> RandomCrafts.randomcraft.contains(name)
                else -> false
            }
        }
    }

    var pluginConfig: FileConfiguration = config
    private val file: File? = null
    val individualTasks: MutableMap<UUID, MutableMap<String, MutableList<Int>>> = HashMap()

    init {
        instance = this
    }

    override fun reloadConfig() {
        super.reloadConfig()
        saveDefaultConfig()
        pluginConfig = config
        pluginConfig.options().copyDefaults(true)
    }

    fun getSkull(): ItemStack {
        val skull = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGY0YzY3MzZjYjFjNjg4MGQ4MGEyMDYyZDdhYTRhYWIxZWEwYzU1YmYxNDJhZDMwZmQ1MmM1NzUxNWYwYzJkMSJ9fX0=")
        val skullmeta = skull.itemMeta
        skullmeta!!.setDisplayName("§b§lTrolling§3§lFreedomReborn §7| §bTroll GUI")
        skullmeta.lore = listOf("§0§k000000000", "§7§k777777", "§9§k999999", "§a§k§l133742069YEET!")
        skullmeta.addEnchant(XEnchantment.UNBREAKING.getEnchant()!!, 1, false)
        skullmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        skull.itemMeta = skullmeta
        return skull
    }

    override fun onEnable() {
        setupConfig()

        if (config.getBoolean("values.dependency-downloader")) {
            try {
                DependencyChecker()
                DependencyChecker.DependencyChecker()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (!config.getBoolean("values.dependency-downloader")) {
                return
            }
        }

        getCommand("trollingfreedom")!!.setExecutor(Help())
        getCommand("trollingfreedom")!!.setTabCompleter(Help())
        getCommand("untroll")!!.setExecutor(UnTroll())
        getCommand("untroll")!!.setTabCompleter(UnTroll())
        getCommand("control")!!.setExecutor(Control())
        server.pluginManager.registerEvents(this, this)
        server.pluginManager.registerEvents(EventListener(), this)
        getCommand("trollf")!!.setExecutor(TrollWithCMD())
        getCommand("panicstoptroll")!!.setExecutor(Panic())
        getCommand("trollf")!!.setTabCompleter(TrollWithCMD())
        getCommand("trollgui")!!.setExecutor(TrollGUIAlias())
        server.pluginManager.registerEvents(AFK(), this)
        server.pluginManager.registerEvents(OP(), this)
        server.pluginManager.registerEvents(Demo(), this)
        server.pluginManager.registerEvents(TNT(), this)
        server.pluginManager.registerEvents(Credits(), this)
        server.pluginManager.registerEvents(Freeze(), this)
        server.pluginManager.registerEvents(Spin(), this)
        server.pluginManager.registerEvents(Launch(), this)
        server.pluginManager.registerEvents(Herobrine(), this)
        server.pluginManager.registerEvents(FakeKicks(), this)
        server.pluginManager.registerEvents(Break(), this)
        server.pluginManager.registerEvents(Potato(), this)
        server.pluginManager.registerEvents(Void(), this)
        server.pluginManager.registerEvents(Annoy(), this)
        server.pluginManager.registerEvents(Vomit(), this)
        server.pluginManager.registerEvents(WorldLoading(), this)
        server.pluginManager.registerEvents(Pumpkin(), this)
        server.pluginManager.registerEvents(Sounds(), this)
        server.pluginManager.registerEvents(AnvilDrop(), this)
        server.pluginManager.registerEvents(InventoryStop(), this)
        server.pluginManager.registerEvents(Slenderman(), this)
        server.pluginManager.registerEvents(CreeperAwMan(), this)
        server.pluginManager.registerEvents(Coffin(), this)
        server.pluginManager.registerEvents(DropAll(), this)
        server.pluginManager.registerEvents(Cage(), this)
        server.pluginManager.registerEvents(Starve(), this)
        server.pluginManager.registerEvents(ChatChange(), this)
        server.pluginManager.registerEvents(Invsee(), this)
        server.pluginManager.registerEvents(RandomTP(), this)
        server.pluginManager.registerEvents(RandomCrafts(), this)
        server.pluginManager.registerEvents(Lightning(), this)
        server.pluginManager.registerEvents(HideAllPlayers(), this)
        server.pluginManager.registerEvents(TimeFlash(), this)
        server.pluginManager.registerEvents(TNTPlace(), this)
        server.pluginManager.registerEvents(Aquaphobia(), this)
        server.pluginManager.registerEvents(SlipperyHands(), this)
        server.pluginManager.registerEvents(LockInventory(), this)
        server.pluginManager.registerEvents(SneakDestroy(), this)
        server.pluginManager.registerEvents(InstaToolBreak(), this)
        server.pluginManager.registerEvents(Burn(), this)
        server.pluginManager.registerEvents(EntityMultiply(), this)
        server.pluginManager.registerEvents(BedExplosion(), this)
        server.pluginManager.registerEvents(Lag(), this)
        server.pluginManager.registerEvents(FakeReload(), this)
        server.pluginManager.registerEvents(ExplodingChicken(), this)
        server.pluginManager.registerEvents(ExplosiveSheep(), this)
        server.pluginManager.registerEvents(MathUtils, this)
        server.pluginManager.registerEvents(RickRoll(), this)
        server.pluginManager.registerEvents(RandomInv(), this)
        server.pluginManager.registerEvents(RandomParticle(), this)
        server.pluginManager.registerEvents(AllEntitiesDie(), this)
        server.pluginManager.registerEvents(ForceJump(), this)
        server.pluginManager.registerEvents(Deafen(), this)
        server.pluginManager.registerEvents(KittyCannon(), this)
        server.pluginManager.registerEvents(BedMissing(), this)
        server.pluginManager.registerEvents(ExplodeOnChat(), this)
        server.pluginManager.registerEvents(InventoryRave(), this)
        server.pluginManager.registerEvents(InvertWalk(), this)
        server.pluginManager.registerEvents(UntrollOnQuit(), this)
        server.pluginManager.registerEvents(BedNight(), this)
        server.pluginManager.registerEvents(ReverseMessage(), this)
        server.pluginManager.registerEvents(RainItems(), this)
        server.pluginManager.registerEvents(Poop(), this)
        server.pluginManager.registerEvents(ConfirmIH(), this)
        server.pluginManager.registerEvents(Control(), this)
        server.pluginManager.registerEvents(RingOfFire(), this)

        try {
            val f = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
            f.isAccessible = true
            val commandMap = f.get(Bukkit.getServer()) as CommandMap
            registerCustomAliases(commandMap)
        } catch (e: Exception) {
            logger.severe("Could not register custom aliases: " + e.message)
        }

        super.onEnable()
        reloadConfig()
        usingUUID = config.getBoolean("values.using-uuid")

        try {
            val f = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
            f.isAccessible = true
            val commandMap = f.get(Bukkit.getServer()) as CommandMap
            TrollCommand(commandMap, this)
        } catch (e: NoSuchFieldException) {
            logger.severe(e.localizedMessage)
            e.printStackTrace()
            Bukkit.getPluginManager().disablePlugin(this)
        } catch (e: IllegalAccessException) {
            logger.severe(e.localizedMessage)
            e.printStackTrace()
            Bukkit.getPluginManager().disablePlugin(this)
        }

        usingUUID = server.onlineMode

        UpdateChecker(this, 131388).getVersion { version: String ->
            if (this.description.version.equals(version, ignoreCase = true)) {
                val message1 = instance.pluginConfig.get("trollingfreedom-console-no-update") as String
                val replaced1 = message1.replace("&", "§")
                server.consoleSender.sendMessage(replaced1)
            } else {
                server.consoleSender.sendMessage("${ChatColor.RED}=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=")
                server.consoleSender.sendMessage("${ChatColor.RED}TrollingFreedomReborn has an update available!")
                server.consoleSender.sendMessage("${ChatColor.RED}Grab it from one of these links:")
                server.consoleSender.sendMessage("${ChatColor.LIGHT_PURPLE}GitHub: §nhttps://github.com/leomadrassidev/TrollingFreedomReborn")
                server.consoleSender.sendMessage("${ChatColor.LIGHT_PURPLE}Spigot: §nhttps://www.spigotmc.org/resources/.131388/")
                server.consoleSender.sendMessage("${ChatColor.LIGHT_PURPLE}Modrinth: §nhttps://modrinth.com/plugin/trollingfreedomreborn")
                server.consoleSender.sendMessage("${ChatColor.LIGHT_PURPLE}GitHub: §nhttps://hangar.papermc.io/leomadrassidev/TrollingFreedomReborn")
                server.consoleSender.sendMessage("${ChatColor.RED}=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=")
                Bukkit.getOnlinePlayers().stream().filter { obj: ServerOperator -> obj.isOp() }.forEach { op: ServerOperator ->
                    val player = op as Player
                    player.sendMessage("${ChatColor.RED}=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=")
                    player.sendMessage("${ChatColor.RED}TrollingFreedomReborn has an update available!")
                    player.sendMessage("${ChatColor.RED}Grab it from one of these links:")
                    player.sendMessage("${ChatColor.LIGHT_PURPLE}GitHub: §nhttps://github.com/leomadrassidev/TrollingFreedomReborn")
                    player.sendMessage("${ChatColor.LIGHT_PURPLE}Spigot: §nhttps://www.spigotmc.org/resources/.131388/")
                    player.sendMessage("${ChatColor.LIGHT_PURPLE}Modrinth: §nhttps://modrinth.com/plugin/trollingfreedomreborn")
                    player.sendMessage("${ChatColor.LIGHT_PURPLE}GitHub: §nhttps://hangar.papermc.io/leomadrassidev/TrollingFreedomReborn")
                    player.sendMessage("${ChatColor.RED}=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=")
                }
            }
        }
    }

    fun addTask(player: Player, trollName: String, taskId: Int) {
        val uuid = player.uniqueId
        individualTasks.computeIfAbsent(uuid) { HashMap() }
        individualTasks[uuid]!!.computeIfAbsent(trollName) { ArrayList() }.add(taskId)
    }

    fun registerCustomAliases(commandMap: CommandMap) {
        val cfg = config
        val aliasPaths = HashMap<String, String>()
        aliasPaths["custom-aliases.trollf"] = "trollf"
        aliasPaths["custom-aliases.trollgui"] = "trollgui"
        for ((configPath, originalCmdName) in aliasPaths) {
            val aliases = cfg.getStringList(configPath)
            if (aliases != null && aliases.isNotEmpty()) {
                val originalCmd = getCommand(originalCmdName)
                if (originalCmd != null) {
                    for (alias in aliases) {
                        val dynamicAlias = DynamicAlias(alias, originalCmd)
                        commandMap.register(this.description.name, dynamicAlias)
                        logger.info("Added alias /$alias targeting /$originalCmdName")
                    }
                }
            }
        }
    }

    fun getP(): String = tcc(pluginConfig.getString("prefix")!!)

    fun getPrefixedMessage(configPath: String): String = tcc(pluginConfig.getString("prefix")!! + pluginConfig.get(configPath))

    override fun onDisable() {
        server.logger.info("§b§lTrolling§3§lFreedomReborn §7| §cShutting down")
        server.logger.info("§c§lDone")
    }

    fun setupConfig() {
        val cfg = config
        val defaultBlocklist = listOf("LeoMadrassiDev", "Herobrine")
        cfg.addDefault("blocklist", defaultBlocklist)
        cfg.addDefault("allow-troll-op", false)
        cfg.options().copyDefaults(true)
        saveConfig()
        cfg.setComments("blocklist", listOf("Players listed here are immune to trolling"))
        cfg.setComments("allow-troll-op", listOf("Set to true if you want to allow trolling operators"))
        saveConfig()
    }
}
