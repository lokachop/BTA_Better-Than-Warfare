package lokachop.betterthanwarfare.items.ranged.modern;

import lokachop.betterthanwarfare.items.ranged.BaseGun;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class AKRifle extends BaseGun {
	public AKRifle(String translationKey, String namespaceId, int itemId) {
		super(translationKey, namespaceId, itemId);
	}

	@Override
	public int getClipSize() {
		return 30;
	}

	@Override
	public int getShootDelay() {
		return 2;
	}

	@Override
	public int getReloadDelay() {
		return 35;
	}

	@Override
	public String getAmmoType() {
		return MOD_ID + ":AKAmmo";
	}

	@Override
	public String getSoundType() {
		return "ak";
	}

	@Override
	public int getBulletDamage() {
		return 4;
	}

	@Override
	public float getBulletVelocity() {
		return 5.0f;
	}

	@Override
	public float getBulletSpread() {
		return 2.0f;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilPitch() {
		return -2;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilYaw() {
		return (float) (Math.random() * 2) - 1;
	}
}
