package lokachop.betterthanwarfare;

import lokachop.betterthanwarfare.items.ranged.AKRifle;
import lokachop.betterthanwarfare.items.ranged.BasicPistol;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemBuilder;

import java.util.HashMap;

public class ModItems {
	public static HashMap<Item, String> itemTextures = new HashMap<>(); // stealing the homework...
	public static HashMap<Item, String> weaponTextures = new HashMap<>();

	private static int lastItemID = 29000;
	private static int newItemID() {
		return lastItemID++;
	}

	private static String namespaceConvert(String name) {
		return BetterThanWarfareMod.MOD_ID + ":" + name;
	}

	private static final ItemBuilder genericItemBuilder = new ItemBuilder(BetterThanWarfareMod.MOD_ID);
	public static Item basicItem(String name, String texture) {
		Item item = new Item(name, BetterThanWarfareMod.MOD_ID + ":" + name, newItemID());

		itemTextures.put(item, texture);
		return genericItemBuilder.build(item);
	}

	public static Item basicWeapon(Item item, String texture) {
		itemTextures.put(item, texture);
		return genericItemBuilder.build(item);
	}

	public static Item BasicPistolItem;
	public static Item ReceiverItem;
	public static Item BasicAmmoItem;

	public static Item AKRifleItem;
	public static Item AKAmmoItem;
	public static void RegisterItems() {
		BetterThanWarfareMod.LOGGER.info("Registering items...");

		BasicPistolItem = basicWeapon(new BasicPistol("BasicPistol", namespaceConvert("BasicPistol"), newItemID()), "basicpistol");
		ReceiverItem = basicItem("Receiver", "receiver");
		BasicAmmoItem = basicItem("BasicAmmo", "basicammo");

		AKRifleItem = basicWeapon(new AKRifle("AKRifle", namespaceConvert("AKRifle"), newItemID()), "ak");
		AKAmmoItem = basicItem("AKAmmo", "akbullets");

		BetterThanWarfareMod.LOGGER.info("Done, {} IDs spent...", lastItemID - 29000);
	}
}
