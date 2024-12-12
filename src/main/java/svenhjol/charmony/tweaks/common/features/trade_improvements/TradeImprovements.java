package svenhjol.charmony.tweaks.common.features.trade_improvements;

import svenhjol.charmony.core.annotations.Configurable;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Common, description = "Adds more villager trades.")
public final class TradeImprovements extends SidedFeature {
    public final Registers registers;
    public final Providers providers;

    @Configurable(name = "Enchanted books", description = "If true, librarians will buy any enchanted book in return for emeralds.")
    private static boolean enchantedBooks = true;

    @Configurable(name = "Repaired anvils", description = "If true, armorers, weaponsmiths and toolsmiths will buy chipped or damaged anvils along with iron ingots in return for repaired anvils.")
    private static boolean repairedAnvils = true;

    @Configurable(name = "Leather for rotten flesh", description = "If true, leatherworkers will sell leather in return for rotten flesh.")
    private static boolean leatherForRottenFlesh = true;

    @Configurable(name = "Beef for rotten flesh", description = "If true, butchers will sell beef in return for rotten flesh.")
    private static boolean beefForRottenFlesh = true;

    @Configurable(name = "Phantom membrane", description = "If true, clerics will sell phantom membrane in return for emeralds.")
    private static boolean phantomMembrane = true;

    @Configurable(name = "Charmony mod items", description = "If true, wandering traders have a chance to sell various items from Charmony mods.")
    private static boolean charmonyModItems = true;

    public TradeImprovements(Mod mod) {
        super(mod);
        registers = new Registers(this);
        providers = new Providers(this);
    }

    public boolean enchantedBooks() {
        return enchantedBooks;
    }

    public boolean repairedAnvils() {
        return repairedAnvils;
    }

    public boolean leatherForRottenFlesh() {
        return leatherForRottenFlesh;
    }

    public boolean beefForRottenFlesh() {
        return beefForRottenFlesh;
    }

    public boolean phantomMembrane() {
        return phantomMembrane;
    }

    public boolean charmonyModItems() {
        return charmonyModItems;
    }
}
