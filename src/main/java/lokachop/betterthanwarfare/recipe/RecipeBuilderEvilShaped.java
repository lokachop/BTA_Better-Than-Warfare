package lokachop.betterthanwarfare.recipe;

import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.IItemConvertible;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;

public class RecipeBuilderEvilShaped extends RecipeBuilderShaped {
	public RecipeBuilderEvilShaped(String modID) {
		super(modID);
	}

	public RecipeBuilderEvilShaped(String modID, String... shape) {
		super(modID, shape);
	}

	@Override
	public RecipeBuilderEvilShaped setShape(String... shapeTemplate) {
		return (RecipeBuilderEvilShaped) super.setShape(shapeTemplate);
	}


	@Override
	public RecipeBuilderEvilShaped addInput(char templateSymbol, ItemStack stack) {
		return (RecipeBuilderEvilShaped) super.addInput(templateSymbol, stack);
	}

	@Override
	public RecipeBuilderEvilShaped addInput(char templateSymbol, IItemConvertible stack) {
		return (RecipeBuilderEvilShaped) super.addInput(templateSymbol, stack);
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
