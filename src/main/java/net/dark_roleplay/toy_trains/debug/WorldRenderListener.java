package net.dark_roleplay.toy_trains.debug;

import net.dark_roleplay.toy_trains.ToyTrains;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ToyTrains.MODID, value=Dist.CLIENT)
public class WorldRenderListener {

	@SubscribeEvent
	public static void onWorldDrawPost(RenderWorldLastEvent event){

	}
}
