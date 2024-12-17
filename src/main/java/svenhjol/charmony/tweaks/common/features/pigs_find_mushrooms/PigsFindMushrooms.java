package svenhjol.charmony.tweaks.common.features.pigs_find_mushrooms;

import net.minecraft.util.Mth;
import svenhjol.charmony.core.annotations.Configurable;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.TweaksMod;

@FeatureDefinition(side = Side.Common, description = """
    Pigs have a chance to find mushrooms from certain blocks. By default mushrooms are unearthed from mycelium and podzol blocks.
    The item tag 'pigs_find_mushrooms' can be used to configure the blocks in which mushrooms can be unearthed by a pig.""")
public final class PigsFindMushrooms extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;
    public final Advancements advancements;

    @Configurable(
        name = "Chance to find mushroom",
        description = "Approximately 1 in X chance of a pig finding a mushroom per game tick.",
        requireRestart = false
    )
    private static int findChance = 1200;

    @Configurable(
        name = "Chance to erode block",
        description = "Chance (out of 1.0) of a block being converted to dirt when a pig finds a mushroom.",
        requireRestart = false
    )
    private static double erodeChance = 0.25d;

    public PigsFindMushrooms(Mod mod) {
        super(mod);

        registers = new Registers(this);
        handlers = new Handlers(this);
        advancements = new Advancements(this);
    }

    public static PigsFindMushrooms feature() {
        return TweaksMod.instance().sidedFeature(PigsFindMushrooms.class);
    }

    public int findChance() {
        return Mth.clamp(findChance, 100, 20000);
    }

    public double erodeChance() {
        return Mth.clamp(erodeChance, 0.0d, 1.0f);
    }
}
