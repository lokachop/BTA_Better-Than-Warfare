package lokachop.betterthanwarfare.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import lokachop.betterthanwarfare.BetterThanWarfareMod;
import lokachop.betterthanwarfare.interfaces.IGunDetailsOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.hud.HudIngame;
import net.minecraft.client.render.entity.EntityRendererItem;
import net.minecraft.client.render.font.Font;
import net.minecraft.client.render.font.FontRenderer;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HudIngame.class, remap = false)
public class HudIngameMixin {

	@Shadow
	protected Minecraft mc;

	@Shadow
	protected FontRenderer fontRenderer;

	@Unique
	public HudIngame thisAs = (HudIngame) (Object) this;

	@Unique
	public Gui gui = new Gui();

	@Unique
	private static final EntityRendererItem itemRenderer = new EntityRendererItem();

	// from https://github.com/MartinSVK12/signalindustries/blob/8.0/src/main/java/sunsetsatellite/signalindustries/mixin/HudIngameMixin.java#L66
	@Inject(
		method = "renderGameOverlay",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;setupScaledResolution()V", shift = At.Shift.AFTER)
	)
	public void renderAfterGameOverlay(float partialTicks, boolean flag, int mouseX, int mouseY, CallbackInfo ci) {
		int width = this.mc.resolution.getScaledWidthScreenCoords();
		int height = this.mc.resolution.getScaledHeightScreenCoords();
		ContainerInventory inv = this.mc.thePlayer.inventory;
		if (this.mc.thePlayer.inventory.getCurrentItem() != null) {
			if (this.mc.thePlayer.inventory.getCurrentItem().getItem() instanceof IGunDetailsOverlay) {
				((IGunDetailsOverlay) inv.getCurrentItem().getItem()).renderOverlay(thisAs, this.mc.thePlayer, height, width, mouseX, mouseY, gui, fontRenderer, itemRenderer);
			}
		}
	}

}
