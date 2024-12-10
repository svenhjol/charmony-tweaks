package svenhjol.charmony.tweaks.common.features.suspicious_block_creating;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

@FeatureDefinition(side = Side.Common, description = "Use a piston to push an item into sand or gravel, making it suspicious.")
public final class SuspiciousBlockCreating extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;
    public final Advancements advancements;

    public SuspiciousBlockCreating(Mod mod) {
        super(mod);

        registers = new Registers(this);
        handlers = new Handlers(this);
        advancements = new Advancements(this);
    }

    public static SuspiciousBlockCreating feature() {
        return Tweaks.instance().feature(SuspiciousBlockCreating.class);
    }
}
