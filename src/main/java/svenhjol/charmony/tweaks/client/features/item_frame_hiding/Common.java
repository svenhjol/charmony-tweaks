package svenhjol.charmony.tweaks.client.features.item_frame_hiding;

import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.common.features.item_frame_hiding.Registers;

public final class Common extends Setup<ItemFrameHiding> {
    public final Registers registers;

    public Common(ItemFrameHiding feature) {
        super(feature);
        var common = Tweaks.instance().feature(svenhjol.charmony.tweaks.common.features.item_frame_hiding.ItemFrameHiding.class);
        registers = common.registers;
    }
}
