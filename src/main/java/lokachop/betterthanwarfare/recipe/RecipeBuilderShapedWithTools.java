package lokachop.betterthanwarfare.recipe;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.IItemConvertible;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;

public class RecipeBuilderShapedWithTools extends RecipeBuilderShaped {
	public RecipeBuilderShapedWithTools(String modID) {
		super(modID);
	}

	public RecipeBuilderShapedWithTools(String modID, String... shape) {
		super(modID, shape);
	}

	@Override
	public RecipeBuilderShapedWithTools setShape(String... shapeTemplate) {
		return (RecipeBuilderShapedWithTools) super.setShape(shapeTemplate);
	}


	@Override
	public RecipeBuilderShapedWithTools addInput(char templateSymbol, ItemStack stack) {
		return (RecipeBuilderShapedWithTools) super.addInput(templateSymbol, stack);
	}

	@Override
	public RecipeBuilderShapedWithTools addInput(char templateSymbol, IItemConvertible stack) {
		return (RecipeBuilderShapedWithTools) super.addInput(templateSymbol, stack);
	}

	@Override
	@SuppressWarnings({"unchecked", "unused"})
	public void create(String recipeID, ItemStack outputStack) {
		if (shape == null) throw new RuntimeException("Shaped recipe: " + recipeID + " attempted to build without a assigned shape!!");
		RecipeSymbol[] recipe = new RecipeSymbol[height * width];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Character cha = null;
				if (shape[y].length() > x) {
					cha = shape[y].charAt(x);
				}
				RecipeSymbol tempplate = symbolShapedMap.get(cha);
				if (tempplate == null){
					recipe[x + y * width] = null;
				} else {
					if(tempplate.getItemGroup() == null){
						RecipeSymbol s =  new RecipeSymbol(cha == null ? ' ' : cha, tempplate.getStack());
						recipe[x + y * width] = s;
					} else {
						recipe[x + y * width] = new RecipeSymbol(cha == null ? ' ' : cha, tempplate.getStack(), tempplate.getItemGroup());
					}

				}

			}
		}
		((RecipeGroup<RecipeEntryCrafting<?, ?>>) RecipeBuilder.getRecipeGroup(modID, "workbench", new RecipeSymbol(Blocks.WORKBENCH.getDefaultStack())))
			.register(recipeID, new RecipeEntryCraftingShapedWithTools(width, height, recipe, outputStack));
	}


	public RecipeSymbol[] getRecipe() {
		if (shape == null) throw new RuntimeException("Evil recipe builder attempted to build without a assigned shape!!");
		RecipeSymbol[] recipe = new RecipeSymbol[height * width];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Character cha = null;
				if (shape[y].length() > x) {
					cha = shape[y].charAt(x);
				}
				RecipeSymbol tempplate = symbolShapedMap.get(cha);
				if (tempplate == null){
					recipe[x + y * width] = null;
				} else {
					if(tempplate.getItemGroup() == null){
						RecipeSymbol s =  new RecipeSymbol(cha == null ? ' ' : cha, tempplate.getStack());
						recipe[x + y * width] = s;
					} else {
						recipe[x + y * width] = new RecipeSymbol(cha == null ? ' ' : cha, tempplate.getStack(), tempplate.getItemGroup());
					}

				}

			}
		}

		return recipe;
	}
}
