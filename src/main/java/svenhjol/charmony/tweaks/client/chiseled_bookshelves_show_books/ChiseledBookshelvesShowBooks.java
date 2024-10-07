package svenhjol.charmony.tweaks.client.chiseled_bookshelves_show_books;

import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;

@Feature(side = Side.Client)
public class ChiseledBookshelvesShowBooks extends ModFeature {
    public final Registers registers;
    public final Handlers handlers;

    public ChiseledBookshelvesShowBooks(Mod mod) {
        super(mod);
        this.registers = new Registers(this);
        this.handlers = new Handlers(this);
    }

    public static ChiseledBookshelvesShowBooks feature() {
        return Tweaks.instance().feature(ChiseledBookshelvesShowBooks.class);
    }
}
