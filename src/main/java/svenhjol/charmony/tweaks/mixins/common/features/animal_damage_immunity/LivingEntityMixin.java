package svenhjol.charmony.tweaks.mixins.common.features.animal_damage_immunity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charmony.tweaks.common.animal_damage_immunity.AnimalDamageImmunity;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(
        method = "hurtServer",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hurt(ServerLevel serverLevel, DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
        var shouldHurt = AnimalDamageImmunity.feature().handlers.shouldHurt((LivingEntity)(Object)this, damageSource);
        if (!shouldHurt) {
            cir.setReturnValue(false);
        }
    }
}
