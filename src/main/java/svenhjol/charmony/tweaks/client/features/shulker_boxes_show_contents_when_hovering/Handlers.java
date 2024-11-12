package svenhjol.charmony.tweaks.client.features.shulker_boxes_show_contents_when_hovering;

import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import svenhjol.charmony.core.base.Setup;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public final class Handlers extends Setup<ShulkerBoxesShowContentsWhenHovering> {
    public Handlers(ShulkerBoxesShowContentsWhenHovering feature) {
        super(feature);
    }

    /**
     * When hovering over a shulker box, remove all lines that show the contents.
     */
    public void removeLinesFromShulkerBox(ItemStack stack, List<Component> components, TooltipFlag tooltipFlag) {
        var shulkerBoxBlock = tryGetShulkerBoxBlock(stack);
        if (shulkerBoxBlock != null && components.size() > 1) {
            var title = components.getFirst();
            components.clear();
            components.addFirst(title);
        }
    }

    /**
     * When hovering over a shulker box, display a custom grid.
     */
    public Optional<TooltipComponent> addGridToShulkerBox(ItemStack stack) {
        var shulkerBoxBlock = tryGetShulkerBoxBlock(stack);
        var level = Minecraft.getInstance().level;
        if (level != null
            && shulkerBoxBlock != null
            && stack.has(DataComponents.CONTAINER)
        ) {
            var data = stack.get(DataComponents.CONTAINER);
            if (data != null) {
                var items = data.stream().toList();
                return Optional.of(new ShulkerBoxTooltip(items));
            }
        }

        return Optional.empty();
    }

    @Nullable
    public ShulkerBoxBlock tryGetShulkerBoxBlock(ItemStack stack) {
        if (Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock shulkerBoxBlock) {
            return shulkerBoxBlock;
        }
        return null;
    }
}
