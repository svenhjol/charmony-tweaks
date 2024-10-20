package svenhjol.charmony.tweaks.client.shulker_boxes_show_when_hovering;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Client, description = "Shulker boxes show their content when hovering over the inventory item.")
public final class ShulkerBoxesShowWhenHovering extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;

    public ShulkerBoxesShowWhenHovering(Mod mod) {
        super(mod);
        registers = new Registers(this);
        handlers = new Handlers(this);
    }
}
