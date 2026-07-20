package lokachop.betterthanwarfare.items.craftingcomponent;

import net.minecraft.core.item.Item;

public class CraftingHammer extends Item {
	public CraftingHammer(String translationKey, String namespaceId, int id) {
		super(translationKey, namespaceId, id);
		this.maxStackSize = 1;
		this.setMaxDamage(32); // 32 uses per iron hammer
	}
}
