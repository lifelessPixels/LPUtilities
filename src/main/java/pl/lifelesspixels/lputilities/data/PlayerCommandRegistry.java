package pl.lifelesspixels.lputilities.data;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerCommandRegistry {

    private final HashMap<Player, String> lastSentCommand = new HashMap<>();
    private String lastConsoleCommand;

    public void setForPlayer(Player player, String commandString) {
        lastSentCommand.put(player, commandString);
    }

    public String getForPlayer(Player player) {
        return lastSentCommand.get(player);
    }

    public void removeForPlayer(Player player) {
        lastSentCommand.remove(player);
    }

    public void setForConsole(String commandString) {
        if(!commandString.startsWith("/"))
            commandString = "/" + commandString;

        lastConsoleCommand = commandString;
    }

    public String getForConsole() {
        return lastConsoleCommand;
    }

}
