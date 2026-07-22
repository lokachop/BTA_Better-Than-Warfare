package lokachop.betterthanwarfare.renderer;

import lokachop.betterthanwarfare.entities.ProjectileBullet;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.LOGGER;

public class EntityRendererBullet extends EntityRenderer<ProjectileBullet> {
	public EntityRendererBullet() {}

	@Override
	public void render(@NotNull TessellatorGeneral tessellator, @NotNull ProjectileBullet bullet, double x, double y, double z, float yaw, float partialTick) {
		this.bindTexture("/assets/betterthanwarfare/textures/entity/bullets.png");

		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glRotatef(bullet.yRotO + (bullet.yRot - bullet.yRotO) * partialTick - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(bullet.xRotO + (bullet.xRot - bullet.xRotO) * partialTick, 0.0F, 0.0F, 1.0F);
		float bodyMinU = 0.0F;
		float bodyMaxU = (float)(5) / 32.0F;
		float bodyMinV = (float)(0) / 32.0F;
		float bodyMaxV = (float)(5) / 32.0F;

		float tailMinU = 0.0F;
		float tailMaxU = (float)(5) / 32.0F;
		float tailMinV = (float)(5) / 32.0F;
		float tailMaxV = (float)(10) / 32.0F;
		float scale = 0.05625F;
		GL11.glEnable(32826);

		GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
		GL11.glNormal3f(scale, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-0.0F, -2.0F, -2.0F, tailMinU, tailMinV);
		tessellator.addVertexWithUV(-0.0F, -2.0F, 2.0F, tailMaxU, tailMinV);
		tessellator.addVertexWithUV(-0.0F, 2.0F, 2.0F, tailMaxU, tailMaxV);
		tessellator.addVertexWithUV(-0.0F, 2.0F, -2.0F, tailMinU, tailMaxV);
		tessellator.draw();
		GL11.glNormal3f(-scale, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-0.0F, 2.0F, -2.0F, tailMinU, tailMinV);
		tessellator.addVertexWithUV(-0.0F, 2.0F, 2.0F, tailMaxU, tailMinV);
		tessellator.addVertexWithUV(-0.0F, -2.0F, 2.0F, tailMaxU, tailMaxV);
		tessellator.addVertexWithUV(-0.0F, -2.0F, -2.0F, tailMinU, tailMaxV);
		tessellator.draw();

		for(int i = 0; i < 4; ++i) {
			GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
			GL11.glNormal3f(0.0F, 0.0F, scale);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-2.0F, -2.0F, 0.0F, bodyMinU, bodyMinV);
			tessellator.addVertexWithUV(2.0F, -2.0F, 0.0F, bodyMaxU, bodyMinV);
			tessellator.addVertexWithUV(2.0F, 2.0F, 0.0F, bodyMaxU, bodyMaxV);
			tessellator.addVertexWithUV(-2.0F, 2.0F, 0.0F, bodyMinU, bodyMaxV);
			tessellator.draw();
		}

		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}
}
