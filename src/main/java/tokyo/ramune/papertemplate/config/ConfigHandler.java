package tokyo.ramune.papertemplate.config;

import org.bukkit.configuration.file.FileConfiguration;
import tokyo.ramune.papertemplate.PaperTemplate;

public class ConfigHandler {

    private final static FileConfiguration config = PaperTemplate.getPlugin().getConfig();

    public static String CONFIG_VERSION;

    public static void load(FileConfiguration config) {
        CONFIG_VERSION = config.getString("config.version", "1.0");
    }
}
