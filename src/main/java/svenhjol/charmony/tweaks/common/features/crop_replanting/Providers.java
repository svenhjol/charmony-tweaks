package svenhjol.charmony.tweaks.common.features.crop_replanting;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import svenhjol.charmony.api.AutoPickupItemProvider;
import svenhjol.charmony.api.QuickReplantProvider;
import svenhjol.charmony.core.Api;
import svenhjol.charmony.core.base.Setup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Providers extends Setup<CropReplanting> implements QuickReplantProvider {
    public final List<BlockState> replantable = new ArrayList<>();
    public final List<Function<Player, Boolean>> autoPickup = new ArrayList<>();
    public final List<Block> notReplantable = List.of(
        Blocks.TORCHFLOWER,
        Blocks.PITCHER_CROP,
        Blocks.PITCHER_PLANT
    );

    public Providers(CropReplanting feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            Api.registerProvider(this);

            // Listen to quick replant providers.
            Api.consume(QuickReplantProvider.class,
                provider -> provider.getHarvestableBlocks().forEach(
                    supplier -> replantable.add(supplier.get())));

            // Listen to auto pickup providers.
            Api.consume(AutoPickupItemProvider.class,
                provider -> autoPickup.add(provider.getAutoPickupItemChecks()));
        };
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public List<Supplier<BlockState>> getHarvestableBlocks() {
        List<Supplier<BlockState>> harvestables = new ArrayList<>(List.of(
            () -> Blocks.BEETROOTS.defaultBlockState().setValue(BeetrootBlock.AGE, 3),
            () -> Blocks.CARROTS.defaultBlockState().setValue(CarrotBlock.AGE, 7),
            () -> Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 3),
            () -> Blocks.POTATOES.defaultBlockState().setValue(PotatoBlock.AGE, 7),
            () -> Blocks.WHEAT.defaultBlockState().setValue(CropBlock.AGE, 7),
            () -> Blocks.PITCHER_CROP.defaultBlockState().setValue(PitcherCropBlock.AGE, 4),
            () -> Blocks.PITCHER_PLANT.defaultBlockState(),
            () -> Blocks.TORCHFLOWER.defaultBlockState()
        ));

        // Cocoa also has FACING property. We need to capture all states with AGE=2.
        Blocks.COCOA.getStateDefinition().getPossibleStates().stream()
            .filter(s -> s.getValue(CocoaBlock.AGE) == 2)
            .forEach(s -> harvestables.add(() -> s));

        return harvestables;
    }
}
