package pl.lifelesspixels.lputilities;

import org.bukkit.plugin.java.JavaPlugin;
import pl.lifelesspixels.lputilities.commands.GiveSkullCommand;
import pl.lifelesspixels.lputilities.data.PlayerCommandRegistry;
import pl.lifelesspixels.lputilities.events.CommandRegistryEventHandler;

import java.util.Objects;

public class LPUtilities extends JavaPlugin {

    private static LPUtilities instance;

    private PlayerCommandRegistry playerCommandRegistry;

    @Override
    public void onEnable() {
        instance = this;

        // create data storage
        playerCommandRegistry = new PlayerCommandRegistry();

        // register commands
        Objects.requireNonNull(getCommand("giveskull")).setExecutor(new GiveSkullCommand());

        // register event handlers
        getServer().getPluginManager().registerEvents(new CommandRegistryEventHandler(), this);
    }

    public static LPUtilities getInstance() {
        return instance;
    }

    public PlayerCommandRegistry getPlayerCommandRegistry() {
        return playerCommandRegistry;
    }
}
