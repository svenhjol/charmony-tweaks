package svenhjol.charmony.tweaks.client.mixins.totem_emergency_swap;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.tweaks.client.features.totem_emergency_swap.TotemEmergencySwap;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {
    /**
     * Prevents the inventory screen from being rendered.
     * Check if the totem is currently being swapped, and if it is, suppress the rendering.
     * Without this there will be a momentary popup of the inventory screen.
     */
    @Inject(
        method = "render",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookRender(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        if (TotemEmergencySwap.feature().handlers.preventInventoryRendering()) {
            ci.cancel();
        }
    }
}
