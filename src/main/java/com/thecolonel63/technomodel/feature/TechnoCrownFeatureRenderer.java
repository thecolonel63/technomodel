package com.thecolonel63.technomodel.feature;

import com.thecolonel63.technomodel.duck.RenderCrownDuck;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TechnoCrownFeatureRenderer<S extends LivingEntityRenderState, RM extends EntityModel<? super S>, EM extends EntityModel<? super S>>
        extends FeatureRenderer<S, RM> {

    private final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/pig/technocrown.png");
    private final EM adultModel;
    private final EM babyModel;

    public TechnoCrownFeatureRenderer(FeatureRendererContext<S, RM> context, EM adultModel, EM babyModel) {
        super(context);
        this.adultModel = adultModel;
        this.babyModel = babyModel;
    }

    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, S state, float limbAngle, float limbDistance) {
        if (((RenderCrownDuck) state).technomodel$shouldRenderCrown()) {
            EM model = state.baby ? this.babyModel : this.adultModel;
            model.setAngles(state);
            queue.submitModel(model, state, matrices, RenderLayers.entityCutoutNoCull(this.TEXTURE), light, OverlayTexture.DEFAULT_UV, state.outlineColor, null);
        }
    }
}