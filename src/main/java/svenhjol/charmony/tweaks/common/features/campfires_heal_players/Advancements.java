package svenhjol.charmony.tweaks.common.features.campfires_heal_players;

import net.minecraft.server.level.ServerPlayer;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;

public final class Advancements extends Setup<CampfiresHealPlayers> {
    public Advancements(CampfiresHealPlayers feature) {
        super(feature);
    }

    public void healedNearCampfire(ServerPlayer player) {
        AdvancementHelper.trigger("healed_near_campfire", player);
    }
}
