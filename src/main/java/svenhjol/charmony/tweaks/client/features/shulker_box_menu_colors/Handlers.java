package svenhjol.charmony.tweaks.client.features.shulker_box_menu_colors;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.client.TintedGuiGraphics;
import svenhjol.charmony.core.helpers.ColorHelper;

import javax.annotation.Nullable;

public class Handlers extends Setup<ShulkerBoxMenuColors> {
    public @Nullable DyeColor color;

    public Handlers(ShulkerBoxMenuColors feature) {
        super(feature);
    }

    /**
     * Use the reference to the last clicked block color to render a tinted background.
     * This is called by a mixin.
     * @see svenhjol.charmony.tweaks.client.mixins.shulker_box_menu_colors.ShulkerBoxScreenMixin
     */
    public boolean tryRenderBackground(GuiGraphics guiGraphics, int width, int height, int imageWidth, int imageHeight) {
        if (color != null) {
            var x = (width - imageWidth) / 2;
            var y = (height - imageHeight) / 2;
            var bgColor = ColorHelper.tintBackgroundColor(color);
            ((TintedGuiGraphics)guiGraphics).tint(bgColor).blit(RenderType::guiTextured, ShulkerBoxScreen.CONTAINER_TEXTURE, x, y, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);
            return true;
        }
        return false;
    }

    /**
     * Use the reference to the last clicked block color to render tinted text.
     * This is called by a mixin.
     * @see svenhjol.charmony.tweaks.client.mixins.shulker_box_menu_colors.ShulkerBoxScreenMixin
     */
    public boolean tryRenderLabels(GuiGraphics guiGraphics, Font font,
                                   Component containerTitle, int containerTitleX, int containerTitleY,
                                   Component playerTitle, int playerTitleX, int playerTitleY) {
        if (color != null) {
            var textColor = ColorHelper.tintForegroundColor(color);
            guiGraphics.drawString(font, containerTitle, containerTitleX, containerTitleY, textColor, false);
            guiGraphics.drawString(font, playerTitle, playerTitleX, playerTitleY, textColor, false);
            return true;
        }
        return false;
    }

    /**
     * If the block clicked (on the client) is a shulker box block, try and get its color.
     * Set the color to a reference in this handler so that the custom render methods can use it.
     */
    public InteractionResult useBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        var pos = hitResult.getBlockPos();
        if (level.isClientSide() && level.getBlockEntity(pos) instanceof ShulkerBoxBlockEntity shulkerBox) {
            this.color = shulkerBox.getColor();
        }
        return InteractionResult.PASS;
    }
}
