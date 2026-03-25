package com.thecolonel63.technomodel.mixin;

import com.thecolonel63.technomodel.duck.RenderCrownDuck;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PigRenderState.class)
public class PigRenderStateMixin implements RenderCrownDuck {
    @Unique
    private boolean shouldRenderCrown = false;

    @Override
    public boolean technomodel$shouldRenderCrown() {
        return shouldRenderCrown;
    }

    @Override
    public void technomodel$setRenderCrown(boolean render) {
        this.shouldRenderCrown = render;
    }
}
