package svenhjol.charmony.tweaks.common.features.nether_portal_blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import svenhjol.charmony.tweaks.TweaksMod;

public class Tags {
    public static final TagKey<Block> NETHER_PORTAL_BLOCKS = TagKey.create(Registries.BLOCK,
        TweaksMod.id("nether_portal_blocks"));
}
