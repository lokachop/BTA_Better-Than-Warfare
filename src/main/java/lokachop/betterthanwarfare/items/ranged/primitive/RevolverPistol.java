package lokachop.betterthanwarfare.items.ranged.primitive;

import lokachop.betterthanwarfare.items.ranged.BaseGun;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class RevolverPistol extends BaseGun {
	public RevolverPistol(String translationKey, String namespaceId, int itemId) {
		super(translationKey, namespaceId, itemId);
	}

	@Override
	public int getClipSize() {
		return 6;
	}

	@Override
	public int getShootDelay() {
		return 5;
	}

	@Override
	public int getReloadDelay() {
		return 40;
	}

	@Override
	public String getAmmoType() {
		return MOD_ID + ":RevolverAmmo";
	}

	@Override
	public String getSoundType() {
		return "revolver";
	}

	@Override
	public int getBulletDamage() {
		return 8;
	}

	@Override
	public float getBulletVelocity() {
		return 3.5f;
	}

	@Override
	public float getBulletSpread() {
		return 1.2f;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilPitch() {
		return -6;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilYaw() {
		return (float) (Math.random() * 3) - 1.5f;
	}
}
