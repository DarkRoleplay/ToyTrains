package net.dark_roleplay.toy_trains.common.railways;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.math.vector.Vector3i;

import java.util.ArrayList;
import java.util.List;

public class RailwayData {

	private List<Pair<Vector3i, Vector3i>> railways = new ArrayList();

	public void addRailway(Vector3i start, Vector3i end){
		this.addRailway(Pair.of(start, end));
	}

	public void addRailway(Pair<Vector3i, Vector3i> rail){
		this.railways.add(rail);
	}

	public List<Pair<Vector3i, Vector3i>> getRails(){
		return this.railways;
	}

	public void clearRails(){
		this.railways.clear();
	}
}
