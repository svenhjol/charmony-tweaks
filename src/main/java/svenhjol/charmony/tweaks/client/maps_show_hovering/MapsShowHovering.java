package svenhjol.charmony.tweaks.client.maps_show_hovering;

import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.enums.Side;

@Feature(side = Side.Client, description = """
    Maps show their content when hovering over the map item.""")
public class MapsShowHovering extends ModFeature {
    public final Registers registers;
    public final Handlers handlers;

    public MapsShowHovering(Mod mod) {
        super(mod);
        registers = new Registers(this);
        handlers = new Handlers(this);
    }
}
