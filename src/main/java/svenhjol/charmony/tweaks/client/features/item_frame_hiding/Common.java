package svenhjol.charmony.tweaks.client.features.item_frame_hiding;

import svenhjol.charmony.tweaks.TweaksMod;
import svenhjol.charmony.tweaks.common.features.item_frame_hiding.ItemFrameHiding;
import svenhjol.charmony.tweaks.common.features.item_frame_hiding.Registers;

public final class Common {
    public final Registers registers;

    public Common() {
        var common = TweaksMod.instance().sidedFeature(ItemFrameHiding.class);
        registers = common.registers;
    }
}
