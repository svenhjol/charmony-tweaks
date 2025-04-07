package svenhjol.charmony.tweaks.common.features.trade_improvements;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import svenhjol.charmony.tweaks.TweaksMod;

public class Tags {
    public static final TagKey<Item> COLORED_BUNDLES = TagKey.create(Registries.ITEM,
        TweaksMod.id("colored_bundles"));

    public static final TagKey<Item> COLORED_CANDLES = TagKey.create(Registries.ITEM,
        TweaksMod.id("colored_candles"));

    public static final TagKey<Block> COLORED_GLASS = TagKey.create(Registries.BLOCK,
        TweaksMod.id("colored_glass"));

    public static final TagKey<Block> COLORED_TERRACOTTAS = TagKey.create(Registries.BLOCK,
        TweaksMod.id("colored_terracottas"));
}
