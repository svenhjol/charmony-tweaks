package svenhjol.charmony.tweaks.client.item_tidying;

import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.enums.Side;

@Feature(side = Side.Client, description = "Button to automatically stack and arrange items in inventory and containers.")
public final class ItemTidying extends ModFeature {
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
