package lokachop.betterthanwarfare.recipe;

import lokachop.betterthanwarfare.ModItems;
import net.minecraft.client.gui.guidebook.crafting.GuidebookSectionCrafting;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeRegistry;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.RecipeBuilder;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;


public class ModRecipes extends RecipeRegistry {
	public static final RecipeNamespace BETTERTHANWARFARE = new RecipeNamespace();

	public static void InitRecipes() {
		// hammer recipe
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"II ",
				"IIS",
				"II ")
			.addInput('I', Items.INGOT_IRON)
			.addInput('S', Items.STICK)
			.create("CraftingHammerRecipe", ModItems.CraftingHammerItem.getDefaultStack());


		// revolver recipe
		/*
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"DXH",
				"BCS",
				"  G")
			.addInput('D', ModItems.CraftingSawDiamondItem)
			.addInput('X', Items.INGOT_STEEL_CRUDE)
			.addInput('H', ModItems.CraftingHammerItem)
			.addInput('B', ModItems.RevolverCraftingBarrelItem)
			.addInput('C', ModItems.RevolverCraftingCylinderItem)
			.addInput('S', Items.INGOT_STEEL)
			.addInput('G', ModItems.RevolverCraftingGripItem)
			.create("RevolverRecipe", ModItems.RevolverPistolItem.getDefaultStack());
		 */
	}

	public static void InitNamespaces() {
		final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH =
			new RecipeGroup<>(
				new RecipeSymbol(
					new ItemStack(Blocks.WORKBENCH)
				)
			);

		BETTERTHANWARFARE.register("workbench", WORKBENCH);
		RecipeBuilder.initNameSpace(MOD_ID);

		RecipeSymbol[] revolverRecipe = new RecipeBuilderEvilShaped(MOD_ID)
			.setShape(
				"DXH",
				"BCS",
				"  G")
			.addInput('D', ModItems.CraftingSawDiamondItem)
			.addInput('X', Items.INGOT_STEEL_CRUDE)
			.addInput('H', ModItems.CraftingHammerItem)
			.addInput('B', ModItems.RevolverCraftingBarrelItem)
			.addInput('C', ModItems.RevolverCraftingCylinderItem)
			.addInput('S', Items.INGOT_STEEL)
			.addInput('G', ModItems.RevolverCraftingGripItem)
			.getRecipe();

		Registries.RECIPES.addCustomRecipe(
			MOD_ID + ":workbench/RevolverRecipe",
			new RecipeEntryCraftingShapedWithTools(3, 3,
				revolverRecipe,
				ModItems.RevolverPistolItem.getDefaultStack()
			)
		);

		Registries.RECIPES.register(MOD_ID, BETTERTHANWARFARE);
	}

}
