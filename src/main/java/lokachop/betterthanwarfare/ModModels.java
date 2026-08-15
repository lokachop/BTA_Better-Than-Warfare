package lokachop.betterthanwarfare;

import lokachop.betterthanwarfare.entities.ProjectileBullet;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.entity.EntityRendererSprite;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.block.BlockLogicActivator;

import static net.minecraft.client.render.item.model.ItemModelDispatcher.*;

public class ModModels {
	public void initItemModels(ItemModelDispatcher dispatcher) {
		BetterThanWarfareMod.LOGGER.info("Generating item models...");

		ModItems.itemTextures.forEach((item, texture) -> {
			ItemModelStandard model = new ItemModelStandard(item, false);
			model.setIcon(BetterThanWarfareMod.ID("item/" + texture));
			dispatcher.addDispatch(model);
		});

		ModItems.gunTextures.forEach((item, texture) -> {
			ItemModelStandard model = new ItemModelStandard(item, false);
			model.setIcon(BetterThanWarfareMod.ID("item/" + texture));
			model.setDisplayPos("firstperson_righthand", HANDHELD_FIRST_PERSON_RIGHT_HAND)
				.setDisplayPos("firstperson_lefthand", HANDHELD_FIRST_PERSON_LEFT_HAND)
				.setDisplayPos("thirdperson_righthand", HANDHELD_THIRD_PERSON_RIGHT_HAND)
				.setDisplayPos("thirdperson_lefthand", HANDHELD_THIRD_PERSON_LEFT_HAND);
			dispatcher.addDispatch(model);
		});

		ModItems.meleeTextures.forEach((item, texture) -> {
			ItemModelStandard model = new ItemModelStandard(item, false);
			model.setIcon(BetterThanWarfareMod.ID("item/" + texture));
			model.setDisplayPos("firstperson_righthand", HANDHELD_FIRST_PERSON_RIGHT_HAND)
				.setDisplayPos("firstperson_lefthand", HANDHELD_FIRST_PERSON_LEFT_HAND)
				.setDisplayPos("thirdperson_righthand", HANDHELD_THIRD_PERSON_RIGHT_HAND)
				.setDisplayPos("thirdperson_lefthand", HANDHELD_THIRD_PERSON_LEFT_HAND);
			dispatcher.addDispatch(model);
		});
	}

	public void initEntityModels(EntityRendererDispatcher dispatcher) {
		dispatcher.assignRenderer(ProjectileBullet.class, new EntityRendererSprite<>(ModItems.BulletIconItem));
	}
}
