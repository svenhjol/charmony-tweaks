package svenhjol.charmony.tweaks.client.chiseled_bookshelves_labelling;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public abstract class BaseHudRenderer {
    protected static final int MAX_FADE_TICKS = 200;
    protected static final int MIN_BACKOFF_TICKS = 5;
    protected static final int MAX_BACKOFF_TICKS = 10;

    protected int fadeInSpeed;
    protected int fadeOutSpeed;
    protected int ticks = 0;
    protected int ticksFade = 0;
    protected int ticksBackoff = MIN_BACKOFF_TICKS;
    protected float scaleX = 1.0f;
    protected float scaleY = 1.0f;

    private boolean isValid = false;

    public BaseHudRenderer() {
        this.fadeInSpeed = 3;
        this.fadeOutSpeed = 10;
    }

    public void tick(Player player) {
        if (ticks % ticksBackoff == 0) {
            var nowValid = isValid(player);
            if (isValid && !nowValid) {
                isValid = false;
                ticksFade = MAX_FADE_TICKS;
                ticksBackoff = MIN_BACKOFF_TICKS;
                ticks = 0;
            }
            if (!isValid && nowValid) {
                isValid = true;
                ticksFade = 1;
                ticks = 0;
            }
            if (!isValid && ticksBackoff < MAX_BACKOFF_TICKS) {
                ticksBackoff += 5;
            }
        }

        if (ticksFade < 0) {
            ticksFade = 0;
        }
        ticks++;
    }

    public abstract void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker);

    protected abstract boolean isValid(Player player);

    protected void doFadeTicks() {
        if (isValid && ticksFade > 0 && ticksFade < MAX_FADE_TICKS) {
            ticksFade += fadeInSpeed;
        } else if (!isValid && ticksFade > 0) {
            ticksFade -= fadeOutSpeed;
        }
    }

    protected void renderScaledGuiItem(GuiGraphics guiGraphics, ItemStack stack, int x, int y, float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        guiGraphics.renderFakeItem(stack, x, y);
    }
}
