package svenhjol.charmony.tweaks.mixins.features.chiseled_bookshelves_show_books;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChiseledBookShelfBlockEntity.class)
public abstract class ChiseledBookShelfBlockEntityMixin extends BlockEntity {
    @Shadow protected abstract void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider);

    public ChiseledBookShelfBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        var updateTag = new CompoundTag();
        saveAdditional(updateTag, provider);
        return updateTag;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        syncToClient();
    }

    @Unique
    private void syncToClient() {
        var level = getLevel();
        if (level != null && !level.isClientSide()) {
            ((ServerLevel)level).getChunkSource().blockChanged(getBlockPos());
        }
    }
}
