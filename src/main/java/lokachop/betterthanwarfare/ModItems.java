package lokachop.betterthanwarfare;

import lokachop.betterthanwarfare.items.ammo.LeatherSulfurPouch;
import lokachop.betterthanwarfare.items.ammo.modern.MinigunAmmo;
import lokachop.betterthanwarfare.items.craftingcomponent.CraftingHammer;
import lokachop.betterthanwarfare.items.craftingcomponent.CraftingSaw;
import lokachop.betterthanwarfare.items.craftingcomponent.CraftingSawDiamond;
import lokachop.betterthanwarfare.items.melee.admin.KnightSword;
import lokachop.betterthanwarfare.items.ranged.modern.AKRifle;
import lokachop.betterthanwarfare.items.ranged.modern.FlightMinigun;
import lokachop.betterthanwarfare.items.ranged.modern.Pistol;
import lokachop.betterthanwarfare.items.ranged.primitive.BlunderbussRifle;
import lokachop.betterthanwarfare.items.ranged.primitive.FlintlockPistol;
import lokachop.betterthanwarfare.items.ranged.primitive.RevolverPistol;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemBuilder;

import java.util.HashMap;

public class ModItems {
	public static HashMap<Item, String> itemTextures = new HashMap<>(); // stealing the homework...
	public static HashMap<Item, String> gunTextures = new HashMap<>();
	public static HashMap<Item, String> meleeTextures = new HashMap<>();

	private static int lastItemID = 29000;
	private static int newItemID() {
		return lastItemID++;
	}

	private static String namespaceConvert(String name) {
		return BetterThanWarfareMod.MOD_ID + ":" + name;
	}

	private static final ItemBuilder genericItemBuilder = new ItemBuilder(BetterThanWarfareMod.MOD_ID);
	private static Item basicItem(String name, String texture) {
		Item item = new Item(name, BetterThanWarfareMod.MOD_ID + ":" + name, newItemID());

		itemTextures.put(item, texture);
		return genericItemBuilder.build(item);
	}
	private static Item advancedItem(Item item, String texture) {
		itemTextures.put(item, texture);
		return genericItemBuilder.build(item);
	}

	private static Item basicGun(Item item, String texture) {
		gunTextures.put(item, texture);
		return genericItemBuilder.build(item);
	}

	private static Item basicMelee(Item item, String texture) {
		meleeTextures.put(item, texture);
		return genericItemBuilder.build(item);
	}

	public static Item DustTinySulphurItem;
	public static Item PlateSteelItem;
	public static Item PlateIronItem;
	public static Item PlateGoldItem;
	public static Item CylinderSteelItem;

	public static Item CraftingSawItem;
	public static Item CraftingSawDiamondItem;
	public static Item CraftingHammerItem;

	public static Item PistolItem;
	public static Item ReceiverItem;
	public static Item PistolAmmoItem;

	public static Item AKRifleItem;
	public static Item AKAmmoItem;

	public static Item IronBallsItem;
	public static Item LeatherSulfurPouchItem;
	public static Item FlintlockPistolItem;
	public static Item BlunderbussRifleItem;

	public static Item RevolverCraftingBarrelItem;
	public static Item RevolverCraftingCylinderItem;
	public static Item RevolverCraftingGripItem;

	public static Item RevolverCraftingBulletItem;
	public static Item RevolverCraftingCasingItem;

	public static Item RevolverAmmoItem;
	public static Item RevolverPistolItem;

	public static Item KnightSwordItem;

	public static Item MinigunAmmoItem;
	public static Item MinigunMachinegunItem;
	public static void RegisterItems() {
		BetterThanWarfareMod.LOGGER.info("Registering items...");

		DustTinySulphurItem = basicItem("DustTinySulphur", "crafting_component/general/tiny_sulphur");
		PlateSteelItem = basicItem("PlateSteel", "crafting_component/general/steel_plate");
		PlateIronItem = basicItem("PlateIron", "crafting_component/general/iron_plate");
		PlateGoldItem = basicItem("PlateGold", "crafting_component/general/gold_plate");
		CylinderSteelItem = basicItem("CylinderSteel", "crafting_component/general/steel_cylinder");

		CraftingSawItem = advancedItem(new CraftingSaw("CraftingSaw", namespaceConvert("CraftingSaw"), newItemID()), "crafting_component/general/saw");
		CraftingSawDiamondItem = advancedItem(new CraftingSawDiamond("CraftingSawDiamond", namespaceConvert("CraftingSawDiamond"), newItemID()), "crafting_component/general/saw_diamond");
		CraftingHammerItem = advancedItem(new CraftingHammer("CraftingHammer", namespaceConvert("CraftingHammer"), newItemID()), "crafting_component/general/hammer");

		PistolItem = basicGun(new Pistol("Pistol", namespaceConvert("Pistol"), newItemID()), "gun/modern/pistol");
		ReceiverItem = basicItem("Receiver", "crafting_component/receiver");
		PistolAmmoItem = basicItem("PistolAmmo", "ammo/modern/pistol_ammo");

		AKRifleItem = basicGun(new AKRifle("AKRifle", namespaceConvert("AKRifle"), newItemID()), "gun/modern/ak");
		AKAmmoItem = basicItem("AKAmmo", "ammo/modern/ak_ammo");

		IronBallsItem = basicItem("IronBalls", "ammo/primitive/iron_balls");
		LeatherSulfurPouchItem = advancedItem(new LeatherSulfurPouch("LeatherSulfurPouch", namespaceConvert("LeatherSulfurPouch"), newItemID()), "ammo/primitive/leather_pouch");
		FlintlockPistolItem = basicGun(new FlintlockPistol("FlintlockPistol", namespaceConvert("FlintlockPistol"), newItemID()), "gun/primitive/flintlock");
		BlunderbussRifleItem = basicGun(new BlunderbussRifle("BlunderbussRifle", namespaceConvert("BlunderbussRifle"), newItemID()), "gun/primitive/blunderbuss");

		RevolverCraftingBarrelItem = basicItem("RevolverCraftingBarrel", "crafting_component/revolver/barrel");
		RevolverCraftingCylinderItem = basicItem("RevolverCraftingCylinder", "crafting_component/revolver/cylinder");
		RevolverCraftingGripItem = basicItem("RevolverCraftingGrip", "crafting_component/revolver/grip");
		RevolverCraftingBulletItem = basicItem("RevolverCraftingBullet", "crafting_component/revolver/bullet");
		RevolverCraftingCasingItem = basicItem("RevolverCraftingCasing", "crafting_component/revolver/casing");

		RevolverAmmoItem = basicItem("RevolverAmmo", "ammo/primitive/revolver_ammo");
		RevolverPistolItem = basicGun(new RevolverPistol("RevolverPistol", namespaceConvert("RevolverPistol"), newItemID()), "gun/primitive/revolver");

		KnightSwordItem = basicMelee(new KnightSword("KnightSword", namespaceConvert("KnightSword"), newItemID()), "melee/admin/knight_sword");

		MinigunAmmoItem = advancedItem(new MinigunAmmo("MinigunAmmo", namespaceConvert("MinigunAmmo"), newItemID()), "ammo/modern/minigun_ammo");
		MinigunMachinegunItem = basicGun(new FlightMinigun("FlightMinigun", namespaceConvert("FlightMinigun"), newItemID()), "gun/modern/minigun");

		BetterThanWarfareMod.LOGGER.info("Done, {} IDs spent...", lastItemID - 29000);
	}
}
