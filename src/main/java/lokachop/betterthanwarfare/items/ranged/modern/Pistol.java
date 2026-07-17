package lokachop.betterthanwarfare.items.ranged.modern;

import lokachop.betterthanwarfare.items.ranged.BaseGun;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.MOD_ID;

public class Pistol extends BaseGun {
	public Pistol(String translationKey, String namespaceId, int itemId) {
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
		return MOD_ID + ":PistolAmmo";
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

	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilPitch() {
		return -6;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float calcRecoilYaw() {
		return (float) (Math.random() * 2) - 1;
	}
}
