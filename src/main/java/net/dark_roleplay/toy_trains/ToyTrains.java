package net.dark_roleplay.toy_trains;

import net.dark_roleplay.toy_trains.common.registries.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ToyTrains.MODID)
public class ToyTrains {

	public static final String MODID = "toy_trains";
	public static final Logger LOG = LogManager.getLogger(MODID);

	public ToyTrains() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::hackyHackToByPassLoadingOrder);

		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ToyTrainsClient::modConstructorInit);
	}

	public void hackyHackToByPassLoadingOrder(RegistryEvent.NewRegistry event){
		ToyTrainsBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ToyTrainsItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ToyTrainsTileEntities.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ToyTrainsEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ToyTrainsSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
