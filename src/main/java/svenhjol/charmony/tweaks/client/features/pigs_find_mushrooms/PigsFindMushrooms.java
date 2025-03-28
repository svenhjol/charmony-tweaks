package svenhjol.charmony.tweaks.client.features.pigs_find_mushrooms;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

import java.util.function.Supplier;

@FeatureDefinition(side = Side.Client, canBeDisabledInConfig = false)
public final class PigsFindMushrooms extends SidedFeature {
    public final Handlers handlers;
    public final Supplier<Common> common;

    public PigsFindMushrooms(Mod mod) {
        super(mod);
        common = Common::new;
        handlers = new Handlers(this);
    }

    public static PigsFindMushrooms feature() {
        return Mod.getSidedFeature(PigsFindMushrooms.class);
    }
}
