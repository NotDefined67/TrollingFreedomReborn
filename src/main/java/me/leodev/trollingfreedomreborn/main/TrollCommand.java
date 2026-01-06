package me.leodev.trollingfreedomreborn.main;

import me.leodev.trollingfreedomreborn.commands.Help;
import me.leodev.trollingfreedomreborn.ui.PlayerSelectorInventory;
import me.leodev.trollingfreedomreborn.ui.SettingsMenuInventory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TrollCommand extends CommandHandler {

    Set<String> keys = Core.instance.config.getDefaultSection().getKeys(true);
    String[] args;

    public TrollCommand(CommandMap commandMap, JavaPlugin plugin) {
        super(plugin, "troll");
        // Keep your keys.remove() logic here

        addPermission("trollingfreedom.open");
        registerCommand(commandMap);

        // Remove the static addListTabbComplete calls here if you want it purely dynamic
    }

    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, String[] args) throws IllegalArgumentException {
        // Index 0: Main sub-commands
        if (args.length == 1) {
            List<String> subcommands = Arrays.asList("reload", "giveskull", "contact", "help", "toggle-troll-op", "add-blocked", "remove-blocked");
            return subcommands.stream()
                    .filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase()))
                    .collect(java.util.stream.Collectors.toList());
        }

        // Index 1: The dynamic logic based on the first argument
        if (args.length == 2) {
            String sub = args[0].toLowerCase();

            // IF arg[0] is add-blocked -> show online players
            if (sub.equals("add-blocked")) {
                return Bukkit.getOnlinePlayers().stream()
                        .map(Player::getName)
                        .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase()))
                        .collect(java.util.stream.Collectors.toList());
            }

            // IF arg[0] is remove-blocked -> show current config list
            if (sub.equals("remove-blocked")) {
                return Core.instance.getConfig().getStringList("blocklist").stream()
                        .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase()))
                        .collect(java.util.stream.Collectors.toList());
            }

            // ELSE (none of the two) -> show your static keys or online players
            // If you want online players as the default for everything else:
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase()))
                    .collect(java.util.stream.Collectors.toList());
        }

        return super.tabComplete(sender, alias, args);
    }

    // Not 1:1 Stolen fon GeeksforGeeks i swear....
    public static String[] convert(Set<String> setOfString) {
        String[] arrayOfString = new String[setOfString.size()];
        int index = 0;
        for (String str : setOfString)
            arrayOfString[index++] = str;
        return arrayOfString;
    }

    public static boolean isNumeric(String str) { // Surely not stolen.... xd

        if (str == null || str.length() == 0) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {
        String ags = strings.length > 0 ? strings[0] : "";
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Core.advCheck("trollingfreedom.open", p)) {
                switch (ags) {
                    case "reload":
                        SettingsMenuInventory sm = new SettingsMenuInventory();
                        sm.openInventory(p);
                        break;
                    case "toggle-troll-op":
                        // Get current value and flip it
                        boolean currentVal = Core.instance.getConfig().getBoolean("allow-troll-op", false);
                        Core.instance.getConfig().set("allow-troll-op", !currentVal);

                        // Save to file and refresh memory
                        Core.instance.saveConfig();
                        sender.sendMessage(Core.instance.getP() + "§7Allow-Troll-OP is now: " + (!currentVal ? "§aEnabled" : "§cDisabled"));
                        break;

                    case "add-blocked":
                        if (strings.length < 2) {
                            sender.sendMessage(Core.instance.getP() + "§cUsage: /troll add-blocked <player>");
                            return true;
                        }
                        String toBlock = strings[1];
                        List<String> blocklistAdd = Core.instance.getConfig().getStringList("blocklist");

                        if (!blocklistAdd.contains(toBlock)) {
                            blocklistAdd.add(toBlock);
                            Core.instance.getConfig().set("blocklist", blocklistAdd);
                            Core.instance.saveConfig();
                            sender.sendMessage(Core.instance.getP() + "§b" + toBlock + " §7has been added to the blocklist.");
                        } else {
                            sender.sendMessage(Core.instance.getP() + "§cThat player is already blocked.");
                        }
                        break;

                    case "remove-blocked":
                        if (strings.length < 2) {
                            sender.sendMessage(Core.instance.getP() + "§cUsage: /troll remove-blocked <player>");
                            return true;
                        }
                        String toRemove = strings[1];
                        List<String> blocklistRem = Core.instance.getConfig().getStringList("blocklist");

                        if (blocklistRem.contains(toRemove)) {
                            blocklistRem.remove(toRemove);
                            Core.instance.getConfig().set("blocklist", blocklistRem);
                            Core.instance.saveConfig();
                            sender.sendMessage(Core.instance.getP() + "§b" + toRemove + " §7has been removed from the blocklist.");
                        } else {
                            sender.sendMessage(Core.instance.getP() + "§cThat player is not in the blocklist.");
                        }
                        break;
                    case "giveskull":
                        p.getInventory().addItem(Core.instance.getSkull());
                        p.sendMessage(Core.instance.getP() + "§bYou got the skull item.");
                        break;
                    case "help":
                        Help helpcmd = new Help();
                        helpcmd.Help(p);
                        break;
                    case "contact":
                        p.sendMessage(Core.instance.getP() + "§7Plugin Contact: ");
                        p.sendMessage(Core.instance.getP() + "§9Discord§7: §nhttps://discord.gg/???/");
                        p.sendMessage(Core.instance.getP() + "https://www.spigotmc.org/resources/?/");
                        break;
                    case "player":
                        p.sendMessage(Core.instance.getP() + "§7For command line trolling:");
                        p.sendMessage(Core.instance.getP() + "§9/trollf <PLAYER> <TROLL>");
                        p.sendMessage(Core.instance.getP());
                        p.sendMessage(Core.instance.getP() + "§7For GUI trolling:");
                        p.sendMessage(Core.instance.getP() + "§9/Troll");
                        break;
                    default:
                        if (!(sender instanceof Player)) {
                            sender.sendMessage("§cOnly players can open the selection GUI.");
                            return true;
                        }
                        PlayerSelectorInventory ps = new PlayerSelectorInventory();
                        ps.openSel((Player) sender);
                        break;
                }
            }
//            if(ags.equals("settings")){
//                SettingsMenu sm = new SettingsMenu();
//                sm.openInventory(p);
//            } else if(ags.equals("giveskull")) {
//                p.getInventory().addItem(core.instance.getSkull());
//                p.sendMessage(core.instance.getP()+"§bYou got the skull item.");
//            } else {
//                PlayerSelector ps = new PlayerSelector();
//                ps.openSel(p);
//            }

        } else {
            sender.sendMessage("no player");
        }

        return true;
    }

}
