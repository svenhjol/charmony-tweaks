package svenhjol.charmony.tweaks.client.maps_show_when_hovering;

import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.RenderTooltipCallback;

public final class Registers extends Setup<MapsShowWhenHovering> {
    public Registers(MapsShowWhenHovering feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> RenderTooltipCallback.EVENT.register(feature().handlers::renderMapTooltip);
    }
}
