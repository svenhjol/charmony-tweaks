package svenhjol.charmony.tweaks.client.repair_cost_visible;

import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.enums.Side;

@Feature(side = Side.Client, description = "An item's repair cost is shown in its tooltip when viewing on the anvil.")
public final class RepairCostVisible extends ModFeature {
    public Handlers handlers;

    public RepairCostVisible(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
    }
}
