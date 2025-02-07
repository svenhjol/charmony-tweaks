package svenhjol.charmony.tweaks.common.features.chiseled_bookshelves_show_book_on_hover;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Common, description = """
    Shows the name and type of book when looking at a slot of a chiseled bookshelf.""")
public class ChiseledBookshelvesShowBookOnHover extends SidedFeature {
    public ChiseledBookshelvesShowBookOnHover(Mod mod) {
        super(mod);
    }
}
