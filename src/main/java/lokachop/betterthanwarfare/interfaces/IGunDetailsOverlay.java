package lokachop.betterthanwarfare.interfaces;

import net.minecraft.client.gui.hud.HudIngame;
import net.minecraft.client.render.Font;
import net.minecraft.client.render.entity.EntityRendererItem;
import net.minecraft.core.entity.player.Player;

public interface IGunDetailsOverlay {
	void renderOverlay(HudIngame guiIngame, Player player, int height, int width, int mouseX, int mouseY, Font fontRenderer, EntityRendererItem itemRenderer);
}

