package svenhjol.charmony.tweaks.common.features.crop_feather_falling;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.TweaksMod;

@FeatureDefinition(side = Side.Common, description = """
    Prevents crop trampling when wearing boots enchanted with Feather Falling.""")
public final class CropFeatherFalling extends SidedFeature {
    public final Handlers handlers;
    public final Advancements advancements;

    public CropFeatherFalling(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
        advancements = new Advancements(this);
    }

    public static CropFeatherFalling feature() {
        return TweaksMod.instance().sidedFeature(CropFeatherFalling.class);
    }
}
