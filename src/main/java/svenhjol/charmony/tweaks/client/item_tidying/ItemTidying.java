package svenhjol.charmony.tweaks.client.item_tidying;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Client, description = "Button to automatically stack and arrange items in inventory and containers.")
public final class ItemTidying extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;
    public final Providers providers;

    public ItemTidying(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
        registers = new Registers(this);
        providers = new Providers(this);
    }
}
