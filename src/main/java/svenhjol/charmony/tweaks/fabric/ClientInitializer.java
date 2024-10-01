package svenhjol.charmony.tweaks.fabric;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmony.scaffold.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.client.compasses_show_position.CompassesShowPosition;
import svenhjol.charmony.tweaks.client.mob_textures.MobTextures;
import svenhjol.charmony.tweaks.client.repair_cost_visible.RepairCostVisible;

public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        var tweaks = Tweaks.instance();

        // Add features.
        tweaks.addFeature(CompassesShowPosition.class);
        tweaks.addFeature(MobTextures.class);
        tweaks.addFeature(RepairCostVisible.class);

        // Run the mod.
        tweaks.run(Side.Client);
    }
}
