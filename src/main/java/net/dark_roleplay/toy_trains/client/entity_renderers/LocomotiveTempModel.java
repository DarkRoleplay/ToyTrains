package net.dark_roleplay.toy_trains.client.entity_renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.dark_roleplay.toy_trains.common.entities.LocomotiveEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class LocomotiveTempModel extends EntityModel<LocomotiveEntity> {
	public ModelRenderer line;

	public LocomotiveTempModel() {
		this.line = new ModelRenderer(this, 0 , 0);
		line.addBox(-1, 0, -5, 2, 1, 10);
	}

	@Override
	public void setRotationAngles(LocomotiveEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.line.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
