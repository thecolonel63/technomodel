package com.thecolonel63.technomodel.mixin;

import com.thecolonel63.technomodel.duck.RenderCrownDuck;
import com.thecolonel63.technomodel.feature.TechnoCrownFeatureRenderer;
import net.minecraft.client.model.animal.pig.BabyPigModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.animal.pig.PigModel;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.world.entity.animal.pig.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"unchecked", "rawtypes"})
@Mixin(PigRenderer.class)
public abstract class PigRendererMixin extends LivingEntityRenderer {
    public PigRendererMixin(EntityRendererProvider.Context ctx, EntityModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void addTechnoCrownFeature(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new TechnoCrownFeatureRenderer<PigRenderState, PigModel, PigModel>(this, new PigModel(context.bakeLayer(ModelLayers.PIG_SADDLE)), new BabyPigModel(context.bakeLayer(ModelLayers.PIG_BABY))));
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/pig/Pig;Lnet/minecraft/client/renderer/entity/state/PigRenderState;F)V", at = @At("TAIL"))
    private void onUpdateRenderState(Pig pigEntity, PigRenderState pigEntityRenderState, float f, CallbackInfo ci) {
        ((RenderCrownDuck) pigEntityRenderState).technomodel$setRenderCrown(pigEntity.getName().getString().equals("Technoblade"));
    }
}
