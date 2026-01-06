package me.leodev.trollingfreedomreborn.commands;

import me.leodev.trollingfreedomreborn.main.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.List;

//ArgsCmd also implements CommandInterface
public class Help implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        // Check if the command matches
        if (commandLabel.equalsIgnoreCase("trollingfreedom") || cmd.getName().equalsIgnoreCase("trollingfreedom")) {
            // Pass 'sender' directly (this works for both Console and Player)
            Help(sender);
            return true;
        }
        return false;
    }

    public void Help(CommandSender s) { // Changed 'Player p' to 'CommandSender s'
        PluginDescriptionFile pdf = Core.instance.getDescription();
        List<String> messages = Core.instance.getConfig().getStringList("trollingfreedom-help");

        if (messages == null) return;

        for (String msg : messages) {
            String replaced = msg.replace("%version%", pdf.getVersion());
            // 's' is the CommandSender; it will never be null here
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', replaced));
        }
    }
}