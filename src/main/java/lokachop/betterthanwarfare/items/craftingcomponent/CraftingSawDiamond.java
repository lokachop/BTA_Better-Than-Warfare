package lokachop.betterthanwarfare.items.craftingcomponent;

import net.minecraft.core.item.Item;

public class CraftingSawDiamond extends Item {
	public CraftingSawDiamond(String translationKey, String namespaceId, int id) {
		super(translationKey, namespaceId, id);
		this.maxStackSize = 1;
		this.setMaxDamage(1024); // 1024 uses per diamond saw
	}
}
