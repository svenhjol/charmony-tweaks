package svenhjol.charmony.tweaks.client.totem_emergency_swap;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

@FeatureDefinition(side = Side.Client, description = """
    Adds a keybind to swap any totem in your inventory with your offhand.
    Note that this requires an off-screen drag and drop.
    Pressing the key will interrupt the mouse operation for one tick.""")
public final class TotemEmergencySwap extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;

    public TotemEmergencySwap(Mod mod) {
        super(mod);
        registers = new Registers(this);
        handlers = new Handlers(this);
    }

    public static TotemEmergencySwap feature() {
        return Tweaks.instance().feature(TotemEmergencySwap.class);
    }
}
