package svenhjol.charmony.tweaks.common.features.villager_attracting;

import net.minecraft.server.level.ServerPlayer;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;

public final class Advancements extends Setup<VillagerAttracting> {
    public Advancements(VillagerAttracting feature) {
        super(feature);
    }

    public void attractedVillager(ServerPlayer player) {
        AdvancementHelper.trigger("attracted_villager", player);
    }
}
