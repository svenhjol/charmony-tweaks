package svenhjol.charmony.tweaks.mixins;

import svenhjol.charmony.scaffold.mixins.BaseMixinConfig;
import svenhjol.charmony.tweaks.Tweaks;

public class MixinConfig extends BaseMixinConfig {
    @Override
    protected String modId() {
        return Tweaks.ID;
    }

    @Override
    protected String rootClassPath() {
        return "svenhjol.charmony.tweaks";
    }
}
