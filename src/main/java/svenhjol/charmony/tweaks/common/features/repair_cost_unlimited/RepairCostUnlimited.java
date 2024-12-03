package svenhjol.charmony.tweaks.common.features.repair_cost_unlimited;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Common, description = "Allows anvil repair of items with repair cost 39 or more.")
public final class RepairCostUnlimited extends SidedFeature {
    public RepairCostUnlimited(Mod mod) {
        super(mod);
    }
}
