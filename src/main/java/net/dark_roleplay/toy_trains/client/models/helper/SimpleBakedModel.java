package net.dark_roleplay.toy_trains.client.models.helper;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleBakedModel implements IBakedModel {
	private TextureAtlasSprite sprite;
	private List<BakedQuad> quads;
	private static final List<BakedQuad> EMPTY = new ArrayList();

	public SimpleBakedModel(TextureAtlasSprite sprite, List<BakedQuad> quads) {
		this.sprite = sprite;
		this.quads = quads;
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
		return side == null ? quads : EMPTY;
	}

	@Override
	public boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isSideLit() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return sprite;
	}

	@Override
	public ItemOverrideList getOverrides() {
		return null;
	}
}
