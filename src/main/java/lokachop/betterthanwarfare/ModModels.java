package lokachop.betterthanwarfare;

import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.collection.NamespaceID;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;


public class ModModels implements ModelEntrypoint {

	@Override
	public void initBlockModels(BlockModelDispatcher dispatcher) {

	}

	@Override
	public void initItemModels(ItemModelDispatcher dispatcher) {
		BetterThanWarfareMod.LOGGER.info("Generating item models...");

		ModItems.itemTextures.forEach((item, texture) -> {
			ModelHelper.setItemModel(item, () -> {
				ItemModelStandard model = new ItemModelStandard(item, BetterThanWarfareMod.MOD_ID);

				model.icon = TextureRegistry.getTexture(NamespaceID.getTemp(BetterThanWarfareMod.MOD_ID, "item/" + texture));
				return model;
			});
		});

		ModItems.weaponTextures.forEach((item, texture) -> {
			ModelHelper.setItemModel(item, () -> {
				ItemModelStandard model = new ItemModelStandard(item, BetterThanWarfareMod.MOD_ID);
				model.setFull3D();

				model.icon = TextureRegistry.getTexture(NamespaceID.getTemp(BetterThanWarfareMod.MOD_ID, "item/" + texture));
				return model;
			});
		});
	}

	@Override
	public void initEntityModels(EntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher dispatcher) {

	}
}
