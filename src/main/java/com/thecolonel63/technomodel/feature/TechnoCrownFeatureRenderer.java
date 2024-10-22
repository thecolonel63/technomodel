package com.thecolonel63.technomodel.feature;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TechnoCrownFeatureRenderer<S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends FeatureRenderer<S, M> {

    private final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/pig/technocrown.png");
    private final M model;
    private final M babyModel;

    public TechnoCrownFeatureRenderer(FeatureRendererContext<S, M> context, M model, M babyModel) {
        super(context);
        this.model = model;
        this.babyModel = babyModel;
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, S livingEntityRenderState, float f, float g) {
        if (livingEntityRenderState.customName != null && livingEntityRenderState.customName.getString().equals("Technoblade")) {
            M entityModel = livingEntityRenderState.baby ? this.babyModel : this.model;
            entityModel.setAngles(livingEntityRenderState);
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(this.TEXTURE));
            entityModel.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
        }
    }
}