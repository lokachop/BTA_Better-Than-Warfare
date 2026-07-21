package lokachop.betterthanwarfare.mixin;

import lokachop.betterthanwarfare.interfaces.INoCooldownItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class, remap = false)
public class NoCooldownMixin {
	@Shadow
	private int mouseTicksRan;

	@Shadow
	public PlayerLocal thePlayer;

	@Inject(method = "clickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/controller/PlayerController;useItemStackOnNothing(Lnet/minecraft/core/entity/player/Player;Lnet/minecraft/core/world/World;Lnet/minecraft/core/item/ItemStack;)Z", shift = At.Shift.AFTER))
	private void clickNukeCooldown(CallbackInfo callbackInfo) {
		ItemStack itemstack = this.thePlayer.inventory.getCurrentItem();

		if(itemstack != null && itemstack.getItem() instanceof INoCooldownItem) {
			this.mouseTicksRan = 0;
		}
	}
}
