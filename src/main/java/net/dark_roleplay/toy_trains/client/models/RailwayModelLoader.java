package net.dark_roleplay.toy_trains.client.models;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.dark_roleplay.toy_trains.ToyTrains;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraftforge.client.model.IModelLoader;

public final class RailwayModelLoader implements IModelLoader<RailwayModel> {
	public static final RailwayModelLoader INSTANCE = new RailwayModelLoader();

	private RailwayModelLoader(){}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {

	}

	@Override
	public RailwayModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
		JsonObject textures = JSONUtils.getJsonObject(modelContents, "textures", new JsonObject());
		return RailwayModel.CODEC.parse(JsonOps.INSTANCE, textures).getOrThrow(false, error -> ToyTrains.LOG.error(error));
	}
}
