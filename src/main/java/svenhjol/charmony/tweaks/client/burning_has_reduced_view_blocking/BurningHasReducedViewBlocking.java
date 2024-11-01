package svenhjol.charmony.tweaks.client.burning_has_reduced_view_blocking;

import net.minecraft.util.Mth;
import svenhjol.charmony.core.annotations.Configurable;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

@FeatureDefinition(side = Side.Client)
public final class BurningHasReducedViewBlocking extends SidedFeature {
    public final Handlers handlers;

    @Configurable(
        name = "Vertical position",
        description = "0.0 is default, negative values move the fire down, positive values move the fire up.",
        requireRestart = false
    )
    private static double verticalPosition = -0.16d;

    @Configurable(
        name = "Scaled size",
        description = "1.0 is default, negative values shrink the fire, positive values enlarge the fire.",
        requireRestart = false
    )
    private static double scaledSize = 0.55d;

    public BurningHasReducedViewBlocking(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
    }

    public static BurningHasReducedViewBlocking feature() {
        return Tweaks.instance().feature(BurningHasReducedViewBlocking.class);
    }

    public double verticalPosition() {
        return Mth.clamp(verticalPosition, -1.0d, 1.0d);
    }

    public float scaledSize() {
        return Mth.clamp((float)scaledSize, 0.1f, 2.0f);
    }
}
