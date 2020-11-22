package net.dark_roleplay.toy_trains.common.railways;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

import java.util.ArrayList;
import java.util.List;

public class RailwayData {

	private Vector3d start, end, direction;
	private float angleStart, angleEnd;

	private RailwayData prev;
	private RailwayData next;

	public RailwayData(Vector3d start, Vector3d end) {
		this.start = start;
		this.end = end;
		this.direction = end.subtract(start).normalize();
		this.angleStart = 90;
		this.angleEnd = 90;
	}

	public void setNext(RailwayData next){
		this.next = next;
		this.angleEnd = 90 - ((float) Math.toDegrees(Math.acos(this.getDirection().dotProduct(next.getDirection()))) / 2);
	}

	public void setPrev(RailwayData prev){
		this.prev = prev;
		this.angleStart = 90 + ((float) Math.toDegrees(Math.acos(this.getDirection().dotProduct(prev.getDirection()))) / 2);
	}

	public Vector3d getStart() {
		return start;
	}

	public Vector3d getEnd() {
		return end;
	}

	public Vector3d getDirection() {
		return direction;
	}

	public float getAngleStart() {
		return angleStart;
	}

	public float getAngleEnd() {
		return angleEnd;
	}

	public RailwayData getPrev() {
		return prev;
	}

	public RailwayData getNext() {
		return next;
	}
}
