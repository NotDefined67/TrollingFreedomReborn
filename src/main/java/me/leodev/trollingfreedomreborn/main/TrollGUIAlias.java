package me.leodev.trollingfreedomreborn.main;

import me.leodev.trollingfreedomreborn.ui.PlayerSelectorInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


//ArgsCmd also implements CommandInterface
public class TrollGUIAlias implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use the GUI.");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("trollingfreedom.open")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) Core.instance.getConfig().get("no-perms")));
            return true;
        }

        // If they typed a name: /troll Technoblade
        if (args.length > 0) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                // THE CRITICAL CHECK
                if (!Core.canTroll(target)) {
                    p.sendMessage("§c§l[TFR] §7This player is §4protected §7and cannot be trolled.");
                    return true;
                }
                // If not blocked, open the specific troll menu for that target
                // (Assuming your UI has a way to open directly to a player's page)
            }
        }

        // Default: Open the player selector
        new PlayerSelectorInventory().openSel(p);
        return true;
    }

}
