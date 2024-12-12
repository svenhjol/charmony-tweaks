package svenhjol.charmony.tweaks.client.features.mob_textures.custom_renderers;

import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.client.features.mob_textures.Handlers;
import svenhjol.charmony.tweaks.client.features.mob_textures.MobTextures;
import svenhjol.charmony.tweaks.client.features.mob_textures.Registers;

public interface CustomRenderer {
    Handlers handlers = Tweaks.instance().sidedFeature(MobTextures.class).handlers;
    Registers registers = Tweaks.instance().sidedFeature(MobTextures.class).registers;
}
