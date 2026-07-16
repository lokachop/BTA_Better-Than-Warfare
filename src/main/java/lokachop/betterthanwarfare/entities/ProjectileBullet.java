package lokachop.betterthanwarfare.entities;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.projectile.Projectile;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;

public class ProjectileBullet extends Projectile {
	public ProjectileBullet(World world, Mob owner, Vec3 pos, Vec3 dir, int damage, float speed, float spread) {
		super(world, owner);
		this.setPos(pos.x, pos.y, pos.z);
		this.setHeading(dir.x, dir.y, dir.z, speed, spread);
		this.damage = damage;
	}

	@Override
	protected void initProjectile() {
		super.initProjectile();
	}
}
