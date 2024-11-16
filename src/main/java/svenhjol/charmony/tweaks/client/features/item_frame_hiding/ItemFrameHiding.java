package svenhjol.charmony.tweaks.client.features.item_frame_hiding;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Client, showInConfig = false)
public final class ItemFrameHiding extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;
    public final Common common;

    public ItemFrameHiding(Mod mod) {
        super(mod);
        common = new Common(this);
        handlers = new Handlers(this);
        registers = new Registers(this);
    }
}