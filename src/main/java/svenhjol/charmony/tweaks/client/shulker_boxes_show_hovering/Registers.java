package svenhjol.charmony.tweaks.client.shulker_boxes_show_hovering;

import svenhjol.charmony.scaffold.base.Setup;
import svenhjol.charmony.scaffold.events.HoverOverItemTooltipCallback;
import svenhjol.charmony.scaffold.events.RenderTooltipComponentCallback;

public class Registers extends Setup<ShulkerBoxesShowHovering> {
    public Registers(ShulkerBoxesShowHovering feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            HoverOverItemTooltipCallback.EVENT.register(feature().handlers::removeLinesFromShulkerBox);
            RenderTooltipComponentCallback.EVENT.register(feature().handlers::addGridToShulkerBox);
        };
    }
}
