package svenhjol.charmony.tweaks.common.features.crop_replanting;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import svenhjol.charmony.core.base.Setup;

public final class Registers extends Setup<CropReplanting> {
    public Registers(CropReplanting feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            UseBlockCallback.EVENT.register(feature().handlers::useBlock);
        };
    }
}
