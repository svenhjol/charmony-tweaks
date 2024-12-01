package svenhjol.charmony.tweaks.client.features.grindstone_disenchanting;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

import java.util.function.Supplier;

@FeatureDefinition(side = Side.Client, showInConfig = false)
public final class GrindstoneDisenchanting extends SidedFeature {
    public final Handlers handlers;
    public final Supplier<Common> common;

    public GrindstoneDisenchanting(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
        common = () -> new Common(this);
    }

    public static GrindstoneDisenchanting feature() {
        return Tweaks.instance().feature(GrindstoneDisenchanting.class);
    }
}
