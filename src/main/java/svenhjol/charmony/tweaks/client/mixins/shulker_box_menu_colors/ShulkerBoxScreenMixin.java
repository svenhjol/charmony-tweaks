package svenhjol.charmony.tweaks.client.mixins.shulker_box_menu_colors;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.tweaks.client.features.shulker_box_menu_colors.ShulkerBoxMenuColors;

@Mixin(ShulkerBoxScreen.class)
public abstract class ShulkerBoxScreenMixin extends AbstractContainerScreen<ShulkerBoxMenu> {
    public ShulkerBoxScreenMixin(ShulkerBoxMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    /**
     * Try calling a custom render function that has a reference to the last clicked block color.
     * On success cancel the vanilla rendering.
     * If it fails, fall through to default vanilla rendering.
     */
    @Inject(
        method = "renderBg",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookRenderBg(GuiGraphics guiGraphics, float ticks, int mouseX, int mouseY, CallbackInfo ci) {
        var result = ShulkerBoxMenuColors.feature().handlers.tryRenderBackground(guiGraphics, width, height, imageWidth, imageHeight);
        if (result) {
            ci.cancel();
        }

        // Fallthrough to default behaviour
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        var result = ShulkerBoxMenuColors.feature().handlers.tryRenderLabels(guiGraphics, font, title, titleLabelX, titleLabelY, playerInventoryTitle, inventoryLabelX, inventoryLabelY);
        if (result) {
            return;
        }

        // Default behaviour
        super.renderLabels(guiGraphics, mouseX, mouseY);
    }
}
