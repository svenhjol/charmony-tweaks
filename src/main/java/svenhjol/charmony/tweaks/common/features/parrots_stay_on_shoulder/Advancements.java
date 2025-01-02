package svenhjol.charmony.tweaks.common.features.parrots_stay_on_shoulder;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helpers.AdvancementHelper;

public final class Advancements extends Setup<ParrotsStayOnShoulder> {
    public Advancements(ParrotsStayOnShoulder feature) {
        super(feature);
    }

    public void dismountedFromShoulder(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            AdvancementHelper.trigger("dismounted_from_shoulder", serverPlayer);
        }
    }

    public void remainedOnShoulder(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            AdvancementHelper.trigger("remained_on_shoulder", serverPlayer);
        }
    }
}
