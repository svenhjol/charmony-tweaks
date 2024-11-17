package svenhjol.charmony.tweaks.common.features.item_repairing;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import svenhjol.charmony.tweaks.Tweaks;

public final class Tags {
    public static final TagKey<Item> REPAIRABLE_USING_SCRAP = TagKey.create(Registries.ITEM,
        Tweaks.id("repairable_using_scrap"));
}
