package svenhjol.charmony.tweaks.common.features.parrots_stay_on_shoulder;

import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.PlayerTickCallback;

public final class Registers extends Setup<ParrotsStayOnShoulder> {
    public Registers(ParrotsStayOnShoulder feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            PlayerTickCallback.EVENT.register(feature().handlers::playerTick);
        };
    }
}
