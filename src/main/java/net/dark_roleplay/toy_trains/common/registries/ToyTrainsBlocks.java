package net.dark_roleplay.toy_trains.common.registries;

import net.dark_roleplay.toy_trains.ToyTrains;
import net.dark_roleplay.toy_trains.common.blocks.RailwayBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ToyTrainsBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ToyTrains.MODID);

	public static final RegistryObject<Block>
			OAK_TRACK = BLOCKS.register("oak_track", () -> new RailwayBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F)));
}
