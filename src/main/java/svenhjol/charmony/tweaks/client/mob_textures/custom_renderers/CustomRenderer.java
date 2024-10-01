package svenhjol.charmony.tweaks.client.mob_textures.custom_renderers;

import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.client.mob_textures.Handlers;
import svenhjol.charmony.tweaks.client.mob_textures.MobTextures;
import svenhjol.charmony.tweaks.client.mob_textures.Registers;

public interface CustomRenderer {
    Handlers handlers = Tweaks.instance().feature(MobTextures.class).handlers;
    Registers registers = Tweaks.instance().feature(MobTextures.class).registers;
}
