package svenhjol.charmony.tweaks.client.features.piglin_pointing;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

import java.util.function.Supplier;

@FeatureDefinition(side = Side.Client, canBeDisabledInConfig = false)
public final class PiglinPointing extends SidedFeature {
    public final Handlers handlers;
    public final Supplier<Common> common;

    public PiglinPointing(Mod mod) {
        super(mod);

        common = Common::new;
        handlers = new Handlers(this);
    }

    public static PiglinPointing feature() {
        return Mod.getSidedFeature(PiglinPointing.class);
    }
}
