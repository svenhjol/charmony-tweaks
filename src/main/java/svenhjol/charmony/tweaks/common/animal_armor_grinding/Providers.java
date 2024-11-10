package svenhjol.charmony.tweaks.common.animal_armor_grinding;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import svenhjol.charmony.api.GrindableItemProvider;
import svenhjol.charmony.core.Api;
import svenhjol.charmony.core.base.Setup;

import java.util.List;

public class Providers extends Setup<AnimalArmorGrinding> implements GrindableItemProvider {
    public Providers(AnimalArmorGrinding feature) {
        super(feature);
        Api.registerProvider(this); // Must register this provider with the Api
    }

    @Override
    public List<Pair<ItemLike, ItemLike>> getItemGrindResults() {
        return List.of(
            Pair.of(Items.WOLF_ARMOR, Items.ARMADILLO_SCUTE),
            Pair.of(Items.SADDLE, Items.LEATHER),
            Pair.of(Items.LEATHER_HORSE_ARMOR, Items.LEATHER),
            Pair.of(Items.IRON_HORSE_ARMOR, Items.IRON_INGOT),
            Pair.of(Items.GOLDEN_HORSE_ARMOR, Items.GOLD_INGOT),
            Pair.of(Items.DIAMOND_HORSE_ARMOR, Items.DIAMOND)
        );
    }

    @Override
    public Runnable boot() {
        return () -> Api.consume(GrindableItemProvider.class,
            provider -> provider.getItemGrindResults().forEach(
                result -> feature().registers.recipes.put(result.getFirst(), result.getSecond())));
    }
}
