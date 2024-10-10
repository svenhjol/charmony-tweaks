package svenhjol.charmony.tweaks.client.maps_show_when_hovering;

import svenhjol.charmony.scaffold.base.Setup;
import svenhjol.charmony.scaffold.events.RenderTooltipCallback;

public class Registers extends Setup<MapsShowWhenHovering> {
    public Registers(MapsShowWhenHovering feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> RenderTooltipCallback.EVENT.register(feature().handlers::renderMapTooltip);
    }
}
