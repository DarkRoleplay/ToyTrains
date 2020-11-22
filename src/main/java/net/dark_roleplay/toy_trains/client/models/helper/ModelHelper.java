package net.dark_roleplay.toy_trains.client.models.helper;

import com.google.common.collect.Streams;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ModelHelper {

	private static final float RAIL_WIDTH = 1/16F * (3 * 0.5F);

	public static IBakedModel getRailModel(Vector3d posA, Vector3d posB, float angleA, float angleB){
		TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(new ResourceLocation("minecraft:textures/block/iron_block"));

		Vector3d[] vertBase = thicken(posA, posB, 0.03125F*4, angleA, angleB);

		Vector3d[] vertA = thicken(vertBase[0], vertBase[1], 0.03125F, angleA, angleB);
		Vector3d[] vertB = thicken(vertBase[2], vertBase[3], 0.03125F, angleA, angleB);

		return new SimpleBakedModel(sprite, Streams.concat(Arrays.stream(getQuadsForBox(sprite, vertA)), Arrays.stream(getQuadsForBox(sprite, vertB))).collect(Collectors.toList()));
	}

	private static Vector3d[] thicken(Vector3d posA, Vector3d posB, float width, float angleA, float angleB){
		Vector3d[] result = new Vector3d[4];

		result[0] = new Vector3d(posA.getX(), posA.getY(), posA.getZ());
		result[1] = new Vector3d(posB.getX(), posB.getY(), posB.getZ());
		result[2] = new Vector3d(posA.getX(), posA.getY(), posA.getZ());
		result[3] = new Vector3d(posB.getX(), posB.getY(), posB.getZ());

		float cosAngleA = (float) Math.cos(angleA);
		float sinAngleA = (float) Math.sin(angleA);
		float cosAngleB = (float) Math.cos(angleB);
		float sinAngleB = (float) Math.sin(angleB);

		Vector3d ab = new Vector3d(posB.getX() - posA.getX(), posB.getY() - posA.getY(), posB.getZ() - posA.getZ());


		Vector3d bA = new Vector3d((ab.getX() * cosAngleA) + (ab.getZ() * sinAngleA), ab.getY(), (-ab.getX() * sinAngleA) + (ab.getZ() * cosAngleA));
		Vector3d bB = new Vector3d((ab.getX() * cosAngleB) + (ab.getZ() * sinAngleB), ab.getY(), (-ab.getX() * sinAngleB) + (ab.getZ() * cosAngleB));

		bA = bA.normalize().mul(width, width, width);
		bB = bB.normalize().mul(width, width, width);

		result[0] = result[0].add(bA);
		result[1] = result[1].add(bB);
		result[2] = result[2].subtract(bA);
		result[3] = result[3].subtract(bB);

		return result;
	}

	public static BakedQuad[] getQuadsForBox(TextureAtlasSprite sprite, Vector3d... verts){
		Vector3d upperVerts[] = new Vector3d[verts.length];
		for(int i = 0; i < verts.length; i++){
			upperVerts[i] = new Vector3d(verts[i].getX(), verts[i].getY() + 0.0625F ,verts[i].getZ()); //TODO Switch size to be positive
		}

		BakedQuad[] quads = new BakedQuad[4];
		quads[0] = getQuad(new BakedQuadBuilder(sprite), Direction.DOWN, 0, 0, 1, 1, verts[1], verts[3], verts[2], verts[0]);
		quads[1] = getQuad(new BakedQuadBuilder(sprite), Direction.UP, 0, 0, 1, 1, upperVerts[0], upperVerts[2], upperVerts[3], upperVerts[1]);
		quads[2] = getQuad(new BakedQuadBuilder(sprite), Direction.NORTH, 0, 0, 1, 1, upperVerts[0], upperVerts[1], verts[1], verts[0]);
		quads[3] = getQuad(new BakedQuadBuilder(sprite), Direction.SOUTH, 0, 0, 1, 1, upperVerts[3], upperVerts[2], verts[2], verts[3]);

		return quads;
	}

//	.add(POSITION_3F).add(COLOR_4UB).add(TEX_2F).add(TEX_2SB).add(NORMAL_3B).add(PADDING_1B).build());


	public static BakedQuad getQuad(BakedQuadBuilder builder, Direction facing, float u1, float v1, float u2, float v2, Vector3d... vertices){
		float[][] uv = {{u1, v1}, {u2, v1}, {u2, v2}, {u1, v2}};
		builder.setQuadOrientation(facing);
		for(int i = 0; i < vertices.length; i++){
			builder.put(0, (float)vertices[i].getX(), (float)vertices[i].getY(), (float)vertices[i].getZ());
			builder.put(1, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF);
			builder.put(2, uv[i][0], uv[i][1]);
			builder.put(3, 0xFF, 0xFF);
			builder.put(4, 0x0, 0x1, 0x0); //TODO fix normals
			builder.put(5);
		}

		return builder.build();
	}
}
