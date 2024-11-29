package svenhjol.charmony.tweaks.common.features.mob_drops;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import svenhjol.charmony.core.base.Setup;

public final class Handlers extends Setup<MobDrops> {
    public Handlers(MobDrops feature) {
        super(feature);
    }

    public void entityTick(LivingEntity entity) {
        if (entity.level().isClientSide()) return;

        for (var handler : feature().registers.drops) {
            var stack = handler.dropByChance(entity).orElse(ItemStack.EMPTY);

            if (!stack.isEmpty()) {
                spawnEntityItem(entity, stack);
            }
        }
    }

    public InteractionResult entityKilledDrop(LivingEntity entity, DamageSource source) {
        if (!entity.level().isClientSide()) {
            for (var handler : feature().registers.drops) {
                var stack = handler.dropWhenKilled(entity, source).orElse(ItemStack.EMPTY);

                if (!stack.isEmpty()) {
                    spawnEntityItem(entity, stack);
                }
            }
        }

        return InteractionResult.PASS;
    }

    public void spawnEntityItem(LivingEntity entity, ItemStack stack) {
        var level = entity.level();
        var pos = entity.blockPosition();
        level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, stack));
    }
}
