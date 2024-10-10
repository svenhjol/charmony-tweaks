package svenhjol.charmony.tweaks.client.chiseled_bookshelves_labelling;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import svenhjol.charmony.scaffold.base.Setup;

public class Registers extends Setup<ChiseledBookshelvesLabelling> {
    public final HudRenderer hudRenderer;

    public Registers(ChiseledBookshelvesLabelling feature) {
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
