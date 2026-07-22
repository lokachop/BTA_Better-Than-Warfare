package lokachop.betterthanwarfare;

import lokachop.betterthanwarfare.entities.ProjectileBullet;
import lokachop.betterthanwarfare.renderer.EntityRendererBullet;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.entity.EntityRendererDefault;
import net.minecraft.client.render.entity.EntityRendererSprite;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.collection.NamespaceID;

public class ModModels {
	public void initItemModels(ItemModelDispatcher dispatcher) {
		BetterThanWarfareMod.LOGGER.info("Generating item models...");

		ModItems.itemTextures.forEach((item, texture) -> {
			ItemModelStandard model = new ItemModelStandard(item, false);
			model.setIcon(BetterThanWarfareMod.ID("item/" + texture));
			dispatcher.addDispatch(model);
		});

		ModItems.gunTextures.forEach((item, texture) -> {
			ItemModelStandard model = new ItemModelStandard(item, BetterThanWarfareMod.MOD_ID);
			model.setIcon(BetterThanWarfareMod.ID("item/" + texture));
			//model.setFull3D();
			dispatcher.addDispatch(model);
		});

		ModItems.meleeTextures.forEach((item, texture) -> {
			ItemModelStandard model = new ItemModelStandard(item, BetterThanWarfareMod.MOD_ID);
			model.setIcon(BetterThanWarfareMod.ID("item/" + texture));
			//model.setFull3D();
			dispatcher.addDispatch(model);
		});
	}

	public void initEntityModels(EntityRendererDispatcher dispatcher) {
		dispatcher.assignRenderer(ProjectileBullet.class, new EntityRendererSprite<>(ModItems.BulletIconItem));
	}
}
