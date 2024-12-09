package svenhjol.charmony.tweaks.client.features.shulker_box_transferring;

import net.minecraft.client.Minecraft;
import net.minecraft.client.ScrollWheelHandler;
import net.minecraft.client.gui.ItemSlotMouseAction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import svenhjol.charmony.core.helper.ItemStackHelper;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class ShulkerBoxMouseActions implements ItemSlotMouseAction {
    private final ScrollWheelHandler scrollWheelHandler;

    public ShulkerBoxMouseActions(Minecraft minecraft) {
        this.scrollWheelHandler = new ScrollWheelHandler();
    }

    @Override
    public boolean matches(Slot slot) {
        return slot.getItem().is(ItemTags.SHULKER_BOXES);
    }

    @Override
    public boolean onMouseScrolled(double d, double e, int i, ItemStack stack) {
        var vector = scrollWheelHandler.onMouseScroll(d, e);
        var direction = vector.y == 0 ? -vector.x : vector.y;

        var data = stack.get(DataComponents.CONTAINER);
        if (data != null && direction != 0) {
            var stacks = new ArrayList<>(data.stream().toList());
            ItemStackHelper.mergeStacks(stacks);

            if (direction > 0) {
                ItemStack last = stacks.removeLast();
                stacks.addFirst(last);
            }

            if (direction < 0) {
                ItemStack first = stacks.removeFirst();
                stacks.add(first);
            }

            var itemContainerContents = ItemContainerContents.fromItems(stacks);
            stack.set(DataComponents.CONTAINER, itemContainerContents);
        }

        return stack.is(ItemTags.SHULKER_BOXES);
    }

    @Override
    public void onStopHovering(Slot slot) {
        // no op
    }

    @Override
    public void onSlotClicked(Slot slot, ClickType clickType) {
        // no op
    }
}
