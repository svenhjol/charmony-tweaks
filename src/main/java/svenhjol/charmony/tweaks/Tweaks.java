package svenhjol.charmony.tweaks;

import svenhjol.charmony.scaffold.base.Mod;

public class Tweaks extends Mod {
    public static final String ID = "charmony-tweaks-nano";
    private static Tweaks instance;

    private Tweaks() {}

    @Override
    public String id() {
        return ID;
    }

    public static Tweaks instance() {
        if (instance == null) {
            instance = new Tweaks();
        }
        return instance;
    }
}
