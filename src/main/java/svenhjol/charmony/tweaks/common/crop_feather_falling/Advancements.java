package svenhjol.charmony.tweaks.common.crop_feather_falling;

import net.minecraft.server.level.ServerPlayer;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;

public final class Advancements extends Setup<CropFeatherFalling> {
    public Advancements(CropFeatherFalling feature) {
        super(feature);
    }

    public void preventedCropDamage(ServerPlayer player) {
        AdvancementHelper.trigger("prevented_crop_damage", player);
    }
}
