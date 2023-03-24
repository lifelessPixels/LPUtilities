package pl.lifelesspixels.lputilities;

import org.bukkit.plugin.java.JavaPlugin;
import pl.lifelesspixels.lputilities.commands.GiveSkullCommand;

public class LPUtilities extends JavaPlugin {

    private static LPUtilities instance;

    @Override
    public void onEnable() {
        instance = this;

        // register commands
        getCommand("giveskull").setExecutor(new GiveSkullCommand());
    }

    public static LPUtilities getInstance() {
        return instance;
    }
}
