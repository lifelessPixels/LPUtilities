package pl.lifelesspixels.lputilities;

import org.bukkit.plugin.java.JavaPlugin;

public class LPUtilities extends JavaPlugin {

    private static LPUtilities instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    public static LPUtilities getInstance() {
        return instance;
    }
}
