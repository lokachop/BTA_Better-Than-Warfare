package lokachop.betterthanwarfare;

import lokachop.betterthanwarfare.entities.netentries.NetEntryProjectileBullet;
import lokachop.betterthanwarfare.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.sound.SoundRepository;
import net.minecraft.core.net.entity.NetEntityHandler;
import net.minecraft.core.sound.SoundTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EnvironmentHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class BetterThanWarfareMod implements ModInitializer, RecipeEntrypoint, GameStartEntrypoint {
	public static final String MOD_ID = "betterthanwarfare";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		LOGGER.info("BetterThanWarfare initialized.");
	}

	@Override
	public void onRecipesReady() {
		ModRecipes.InitRecipes();
	}

	@Override
	public void initNamespaces() {
		ModRecipes.InitNamespaces();
	}

	@Override
	public void beforeGameStart() {
		ModItems.RegisterItems();

		if(!EnvironmentHelper.isServerEnvironment()) {
			SoundRepository.registerNamespace(MOD_ID);
		}
		SoundTypes.loadSoundsJson(MOD_ID);

		NetEntityHandler.registerNetworkEntry(new NetEntryProjectileBullet(), 500);
	}

	@Override
	public void afterGameStart() {}
}
