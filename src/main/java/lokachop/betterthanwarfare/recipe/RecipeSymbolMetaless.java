package lokachop.betterthanwarfare.recipe;

import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.ItemStack;

import java.util.List;
import java.util.Objects;

public class RecipeSymbolMetaless extends RecipeSymbol {
	public RecipeSymbolMetaless(char symbol, ItemStack stack) {
		super(symbol, stack);
	}

	public RecipeSymbolMetaless(char symbol, ItemStack stack, String itemGroup) {
		super(symbol, stack, itemGroup);
	}

	public boolean matchesIgnoreMeta(ItemStack stack) {
		if (stack == null) {
			return false;
		} else {
			List<ItemStack> stacks = this.resolve();
			boolean found = false;

			for(ItemStack resolvedStack : stacks) {
				if (resolvedStack.itemID == stack.itemID) {
					found = true;
					break;
				}
			}

			return found;
		}
	}
}
