package com.leomadrassi.trollingfreedomreborn.trolls.movement

import com.leomadrassi.trollingfreedomreborn.main.Core
import com.leomadrassi.trollingfreedomreborn.ui.PlayerSelectorInventory
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.PlayerDeathEvent
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.player.*
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team

class Control : CommandExecutor, Listener {
    companion object {
        val controlled1: MutableList<String> = ArrayList()
        val controller1: MutableList<String> = ArrayList()
    }

    private var allowTargetChat = false
    private var controllerInventory: Array<ItemStack?> = emptyArray()
    private var controllerArmor: Array<ItemStack?> = emptyArray()

    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        if (!sender.hasPermission("trollingfreedom.open")) {
            val message2 = Core.instance.config.get("no-perms") as String
            val replaced2 = message2.replace("&", "§").replace("%player%", sender.name)
            sender.sendMessage(replaced2)
            return false
        }
        if (args.isEmpty()) {
            val p = sender as Player
            val ps = PlayerSelectorInventory()
            ps.openSel(p)
            return false
        }
        if (args.size == 1) {
            val t = Bukkit.getServer().getPlayer(args[0])
            if (args[0] == "stop") {
                if (controlled1.contains(controlled!!.name)) {
                    UnControl(controlled)
                }
                return false
            }
            if (t == null) {
                val message4 = Core.instance.config.get("not-online") as String
                val replaced4 = message4.replace("&", "§").replace("%player%", args[0])
                sender.sendMessage(replaced4)
                return false
            }
            val message3 = Core.instance.config.get("cannot-troll-yourself") as String
            val replaced3 = message3.replace("&", "§")
            if (t == sender) {
                sender.sendMessage(replaced3)
            } else {
                controller1.add(sender.name)
                controlled1.add(args[0])
                Control(controlled!!)
            }
        }
        return false
    }

    fun Control(p: Player) {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        StatTracker()
        Setup()
    }

    fun UnControl(p: Player) {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        for (allPlayers in Bukkit.getOnlinePlayers()) {
            allPlayers.showPlayer(Core.instance, controller!!)
        }
        controller!!.isInvisible = false
        controller.isInvulnerable = false
        controller.isCollidable = false
        controller.foodLevel = 20
        controller.health = 20.0
        controller.setDisplayName("")
        val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
        val team = scoreboard.getTeam("icuCollision")
        team!!.unregister()
        Undo()
    }

    fun Undo() {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        restoreControllerInventory()
        val message3 = Core.instance.config.get("control-troll-grace-period") as String
        val replaced3 = message3.replace("&", "§").replace("%player%", controller!!.name)
        controller.sendMessage(replaced3)
        controller.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, 600, 2))
        controller.gameMode = GameMode.SPECTATOR
        val grace = Core.instance.server.scheduler.scheduleSyncDelayedTask(Core.instance, Runnable {
            val msg = Core.instance.config.get("control-troll-grace-period-ended") as String
            val rep = msg.replace("&", "§").replace("%player%", controller.name)
            controller.sendMessage(rep)
            controller.gameMode = GameMode.SURVIVAL
        }, 600)
        Core.instance.addTask(controller, "control", grace)
        controlled1.remove(controlled!!.name)
        controller1.remove(controller.name)
    }

    fun Setup() {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controlledgm = controlled!!.gameMode
        controller!!.gameMode = controlledgm
        controller.isInvisible = true
        controller.isCollidable = false
        controller.isInvulnerable = true
        controller.gameMode = controlled.gameMode
        saveControllerInventory()
        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
            onlinePlayer.hidePlayer(Core.instance, controller!!)
        }
        val controlledflight = controlled.isFlying
        controller.isFlying = controlledflight
        val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
        var team = scoreboard.getTeam("icuCollision")
        if (team == null) {
            team = scoreboard.registerNewTeam("icuCollision")
        }
        try {
            team.javaClass.getMethod("setCanSeeFriendlyInvisibles", Boolean::class.javaPrimitiveType).invoke(team, false)
        } catch (_: Exception) { }
        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)
        if (!team.hasEntry(controller.name)) {
            team.addEntry(controller.name)
        }
    }

    fun StatTracker() {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, Runnable {
            val controllerhunger = controlled!!.foodLevel
            controller!!.foodLevel = controllerhunger
            val controllerhealth = controlled.health
            controller.health = controllerhealth
            controlled.teleport(controller.location)
            controlled.level = controller.level
            controlled.isSneaking = controller.isSneaking
            controlled.isSprinting = controller.isSprinting
            if (controller != null) {
                for (i in 0 until controller.inventory.size) {
                    val item = controller.inventory.getItem(i)
                    if (item != null) {
                        if (item != controlled.inventory.getItem(i)) {
                            controlled.inventory.setItem(i, item)
                        }
                    } else {
                        controlled.inventory.setItem(i, null)
                    }
                }
            }
        }, 2L, 5L)
        Core.instance.addTask(controlled!!, "control", id)
    }

    @EventHandler
    private fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        if (controlled1.contains(controlled!!.name)) {
            if ((event.action == Action.LEFT_CLICK_AIR || event.action == Action.LEFT_CLICK_BLOCK) && player != null) {
                if (event.hand == EquipmentSlot.HAND) {
                    controlled.swingMainHand()
                } else if (event.hand == EquipmentSlot.OFF_HAND) {
                    controlled.swingOffHand()
                }
            }
        }
    }

    @EventHandler
    fun onDrop(e: PlayerDropItemEvent) {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        if (controlled1.contains(controlled!!.name)) {
            if (e.player == controlled) {
                e.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onMove(e: PlayerMoveEvent) {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        if (controlled1.contains(controlled!!.name)) {
            if (e.player == controlled) {
                e.isCancelled = true
            }
        }
    }

    private fun saveControllerInventory() {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        controllerInventory = controller!!.inventory.contents
        controllerArmor = controller.inventory.armorContents
        controller.inventory.contents = controlled!!.inventory.contents
        controller.inventory.armorContents = controlled.inventory.armorContents
    }

    private fun restoreControllerInventory() {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        controlled!!.inventory.contents = controller!!.inventory.contents
        controlled.inventory.armorContents = controller.inventory.armorContents
        controller.inventory.contents = controllerInventory
        controller.inventory.armorContents = controllerArmor
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onChat(e: AsyncChatEvent) {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        if (controller1.contains(controller!!.name)) {
            if (e.player == controlled) {
                e.isCancelled = !allowTargetChat
                allowTargetChat = false
            } else if (e.player == controller) {
                e.isCancelled = true
                allowTargetChat = true
                controlled!!.chat(PlainTextComponentSerializer.plainText().serialize(e.message()))
            }
        }
    }

    @EventHandler
    fun onDie(e: PlayerDeathEvent) {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        if (Control.controlled1.contains(controlled!!.name)) {
            controller!!.performCommand("control stop")
        }
    }

    @EventHandler
    fun onLeave(e: PlayerQuitEvent) {
        val controlled = Bukkit.getServer().getPlayer(controlled1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        val controller = Bukkit.getServer().getPlayer(controller1.toString().replace("\\[".toRegex(), "").replace("\\]".toRegex(), ""))
        if (Control.controlled1.contains(controlled!!.name)) {
            controller!!.performCommand("control stop")
        }
    }
}
