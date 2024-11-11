package svenhjol.charmony.tweaks.common.animal_damage_immunity;

import net.minecraft.server.level.ServerPlayer;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;

public final class Advancements extends Setup<AnimalDamageImmunity> {
    public Advancements(AnimalDamageImmunity feature) {
        super(feature);
    }
    
    public void preventedAnimalDamage(ServerPlayer player) {
        AdvancementHelper.trigger("prevented_animal_damage", player);
    }
}
