package lokachop.betterthanwarfare.items.ranged;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import net.minecraft.core.world.World;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.LOGGER;

public abstract class MagGun extends BaseGun {
	public MagGun(String translationKey, String namespaceId, int id) {
		super(translationKey, namespaceId, id);
	}

	@Override
	protected ItemStack doReload(ItemStack itemstack, World world, Player player) {
		// check if we're full
		if(itemstack.getData().getInteger("currClip") == this.getClipSize()) {
			return itemstack;
		}

		int currClip = this.getCurrentClip(itemstack);

		// chekc our owner's inv to see if we can reload
		String ammoType = this.getAmmoType();
		if(ammoType.equals("infinite")) {
			this.setDelay(itemstack, this.getReloadDelay());

			this.setCurrentClip(itemstack, this.getClipSize());
			world.playSoundAtEntity(player, player,  "betterthanwarfare:gun.reload." + this.getSoundType(), 1.0f, 1.0f);
			return itemstack;
		}

		int totalReloaded = 0;
		int reloadAmount = this.getClipSize() - currClip;
		if(player != null) {
			ContainerInventory inv = player.inventory;
			int invSz = inv.getContainerSize();

			for(int i = 0; i < invSz; i++) {
				ItemStack item = inv.getItem(i);
				if(item == null) {
					continue;
				}

				if(!item.getItem().namespaceID.toString().equals(ammoType)) {
					continue;
				}

				int ammoOnThatStack = item.getMaxDamage() - item.getMetadata();
				int maxCanTake = Math.min(reloadAmount, ammoOnThatStack);

				if(maxCanTake > 0) {
					totalReloaded += maxCanTake;
					reloadAmount -= maxCanTake;

					item.setMetadata(item.getMetadata() + maxCanTake);
				}
				LOGGER.info("{}, {}, {}, {}", item.getMetadata(), item.getMaxDamage(), ammoOnThatStack, maxCanTake);
				if(item.getMetadata() >= item.getMaxDamage()) {
					item.setMetadata(0);
					inv.removeItem(i, 1);
				}
			}
		}

		if(totalReloaded <= 0) {
			return itemstack;
		}

		this.setDelay(itemstack, this.getReloadDelay());
		this.setCurrentClip(itemstack, currClip + totalReloaded);
		world.playSoundAtEntity(player, player,  "betterthanwarfare:gun.reload." + this.getSoundType(), 1.0f, 1.0f);

		return itemstack;
	}

}
