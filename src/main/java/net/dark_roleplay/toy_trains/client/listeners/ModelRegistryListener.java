package net.dark_roleplay.toy_trains.client.listeners;

import net.dark_roleplay.toy_trains.ToyTrains;
import net.dark_roleplay.toy_trains.client.models.railway.RailwayModelLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ToyTrains.MODID, value = Dist.CLIENT)
public class ModelRegistryListener {

	@SubscribeEvent
	public static void registerModel(ModelRegistryEvent event){
		ModelLoaderRegistry.registerLoader(new ResourceLocation(ToyTrains.MODID, "railway"), RailwayModelLoader.INSTANCE);
	}
}
