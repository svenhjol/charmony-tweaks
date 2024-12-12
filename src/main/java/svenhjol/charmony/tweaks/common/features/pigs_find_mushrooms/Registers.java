package svenhjol.charmony.tweaks.common.features.pigs_find_mushrooms;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.sounds.SoundEvent;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.common.CommonRegistry;
import svenhjol.charmony.tweaks.Tweaks;

import java.util.function.Supplier;

public final class Registers extends Setup<PigsFindMushrooms> {
    public final Supplier<SoundEvent> sniffingSound;

    public Registers(PigsFindMushrooms feature) {
        super(feature);
        sniffingSound = CommonRegistry.instance().sound(Tweaks.id("pig_sniffing"));
    }

    @Override
    public Runnable boot() {
        return () -> {
            ServerEntityEvents.ENTITY_LOAD.register(feature().handlers::entityJoin);
        };
    }
}
