package svenhjol.charmony.tweaks.mixins.features.burning_has_reduced_view_blocking;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.tweaks.client.burning_has_reduced_view_blocking.BurningHasReducedViewBlocking;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {
    @Inject(
        method = "renderFire",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V"
        )
    )
    private static void hookRenderFire(Minecraft minecraft, PoseStack poseStack, CallbackInfo ci) {
        BurningHasReducedViewBlocking.feature().handlers.changeFireSize(poseStack);
    }
}
