package lokachop.betterthanwarfare.items.ranged.primitive;

import lokachop.betterthanwarfare.items.ranged.BaseGun;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class FlintlockPistol extends BaseGun {
	public FlintlockPistol(String translationKey, String namespaceId, int itemId) {
		super(translationKey, namespaceId, itemId);
	}

	@Override
	public int getClipSize() {
		return 1;
	}

	@Override
	public int getShootDelay() {
		return 20;
	}

	@Override
	public int getReloadDelay() {
		return 40;
	}

	@Override
	public String getAmmoType() {
		return MOD_ID + ":IronBalls";
	}

	@Override
	public String getSoundType() {
		return "flintlock";
	}

	@Override
	public int getBulletDamage() {
		return 8;
	}

	@Override
	public float getBulletVelocity() {
		return 3.0f;
	}

	@Override
	public float getBulletSpread() {
		return 1.0f;
	}
}
