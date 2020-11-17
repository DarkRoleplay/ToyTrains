package net.dark_roleplay.toy_trains.client.models.helper;

import com.google.common.collect.Streams;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ModelHelper {

	private static final float RAIL_WIDTH = 1/16F * (3 * 0.5F);

	public static IBakedModel getRailModel(Vector3f posA, Vector3f posB, float angleA, float angleB){
		TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(new ResourceLocation("minecraft:textures/block/iron_block"));

		Vector3f[] vertBase = thicken(posA, posB, 0.03125F*4, angleA, angleB);

		Vector3f[] vertA = thicken(vertBase[0], vertBase[1], 0.03125F, angleA, angleB);
		Vector3f[] vertB = thicken(vertBase[2], vertBase[3], 0.03125F, angleA, angleB);

		return new SimpleBakedModel(sprite, Streams.concat(Arrays.stream(getQuadsForBox(sprite, vertA)), Arrays.stream(getQuadsForBox(sprite, vertB))).collect(Collectors.toList()));
	}

	private static Vector3f[] thicken(Vector3f posA, Vector3f posB, float width, float angleA, float angleB){
		Vector3f[] result = new Vector3f[4];

		result[0] = posA.copy();
		result[1] = posB.copy();
		result[2] = posA.copy();
		result[3] = posB.copy();

		float cosAngleA = (float) Math.cos(angleA);
		float sinAngleA = (float) Math.sin(angleA);
		float cosAngleB = (float) Math.cos(angleB);
		float sinAngleB = (float) Math.sin(angleB);

		Vector3f ab = new Vector3f(posB.getX() - posA.getX(), posB.getY() - posA.getY(), posB.getZ() - posA.getZ());


		Vector3f bA = new Vector3f((ab.getX() * cosAngleA) + (ab.getZ() * sinAngleA), ab.getY(), (-ab.getX() * sinAngleA) + (ab.getZ() * cosAngleA));
		Vector3f bB = new Vector3f((ab.getX() * cosAngleB) + (ab.getZ() * sinAngleB), ab.getY(), (-ab.getX() * sinAngleB) + (ab.getZ() * cosAngleB));

		bA.normalize();
		bB.normalize();
		bA.mul(width);
		bB.mul(width);

		result[0].add(bA);
		result[1].add(bB);
		result[2].sub(bA);
		result[3].sub(bB);

		return result;
	}

	public static BakedQuad[] getQuadsForBox(TextureAtlasSprite sprite, Vector3f... verts){
		Vector3f upperVerts[] = new Vector3f[verts.length];
		for(int i = 0; i < verts.length; i++){
			upperVerts[i] = new Vector3f(verts[i].getX(), verts[i].getY() + 0.0625F ,verts[i].getZ()); //TODO Switch size to be positive
		}

		BakedQuad[] quads = new BakedQuad[4];
		quads[0] = getQuad(new BakedQuadBuilder(sprite), Direction.DOWN, 0, 0, 1, 1, verts[1], verts[3], verts[2], verts[0]);
		quads[1] = getQuad(new BakedQuadBuilder(sprite), Direction.UP, 0, 0, 1, 1, upperVerts[0], upperVerts[2], upperVerts[3], upperVerts[1]);
		quads[2] = getQuad(new BakedQuadBuilder(sprite), Direction.NORTH, 0, 0, 1, 1, upperVerts[0], upperVerts[1], verts[1], verts[0]);
		quads[3] = getQuad(new BakedQuadBuilder(sprite), Direction.SOUTH, 0, 0, 1, 1, upperVerts[3], upperVerts[2], verts[2], verts[3]);

		return quads;
	}

//	.add(POSITION_3F).add(COLOR_4UB).add(TEX_2F).add(TEX_2SB).add(NORMAL_3B).add(PADDING_1B).build());


	private static BakedQuad getQuad(BakedQuadBuilder builder, Direction facing, float u1, float v1, float u2, float v2, Vector3f... vertices){
		float[][] uv = {{u1, v1}, {u2, v1}, {u2, v2}, {u1, v2}};
		builder.setQuadOrientation(facing);
		for(int i = 0; i < vertices.length; i++){
			builder.put(0, vertices[i].getX(), vertices[i].getY(), vertices[i].getZ());
			builder.put(1, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF);
			builder.put(2, uv[i][0], uv[i][1]);
			builder.put(3, 0xFF, 0xFF);
			builder.put(4, 0x0, 0x1, 0x0); //TODO fix normals
			builder.put(5);
		}

		return builder.build();
	}
}
