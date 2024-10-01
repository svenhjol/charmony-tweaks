package svenhjol.charmony.tweaks.client.compasses_show_position;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import svenhjol.charmony.scaffold.base.Setup;

public class Registers extends Setup<CompassesShowPosition> {
    public Registers(CompassesShowPosition feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> HudRenderCallback.EVENT.register(feature().handlers::hudRender);
    }
}
