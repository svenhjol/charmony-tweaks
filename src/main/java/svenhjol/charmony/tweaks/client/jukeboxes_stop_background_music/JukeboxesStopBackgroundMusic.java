package svenhjol.charmony.tweaks.client.jukeboxes_stop_background_music;

import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.enums.Side;

@Feature(side = Side.Client, description = """
    Playing a music disc in a jukebox prevents background music from playing at the same time.""")
public final class JukeboxesStopBackgroundMusic extends ModFeature {
    public final Registers registers;
    public final Handlers handlers;

    public JukeboxesStopBackgroundMusic(Mod mod) {
        super(mod);
        registers = new Registers(this);
        handlers = new Handlers(this);
    }
}
