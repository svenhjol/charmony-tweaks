package svenhjol.charmony.tweaks.common;

import net.fabricmc.api.ModInitializer;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.common.features.animal_armor_grinding.AnimalArmorGrinding;
import svenhjol.charmony.tweaks.common.features.animal_damage_immunity.AnimalDamageImmunity;
import svenhjol.charmony.tweaks.common.features.animal_reviving.AnimalReviving;
import svenhjol.charmony.tweaks.common.features.campfires_heal_players.CampfiresHealPlayers;
import svenhjol.charmony.tweaks.common.features.crop_feather_falling.CropFeatherFalling;
import svenhjol.charmony.tweaks.common.features.crop_replanting.CropReplanting;
import svenhjol.charmony.tweaks.common.features.deepslate_dungeons.DeepslateDungeons;
import svenhjol.charmony.tweaks.common.features.grindstone_disenchanting.GrindstoneDisenchanting;
import svenhjol.charmony.tweaks.common.features.item_frame_hiding.ItemFrameHiding;
import svenhjol.charmony.tweaks.common.features.item_repairing.ItemRepairing;
import svenhjol.charmony.tweaks.common.features.item_restocking.ItemRestocking;

public class CommonInitializer implements ModInitializer {
    @Override
    public void onInitialize() {
        // Ensure charmony is launched first.
        svenhjol.charmony.core.common.CommonInitializer.init();

        // Prepare and run the mod.
        var tweaks = Tweaks.instance();
        tweaks.addFeature(AnimalArmorGrinding.class);
        tweaks.addFeature(AnimalDamageImmunity.class);
        tweaks.addFeature(AnimalReviving.class);
        tweaks.addFeature(CampfiresHealPlayers.class);
        tweaks.addFeature(CropFeatherFalling.class);
        tweaks.addFeature(CropReplanting.class);
        tweaks.addFeature(DeepslateDungeons.class);
        tweaks.addFeature(GrindstoneDisenchanting.class);
        tweaks.addFeature(ItemFrameHiding.class);
        tweaks.addFeature(ItemRepairing.class);
        tweaks.addFeature(ItemRestocking.class);
        tweaks.run(Side.Common);
    }
}
