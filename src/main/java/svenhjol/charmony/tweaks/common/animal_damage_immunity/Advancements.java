package svenhjol.charmony.tweaks.common.animal_damage_immunity;

import net.minecraft.server.level.ServerPlayer;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;

public class Advancements extends Setup<AnimalDamageImmunity> {
    public Advancements(AnimalDamageImmunity feature) {
        super(feature);
    }
    
    public void negatedAnimalDamage(ServerPlayer player) {
        AdvancementHelper.trigger("negated_animal_damage", player);
    }
}
