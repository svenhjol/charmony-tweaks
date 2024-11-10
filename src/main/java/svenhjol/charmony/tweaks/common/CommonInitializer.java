package svenhjol.charmony.tweaks.common;

import net.fabricmc.api.ModInitializer;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.common.animal_armor_grinding.AnimalArmorGrinding;
import svenhjol.charmony.tweaks.common.animal_damage_immunity.AnimalDamageImmunity;
import svenhjol.charmony.tweaks.common.animal_reviving.AnimalReviving;
import svenhjol.charmony.tweaks.common.campfires_heal_players.CampfiresHealPlayers;

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
        tweaks.run(Side.Common);
    }
}
