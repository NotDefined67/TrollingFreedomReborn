package com.leomadrassi.trollingfreedomreborn.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import java.util.List;

public class DynamicAlias extends Command {
    private final PluginCommand original;

    protected DynamicAlias(String name, PluginCommand original) {
        super(name);
        this.original = original;
        // Copy description and usage from original
        this.setDescription(original.getDescription());
        this.setUsage(original.getUsage());
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        // Redirect execution to the original command's executor
        return original.execute(sender, commandLabel, args);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        // Redirect tab completion to the original command's tab completer
        return original.tabComplete(sender, alias, args);
    }
}