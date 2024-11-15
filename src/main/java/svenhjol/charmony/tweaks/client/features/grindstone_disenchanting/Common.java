package svenhjol.charmony.tweaks.client.features.grindstone_disenchanting;

import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.common.features.grindstone_disenchanting.Handlers;

public final class Common extends Setup<GrindstoneDisenchanting> {
    public final Handlers handlers;

    public Common(GrindstoneDisenchanting feature) {
        super(feature);
        var common = Tweaks.instance().feature(svenhjol.charmony.tweaks.common.features.grindstone_disenchanting.GrindstoneDisenchanting.class);
        handlers = common.handlers;
    }
}
