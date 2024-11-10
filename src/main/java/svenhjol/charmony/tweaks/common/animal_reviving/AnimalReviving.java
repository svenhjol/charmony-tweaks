package svenhjol.charmony.tweaks.common.animal_reviving;

import svenhjol.charmony.core.annotations.Configurable;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

@FeatureDefinition(side = Side.Common, description = """
    A tamed animal with a name drops its name tag on death.
    Right-click (use) the name tag while holding a Totem of Undying to revive the animal and consume the totem.""")
public final class AnimalReviving extends SidedFeature {
    public final Advancements advancements;
    public final Handlers handlers;
    public final Registers registers;

    @Configurable(
        name = "Drop loot on death",
        description = """
            If true, tamed and named animals drop items from their loot table (such as leather from horses) when they die.
            This could be unwanted behavior as it can be used to dupe items by killing and resurrecting an animal repeatedly."""
    )
    private static boolean dropLootOnDeath = false;

    @Configurable(
        name = "Drop experience on death",
        description = """
            If true, tamed and named animals drop experience when they die. This could be unwanted behavior as it can be used
            to receive an increasing amount of experience by killing and resurrecting an animal repeatedly."""
    )
    private static boolean dropExperienceOnDeath = false;

    public AnimalReviving(Mod mod) {
        super(mod);
        registers = new Registers(this);
        advancements = new Advancements(this);
        handlers = new Handlers(this);
    }

    public static AnimalReviving feature() {
        return Tweaks.instance().feature(AnimalReviving.class);
    }

    public boolean dropLootOnDeath() {
        return dropLootOnDeath;
    }

    public boolean dropExperienceOnDeath() {
        return dropExperienceOnDeath;
    }
}
