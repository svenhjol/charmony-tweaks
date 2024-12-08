package svenhjol.charmony.tweaks.client.features.piglin_pointing;

import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.common.features.piglin_pointing.Handlers;
import svenhjol.charmony.tweaks.common.features.piglin_pointing.PiglinPointing;

public final class Common {
    public final Handlers handlers;

    public Common() {
        var feature = Tweaks.instance().feature(PiglinPointing.class);
        handlers = feature.handlers;
    }
}
