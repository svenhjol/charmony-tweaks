package svenhjol.charmony.tweaks.client.mixins.item_tidying;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charmony.tweaks.client.features.item_tidying.ItemTidying;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {
    @Inject(
        method = "keyPressed",
        at = @At("HEAD")
    )
    private void hookKeyPressed(int i, int j, int k, CallbackInfoReturnable<Boolean> cir) {
        ItemTidying.feature().handlers.handleKeypress(i, j, k);
    }
}
