package svenhjol.charmony.tweaks.common.features.shulker_box_transferring;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.ItemDragDropCallback.StackType;
import svenhjol.charmony.core.helper.ItemStackHelper;
import svenhjol.charmony.tweaks.common.features.shulker_box_transferring.Networking.C2SAddItemToShulkerBox;

public final class Handlers extends Setup<ShulkerBoxTransferring> {
    public Handlers(ShulkerBoxTransferring feature) {
        super(feature);
    }

    public InteractionResult itemDragDrop(StackType direction, ItemStack source, ItemStack dest, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        var registers = feature().registers;

        if (!slot.allowModification(player)) {
            return InteractionResult.PASS;
        }

        if (direction == StackType.STACKED_ON_OTHER && source.isEmpty()) {
            return InteractionResult.PASS;
        }

        if (Block.byItem(dest.getItem()) instanceof ShulkerBoxBlock
            && dest.has(DataComponents.CONTAINER)) {
            // Check if the source item is not in the blacklist.
            var blockItem = source.getItem();
            var block = Block.byItem(blockItem);
            if (registers.blacklist.contains(blockItem) || registers.blacklist.contains(block)) {
                return InteractionResult.PASS;
            }

            var containerSize = ShulkerBoxBlockEntity.CONTAINER_SIZE;
            var data = dest.get(DataComponents.CONTAINER);

            if (data != null) {
                // Main a NonNullList of itemstacks that represent the container contents.
                // Read this from the shulkerbox data and then write it back after modification.
                var containerItems = NonNullList.withSize(containerSize, ItemStack.EMPTY);
                data.copyInto(containerItems);

                // Populate the container.
                var container = new SimpleContainer(containerSize);
                for (var i = 0; i < containerSize; i++) {
                    container.setItem(i, containerItems.get(i));
                }

                if (clickAction == ClickAction.SECONDARY && source.isEmpty()) {
                    // Empty out one item from the container.
                    var index = 0;
                    for (var i = containerSize - 1; i >= 0; i--) {
                        if (!container.getItem(i).isEmpty()) {
                            index = i;
                        }
                    }
                    var stack = container.getItem(index);
                    if (stack.isEmpty()) {
                        return InteractionResult.PASS;
                    }

                    var out = stack.copy();
                    container.setItem(index, ItemStack.EMPTY);
                    ItemStackHelper.mergeStacks(container); // merge to remove empty slots
                    if (slot.safeInsert(out).isEmpty()) {
                        playRemoveOneSound(player);
                    }

                    broadcastChangesOnContainerMenu(player);

                } else if (clickAction == ClickAction.PRIMARY && !source.isEmpty()) {
                    // Add hovering item into the container.
                    var result = container.addItem(source);
                    source.setCount(result.getCount());

                    if (result.getCount() == 0) {
                        playInsertSound(player);
                    }

                    broadcastChangesOnContainerMenu(player);

                } else {
                    return InteractionResult.PASS;
                }

                // Write container back to shulkerbox.
                for (var i = 0; i < containerSize; i++) {
                    var stackInSlot = container.getItem(i);
                    containerItems.set(i, stackInSlot);
                }

                var itemContainerContents = ItemContainerContents.fromItems(containerItems);
                dest.set(DataComponents.CONTAINER, itemContainerContents);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    public void handleAddItemToShulkderBoxPacket(C2SAddItemToShulkerBox packet, ServerPlayNetworking.Context context) {
        var player = context.player();
        var server = player.server;

        server.execute(() -> {
            // Do the advancement
            feature().advancements.transferredToShulkerBox(player);
        });
    }

    /**
     * Copypasta from {@link net.minecraft.world.item.BundleItem}
     */
    public void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE,
            0.8f,
            0.8f + entity.level().getRandom().nextFloat() * 0.4f);

        entity.playSound(SoundEvents.SHULKER_BOX_OPEN,
            0.1f + entity.level().getRandom().nextFloat() * 0.3f,
            0.65f + entity.level().getRandom().nextFloat() * 0.4f);
    }

    /**
     * Copypasta from {@link net.minecraft.world.item.BundleItem}
     */
    public void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT,
            0.8f,
            0.8f + entity.level().getRandom().nextFloat() * 0.4f);

        entity.playSound(SoundEvents.SHULKER_BOX_OPEN,
            0.1f + entity.level().getRandom().nextFloat() * 0.3f,
            0.67f + entity.level().getRandom().nextFloat() * 0.4f);
    }

    /**
     * Reflect the behavior of the BundleItem.
     * @see net.minecraft.world.item.BundleItem -> broadcastChangesOnContainerMenu
     */
    private void broadcastChangesOnContainerMenu(Player player) {
        // Send our custom packet to let the server know the action was completed.
        C2SAddItemToShulkerBox.send();

        if (player.containerMenu instanceof AbstractContainerMenu menu) {
            menu.slotsChanged(player.getInventory());
        }
    }
}
