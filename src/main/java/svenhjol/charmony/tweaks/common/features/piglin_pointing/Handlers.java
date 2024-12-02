package svenhjol.charmony.tweaks.common.features.piglin_pointing;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import svenhjol.charmony.core.base.Setup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Handlers extends Setup<PiglinPointing> {
    // Track piglins that are currently in a pointing state.
    public static final List<UUID> pointingPiglins = new ArrayList<>();

    public Handlers(PiglinPointing feature) {
        super(feature);
    }

    /**
     * Called by mixin when piglin has finished considering the block.
     * @see svenhjol.charmony.tweaks.common.mixins.piglin_pointing.PiglinAiMixin
     */
    public void checkBlockAndFindStructure(Piglin piglin, ItemStack stack) {
        if (piglin.level() instanceof ServerLevel level) {
            BlockPos source = piglin.blockPosition();
            BlockPos target = null;

            for (var pair : feature().registers.DIRECTION_BARTERING) {
                if (stack.is(pair.getFirst())) {
                    target = level.findNearestMapStructure(pair.getSecond(), source, 100, false);
                }
            }

            if (target != null) {
                piglin.getBrain().setMemoryWithExpiry(feature().registers.pointingAtTarget, target, 100L);
                feature().advancements.piglinProvidedDirections(level, source);
            }
        }
    }

    /**
     * Called by mixin to update the piglin entity and model as part of a ticking update check.
     * @see svenhjol.charmony.tweaks.common.mixins.piglin_pointing.PiglinAiMixin
     */
    public void setPointing(Piglin piglin) {
        piglin.getBrain().getMemory(feature().registers.pointingAtTarget).ifPresentOrElse(
            pos -> {
                piglin.getLookControl().setLookAt(pos.getX(), 60, pos.getZ());
                piglin.getNavigation().stop();
                pointingPiglins.add(piglin.getUUID());
            },
            () -> {
                pointingPiglins.remove(piglin.getUUID());
            }
        );
    }

    /**
     * Called by mixin to check whether a nearby item should be picked up.
     * @see svenhjol.charmony.tweaks.common.mixins.piglin_pointing.PiglinAiMixin
     */
    public boolean wantsToPickup(Piglin piglin, ItemStack stack) {
        return !piglin.isBaby()
            && isBarteringItem(stack)
            && isNotAdmiringOrPointing(piglin);
    }

    /**
     * Called by mixin to actually pick up the item and what do to with it.
     * @see svenhjol.charmony.tweaks.common.mixins.piglin_pointing.PiglinAiMixin
     */
    public boolean tryToPickup(Piglin piglin, ItemStack stack) {
        if (isBarteringItem(stack) && piglin.level() instanceof ServerLevel serverLevel) {
            piglin.getBrain().eraseMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM);
            piglin.getBrain().setMemoryWithExpiry(MemoryModuleType.ADMIRING_ITEM, true, 40L);
            PiglinAi.holdInOffhand(serverLevel, piglin, stack);
            return true;
        }
        return false;
    }

    public boolean isNotAdmiringOrPointing(Piglin piglin) {
        return piglin.getBrain().getMemory(MemoryModuleType.ADMIRING_ITEM).isEmpty()
            && piglin.getBrain().getMemory(feature().registers.pointingAtTarget).isEmpty();
    }

    public boolean isPointing(Piglin piglin) {
        return pointingPiglins.contains(piglin.getUUID());
    }

    public boolean isBarteringItem(ItemStack stack) {
        return stack.is(Tags.PIGLIN_BARTERS_FOR_DIRECTIONS);
    }
}
