package svenhjol.charmony.tweaks.client.features.shulker_box_transferring;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.TweaksMod;

/**
 * This is the client-side processing of the ShulkerBoxTransferring common feature.
 */
@FeatureDefinition(side = Side.Client, showInConfig = false)
public final class ShulkerBoxTransferring extends SidedFeature {
    public ShulkerBoxTransferring(Mod mod) {
        super(mod);
    }

    public static ShulkerBoxTransferring feature() {
        return TweaksMod.instance().sidedFeature(ShulkerBoxTransferring.class);
    }
}
