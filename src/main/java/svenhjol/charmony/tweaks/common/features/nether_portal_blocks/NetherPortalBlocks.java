package svenhjol.charmony.tweaks.common.features.nether_portal_blocks;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Common, description = """
    Adds more blocks that can be used to build nether portals. By default this adds Crying Obsidian.
    The item tag 'nether_portal_blocks' can be used to configure the blocks that can be used to build portals.""")
public final class NetherPortalBlocks extends SidedFeature {
    public final Registers registers;

    public NetherPortalBlocks(Mod loader) {
        super(loader);

        registers = new Registers(this);
    }
}
