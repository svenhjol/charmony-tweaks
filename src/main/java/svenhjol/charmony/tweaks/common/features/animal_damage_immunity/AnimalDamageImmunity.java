package svenhjol.charmony.tweaks.common.features.animal_damage_immunity;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Common, description = "Tamed animals do not take direct damage from players.")
public final class AnimalDamageImmunity extends SidedFeature {
    public final Handlers handlers;
    public final Advancements advancements;

    public AnimalDamageImmunity(Mod mod) {
        super(mod);

        handlers = new Handlers(this);
        advancements = new Advancements(this);
    }

    public static AnimalDamageImmunity feature() {
        return Mod.getSidedFeature(AnimalDamageImmunity.class);
    }
}
