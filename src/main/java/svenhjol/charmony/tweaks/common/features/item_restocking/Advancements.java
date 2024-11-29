package svenhjol.charmony.tweaks.common.features.item_restocking;

import net.minecraft.server.level.ServerPlayer;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;

public final class Advancements extends Setup<ItemRestocking> {
    public Advancements(ItemRestocking feature) {
        super(feature);
    }

    public void restockedCurrentItem(ServerPlayer player) {
        AdvancementHelper.trigger("restocked_current_item", player);
    }
}
