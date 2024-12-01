package svenhjol.charmony.tweaks.common.features.path_converting;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.tweaks.Tweaks;

public final class Registers extends Setup<PathConverting> {
    public final SoundEvent dirtToPathSound;
    public final SoundEvent pathToDirtSound;

    public Registers(PathConverting feature) {
        super(feature);

        var dirtToPathSoundId = Tweaks.id("convert_dirt_to_path");
        var pathToDirtSoundId = Tweaks.id("convert_path_to_dirt");

        dirtToPathSound = Registry.register(BuiltInRegistries.SOUND_EVENT, dirtToPathSoundId,
            SoundEvent.createVariableRangeEvent(dirtToPathSoundId));
        pathToDirtSound = Registry.register(BuiltInRegistries.SOUND_EVENT, pathToDirtSoundId,
            SoundEvent.createVariableRangeEvent(pathToDirtSoundId));
    }

    @Override
    public Runnable boot() {
        return () -> {
            UseBlockCallback.EVENT.register(feature().handlers::useBlock);
        };
    }
}
