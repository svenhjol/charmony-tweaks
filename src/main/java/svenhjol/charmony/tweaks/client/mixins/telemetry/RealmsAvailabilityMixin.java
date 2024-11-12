package svenhjol.charmony.tweaks.client.mixins.telemetry;

import com.mojang.realmsclient.RealmsAvailability;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charmony.tweaks.client.features.telemetry.Telemetry;

import java.util.concurrent.CompletableFuture;

@Mixin(RealmsAvailability.class)
public class RealmsAvailabilityMixin {
    @Inject(
        method = "check",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void hookCheck(CallbackInfoReturnable<CompletableFuture<RealmsAvailability.Result>> cir) {
        if (Telemetry.disableDevEnvironmentConnections()) {
            cir.setReturnValue(CompletableFuture.supplyAsync(() -> new RealmsAvailability.Result(RealmsAvailability.Type.INCOMPATIBLE_CLIENT)));
        }
    }
}
