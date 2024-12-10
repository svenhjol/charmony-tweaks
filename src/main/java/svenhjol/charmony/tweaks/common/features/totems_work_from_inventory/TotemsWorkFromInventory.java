package svenhjol.charmony.tweaks.common.features.totems_work_from_inventory;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

@FeatureDefinition(side = Side.Common, description = """
    A totem will work from anywhere in the player's inventory as well as held in the main or offhand.
    This includes the Totem of Preserving, if enabled.""")
public final class TotemsWorkFromInventory extends SidedFeature {
    public final Providers providers;
    public final Handlers handlers;
    public final Advancements advancements;

    public TotemsWorkFromInventory(Mod mod) {
        super(mod);

        providers = new Providers(this);
        handlers = new Handlers(this);
        advancements = new Advancements(this);
    }

    public static TotemsWorkFromInventory feature() {
        return Tweaks.instance().feature(TotemsWorkFromInventory.class);
    }
}
