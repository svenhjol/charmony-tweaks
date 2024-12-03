package svenhjol.charmony.tweaks.common.features.respawn_anchors_work_everywhere;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Common, enabledByDefault = false, description = """
    The repsawn anchor can be used in any dimension.
    This feature changes core gameplay so is disabled by default.""")
public final class RespawnAnchorsWorkEverywhere extends SidedFeature {
    public RespawnAnchorsWorkEverywhere(Mod mod) {
        super(mod);
    }
}
