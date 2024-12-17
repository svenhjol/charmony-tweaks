package svenhjol.charmony.tweaks.common.features.villager_attracting;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import svenhjol.charmony.tweaks.TweaksMod;

public final class Tags {
    public static final TagKey<Item> VILLAGER_LOVED = TagKey.create(Registries.ITEM,
        TweaksMod.id("villager_loved"));
}
