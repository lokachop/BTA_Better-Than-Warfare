package lokachop.betterthanwarfare.entities;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.projectile.Projectile;
import net.minecraft.core.entity.projectile.ProjectileArrow;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import org.joml.Vector3dc;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.LOGGER;

public class ProjectileBullet extends Projectile {
	public ProjectileBullet(World world, Mob owner, Vector3dc pos, Vector3dc dir, int damage, float speed, float spread) {
		super(world, owner);
		this.setPos(pos.x(), pos.y(), pos.z());
		this.setHeading(dir.x(), dir.y(), dir.z(), speed, spread);
		this.damage = damage;
	}

	public ProjectileBullet(World world, double x, double y, double z, double xd, double yd, double zd) {
		super(world);
		this.setPos(x, y, z);
		this.setHeading(xd, yd, zd, 1.f, 0.f);
	}

	@Override
	protected void initProjectile() {
		super.initProjectile();
	}
}
