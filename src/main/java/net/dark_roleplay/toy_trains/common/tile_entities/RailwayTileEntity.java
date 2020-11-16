package net.dark_roleplay.toy_trains.common.tile_entities;

import net.dark_roleplay.toy_trains.common.registries.ToyTrainsTileEntities;
import net.minecraft.tileentity.TileEntity;

public class RailwayTileEntity extends TileEntity {
	public RailwayTileEntity() {
		super(ToyTrainsTileEntities.TRACK.get());
	}
}
