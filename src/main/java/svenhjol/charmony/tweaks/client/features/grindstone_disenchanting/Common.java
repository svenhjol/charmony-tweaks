package svenhjol.charmony.tweaks.client.features.grindstone_disenchanting;

import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.common.features.grindstone_disenchanting.GrindstoneDisenchanting;
import svenhjol.charmony.tweaks.common.features.grindstone_disenchanting.Handlers;

public final class Common {
    public final Handlers handlers;

    public Common() {
        var common = Tweaks.instance().feature(GrindstoneDisenchanting.class);
        handlers = common.handlers;
    }
}
