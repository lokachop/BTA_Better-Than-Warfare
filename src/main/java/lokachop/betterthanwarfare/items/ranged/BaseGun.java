package lokachop.betterthanwarfare.items.ranged;

import lokachop.betterthanwarfare.entities.ProjectileBullet;
import lokachop.betterthanwarfare.interfaces.IGunDetailsOverlay;
import lokachop.betterthanwarfare.util.RaycastUtil;
import net.minecraft.client.gui.hud.HudIngame;
import net.minecraft.client.render.Font;
import net.minecraft.client.render.entity.EntityRendererItem;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import net.minecraft.core.util.phys.HitResult;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;

import java.util.Arrays;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.LOGGER;

public abstract class BaseGun extends Item implements IGunDetailsOverlay {
	public BaseGun(String translationKey, String namespaceId, int id) {
		super(translationKey, namespaceId, id);
		this.maxStackSize = 1;
	}

	@Override
	public void renderOverlay(HudIngame guiIngame, Player player, int height, int width, int mouseX, int mouseY, Font fontRenderer, EntityRendererItem itemRenderer) {
		ContainerInventory inv = player.inventory;
		ItemStack gun = inv.getCurrentItem();
		int yCalc = height - 42;

		int xCalc = (width / 2) - 64 - 24;
		int currClip = gun.getData().getInteger("currClip");
		fontRenderer.drawStringWithShadow("Clip: ", xCalc, yCalc, 0xFFFFFFFF);
		fontRenderer.drawStringWithShadow(String.valueOf(currClip), xCalc + fontRenderer.getStringWidth("Clip: "), yCalc, currClip <= 0 ? 0xFFFF8080 : 0xFF80FF80);

		int xCalc2 = (width / 2) + 64;
		int currDelay = gun.getData().getInteger("delayTicks");
		fontRenderer.drawStringWithShadow("Delay: ", xCalc2, yCalc, 0xFFFFFFFF);
		fontRenderer.drawStringWithShadow(String.valueOf(currDelay), xCalc2 + fontRenderer.getStringWidth("Delay: "), yCalc, 0xFF8080FF);

		if(currDelay > 0) {
			int oX = (width / 2) - (currDelay / 2);
			int oY = (height / 2) + 8;
			int wCalc = currDelay;
			int hCalc = 1;
			guiIngame.drawRect(oX - 1, oY - 1, oX + wCalc + 1, oY + hCalc + 1, 0xFF202080);
			guiIngame.drawRect(oX, oY, oX + wCalc, oY + hCalc, 0xFF8080FF);
		}
	}

	public abstract int getClipSize();
	public abstract int getShootDelay(); // in ticks
	public abstract int getReloadDelay(); // in ticks
	public abstract String getAmmoType();
	public abstract String getSoundType();
	public abstract int getBulletDamage();
	public abstract float getBulletVelocity();
	public abstract float getBulletSpread();

	public void setDelay(ItemStack itemstack, int delay) {
		itemstack.getData().putInt("delayTicks", delay);
	}

	public boolean isDelayed(ItemStack itemstack) {
		return itemstack.getData().getInteger("delayTicks") > 0;
	}

	public boolean canShoot(ItemStack itemstack) {
		return itemstack.getData().getInteger("currClip") > 0;
	}

	public int spendAmmo(ItemStack itemstack) {
		int currClip = itemstack.getData().getInteger("currClip");
		itemstack.getData().putInt("currClip", --currClip);

		return currClip;
	}

	public int getCurrentClip(ItemStack itemstack) {
		return itemstack.getData().getInteger("currClip");
	}

	public void setCurrentClip(ItemStack itemstack, int clip) {
		itemstack.getData().putInt("currClip", clip);
	}

	private ItemStack doReload(ItemStack itemstack, World world, Player player) {
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
			world.playSoundAtEntity(null, player,  "betterthanwarfare:gun.reload." + this.getSoundType(), 1.0f, 1.0f);
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

				int ammoOnThatStack = item.stackSize;
				int maxCanTake = Math.min(reloadAmount, ammoOnThatStack);
				totalReloaded += maxCanTake;
				reloadAmount -= maxCanTake;
				inv.removeItem(i, maxCanTake);
			}
		}

		if(totalReloaded <= 0) {
			return itemstack;
		}

		this.setDelay(itemstack, this.getReloadDelay());
		this.setCurrentClip(itemstack, currClip + totalReloaded);
		world.playSoundAtEntity(null, player,  "betterthanwarfare:gun.reload." + this.getSoundType(), 1.0f, 1.0f);

		return itemstack;
	}

	private ItemStack doShoot(ItemStack itemstack, World world, Player player) {
		if(!this.canShoot(itemstack)) {
			world.playSoundAtEntity(null, player,  "betterthanwarfare:gun.dryfire." + this.getSoundType(), 1.0f, 1.0f);
			return itemstack;
		}

		this.setDelay(itemstack, this.getShootDelay());

		int currAmmo = this.spendAmmo(itemstack);
		world.playSoundAtEntity(null, player,  "betterthanwarfare:gun.shot." + this.getSoundType(), 1.0f, 1.0f + (float)(Math.random() * 0.5f));

		Vec3 eyePos = player.getPosition(1.f, true);
		Vec3 eyeDir = player.getViewVector(1.f);

		ProjectileBullet bullet = new ProjectileBullet(world, player, eyePos, eyeDir, this.getBulletDamage(), this.getBulletVelocity(), this.getBulletSpread());
		world.entityJoinedWorld(bullet);

		if(currAmmo == 0) {
			world.playSoundAtEntity(null, player,  "betterthanwarfare:gun.slide_open." + this.getSoundType(), 1.0f, 1.0f);
		}

		return itemstack;
	}


	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, Player player) {
		if(this.isDelayed(itemstack)) {
			return itemstack;
		}

		if (player.isSneaking()) {
			return this.doReload(itemstack, world, player);
		} else {
			return this.doShoot(itemstack, world, player);
		}
	}

	@Override
	public void	inventoryTick(ItemStack itemstack, World world, Entity entity, int slotId, boolean flag) {
		int delayTicks = itemstack.getData().getInteger("delayTicks");
		if(delayTicks > 0) {
			itemstack.getData().putInt("delayTicks", --delayTicks);
		}
	}
}
