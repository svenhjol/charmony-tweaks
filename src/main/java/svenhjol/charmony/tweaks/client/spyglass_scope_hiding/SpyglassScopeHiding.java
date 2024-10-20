package svenhjol.charmony.tweaks.client.spyglass_scope_hiding;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Client, description = "Removes the border when zooming in with the spyglass.")
public final class SpyglassScopeHiding extends SidedFeature {
    public SpyglassScopeHiding(Mod mod) {
        super(mod);
    }
}
