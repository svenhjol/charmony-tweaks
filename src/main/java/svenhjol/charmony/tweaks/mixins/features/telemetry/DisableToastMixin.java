package svenhjol.charmony.tweaks.mixins.features.telemetry;

import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import svenhjol.charmony.tweaks.client.telemetry.Telemetry;

@Mixin(ClientPacketListener.class)
public class DisableToastMixin {
    @Redirect(
        method = "handleLogin",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/components/toasts/ToastManager;addToast(Lnet/minecraft/client/gui/components/toasts/Toast;)V"
        )
    )
    private void hookDisableChatNag(ToastManager instance, Toast toast) {
        if (Telemetry.disableChatMessageVerification()) return;
        instance.addToast(toast); // Vanilla behavior
    }
}
