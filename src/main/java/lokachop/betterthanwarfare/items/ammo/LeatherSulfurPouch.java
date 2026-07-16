package lokachop.betterthanwarfare.items.ammo;

import net.minecraft.core.item.Item;

public class LeatherSulfurPouch extends Item {
	public LeatherSulfurPouch(String translationKey, String namespaceId, int id) {
		super(translationKey, namespaceId, id);
		this.maxStackSize = 1;
		this.setMaxDamage(16); // 16 uses per small sulfur
	}
}
