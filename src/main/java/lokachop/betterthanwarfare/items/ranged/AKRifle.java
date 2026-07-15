package lokachop.betterthanwarfare.items.ranged;

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
}
