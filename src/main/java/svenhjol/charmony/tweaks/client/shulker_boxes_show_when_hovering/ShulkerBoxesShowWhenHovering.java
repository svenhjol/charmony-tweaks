package svenhjol.charmony.tweaks.client.shulker_boxes_show_when_hovering;

import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.enums.Side;

@Feature(side = Side.Client, description = "Shulker boxes show their content when hovering over the inventory item.")
public final class ShulkerBoxesShowWhenHovering extends ModFeature {
    public final Registers registers;
    public final Handlers handlers;

    public ShulkerBoxesShowWhenHovering(Mod mod) {
        super(mod);
        registers = new Registers(this);
        handlers = new Handlers(this);
    }
}
