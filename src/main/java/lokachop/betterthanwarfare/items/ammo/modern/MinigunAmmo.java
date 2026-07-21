package lokachop.betterthanwarfare.items.ammo.modern;

import net.minecraft.core.item.Item;

public class MinigunAmmo extends Item {
	public MinigunAmmo(String translationKey, String namespaceId, int id) {
		super(translationKey, namespaceId, id);
		this.maxStackSize = 1;
		this.setMaxDamage(1600); // 1600 uses per minigun ammo
	}
}
