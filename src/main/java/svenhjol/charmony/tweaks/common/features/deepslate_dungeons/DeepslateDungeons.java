package svenhjol.charmony.tweaks.common.features.deepslate_dungeons;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.TweaksMod;

@FeatureDefinition(side = Side.Common, description = """
    Dungeons in the deepslate layer will be constructed of deepslate bricks and cobbled deepslate.""")
public final class DeepslateDungeons extends SidedFeature {
    public final Handlers handlers;

    public DeepslateDungeons(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
    }

    public static DeepslateDungeons feature() {
        return TweaksMod.instance().sidedFeature(DeepslateDungeons.class);
    }
}