package svenhjol.charmony.tweaks.common.mixins.crop_feather_falling;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.tweaks.common.features.crop_feather_falling.CropFeatherFalling;

@Mixin(FarmBlock.class)
public class FarmBlockMixin {
    @Inject(
        method = "fallOn",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookOnLandedUpon(Level level, BlockState blockState, BlockPos blockPos, Entity entity, double d, CallbackInfo ci) {
        if (CropFeatherFalling.feature().handlers.shouldPreventCropDamage(entity)) {
            ci.cancel();
        }
    }
}
