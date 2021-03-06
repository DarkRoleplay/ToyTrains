package net.dark_roleplay.toy_trains;

import net.dark_roleplay.toy_trains.client.entity_renderers.LocomotiveRenderer;
import net.dark_roleplay.toy_trains.common.registries.ToyTrainsEntities;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ToyTrainsClient {

	public static void modConstructorInit(){

		FMLJavaModLoadingContext.get().getModEventBus().addListener(ToyTrainsClient::clientSetup);
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(Keybinds::registerKeybinds);

	}

	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ToyTrainsEntities.LOCOMOTIVE.get(), LocomotiveRenderer::new);
	}
}
