package svenhjol.charmony.tweaks.common.features.mob_drops;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.EntityKilledDropCallback;
import svenhjol.charmony.core.events.EntityTickCallback;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.common.features.mob_drops.mobs.*;

import java.util.ArrayList;
import java.util.List;

public final class Registers extends Setup<MobDrops> {
    public final List<DropProvider> drops = new ArrayList<>();
    public final SoundEvent shedFeatherSound;

    public Registers(MobDrops feature) {
        super(feature);
        drops.add(new CaveSpiderDrops(feature));
        drops.add(new ChickenDrops(feature));
        drops.add(new GoatDrops(feature));
        drops.add(new HuskDrops(feature));
        drops.add(new WitchDrops(feature));

        var shedFeatherSoundId = Tweaks.id("shed_feather");
        shedFeatherSound = Registry.register(BuiltInRegistries.SOUND_EVENT, shedFeatherSoundId, SoundEvent.createVariableRangeEvent(shedFeatherSoundId));
    }

    @Override
    public Runnable boot() {
        return () -> {
            EntityKilledDropCallback.EVENT.register(feature().handlers::entityKilledDrop);
            EntityTickCallback.EVENT.register(feature().handlers::entityTick);
        };
    }
}
