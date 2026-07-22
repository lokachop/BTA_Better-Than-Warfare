package lokachop.betterthanwarfare;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.util.dependency.Key;

import java.io.IOException;
import java.net.URISyntaxException;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class BetterThanWarfareClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID + "|client");

	@Override
	public void onInitializeClient() {
		try {
			TextureRegistry.initializeAllFiles(MOD_ID, TextureRegistry.worldAtlas, true);
			TextureRegistry.initializeAllFiles(MOD_ID, TextureRegistry.guiSpriteAtlas, true);
			TextureRegistry.initializeAllFiles(MOD_ID, TextureRegistry.artAtlas, true);
		} catch (URISyntaxException | IOException e) {
			throw new RuntimeException(e);
		}

		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(MOD_ID),(t)-> new ModModels().initItemModels(t));
		ClientEvents.ENTITY_RENDERER_RELOAD.listen(Key.of(MOD_ID),(t)-> new ModModels().initEntityModels(t));
	}
}
