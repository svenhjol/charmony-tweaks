package svenhjol.charmony.tweaks.client.features.chiseled_bookshelves_show_book_on_hover;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

@FeatureDefinition(side = Side.Client, description = """
    Shows the name and type of book when looking at a slot of a chiseled bookshelf.""")
public final class ChiseledBookshelvesShowBookOnHover extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;

    public ChiseledBookshelvesShowBookOnHover(Mod mod) {
        super(mod);
        this.registers = new Registers(this);
        this.handlers = new Handlers(this);
    }

    public static ChiseledBookshelvesShowBookOnHover feature() {
        return Tweaks.instance().sidedFeature(ChiseledBookshelvesShowBookOnHover.class);
    }
}
