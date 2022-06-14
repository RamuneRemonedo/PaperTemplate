package tokyo.ramune.papertemplate;

import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.papertemplate.command.CommandHandler;
import tokyo.ramune.papertemplate.listener.ListenerHandler;

public final class PaperTemplate extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        new ListenerHandler(this);
        new CommandHandler(this, "template");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
