package net.dark_roleplay.toy_trains.debug;

import net.dark_roleplay.toy_trains.client.models.helper.ModelHelper;
import net.dark_roleplay.toy_trains.common.railways.RailwayData;
import net.dark_roleplay.toy_trains.common.registries.ToyTrainsBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Random;

public class RailwayNetwork {

	public static RailwayData networkStart;

	static{
		setupNetwork();
	}

	public static void setupNetwork(){
		RailwayData last = null;

		int segments = 16;

		for(int i = 0; i < segments; i++){
			float angle = 360F/segments;

			float zOffset = 0.5F;//i >= 8 ? -4.5F : 4.5F;

			float x1 = 0.5F + (float) (2 * Math.cos(Math.toRadians(angle * i)));
			float y1 = zOffset + (float) (2 * Math.sin(Math.toRadians(angle * i)));

			float x2 = 0.5F + (float) (2 * Math.cos(Math.toRadians(angle * (i + 1))));
			float y2 = zOffset + (float) (2 * Math.sin(Math.toRadians(angle * (i + 1))));

			float angleA = 90+(angle/2);
			float angleB = 90-(angle/2);

			RailwayData newRailway = new RailwayData(new Vector3d(x1, 0, y1), new Vector3d(x2, 0, y2));

			if(last == null) {
				last = newRailway;
				networkStart = last;
			}else{
				last.setNext(newRailway);
				newRailway.setPrev(last);
				last = newRailway;
			}
		}

		last.setNext(networkStart);
		networkStart.setPrev(last);
	}
}
