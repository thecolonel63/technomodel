package com.thecolonel63.technomodel.feature;

import com.thecolonel63.technomodel.duck.RenderCrownDuck;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class TechnoCrownFeatureRenderer<S extends LivingEntityRenderState, RM extends EntityModel<? super S>, EM extends EntityModel<? super S>>
        extends RenderLayer<S, RM> {

    private final Identifier TEXTURE_ADULT = Identifier.withDefaultNamespace("textures/entity/pig/technocrown_adult.png");
    private final Identifier TEXTURE_BABY = Identifier.withDefaultNamespace("textures/entity/pig/technocrown_baby.png");
    private final EM adultModel;
    private final EM babyModel;

    public TechnoCrownFeatureRenderer(RenderLayerParent<S, RM> context, EM adultModel, EM babyModel) {
        super(context);
        this.adultModel = adultModel;
        this.babyModel = babyModel;
    }

    @Override
    public void submit(@NonNull PoseStack stack, @NonNull SubmitNodeCollector collector, int light, S state, float limbAngle, float limbDistance) {
        if (((RenderCrownDuck) state).technomodel$shouldRenderCrown()) {
            EM model = state.isBaby ? this.babyModel : this.adultModel;
            model.setupAnim(state);

            if (state.isBaby) {
                stack.translate(0.0,  1.0625, -0.25);
                stack.translate(0.0, -0.0625, 0.0);
                stack.scale(1.125f, 1.125f, 1.125f);
                stack.translate(0.0, -1.0625, 0.25);
                collector.submitModel(model, state, stack, RenderTypes.entityCutout(this.TEXTURE_BABY), light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
            } else {
                collector.submitModel(model, state, stack, RenderTypes.entityCutout(this.TEXTURE_ADULT), light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
            }
        }
    }
}