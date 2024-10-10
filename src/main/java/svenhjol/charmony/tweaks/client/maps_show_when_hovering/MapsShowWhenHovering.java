package svenhjol.charmony.tweaks.client.maps_show_when_hovering;

import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.enums.Side;

@Feature(side = Side.Client, description = "Maps show their content when hovering over the inventory item.")
public final class MapsShowWhenHovering extends ModFeature {
    public final Registers registers;
    public final Handlers handlers;

    public MapsShowWhenHovering(Mod mod) {
        super(mod);
        registers = new Registers(this);
        handlers = new Handlers(this);
    }
}
