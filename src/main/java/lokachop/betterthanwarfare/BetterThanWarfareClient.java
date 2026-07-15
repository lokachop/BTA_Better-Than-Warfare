package lokachop.betterthanwarfare;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class BetterThanWarfareClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID + "|client");

	@Override
	public void onInitializeClient() {
		try {
			TextureRegistry.initializeAllFiles(BetterThanWarfareMod.MOD_ID, TextureRegistry.blockAtlas, true);
			TextureRegistry.initializeAllFiles(BetterThanWarfareMod.MOD_ID, TextureRegistry.itemAtlas, true);
			TextureRegistry.initializeAllFiles(BetterThanWarfareMod.MOD_ID, TextureRegistry.artAtlas, true);
			TextureRegistry.initializeAllFiles(BetterThanWarfareMod.MOD_ID, TextureRegistry.particleAtlas, true);
		} catch (URISyntaxException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
