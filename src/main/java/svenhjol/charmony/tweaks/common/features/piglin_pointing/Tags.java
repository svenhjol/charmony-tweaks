package svenhjol.charmony.tweaks.common.features.piglin_pointing;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.structure.Structure;
import svenhjol.charmony.core.Charmony;

public final class Tags {
    public static final TagKey<Item> PIGLIN_BARTERS_FOR_BASTIONS = TagKey.create(Registries.ITEM,
        Charmony.id("piglin_barters_for_bastions"));

    public static final TagKey<Item> PIGLIN_BARTERS_FOR_DIRECTIONS = TagKey.create(Registries.ITEM,
        Charmony.id("piglin_barters_for_directions"));

    public static final TagKey<Item> PIGLIN_BARTERS_FOR_FORTRESSES = TagKey.create(Registries.ITEM,
        Charmony.id("piglin_barters_for_fortresses"));

    public static final TagKey<Structure> PIGLIN_BASTION_LOCATED = TagKey.create(Registries.STRUCTURE,
        Charmony.id("piglin_bastion_located"));

    public static final TagKey<Structure> PIGLIN_FORTRESS_LOCATED = TagKey.create(Registries.STRUCTURE,
        Charmony.id("piglin_fortress_located"));
}
