package svenhjol.charmony.tweaks.client.features.compasses_show_position;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import svenhjol.charmony.core.base.Setup;

public final class Registers extends Setup<CompassesShowPosition> {
    public Registers(CompassesShowPosition feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> HudLayerRegistrationCallback.EVENT.register(feature().handlers::hudRender);
    }
}
