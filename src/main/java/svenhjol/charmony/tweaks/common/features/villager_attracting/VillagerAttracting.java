package svenhjol.charmony.tweaks.common.features.villager_attracting;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Common, description = """
    Attract villagers by holding a desired item. By default this is an emerald block.
    The item tag 'villager_loved' can be used to configure the items that attract a villager.""")
public final class VillagerAttracting extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;
    public final Advancements advancements;

    public VillagerAttracting(Mod mod) {
        super(mod);
        registers = new Registers(this);
        handlers = new Handlers(this);
        advancements = new Advancements(this);
    }
}
