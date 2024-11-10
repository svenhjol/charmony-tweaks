package svenhjol.charmony.tweaks.client.chiseled_bookshelves_show_book_on_hover;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import svenhjol.charmony.core.base.Setup;

public final class Registers extends Setup<ChiseledBookshelvesShowBookOnHover> {
    public final HudRenderer hudRenderer;

    public Registers(ChiseledBookshelvesShowBookOnHover feature) {
        super(feature);
        this.hudRenderer = new HudRenderer();
    }

    @Override
    public Runnable boot() {
        return () -> {
            ClientTickEvents.END_CLIENT_TICK.register(feature().handlers::clientTick);
            HudRenderCallback.EVENT.register(feature().handlers::hudRender);
        };
    }
}
