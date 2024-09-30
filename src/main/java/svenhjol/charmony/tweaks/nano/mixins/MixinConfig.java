package svenhjol.charmony.tweaks.nano.mixins;

import svenhjol.charmony.scaffold.nano.mixins.BaseMixinConfig;
import svenhjol.charmony.tweaks.nano.Tweaks;

public class MixinConfig extends BaseMixinConfig {
    @Override
    protected String modId() {
        return Tweaks.ID;
    }

    @Override
    protected String rootClassPath() {
        return "svenhjol.charmony.tweaks.nano";
    }
}
