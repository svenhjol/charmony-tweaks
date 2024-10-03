package svenhjol.charmony.tweaks.client.shulker_boxes_show_hovering;

import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.enums.Side;

@Feature(side = Side.Client)
public class ShulkerBoxesShowHovering extends ModFeature {
    public final Registers registers;
    public final Handlers handlers;

    public ShulkerBoxesShowHovering(Mod mod) {
        super(mod);
        registers = new Registers(this);
        handlers = new Handlers(this);
    }
}
