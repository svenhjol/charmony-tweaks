package svenhjol.charmony.tweaks.common.features.suspicious_block_creating;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;
import svenhjol.charmony.core.helper.PlayerHelper;

public final class Advancements extends Setup<SuspiciousBlockCreating> {
    public Advancements(SuspiciousBlockCreating feature) {
        super(feature);
    }

    public void createdSuspiciousBlock(ServerLevel level, BlockPos pos) {
        PlayerHelper.getPlayersInRange(level, pos, 8.0d).forEach(
            player -> AdvancementHelper.trigger("created_suspicious_block", player));
    }
}