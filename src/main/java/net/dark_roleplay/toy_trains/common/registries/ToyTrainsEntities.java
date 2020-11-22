package net.dark_roleplay.toy_trains.common.registries;

import net.dark_roleplay.toy_trains.ToyTrains;
import net.dark_roleplay.toy_trains.common.entities.LocomotiveEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ToyTrainsEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ToyTrains.MODID);

	public static final RegistryObject<EntityType<LocomotiveEntity>> LOCOMOTIVE =
			ENTITIES.register("locomotive",
					() -> EntityType.Builder.<LocomotiveEntity>create(LocomotiveEntity::new, EntityClassification.MISC)
					.setShouldReceiveVelocityUpdates(true)
					.size(0.5F, 0.5F)
					.build("locomotive"));
}
