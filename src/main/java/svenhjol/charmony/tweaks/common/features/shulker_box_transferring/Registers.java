package svenhjol.charmony.tweaks.common.features.shulker_box_transferring;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ItemLike;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.ItemDragDropCallback;
import svenhjol.charmony.core.helper.TagHelper;

import java.util.ArrayList;
import java.util.List;

public final class Registers extends Setup<ShulkerBoxTransferring> {
    public final List<ItemLike> blacklist = new ArrayList<>();

    public Registers(ShulkerBoxTransferring feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            ItemDragDropCallback.EVENT.register(feature().handlers::itemDragDrop);
            ServerWorldEvents.LOAD.register(this::worldLoad);
        };
    }

    private void worldLoad(MinecraftServer server, ServerLevel level) {
        for (var block : TagHelper.getValues(BuiltInRegistries.BLOCK, BlockTags.SHULKER_BOXES)) {
            if (!blacklist.contains(block)) {
                blacklist.add(block);
            }
        }
    }
}
