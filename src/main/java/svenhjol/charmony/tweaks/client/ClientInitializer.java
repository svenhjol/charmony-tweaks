package svenhjol.charmony.tweaks.client;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.client.features.burning_has_reduced_view_blocking.BurningHasReducedViewBlocking;
import svenhjol.charmony.tweaks.client.features.chiseled_bookshelves_show_book_on_hover.ChiseledBookshelvesShowBookOnHover;
import svenhjol.charmony.tweaks.client.features.compasses_show_position.CompassesShowPosition;
import svenhjol.charmony.tweaks.client.features.crafting_table_nearby.CraftingTableNearby;
import svenhjol.charmony.tweaks.client.features.grindstone_disenchanting.GrindstoneDisenchanting;
import svenhjol.charmony.tweaks.client.features.item_tidying.ItemTidying;
import svenhjol.charmony.tweaks.client.features.jukeboxes_stop_background_music.JukeboxesStopBackgroundMusic;
import svenhjol.charmony.tweaks.client.features.maps_show_when_hovering.MapsShowWhenHovering;
import svenhjol.charmony.tweaks.client.features.mob_textures.MobTextures;
import svenhjol.charmony.tweaks.client.features.repair_cost_visible.RepairCostVisible;
import svenhjol.charmony.tweaks.client.features.shields_have_reduced_view_blocking.ShieldsHaveReducedViewBlocking;
import svenhjol.charmony.tweaks.client.features.shulker_boxes_show_contents_when_hovering.ShulkerBoxesShowContentsWhenHovering;
import svenhjol.charmony.tweaks.client.features.spyglass_scope_hiding.SpyglassScopeHiding;
import svenhjol.charmony.tweaks.client.features.telemetry.Telemetry;
import svenhjol.charmony.tweaks.client.features.totem_emergency_swap.TotemEmergencySwap;

public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Ensure charmony is launched first.
        svenhjol.charmony.core.client.ClientInitializer.init();

        // Prepare and run the mod.
        var tweaks = Tweaks.instance();
        tweaks.addFeature(BurningHasReducedViewBlocking.class);
        tweaks.addFeature(ChiseledBookshelvesShowBookOnHover.class);
        tweaks.addFeature(CompassesShowPosition.class);
        tweaks.addFeature(CraftingTableNearby.class);
        tweaks.addFeature(GrindstoneDisenchanting.class);
        tweaks.addFeature(ItemTidying.class);
        tweaks.addFeature(JukeboxesStopBackgroundMusic.class);
        tweaks.addFeature(MapsShowWhenHovering.class);
        tweaks.addFeature(MobTextures.class);
        tweaks.addFeature(RepairCostVisible.class);
        tweaks.addFeature(ShieldsHaveReducedViewBlocking.class);
        tweaks.addFeature(ShulkerBoxesShowContentsWhenHovering.class);
        tweaks.addFeature(SpyglassScopeHiding.class);
        tweaks.addFeature(Telemetry.class);
        tweaks.addFeature(TotemEmergencySwap.class);
        tweaks.run(Side.Client);
    }
}
