package svenhjol.charmony.tweaks.client.chiseled_bookshelves_show_books;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class HudRenderer extends BaseHudRenderer {
    private ChiseledBookShelfBlockEntity bookshelf;
    private BlockHitResult hitResult;
    private BlockState hitState;

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        if (ticksFade == 0) return;
        ticksBackoff = 2;

        var minecraft = Minecraft.getInstance();
        var window = minecraft.getWindow();
        var font = minecraft.font;
        var midX = (int)(window.getGuiScaledWidth() / 2.0f);
        var alpha = Math.max(4, Math.min(MAX_FADE_TICKS, ticksFade)) << 24 & 0xff000000;
        var scale = Math.max(0f, Math.min(1.0f, (ticksFade / (float) MAX_FADE_TICKS)));
        var top = window.getGuiScaledHeight() - 100;

        if (hitResult != null) {
            var slot = feature().handlers.getHitSlot(hitResult, hitState);
            if (slot.isPresent()) {
                var stack = feature().handlers.getItem(bookshelf, slot.getAsInt());
                var lines = stack.getTooltipLines(Item.TooltipContext.EMPTY, minecraft.player, TooltipFlag.NORMAL);
                if (!stack.isEmpty()) {
                    renderScaledGuiItem(guiGraphics, stack, midX - 8, top, scale, scale);
                    for (int i = 0; i < lines.size(); i++) {
                        var component = lines.get(i);
                        guiGraphics.drawCenteredString(font, component, midX, top + 20 + (i * 12), 0xffffff | alpha);
                    }
                }
            }
        }

        doFadeTicks();
    }

    @Override
    protected boolean isValid(Player player) {
        var level = player.level();
        var lookedAt = feature().handlers.lookingAtBlock(player);
        var pos = lookedAt.getBlockPos();

        if (level.getBlockEntity(pos) instanceof ChiseledBookShelfBlockEntity blockEntity) {
            if (player instanceof LocalPlayer localPlayer) {
                // Use the debugger's queryBlockEntity to get the entity data from
                // the server and populate the client blockEntity with it.
                localPlayer.connection.getDebugQueryHandler().queryBlockEntityTag(pos, tag -> {
                    var out = CustomData.of(tag);
                    out.loadInto(blockEntity, level.registryAccess());
                });
            }
            this.bookshelf = blockEntity;
            this.hitResult = lookedAt;
            this.hitState = level.getBlockState(pos);
            return true;
        }

        return false;
    }

    private ChiseledBookshelvesShowBooks feature() {
        return ChiseledBookshelvesShowBooks.feature();
    }
}
