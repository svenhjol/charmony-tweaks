package svenhjol.charmony.tweaks.client.mixins.totem_emergency_swap;

import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.tweaks.client.features.totem_emergency_swap.TotemEmergencySwap;

@Mixin(KeyMapping.class)
public class KeyMappingMixin {
    /**
     * Prevents held keys from being interrupted when the inventory screen is initialized.
     * Check if the totem is currently being swapped, and if it is, suppress the keybind releasing.
     * Without this pressing the totem keybind will stop any held keys such as walking.
     */
    @Inject(
        method = "releaseAll",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void hookReleaseAll(CallbackInfo ci) {
        if (TotemEmergencySwap.feature().handlers.preventKeybindReleasing()) {
            ci.cancel();
        }
    }
}
