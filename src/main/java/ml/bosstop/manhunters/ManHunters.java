package ml.bosstop.manhunters;

import ml.bosstop.commons.storage.JSONStore;
import ml.bosstop.commons.structures.storage.ConfigFormat;
import ml.bosstop.manhunters.utilities.Chat;
import org.bukkit.plugin.java.JavaPlugin;

public class ManHunters extends JavaPlugin {
    public Chat chat;
    private ConfigFormat config = null;

    private final String[] startupLogo = {
            "&b  ██████  ██████ &r" + " %version%",
            "&b  ██   ██ ██   ██&r" + " %server%",
            "&b  ██████  ██████ &r" + " %ranks%",
            "&b  ██      ██   ██&r" + " %update%",
            "&b  ██      ██   ██&r" + " %restarted%"
    };

    @Override
    public void onEnable() {

        this.chat = new Chat();

        try {
            JSONStore.enable();
        } catch (Exception e) {

        } finally {

        }

    }

    @Override
    public void onDisable() {
        try {
            JSONStore.disable();
        } catch (Exception e) {
            this.chat.console("&cFailed to disable ManHunters!");
            this.chat.console(e.getMessage());
        } finally {
            this.chat.console("&aManHunters disabled!");
        }
    }

    public ConfigFormat getMHConfig() {
        return this.config;
    }

    public void setMHConfig(ConfigFormat config) {
        this.config = config;
    }

    public static ManHunters getInstance() {
        return getPlugin(ManHunters.class);
    }
}
