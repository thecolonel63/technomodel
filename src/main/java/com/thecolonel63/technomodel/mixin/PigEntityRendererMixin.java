package com.thecolonel63.technomodel.mixin;

import com.thecolonel63.technomodel.duck.RenderCrownDuck;
import com.thecolonel63.technomodel.feature.TechnoCrownFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.render.entity.state.PigEntityRenderState;
import net.minecraft.entity.passive.PigEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"unchecked", "rawtypes"})
@Mixin(PigEntityRenderer.class)
public abstract class PigEntityRendererMixin extends LivingEntityRenderer {
    public PigEntityRendererMixin(EntityRendererFactory.Context ctx, EntityModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRendererFactory$Context;)V", at = @At("TAIL"))
    private void addTechnoCrownFeature(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(new TechnoCrownFeatureRenderer<PigEntityRenderState, PigEntityModel, PigEntityModel>(this, new PigEntityModel(context.getPart(EntityModelLayers.PIG_SADDLE)), new PigEntityModel(context.getPart(EntityModelLayers.PIG_BABY_SADDLE))));
    }

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/passive/PigEntity;Lnet/minecraft/client/render/entity/state/PigEntityRenderState;F)V", at = @At("TAIL"))
    private void onUpdateRenderState(PigEntity pigEntity, PigEntityRenderState pigEntityRenderState, float f, CallbackInfo ci) {
        ((RenderCrownDuck) pigEntityRenderState).technomodel$setRenderCrown(pigEntity.getName().getString().equals("Technoblade"));
    }
}
