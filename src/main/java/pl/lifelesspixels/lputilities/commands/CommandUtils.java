package pl.lifelesspixels.lputilities.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandUtils {

    public static void sendNoPermissionsMessage(CommandSender sender, String commandName) {
        sender.sendMessage(ChatColor.RED + "You have no permissions to run /" + commandName + " command");
    }

    public static void sendUsage(CommandSender sender, String alias, String argumentsString) {
        sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.RESET + "/" + alias + " " + argumentsString);
    }

    public static void sendPlayerNotFound(CommandSender sender, String playerName) {
        sender.sendMessage(ChatColor.RED + "Player " + ChatColor.RESET + playerName + ChatColor.RED + " cannot be found");
    }

    public static void sendCannotParseInteger(CommandSender sender, String integerString) {
        sender.sendMessage(ChatColor.RED + "Integer " + ChatColor.RESET + integerString + ChatColor.RED + " is invalid");
    }

}
