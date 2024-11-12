package svenhjol.charmony.tweaks.common.features.campfires_heal_players;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

@FeatureDefinition(side = Side.Common, description = """
    Standing within range and sight of a lit campfire provides a small regeneration boost.
    It does not work if there are enemies nearby.""")
public final class CampfiresHealPlayers extends SidedFeature {
    public final Handlers handlers;
    public final Advancements advancements;

    public CampfiresHealPlayers(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
        advancements = new Advancements(this);
    }

    public static CampfiresHealPlayers feature() {
        return Tweaks.instance().feature(CampfiresHealPlayers.class);
    }
}