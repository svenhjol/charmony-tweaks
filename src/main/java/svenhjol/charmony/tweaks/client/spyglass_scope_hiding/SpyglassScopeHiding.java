package svenhjol.charmony.tweaks.client.spyglass_scope_hiding;

import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.enums.Side;

@Feature(side = Side.Client, description = "Removes the border when zooming in with the spyglass.")
public final class SpyglassScopeHiding extends ModFeature {
    public SpyglassScopeHiding(Mod mod) {
        super(mod);
    }
}
