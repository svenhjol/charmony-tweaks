package svenhjol.charmony.tweaks.client.features.item_tidying;

import svenhjol.charmony.core.annotations.Configurable;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Client, description = """
    Button to automatically stack and arrange items in inventory and containers.""")
public final class ItemTidying extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;
    public final Providers providers;

    @Configurable(
        name = "Keybind sorts multiple inventories",
        description = """
            If true, the sorting key will sort player and non-player inventories in the same keypress.
            If false, the sorting key only sorts non-player inventories unless viewing the player's inventory screen.""",
        requireRestart = false
    )
    private static boolean keybindSortsMultipleInventories = false;

    public ItemTidying(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
        registers = new Registers(this);
        providers = new Providers(this);
    }

    public static ItemTidying feature() {
        return Mod.getSidedFeature(ItemTidying.class);
    }

    public boolean keybindSortsMultipleInventories() {
        return keybindSortsMultipleInventories;
    }
}
