package net.dark_roleplay.toy_trains.debug;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dark_roleplay.toy_trains.ToyTrains;
import net.dark_roleplay.toy_trains.client.models.helper.ModelHelper;
import net.dark_roleplay.toy_trains.common.railways.RailwayData;
import net.dark_roleplay.toy_trains.common.registries.ToyTrainsBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@Mod.EventBusSubscriber(modid = ToyTrains.MODID, value=Dist.CLIENT)
public class WorldRenderListener {

	@SubscribeEvent
	public static void onWorldDrawPost(RenderWorldLastEvent event){
		RailwayNetwork.setupNetwork();

		BlockRendererDispatcher renderer = Minecraft.getInstance().getBlockRendererDispatcher();

		Vector3d playerPos = Minecraft.getInstance().player.getEyePosition(event.getPartialTicks());

		RenderSystem.enableTexture();
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		MatrixStack stack = event.getMatrixStack();
		stack.push();
		stack.translate(-playerPos.getX(), -playerPos.getY() + 66, -playerPos.getZ());
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

		RailwayData data = RailwayNetwork.networkStart;
		RailwayData current = data;

		do{

			renderer.getBlockModelRenderer().renderModel(
					Minecraft.getInstance().world,
					ModelHelper.getRailModel(current.getStart(), current.getEnd(), (float) (Math.toRadians(current.getAngleStart())), (float) (Math.toRadians(current.getAngleEnd()))),
					ToyTrainsBlocks.OAK_TRACK.get().getDefaultState(),
					new BlockPos(0, 67, 0),
					stack,
					buf,
					false,
					new Random(),
					0L,
					0
			);
			current = current.getNext();
		}while(data != current);

		tes.draw();
		stack.pop();
	}
}
