package lokachop.betterthanwarfare;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.io.File;
import java.io.IOException;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class ModConfig {
	public static final TomlConfigHandler config;

	static {
		Toml defaultConfig = new Toml("Better Than Warfare config file");
		defaultConfig.addCategory("ID range settings", "ID");
		defaultConfig.addEntry("ID.ItemIDRoot", "Root ID for Item IDs, 29000 by default", 29000);


		config = new TomlConfigHandler(MOD_ID, new Toml("Better Than Warfare config file"), false);
		File configFile = config.getConfigFile();

		if (config.getConfigFile().exists()) {
			config.loadConfig();
			config.setDefaults(config.getRawParsed());
		} else {
			config.setDefaults(defaultConfig);
			try {
				configFile.getParentFile().mkdirs();
				configFile.createNewFile();

				config.writeConfig();
				config.loadConfig();
			} catch (IOException e) {
				throw new RuntimeException("Failed to generate config!", e);
			}
		}
	}

	public static int getItemIDRoot() {
		return config.getInt("ID.ItemIDRoot");
	}
}
