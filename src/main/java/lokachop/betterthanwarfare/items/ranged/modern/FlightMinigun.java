package lokachop.betterthanwarfare.items.ranged.modern;

import lokachop.betterthanwarfare.items.ranged.MagGun;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class FlightMinigun extends MagGun {
	public FlightMinigun(String translationKey, String namespaceId, int id) {
		super(translationKey, namespaceId, id);
	}

	@Override
	public int getClipSize() {
		return 400;
	}

	@Override
	public int getShootDelay() {
		return 0;
	}

	@Override
	public int getReloadDelay() {
		return 120;
	}

	@Override
	public String getAmmoType() {
		return MOD_ID + ":MinigunAmmo";
	}

	@Override
	public String getSoundType() {
		return "minigun";
	}

	@Override
	public int getBulletDamage() {
		return 2;
	}

	@Override
	public float getBulletVelocity() {
		return 5.0f;
	}

	@Override
	public float getBulletSpread() {
		return 8.0f;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilPitch() {
		return 0;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilYaw() {
		return 0;
	}

	@Override
	public void onShoot(ItemStack itemstack, World world, Player player) {
		Vec3 eyeDir = player.getLookAngle();

		double speedMul = 0.1d;

		player.xd -= eyeDir.x * speedMul;
		player.yd -= eyeDir.y * speedMul;
		player.zd -= eyeDir.z * speedMul;
	}
}
