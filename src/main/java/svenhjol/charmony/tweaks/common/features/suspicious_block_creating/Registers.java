package svenhjol.charmony.tweaks.common.features.suspicious_block_creating;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.common.CommonRegistry;
import svenhjol.charmony.tweaks.Tweaks;

import java.util.HashMap;
import java.util.Map;

public final class Registers extends Setup<SuspiciousBlockCreating> {
    public final Map<Block, Block> blockConversions = new HashMap<>();
    public final SoundEvent createBlockSound;

    public Registers(SuspiciousBlockCreating feature) {
        super(feature);

        createBlockSound = CommonRegistry.instance().sound(Tweaks.id("create_suspicious_block"));
        registerBlockConversion(Blocks.SAND, Blocks.SUSPICIOUS_SAND);
        registerBlockConversion(Blocks.GRAVEL, Blocks.SUSPICIOUS_GRAVEL);
    }

    public void registerBlockConversion(Block normal, Block suspicious) {
        blockConversions.put(normal, suspicious);
    }
}
