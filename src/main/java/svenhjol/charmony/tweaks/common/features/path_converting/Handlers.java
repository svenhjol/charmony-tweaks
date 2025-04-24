package svenhjol.charmony.tweaks.common.features.path_converting;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import svenhjol.charmony.core.base.Setup;

public class Handlers extends Setup<PathConverting> {
    public Handlers(PathConverting feature) {
        super(feature);
    }

    public InteractionResult useBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        var pos = hitResult.getBlockPos();
        var stack = player.getItemInHand(hand);
        var state = level.getBlockState(pos);
        var success = false;

        if (feature().allowPathToDirt() && stack.getItem() instanceof HoeItem && state.is(Blocks.DIRT_PATH)) {
            player.swing(hand);

            if (!level.isClientSide) {
                level.setBlock(pos, Blocks.DIRT.defaultBlockState(), 11);
                level.playSound(null, pos, feature().registers.pathToDirtSound.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
                feature().advancements.convertedPathToDirt((ServerPlayer) player);
            }
            success = true;


        } else if (feature().allowDirtToPath() && stack.getItem() instanceof ShovelItem && state.is(Blocks.DIRT)) {
            player.swing(hand);

            if (!level.isClientSide) {
                level.setBlock(pos, Blocks.DIRT_PATH.defaultBlockState(), 11);
                level.playSound(null, pos, feature().registers.dirtToPathSound.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
                feature().advancements.convertedDirtToPath((ServerPlayer) player);
            }
            success = true;
        }

        if (success) {
            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player, Player.getSlotForHand(hand));
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
