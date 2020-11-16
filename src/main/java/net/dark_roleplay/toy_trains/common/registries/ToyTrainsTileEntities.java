package net.dark_roleplay.toy_trains.common.registries;

import net.dark_roleplay.toy_trains.ToyTrains;
import net.dark_roleplay.toy_trains.common.tile_entities.RailwayTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.function.Supplier;

public class ToyTrainsTileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ToyTrains.MODID);

	public static final RegistryObject<TileEntityType<RailwayTileEntity>>
			TRACK = TILE_ENTITIES.register("track", () -> createType(RailwayTileEntity::new, ToyTrainsBlocks.OAK_TRACK));

		protected static <T extends TileEntity> TileEntityType<T> createType(Supplier<T> supplier, RegistryObject<Block>... blocks){
			return TileEntityType.Builder.create(supplier, Arrays.stream(blocks).map(ro -> ro.get()).toArray(size -> new Block[size])).build(null);
		}
}
