package net.dark_roleplay.toy_trains.client.entity_renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.dark_roleplay.toy_trains.common.entities.LocomotiveEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class LocomotiveRenderer extends EntityRenderer<LocomotiveEntity> {

	private LocomotiveTempModel model = new LocomotiveTempModel();

	public LocomotiveRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public ResourceLocation getEntityTexture(LocomotiveEntity entity) {
		return null;
	}

//	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

	@Override
	public void render(LocomotiveEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		model = new LocomotiveTempModel();

		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
		matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
		model.render(matrixStackIn, bufferIn.getBuffer(RenderType.getSolid()), packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.pop();
	}
}
