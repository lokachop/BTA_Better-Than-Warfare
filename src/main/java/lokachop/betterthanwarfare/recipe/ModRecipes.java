package lokachop.betterthanwarfare.recipe;

import lokachop.betterthanwarfare.ModItems;
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

import java.util.List;

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

		// iron plate
		new RecipeBuilderShapedWilthTools(MOD_ID)
			.setShape(
				"H",
				"I")
			.addInput('H', MOD_ID + ":crafting_hammers")
			.addInput('I', Items.INGOT_IRON)
			.create("IronPlateRecipe", ModItems.PlateIronItem.getDefaultStack());

		// steel plate
		new RecipeBuilderShapedWilthTools(MOD_ID)
			.setShape(
				"H",
				"I")
			.addInput('H', MOD_ID + ":crafting_hammers")
			.addInput('I', Items.INGOT_STEEL)
			.create("SteelPlateRecipe", ModItems.PlateSteelItem.getDefaultStack());

		// gold plate
		new RecipeBuilderShapedWilthTools(MOD_ID)
			.setShape(
				"H",
				"I")
			.addInput('H', MOD_ID + ":crafting_hammers")
			.addInput('I', Items.INGOT_GOLD)
			.create("GoldPlateRecipe", ModItems.PlateGoldItem.getDefaultStack());

		// iron saw recipe
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"PPS")
			.addInput('P', ModItems.PlateIronItem)
			.addInput('S', Items.STICK)
			.create("CraftingIronSawRecipe", ModItems.CraftingSawItem.getDefaultStack());

		// diamond saw recipe
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"DDS")
			.addInput('D', Items.DIAMOND)
			.addInput('S', Items.STICK)
			.create("CraftingDiamondSawRecipe", ModItems.CraftingSawDiamondItem.getDefaultStack());

		// steel cylinder
		new RecipeBuilderShapedWilthTools(MOD_ID)
			.setShape(
				"S",
				"I")
			.addInput('S', MOD_ID + ":crafting_saws")
			.addInput('I', Items.INGOT_STEEL)
			.create("SteelCylinderRecipe", ModItems.CylinderSteelItem.getDefaultStack());


		// revolver
		new RecipeBuilderShapedWilthTools(MOD_ID)
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
	}

	private static void registerLists() {
		// saw
		List<ItemStack> sawList = Registries.stackListOf(ModItems.CraftingSawDiamondItem.getDefaultStack());
		sawList.add(ModItems.CraftingSawItem.getDefaultStack());
		Registries.ITEM_GROUPS.register(MOD_ID + ":crafting_saws", sawList);

		// saw
		List<ItemStack> hammerList = Registries.stackListOf(ModItems.CraftingHammerItem.getDefaultStack());
		Registries.ITEM_GROUPS.register(MOD_ID + ":crafting_hammers", hammerList);
	}

	public static void InitNamespaces() {
		registerLists();


		final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH =
			new RecipeGroup<>(
				new RecipeSymbol(
					new ItemStack(Blocks.WORKBENCH)
				)
			);

		BETTERTHANWARFARE.register("workbench", WORKBENCH);
		Registries.RECIPES.register(MOD_ID, BETTERTHANWARFARE);
	}

}
