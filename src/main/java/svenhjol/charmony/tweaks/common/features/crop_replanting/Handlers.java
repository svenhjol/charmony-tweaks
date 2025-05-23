package svenhjol.charmony.tweaks.common.features.crop_replanting;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import svenhjol.charmony.core.base.Setup;

import java.util.Locale;

public class Handlers extends Setup<CropReplanting> {
    public Handlers(CropReplanting feature) {
        super(feature);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public InteractionResult useBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        var mainhand = player.getMainHandItem();
        var offhand = player.getOffhandItem();
        ItemStack held;

        if (mainhand.getItem() instanceof HoeItem) {
            held = mainhand;
        } else if (offhand.getItem() instanceof HoeItem) {
            held = offhand;
        } else {
            return InteractionResult.PASS;
        }

        var pos = hitResult.getBlockPos();
        var state = level.getBlockState(pos);
        var block = state.getBlock();
        var doReplant = !feature().registers.notReplantable.contains(block);

        if (!feature().registers.replantable.contains(state)) {
            return InteractionResult.PASS;
        }

        var blockItem = block.asItem();
        var newState = block.defaultBlockState();

        // Preserve FACING property of original state.
        for (Property<?> prop : state.getProperties()) {
            if (prop.getName().toLowerCase(Locale.ENGLISH).equals("facing")) {
                newState = newState.setValue((Property)prop, state.getValue(prop));
                break;
            }
        }

        if (!level.isClientSide) {
            var serverPlayer = (ServerPlayer)player;
            var serverLevel = (ServerLevel)serverPlayer.level();
            var drops = Block.getDrops(state, serverLevel, pos, null, player, ItemStack.EMPTY);
            var canAutoPickup = feature().registers.autoPickup.stream().anyMatch(func -> func.apply(player));

            for (var drop : drops) {
                if (doReplant && drop.getItem() == blockItem) {
                    drop.shrink(1);
                }

                if (!drop.isEmpty()) {
                    if (canAutoPickup) {
                        player.getInventory().placeItemBackInInventory(drop);
                    } else {
                        Block.popResource(level, pos, drop);
                    }
                }
            }

            // If this crop should not be replanted, just set the new state to air.
            if (!doReplant) {
                newState = Blocks.AIR.defaultBlockState();
            }

            level.globalLevelEvent(2001, pos, Block.getId(newState));
            level.setBlockAndUpdate(pos, newState);
            level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);

            feature().advancements.replantedCrops(serverPlayer);

            // Damage the hoe a bit.
            if (!player.getAbilities().instabuild) {
                held.hurtAndBreak(1, player, Player.getSlotForHand(hand));
            }

            return InteractionResult.CONSUME;
        }

        return InteractionResult.SUCCESS;
    }
}
