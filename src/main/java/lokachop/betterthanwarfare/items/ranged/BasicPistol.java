package lokachop.betterthanwarfare.items.ranged;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class BasicPistol extends BaseGun {
	public BasicPistol(String translationKey, String namespaceId, int itemId) {
		super(translationKey, namespaceId, itemId);
	}

	@Override
	public int getClipSize() {
		return 12;
	}

	@Override
	public int getShootDelay() {
		return 10; // 20tps, 10t is 0.5s
	}

	@Override
	public int getReloadDelay() {
		return 20; // 20tps, 40t is 2s
	}

	@Override
	public String getAmmoType() {
		return MOD_ID + ":BasicAmmo";
	}

	@Override
	public String getSoundType() {
		return "generic";
	}

	public int getBulletDamage() {
		return 6;
	}

	@Override
	public float getBulletVelocity() {
		return 3.0f;
	}

	@Override
	public float getBulletSpread() {
		return 0.5f;
	}
}
