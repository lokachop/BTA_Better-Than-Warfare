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
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;

import java.util.List;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;


public class ModRecipes extends RecipeRegistry {
	public static final RecipeNamespace BETTERTHANWARFARE = new RecipeNamespace();

	public static void InitRecipes() {
		// iron plate
		ItemStack ironPlates = ModItems.PlateIronItem.getDefaultStack();
		ironPlates.stackSize = 3;
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"III")
			.addInput('I', Items.INGOT_IRON)
			.create("IronPlateRecipe", ironPlates);

		// steel plate
		ItemStack steelPlates = ModItems.PlateSteelItem.getDefaultStack();
		steelPlates.stackSize = 3;
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"SSS")
			.addInput('S', Items.INGOT_STEEL)
			.create("SteelPlateRecipe", steelPlates);

		// gold plate
		ItemStack goldPlates = ModItems.PlateGoldItem.getDefaultStack();
		goldPlates.stackSize = 3;
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"GGG")
			.addInput('G', Items.INGOT_GOLD)
			.create("GoldPlateRecipe", goldPlates);

		// steel cylinder
		ItemStack steelCylinders = ModItems.CylinderSteelItem.getDefaultStack();
		steelCylinders.stackSize = 4;
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"SS",
				"SS")
			.addInput('S', Items.INGOT_STEEL)
			.create("SteelCylinderRecipe", steelCylinders);


		// revolver
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"DXX",
				"BCS",
				"  G")
			.addInput('D', Items.DIAMOND)
			.addInput('X', Items.INGOT_STEEL_CRUDE)
			.addInput('B', ModItems.RevolverCraftingBarrelItem)
			.addInput('C', ModItems.RevolverCraftingCylinderItem)
			.addInput('S', Items.INGOT_STEEL)
			.addInput('G', ModItems.RevolverCraftingGripItem)
			.create("RevolverRecipe", ModItems.RevolverPistolItem.getDefaultStack());

		// sulfur recipes
		ItemStack tinySulphur = ModItems.DustTinySulphurItem.getDefaultStack();
		tinySulphur.stackSize = 6;
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(Items.GUNPOWDER)
			.create("TinySulfurRecipe", tinySulphur);

		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"SS",
				"SS",
				"SS")
			.addInput('S', ModItems.DustTinySulphurItem)
			.create("TinySulfurCompact", Items.GUNPOWDER.getDefaultStack());

		// pouch recipes
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				" LS",
				"LLL",
				" L ")
			.addInput('S', Items.STRING)
			.addInput('L', Items.LEATHER)
			.create("EmptyLeatherPouchRecipe", ModItems.LeatherPouchEmptyItem.getDefaultStack());

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(ModItems.LeatherPouchEmptyItem)
			.addInput(ModItems.DustTinySulphurItem)
			.addInput(ModItems.DustTinySulphurItem)
			.create("FillLeatherPouchRecipe", ModItems.LeatherSulfurPouchItem);

		// REVOLVER items
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"WW ",
				" WW",
				" WW")
			.addInput('W', "minecraft:planks")
			.create("RevolverGripRecipe", ModItems.RevolverCraftingGripItem.getDefaultStack());

		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"PPP",
				"SSS",
				"PPP")
			.addInput('P', ModItems.PlateSteelItem)
			.addInput('S', Items.INGOT_STEEL)
			.create("RevolverBarrelRecipe", ModItems.RevolverCraftingBarrelItem.getDefaultStack());

		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"PP",
				"CC",
				"PP")
			.addInput('P', ModItems.PlateSteelItem)
			.addInput('C', ModItems.CylinderSteelItem)
			.create("RevolverCylinderRecipe", ModItems.RevolverCraftingCylinderItem.getDefaultStack());


		// FLINTLOCK recipe
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"IP ",
				" IP",
				"  G")
			.addInput('I', Items.INGOT_IRON)
			.addInput('P', ModItems.PlateIronItem)
			.addInput('G', ModItems.RevolverCraftingGripItem)
			.create("FlintlockRecipe", ModItems.FlintlockPistolItem.getDefaultStack());


		// BLUNDERBUSS recipe
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"PPI",
				"IIW",
				"  G")
			.addInput('I', Items.INGOT_STEEL)
			.addInput('P', ModItems.PlateIronItem)
			.addInput('G', ModItems.RevolverCraftingGripItem)
			.addInput('W', "minecraft:planks")
			.create("BlunderbussRecipe", ModItems.BlunderbussRifleItem.getDefaultStack());

		// AMMO, revolver
		ItemStack revolverCasing = ModItems.RevolverCraftingCasingItem.getDefaultStack();
		revolverCasing.stackSize = 12;
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"P",
				"P")
			.addInput('P', ModItems.PlateGoldItem)
			.create("RevolverCasingRecipe", revolverCasing);

		ItemStack revolverBullet = ModItems.RevolverCraftingBulletItem.getDefaultStack();
		revolverBullet.stackSize = 4;
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"XC")
			.addInput('X', Items.INGOT_STEEL_CRUDE)
			.addInput('C', ModItems.CylinderSteelItem)
			.create("RevolverBulletRecipe", revolverBullet);

		ItemStack revolverAmmo = ModItems.RevolverAmmoItem.getDefaultStack();
		revolverAmmo.stackSize = 4;
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"B",
				"G",
				"C")
			.addInput('B', ModItems.RevolverCraftingBulletItem)
			.addInput('G', ModItems.DustTinySulphurItem)
			.addInput('C', ModItems.RevolverCraftingCasingItem)
			.create("RevolverAmmoRecipe", revolverAmmo);

		ItemStack ironBalls = ModItems.IronBallsItem.getDefaultStack();
		ironBalls.stackSize = 6;
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				" P ",
				"PIP",
				" P ")
			.addInput('P', ModItems.PlateIronItem)
			.addInput('I', Items.INGOT_IRON)
			.create("IronBallsRecipe", ironBalls);
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
