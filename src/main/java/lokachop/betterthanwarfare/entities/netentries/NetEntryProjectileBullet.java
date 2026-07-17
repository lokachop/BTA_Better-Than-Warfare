package lokachop.betterthanwarfare.entities.netentries;

import com.mojang.nbt.tags.CompoundTag;
import lokachop.betterthanwarfare.entities.ProjectileBullet;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.net.entity.EntityTracker;
import net.minecraft.core.net.entity.EntityTrackerEntry;
import net.minecraft.core.net.entity.ITrackedEntry;
import net.minecraft.core.net.entity.IVehicleEntry;
import net.minecraft.core.net.packet.PacketAddEntity;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NetEntryProjectileBullet implements IVehicleEntry<ProjectileBullet>, ITrackedEntry<ProjectileBullet> {
	public @NotNull Class<ProjectileBullet> getAppliedClass() {
		return ProjectileBullet.class;
	}

	public int getTrackingDistance() {
		return 64;
	}

	public int getPacketDelay() {
		return 10;
	}

	public boolean sendMotionUpdates() {
		return true;
	}

	public void onEntityTracked(EntityTracker tracker, EntityTrackerEntry trackerEntry, ProjectileBullet trackedObject) {
	}

	public Entity getEntity(World world, double x, double y, double z, int metadata, boolean hasVelocity, double xd, double yd, double zd, Entity owner, @Nullable CompoundTag tag) {
		return new ProjectileBullet(world, x, y, z, xd, yd, zd);
	}

	public PacketAddEntity getSpawnPacket(EntityTrackerEntry tracker, ProjectileBullet trackedObject) {
		return new PacketAddEntity(trackedObject);
	}
}
