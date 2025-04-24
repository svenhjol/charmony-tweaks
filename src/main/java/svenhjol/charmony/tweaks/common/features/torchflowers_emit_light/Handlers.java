package svenhjol.charmony.tweaks.common.features.torchflowers_emit_light;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchflowerCropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import svenhjol.charmony.core.base.Setup;

import java.util.Optional;

public class Handlers extends Setup<TorchflowersEmitLight> {
    private static int cachedLightLevel = -1;

    public Handlers(TorchflowersEmitLight feature) {
        super(feature);
    }

    public Optional<Integer> lightLevel(BlockBehaviour.BlockStateBase state) {
        if (cachedLightLevel == -1) {
            cachedLightLevel = feature().lightLevel();
        }

        if (state.is(Blocks.TORCHFLOWER) || state.is(Blocks.POTTED_TORCHFLOWER)) {
            return Optional.of(cachedLightLevel);
        }

        if (state.is(Blocks.TORCHFLOWER_CROP) && state.asState().getValue(TorchflowerCropBlock.AGE) == 1) {
            return Optional.of(cachedLightLevel / 2);
        }

        return Optional.empty();
    }
}
