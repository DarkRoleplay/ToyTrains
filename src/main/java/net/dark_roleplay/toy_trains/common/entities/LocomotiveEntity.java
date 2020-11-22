package net.dark_roleplay.toy_trains.common.entities;

import net.dark_roleplay.toy_trains.common.railways.RailwayData;
import net.dark_roleplay.toy_trains.debug.RailwayNetwork;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class LocomotiveEntity extends Entity {
	public LocomotiveEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	RailwayData currentRailway = RailwayNetwork.networkStart;

	@Override
	protected void registerData() {

	}

	@Override
	protected void readAdditional(CompoundNBT compound) {

	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {

	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void tick() {
		super.tick();

		float speed = 0.2F;

		//Cache new position before applying new logic
		Vector3d motionVector = this.getMotion();
		double d0 = this.getPosX() + motionVector.x;
		double d1 = this.getPosY() + motionVector.y;
		double d2 = this.getPosZ() + motionVector.z;

		float f = MathHelper.sqrt(horizontalMag(motionVector));
		this.rotationPitch = (float)Math.toDegrees(MathHelper.atan2(motionVector.y, (double)f));
		this.rotationYaw =  (float)Math.toDegrees(MathHelper.atan2(motionVector.x, motionVector.z)) + 90;

		this.setMotion(currentRailway.getDirection().mul(speed, speed, speed));

		Vector3d end = currentRailway.getEnd();
		Vector3d pos = this.getPositionVec().subtract(end);
		double x = pos.x, z = pos.z;

		Vector3d perp = currentRailway.getDirection();
		double cosA = Math.cos(currentRailway.getAngleEnd());
		double sinA = Math.sin(currentRailway.getAngleEnd());
		perp = new Vector3d(cosA * perp.getX() - sinA  *perp.getZ(), perp.getY(), sinA*perp.getX() + cosA*perp.getZ());

		double x2 = perp.x, z2 = perp.z;

		double d = ((x)*(z2)) - ((z)*(x2));
		if(d < 0)
			currentRailway = currentRailway.getNext();

		//Handle actual movement, client & server side
		if (!this.world.isRemote) {
			this.setPosition(d0, d1, d2);
		} else {
			this.setRawPosition(d0, d1, d2);
		}
	}

	protected static float lerpRotation(float prevRotation, float newRotation) {
		while(newRotation - prevRotation < -180.0F) {
			prevRotation -= 360.0F;
		}

		while(newRotation - prevRotation >= 180.0F) {
			prevRotation += 360.0F;
		}

		return MathHelper.lerp(0.2F, prevRotation, newRotation);
	}
}
