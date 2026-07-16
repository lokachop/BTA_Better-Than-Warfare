package lokachop.betterthanwarfare.util;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.HitResult;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.chunk.ChunkSection;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static lokachop.betterthanwarfare.BetterThanWarfareMod.LOGGER;

public class RaycastUtil {
	private static double dist3D(double x, double y, double z, Vec3 pos) {
		return Math.sqrt(Math.pow(pos.x - x, 2) + Math.pow(pos.y - y, 2) + Math.pow(pos.z - z, 2));
	}

	private static double dist3D(Entity ent, Vec3 pos) {
		return Math.sqrt(Math.pow(pos.x - ent.x, 2) + Math.pow(pos.y - ent.y, 2) + Math.pow(pos.z - ent.z, 2));
	}

	private static boolean inSphere(Entity ent, double dist, Vec3 pos) {
		return dist3D(ent.x, ent.y, ent.z, pos) < dist;
	}

	/* https://github.com/excessive/cpml/blob/master/modules/intersect.lua */
	private static <T extends Entity> boolean rayAABB(T ent, Vec3 dir, Vec3 pos) {
		Vec3 dirFract = dir.add(.0001, .0001, .0001);
		dirFract.x = 1 / dirFract.x;
		dirFract.y = 1 / dirFract.y;
		dirFract.z = 1 / dirFract.z;

		AABB aabb = ent.bb;
		if(aabb == null) {
			LOGGER.info("Null BB");
			return false;
		}
		LOGGER.info("-------------");
		LOGGER.info("{}, {}, {}", ent.bbWidth, ent.bbHeight, ent.heightOffset);
		double width = ent.bbWidth;
		double height = ent.bbHeight;

		double t1 = (ent.x - width - pos.x) * dirFract.x;
		double t2 = (ent.x + width - pos.x) * dirFract.x;
		double t3 = (ent.y + height - pos.y) * dirFract.y;
		double t4 = (ent.y + (height * 2) - pos.y) * dirFract.y;
		double t5 = (ent.z - width - pos.z) * dirFract.z;
		double t6 = (ent.z + width - pos.z) * dirFract.z;
		LOGGER.info("{}, {}, {}, {}, {}, {}, {}", t1, t3, t5, t2, t4, t6, ent.getClass().getName());

		double tMin = Math.max(Math.max(Math.min(t1, t2), Math.min(t3, t4)), Math.min(t5, t6));
		double tMax = Math.min(Math.min(Math.max(t1, t2), Math.max(t3, t4)), Math.max(t5, t6));

		if(tMax < 0) {
			return false;
		}
		if(tMin > tMax) {
			return  false;
		}

		LOGGER.info("INTERSECTS!");
		return true;
	}


	private static <T extends Entity> void getEntitiesWithin(Class<T> ofClass, Chunk ch, double distance, Vec3 pos, List<@NotNull T> entities) {
		int minSection = Math.max(0, MathHelper.floor(((pos.y - distance) - 2.0d) / 16.0d));
		int maxSection = Math.min(MathHelper.floor(((pos.y + distance) + 2.0d) / 16.0d), 15);

		for(int section = minSection; section <= maxSection; ++section) {
			ChunkSection sect = ch.getSection(section);

			for(Entity ent : sect.entities) {
				if(ofClass.isAssignableFrom(ent.getClass()) && inSphere(ent, distance, pos)) {
					entities.add((T) ent);
				}
			}
		}

	}

	public static <T extends Entity> List<T> getEntitiesInSphere(Class<T> ofClass, World world, double distance, Vec3 pos) {
		int minX = MathHelper.floor((pos.x - distance - 2.0d) / 16.0d);
		int maxX = MathHelper.floor((pos.x + distance + 2.0d) / 16.0d);
		int minZ = MathHelper.floor((pos.z - distance - 2.0d) / 16.0d);
		int maxZ = MathHelper.floor((pos.z + distance + 2.0d) / 16.0d);
		List<T> entities = new ArrayList<>();

		for(int x = minX; x <= maxX; ++x) {
			for(int z = minZ; z <= maxZ; ++z) {
				if (world.isChunkLoaded(x, z)) {
					Chunk ch = world.getChunkFromChunkCoords(x, z);
					getEntitiesWithin(ofClass, ch, distance, pos, entities);
				}
			}
		}

		return entities;
	}



	public static <T extends Entity> HitResult raycastWorld(Class<T> ofClass, World world, float dist, Vec3 pos, Vec3 dir, boolean hitFluids, boolean ignoreNonColliderBlocks) {

		Vec3 dst = pos.add(dir.x * dist, dir.y * dist, dir.z * dist);

		HitResult blockRaycast = world.checkBlockCollisionBetweenPoints(pos, dst, hitFluids, ignoreNonColliderBlocks, false);

		List<T> ents = getEntitiesInSphere(ofClass, world, dist, pos);

		double minDist = dist;
		if(blockRaycast != null && blockRaycast.hitType == HitResult.HitType.TILE) {
			minDist = dist3D(blockRaycast.x, blockRaycast.y, blockRaycast.z, pos);
		}

		Entity entHit = null;
		for(T ent : ents) {
			if(ent == null) {
				LOGGER.info("Null ent?");
				continue;
			}

			if(!rayAABB(ent, dir, pos)) {
				continue;
			}

			double distEnt = dist3D(ent, pos);
			if(distEnt < minDist) {
				minDist = distEnt;
				entHit = ent;
			}
		}

		if(entHit != null) {
			return new HitResult(entHit);
		} else {
			return blockRaycast;
		}
	}
}
