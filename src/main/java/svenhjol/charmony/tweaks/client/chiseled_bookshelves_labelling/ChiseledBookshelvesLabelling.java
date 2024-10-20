package svenhjol.charmony.tweaks.client.chiseled_bookshelves_labelling;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

@FeatureDefinition(side = Side.Client, description = """
    Shows the name and type of book when looking at a slot of a chiseled bookshelf.""")
public final class ChiseledBookshelvesLabelling extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;

    public ChiseledBookshelvesLabelling(Mod mod) {
        super(mod);
        this.registers = new Registers(this);
        this.handlers = new Handlers(this);
    }

    public static ChiseledBookshelvesLabelling feature() {
        return Tweaks.instance().feature(ChiseledBookshelvesLabelling.class);
    }
}
