package svenhjol.charmony.tweaks.client.features.chiseled_bookshelves_show_book_on_hover;

import net.minecraft.util.Mth;
import svenhjol.charmony.core.annotations.Configurable;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.TweaksMod;

@FeatureDefinition(side = Side.Client)
public final class ChiseledBookshelvesShowBookOnHover extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;

    @Configurable(
        name = "Offset from center",
        description = "Number of pixels below the center of the screen at which the display text will be rendered.",
        requireRestart = false
    )
    private static int offsetFromCenter = 20;

    public ChiseledBookshelvesShowBookOnHover(Mod mod) {
        super(mod);
        this.registers = new Registers(this);
        this.handlers = new Handlers(this);
    }

    public static ChiseledBookshelvesShowBookOnHover feature() {
        return TweaksMod.instance().sidedFeature(ChiseledBookshelvesShowBookOnHover.class);
    }

    public int offsetFromCenter() {
        return Mth.clamp(offsetFromCenter, -1024, 1024);
    }
}
