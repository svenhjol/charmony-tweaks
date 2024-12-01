package svenhjol.charmony.tweaks.common.features.path_converting;

import net.minecraft.server.level.ServerPlayer;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;

public final class Advancements extends Setup<PathConverting> {
    public Advancements(PathConverting feature) {
        super(feature);
    }

    public void convertedDirtToPath(ServerPlayer player) {
        AdvancementHelper.trigger("converted_dirt_to_path", player);
    }

    public void convertedPathToDirt(ServerPlayer player) {
        AdvancementHelper.trigger("converted_path_to_dirt", player);
    }
}
