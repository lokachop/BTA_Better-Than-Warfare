package lokachop.betterthanwarfare;

import lokachop.betterthanwarfare.items.ammo.LeatherSulfurPouch;
import lokachop.betterthanwarfare.items.melee.admin.KnightSword;
import lokachop.betterthanwarfare.items.ranged.modern.AKRifle;
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

	public static Item PistolItem;
	public static Item ReceiverItem;
	public static Item PistolAmmoItem;

	public static Item AKRifleItem;
	public static Item AKAmmoItem;

	public static Item IronBallsItem;
	public static Item LeatherSulfurPouchItem;
	public static Item FlintlockPistolItem;
	public static Item BlunderbussRifleItem;
	public static Item RevolverAmmoItem;
	public static Item RevolverPistolItem;

	public static Item KnightSwordItem;
	public static void RegisterItems() {
		BetterThanWarfareMod.LOGGER.info("Registering items...");

		PistolItem = basicGun(new Pistol("Pistol", namespaceConvert("Pistol"), newItemID()), "gun/modern/pistol");
		ReceiverItem = basicItem("Receiver", "receiver");
		PistolAmmoItem = basicItem("PistolAmmo", "ammo/modern/pistol_ammo");

		AKRifleItem = basicGun(new AKRifle("AKRifle", namespaceConvert("AKRifle"), newItemID()), "gun/modern/ak");
		AKAmmoItem = basicItem("AKAmmo", "ammo/modern/ak_ammo");

		IronBallsItem = basicItem("IronBalls", "ammo/primitive/iron_balls");
		LeatherSulfurPouchItem = advancedItem(new LeatherSulfurPouch("LeatherSulfurPouch", namespaceConvert("LeatherSulfurPouch"), newItemID()), "ammo/primitive/leather_pouch");
		FlintlockPistolItem = basicGun(new FlintlockPistol("FlintlockPistol", namespaceConvert("FlintlockPistol"), newItemID()), "gun/primitive/flintlock");
		BlunderbussRifleItem = basicGun(new BlunderbussRifle("BlunderbussRifle", namespaceConvert("BlunderbussRifle"), newItemID()), "gun/primitive/blunderbuss");

		RevolverAmmoItem = basicItem("RevolverAmmo", "ammo/primitive/revolver_ammo");
		RevolverPistolItem = basicGun(new RevolverPistol("RevolverPistol", namespaceConvert("RevolverPistol"), newItemID()), "gun/primitive/revolver");

		KnightSwordItem = basicMelee(new KnightSword("KnightSword", namespaceConvert("KnightSword"), newItemID()), "melee/admin/knight_sword");


		BetterThanWarfareMod.LOGGER.info("Done, {} IDs spent...", lastItemID - 29000);
	}
}
