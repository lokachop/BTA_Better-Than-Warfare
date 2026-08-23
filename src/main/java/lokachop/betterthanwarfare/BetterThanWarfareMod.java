package lokachop.betterthanwarfare;

import lokachop.betterthanwarfare.entities.netentries.NetEntryProjectileBullet;
import lokachop.betterthanwarfare.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.sound.SoundRepository;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.net.entity.NetEntityHandler;
import net.minecraft.core.sound.SoundTypes;
import net.minecraft.core.util.collection.NamespaceID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;

public class BetterThanWarfareMod implements ModInitializer {
	public static final String MOD_ID = "betterthanwarfare";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static NamespaceID ID(String id) {
		return NamespaceID.fromPool(MOD_ID, id);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("BetterThanWarfare initializing...");
		HalpLibe.registerMod(MOD_ID);

		new ModConfig();

		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		//CommonEvents.AFTER_BLOCK_INIT.listen(Key.of(MOD_ID), this::afterBlockInit);
		//CommonEvents.AFTER_ITEM_INIT.listen(Key.of(MOD_ID), this::afterItemInit);
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), this::initRecipeNamespaces);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), this::onRecipesReady);

		LOGGER.info("BetterThanWarfare initialized!");
	}

	public void onRecipesReady() {
		ModRecipes.InitRecipes();
	}

	public void initRecipeNamespaces() {
		ModRecipes.InitNamespaces();
	}

	public void beforeGameStart() {
		LOGGER.info("BGS, Before Game Start...");
		ModItems.RegisterItems();
		SoundTypes.loadSoundsJson(MOD_ID);

		NetEntityHandler.registerNetworkEntry(new NetEntryProjectileBullet(), 500);
		LOGGER.info("BGS, OK!");
	}

	public void afterGameStart() {

	}
}
