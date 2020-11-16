package net.dark_roleplay.toy_trains.client.models;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public class RailwayModel implements IModelGeometry<RailwayModel> {
	public static final Codec<RailwayModel> CODEC = RecordCodecBuilder.<RailwayModel>create(
			instance -> instance.group(
					ResourceLocation.CODEC.fieldOf("supports").forGetter(RailwayModel::getSupports),
					ResourceLocation.CODEC.fieldOf("rail").forGetter(RailwayModel::getRails),
					ResourceLocation.CODEC.fieldOf("tie").forGetter(RailwayModel::getTie)
			).apply(instance, RailwayModel::new)
	);

	private ResourceLocation supports;
	private ResourceLocation rails;
	private ResourceLocation tie;

	public RailwayModel(ResourceLocation rails, ResourceLocation tie, ResourceLocation supports){
		this.supports = supports;
		this.rails = rails;
		this.tie = tie;
	}

	@Override
	public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
		return null;
	}

	@Override
	public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
		return null;
	}

	public ResourceLocation getSupports() {
		return supports;
	}

	public ResourceLocation getRails() {
		return rails;
	}

	public ResourceLocation getTie() {
		return tie;
	}
}