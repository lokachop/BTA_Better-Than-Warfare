package lokachop.betterthanwarfare.items.melee.admin;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolSword;

public class KnightSword extends ItemToolSword {
	public KnightSword(String name, String namespaceId, int id) {
		super(name, namespaceId, id, ToolMaterial.diamond);
		this.setMaxDamage(0);
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, Mob target, Mob attacker) {
		assert target.world != null;
		target.world.playSoundAtEntity(attacker, target,  "betterthanwarfare:melee.knightsword", 1.0f, 1.0f);

		return true;
	}

	@Override
	public int getDamageVsEntity(Entity entity, ItemStack is) {
		return 999;
	}
}
