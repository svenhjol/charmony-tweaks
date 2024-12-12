package svenhjol.charmony.tweaks.common.features.pigs_find_mushrooms;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class FindMushroomGoal extends Goal {
    private final Pig pig;
    private final Level level;

    public FindMushroomGoal(Pig pig) {
        this.pig = pig;
        this.level = pig.level();
    }

    private boolean isValidBlock(BlockPos pos) {
        var state = level.getBlockState(pos);
        var is = state.is(Tags.PIGS_FIND_MUSHROOMS);
        return is;
    }

    @Override
    public boolean canUse() {
        if (pig.isBaby()) {
            return false;
        }
        if (pig.getRandom().nextInt(feature().findChance()) != 0) {
            return false;
        }

        var pos = pig.blockPosition().below();
        return isValidBlock(pos);
    }

    @Override
    public void start() {
        feature().handlers.eating.put(pig.getUUID(), adjustedTickDelay(40));
        level.broadcastEntityEvent(pig, (byte)10); // Might need change?
        pig.getNavigation().stop();
        level.playSound(null, pig.blockPosition(), feature().registers.sniffingSound.get(), SoundSource.NEUTRAL, 1.0f, 1.0f);
    }

    @Override
    public void stop() {
        feature().handlers.eating.remove(pig.getUUID());
    }

    @Override
    public boolean canContinueToUse() {
        return feature().handlers.eating.getOrDefault(pig.getUUID(), 0) > 0;
    }

    @Override
    public void tick() {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        var uuid = pig.getUUID();
        var random = pig.getRandom();
        var mobGriefing = serverLevel.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
        var tick = feature().handlers.eating.getOrDefault(uuid, 0);
        pig.getNavigation().stop();

        if (tick > 4) {
            return;
        }

        var pos = pig.blockPosition().below();
        if (isValidBlock(pos)) {
            if (mobGriefing && random.nextFloat() < feature().erodeChance()) {
                level.setBlock(pos, Blocks.DIRT.defaultBlockState(), 2);
                level.levelEvent(2001, pos, Block.getId(Blocks.DIRT.defaultBlockState()));
            }
            var stack = new ItemStack(random.nextBoolean() ? Items.RED_MUSHROOM : Items.BROWN_MUSHROOM, 1);
            level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, stack));
            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 1.0f, 1.0f);

            feature().advancements.unearthedMushroom(level, pos);
        }
    }

    private PigsFindMushrooms feature() {
        return PigsFindMushrooms.feature();
    }
}
