package com.leomadrassi.trollingfreedomreborn.commands;

import com.leomadrassi.trollingfreedomreborn.main.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.util.StringUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Help implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("trollingfreedom.reload")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to reload TFR.");
                return true;
            }

            Core.instance.reloadConfig();

            try {
                Field f = org.bukkit.Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                org.bukkit.command.CommandMap commandMap = (org.bukkit.command.CommandMap) f.get(org.bukkit.Bukkit.getServer());
                Core.instance.registerCustomAliases(commandMap);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Config reloaded, but failed to refresh aliases.");
                e.printStackTrace();
                return true;
            }

            sender.sendMessage(ChatColor.AQUA + "Trolling Freedom Reborn " + ChatColor.GRAY + "| " + ChatColor.GREEN + "Configuration and Aliases reloaded!");
            return true;
        }

        Help(sender);
        return true;
    }

    // NEW: Tab Completion Logic
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            if (sender.hasPermission("trollingfreedom.reload")) {
                options.add("reload");
            }
            options.add("help"); // Optional: add help to the list

            return StringUtil.copyPartialMatches(args[0], options, new ArrayList<>());
        }
        return Collections.emptyList();
    }

    public void Help(CommandSender s) {
        PluginDescriptionFile pdf = Core.instance.getDescription();
        List<String> messages = Core.instance.getConfig().getStringList("trollingfreedom-help");

        if (messages == null || messages.isEmpty()) {
            s.sendMessage(ChatColor.RED + "Help menu not found in config.yml!");
            return;
        }

        for (String msg : messages) {
            String replaced = msg.replace("%version%", pdf.getVersion());
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', replaced));
        }
    }
}