package com.leomadrassi.trollingfreedomreborn.main

import org.bukkit.ChatColor
import org.bukkit.command.*
import org.bukkit.entity.Player

import org.bukkit.plugin.java.JavaPlugin
import java.util.ArrayList

abstract class CommandHandler<T : JavaPlugin>(plugin: T, name: String) : Command(name), CommandExecutor {

    private val plugin: T
    private val register = false
    private val tabComplete: HashMap<Int, ArrayList<TabCommand>>

    init {
        assert(plugin != null)
        assert(name != null)
        assert(name.length > 0)
        setLabel(name)
        this.plugin = plugin
        tabComplete = HashMap()
    }

    protected fun addDescription(description: String?): CommandHandler<T> {
        if (register || description != null) setDescription(description!!)
        return this
    }

    protected fun addUsage(use: String?): CommandHandler<T> {
        if (register || use != null) setUsage(use!!)
        return this
    }

    protected fun addAliases(vararg aliases: String): CommandHandler<T> {
        if (aliases != null && (register || aliases.size > 0)) setAliases(aliases.toList())
        return this
    }

    protected fun addOneTabbComplete(indice: Int, permission: String?, arg: String?, vararg beforeText: String): CommandHandler<T> {
        if (arg != null && indice >= 0) {
            if (tabComplete.containsKey(indice)) {
                tabComplete[indice]!!.add(TabCommand(indice, arg, permission, *beforeText))
            } else {
                val tabCommands = ArrayList<TabCommand>()
                tabCommands.add(TabCommand(indice, arg, permission, *beforeText))
                tabComplete[indice] = tabCommands
            }
        }
        return this
    }

    protected fun addListTabbComplete(indice: Int, permission: String?, beforeText: Array<String>?, vararg arg: String): CommandHandler<T> {
        if (arg != null && arg.size > 0 && indice >= 0) {
            if (tabComplete.containsKey(indice)) {
                tabComplete[indice]!!.addAll(arg.map { TabCommand(indice, it, permission, *(beforeText ?: emptyArray())) })
            } else {
                tabComplete[indice] = arg.map { TabCommand(indice, it, permission, *(beforeText ?: emptyArray())) } as ArrayList<TabCommand>
            }
        }
        return this
    }

    protected fun addListTabbComplete(indice: Int, perms: String, vararg arg: String): CommandHandler<T> {
        if (arg != null && arg.size > 0 && indice >= 0) {
            addListTabbComplete(indice, perms, null, *arg)
        }
        return this
    }

    protected fun addPermission(permission: String?): CommandHandler<T> {
        if (register || permission != null) setPermission(permission)
        return this
    }

    protected fun addPermissionMessage(permissionMessage: String?): CommandHandler<T> {
        if (register || permissionMessage != null) setPermissionMessage(permissionMessage)
        return this
    }

    protected fun registerCommand(commandMap: CommandMap): Boolean {
        return !register && commandMap.register("", this)
    }

    fun getTabComplete(): HashMap<Int, ArrayList<TabCommand>> {
        return tabComplete
    }

    override fun execute(commandSender: CommandSender, command: String, arg: Array<String>): Boolean {
        if (commandSender is Player) {
            if (permission != null) {
                if (!Core.advCheck(permission!!, commandSender)) {
                    if (permissionMessage == null) {
                        if (Core.instance!!.pluginConfig.getBoolean("values.using-no-perm")) {
                            commandSender.sendMessage("${ChatColor.RED}no permit!")
                        }
                    } else {
                        if (Core.instance!!.pluginConfig.getBoolean("values.using-no-perm")) {
                            commandSender.sendMessage(permissionMessage!!)
                        }
                    }
                    return false
                }
            }
            if (onCommand(commandSender, this, command, arg)) return true
            commandSender.sendMessage("${ChatColor.RED}$usage")
            return false
        }
        return false
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): MutableList<String> {
        val indice = args.size - 1
        if (sender is Player) {
            if ((permission != null && !Core.advCheck(permission!!, sender)) || tabComplete.size == 0 || !tabComplete.containsKey(indice))
                return super.tabComplete(sender, alias, args)

            val list = tabComplete[indice]!!.filter { tabCommand ->
                (tabCommand.textAvant == null || tabCommand.textAvant!!.contains(args[indice - 1])) &&
                        (tabCommand.permission == null || Core.advCheck(tabCommand.permission!!, sender)) &&
                        tabCommand.text.startsWith(args[indice])
            }.map { it.text }.toMutableList()

            return if (list.size < 1) super.tabComplete(sender, alias, args) else list
        }
        return mutableListOf()
    }

    inner class TabCommand {
        val indice: Int
        val text: String
        val permission: String?
        val textAvant: ArrayList<String>?

        constructor(indice: Int, text: String, permission: String?, vararg textAvant: String) {
            this.indice = indice
            this.text = text
            this.permission = permission
            this.textAvant = if (textAvant == null || textAvant.size < 1) null else ArrayList(textAvant.toList())
        }

        constructor(indice: Int, text: String, permission: String?) : this(indice, text, permission, "")

        constructor(indice: Int, text: String, textAvant: Array<String>) : this(indice, text, null, *textAvant)

        constructor(indice: Int, text: String) : this(indice, text, null, "")
    }
}
