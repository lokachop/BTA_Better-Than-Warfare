package lokachop.betterthanwarfare.recipe;

import net.minecraft.core.data.registry.recipe.*;
import net.minecraft.core.data.registry.recipe.adapter.RecipeJsonAdapter;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShaped;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerCrafting;

import java.util.List;
import java.util.Objects;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.LOGGER;

public class RecipeEntryCraftingShapedWithTools extends RecipeEntryCraftingShaped {
	public RecipeEntryCraftingShapedWithTools(int recipeWidth, int recipeHeight, RecipeSymbol[] input, ItemStack output) {
		super(recipeWidth, recipeHeight, input, output);
	}

	public boolean matches(ContainerCrafting containerCrafting) {
		for(int _w = 0; _w <= 3 - this.recipeWidth; ++_w) {
			for(int _h = 0; _h <= 3 - this.recipeHeight; ++_h) {
				if (this.allowMirrored && this.isRecipeMatching(containerCrafting, _w, _h, true)) {
					return true;
				}

				if (this.isRecipeMatching(containerCrafting, _w, _h, false)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isRecipeMatching(ContainerCrafting inventory, int x, int y, boolean mirror) {
		for(int k = 0; k < 3; ++k) {
			for(int l = 0; l < 3; ++l) {
				int i1 = k - x;
				int j1 = l - y;
				RecipeSymbol symbol = null;
				if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight) {
					if (mirror) {
						symbol = ((RecipeSymbol[])this.getInput())[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
					} else {
						symbol = ((RecipeSymbol[])this.getInput())[i1 + j1 * this.recipeWidth];
					}
				}

				ItemStack itemstack1 = inventory.getItemStackAt(k, l);
				if (itemstack1 != null || symbol != null) {
					if (itemstack1 == null || symbol == null) {
						return false;
					}

					RecipeSymbolMetaless metaless = new RecipeSymbolMetaless(symbol.getSymbol(), symbol.getStack()); // this is awful but it's 03:38, and i want to make it work
					if (!metaless.matchesIgnoreMeta(itemstack1)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	@Override
	public ItemStack[] onCraftResult(ContainerCrafting containerCrafting) {
		ItemStack[] returnStack = new ItemStack[9];

		for(int i = 0; i < containerCrafting.getContainerSize(); ++i) {
			ItemStack itemstack1 = containerCrafting.getItem(i);
			if (itemstack1 != null) {
				if(itemstack1.isItemStackDamageable()) {
					ItemStack itemToDamage = containerCrafting.getItem(i);
					if(itemToDamage != null) {
						int newMeta = itemToDamage.getMetadata() + 1;

						itemToDamage.setMetadata(newMeta);
						if (newMeta > itemToDamage.getMaxDamage()) {
							int stacks = itemToDamage.stackSize;
							containerCrafting.removeItem(i, 1);

							if(stacks > 1) {
								itemToDamage.setMetadata(0);
							}
						}
					}
				} else {
					containerCrafting.removeItem(i, 1);
				}
			}
		}

		return returnStack;
	}
}
