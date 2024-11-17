package svenhjol.charmony.tweaks.common.features.item_repairing;

import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.AnvilEvents;

public final class Registers extends Setup<ItemRepairing> {
    public Registers(ItemRepairing feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            AnvilEvents.REPAIR.handle(feature().handlers::anvilRepair);
            AnvilEvents.ON_TAKE.handle(feature().handlers::anvilOnTake);
        };
    }
}
