package svenhjol.charmony.tweaks.client.mixins;

import svenhjol.charmony.core.base.MixinConfig;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

public class ClientMixinConfig extends MixinConfig {
    @Override
    protected String modId() {
        return Tweaks.ID;
    }

    @Override
    protected String modRoot() {
        return "svenhjol.charmony.tweaks";
    }

    @Override
    protected Side side() {
        return Side.Client;
    }
}
