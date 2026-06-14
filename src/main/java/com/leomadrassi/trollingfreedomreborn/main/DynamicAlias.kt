package com.leomadrassi.trollingfreedomreborn.main

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand

class DynamicAlias(name: String, private val original: PluginCommand) : Command(name) {

    init {
        description = original.description
        usage = original.usage
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<String>): Boolean {
        return original.execute(sender, commandLabel, args)
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): MutableList<String> {
        return original.tabComplete(sender, alias, args) as MutableList<String>? ?: mutableListOf()
    }
}
