package svenhjol.charmony.tweaks.common.features.item_frame_hiding;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Common, description = """
    Add amethyst shards to item frames to hide them.""")
public final class ItemFrameHiding extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;
    public final Networking networking;
    public final Advancements advancements;

    public ItemFrameHiding(Mod mod) {
        super(mod);

        registers = new Registers(this);
        handlers = new Handlers(this);
        networking = new Networking(this);
        advancements = new Advancements(this);
    }
}
