package lokachop.betterthanwarfare.items.craftingcomponent;

import net.minecraft.core.item.Item;

public class CraftingSaw extends Item {
	public CraftingSaw(String translationKey, String namespaceId, int id) {
		super(translationKey, namespaceId, id);
		this.maxStackSize = 1;
		this.setMaxDamage(32); // 32 uses per iron saw
	}
}
