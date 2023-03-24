package pl.lifelesspixels.lputilities.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import pl.lifelesspixels.lputilities.LPUtilities;
import pl.lifelesspixels.lputilities.data.PlayerCommandRegistry;

public class CommandRegistryEventHandler implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        PlayerCommandRegistry commandRegistry = LPUtilities.getInstance().getPlayerCommandRegistry();
        commandRegistry.setForPlayer(event.getPlayer(), event.getMessage());
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        PlayerCommandRegistry commandRegistry = LPUtilities.getInstance().getPlayerCommandRegistry();
        commandRegistry.setForConsole(event.getCommand());
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        PlayerCommandRegistry commandRegistry = LPUtilities.getInstance().getPlayerCommandRegistry();
        commandRegistry.removeForPlayer(event.getPlayer());
    }

}
