package svenhjol.charmony.tweaks.fabric;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmony.scaffold.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.client.compasses_show_position.CompassesShowPosition;
import svenhjol.charmony.tweaks.client.item_tidying.ItemTidying;
import svenhjol.charmony.tweaks.client.jukeboxes_stop_background_music.JukeboxesStopBackgroundMusic;
import svenhjol.charmony.tweaks.client.maps_show_when_hovering.MapsShowWhenHovering;
import svenhjol.charmony.tweaks.client.mob_textures.MobTextures;
import svenhjol.charmony.tweaks.client.repair_cost_visible.RepairCostVisible;
import svenhjol.charmony.tweaks.client.shulker_boxes_show_when_hovering.ShulkerBoxesShowWhenHovering;
import svenhjol.charmony.tweaks.client.spyglass_scope_hiding.SpyglassScopeHiding;
import svenhjol.charmony.tweaks.client.telemetry.Telemetry;

public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Ensure charmony is launched first.
        svenhjol.charmony.scaffold.fabric.ClientInitializer.init();

        // Prepare and run the mod.
        var tweaks = Tweaks.instance();
        tweaks.addFeature(CompassesShowPosition.class);
        tweaks.addFeature(ItemTidying.class);
        tweaks.addFeature(JukeboxesStopBackgroundMusic.class);
        tweaks.addFeature(MapsShowWhenHovering.class);
        tweaks.addFeature(MobTextures.class);
        tweaks.addFeature(RepairCostVisible.class);
        tweaks.addFeature(ShulkerBoxesShowWhenHovering.class);
        tweaks.addFeature(SpyglassScopeHiding.class);
        tweaks.addFeature(Telemetry.class);
        tweaks.run(Side.Client);
    }
}
