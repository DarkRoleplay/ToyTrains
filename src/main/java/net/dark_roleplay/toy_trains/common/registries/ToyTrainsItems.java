package net.dark_roleplay.toy_trains.common.registries;

import net.dark_roleplay.toy_trains.ToyTrains;
import net.dark_roleplay.toy_trains.common.blocks.RailwayBlock;
import net.dark_roleplay.toy_trains.common.items.RailwayItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ToyTrainsItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ToyTrains.MODID);


	public static final RegistryObject<Item>
			OAK_TRACK = ITEMS.register("oak_track", () -> new RailwayItem(new Item.Properties().group(ItemGroup.DECORATIONS)));
}
