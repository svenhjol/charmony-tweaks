package svenhjol.charmony.tweaks.client;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.client.burning_has_reduced_view_blocking.BurningHasReducedViewBlocking;
import svenhjol.charmony.tweaks.client.chiseled_bookshelves_show_book_on_hover.ChiseledBookshelvesShowBookOnHover;
import svenhjol.charmony.tweaks.client.compasses_show_position.CompassesShowPosition;
import svenhjol.charmony.tweaks.client.crafting_table_nearby.CraftingTableNearby;
import svenhjol.charmony.tweaks.client.item_tidying.ItemTidying;
import svenhjol.charmony.tweaks.client.jukeboxes_stop_background_music.JukeboxesStopBackgroundMusic;
import svenhjol.charmony.tweaks.client.maps_show_when_hovering.MapsShowWhenHovering;
import svenhjol.charmony.tweaks.client.mob_textures.MobTextures;
import svenhjol.charmony.tweaks.client.repair_cost_visible.RepairCostVisible;
import svenhjol.charmony.tweaks.client.shields_have_reduced_view_blocking.ShieldsHaveReducedViewBlocking;
import svenhjol.charmony.tweaks.client.shulker_boxes_show_contents_when_hovering.ShulkerBoxesShowContentsWhenHovering;
import svenhjol.charmony.tweaks.client.spyglass_scope_hiding.SpyglassScopeHiding;
import svenhjol.charmony.tweaks.client.telemetry.Telemetry;
import svenhjol.charmony.tweaks.client.totem_emergency_swap.TotemEmergencySwap;

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
