package lokachop.betterthanwarfare.items.ranged.primitive;

import lokachop.betterthanwarfare.items.ranged.BaseGun;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import org.joml.Vector3dc;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class BlunderbussRifle extends BaseGun {
	public BlunderbussRifle(String translationKey, String namespaceId, int itemId) {
		super(translationKey, namespaceId, itemId);
	}

	@Override
	public int getClipSize() {
		return 1;
	}

	@Override
	public int getShootDelay() {
		return 40;
	}

	@Override
	public int getReloadDelay() {
		return 80;
	}

	@Override
	public String getAmmoType() {
		return MOD_ID + ":IronBalls";
	}

	@Override
	public String getSoundType() {
		return "blunderbuss";
	}

	@Override
	public int getBulletDamage() {
		return 20;
	}

	@Override
	public float getBulletVelocity() {
		return 4.5f;
	}

	@Override
	public float getBulletSpread() {
		return 0.4f;
	}

	@Override
	public void onShoot(ItemStack itemstack, World world, Player player) {
		Vector3dc eyeDir = player.getViewVector(1.0f);

		double speedMul = 0.5d;

		assert eyeDir != null;
		player.xd -= eyeDir.x() * speedMul;
		player.yd -= eyeDir.y() * speedMul;
		player.zd -= eyeDir.z() * speedMul;
	}


	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilPitch() {
		return -30;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilYaw() {
		return (float) (Math.random() * 10) - 5;
	}
}
