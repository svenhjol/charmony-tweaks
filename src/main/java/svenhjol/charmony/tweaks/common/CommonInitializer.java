package svenhjol.charmony.tweaks.common;

import net.fabricmc.api.ModInitializer;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.tweaks.Tweaks;
import svenhjol.charmony.tweaks.common.features.animal_armor_grinding.AnimalArmorGrinding;
import svenhjol.charmony.tweaks.common.features.animal_damage_immunity.AnimalDamageImmunity;
import svenhjol.charmony.tweaks.common.features.animal_reviving.AnimalReviving;
import svenhjol.charmony.tweaks.common.features.campfires_heal_players.CampfiresHealPlayers;
import svenhjol.charmony.tweaks.common.features.crop_feather_falling.CropFeatherFalling;
import svenhjol.charmony.tweaks.common.features.crop_replanting.CropReplanting;
import svenhjol.charmony.tweaks.common.features.deepslate_dungeons.DeepslateDungeons;
import svenhjol.charmony.tweaks.common.features.grindstone_disenchanting.GrindstoneDisenchanting;
import svenhjol.charmony.tweaks.common.features.item_frame_hiding.ItemFrameHiding;
import svenhjol.charmony.tweaks.common.features.item_repairing.ItemRepairing;
import svenhjol.charmony.tweaks.common.features.item_restocking.ItemRestocking;
import svenhjol.charmony.tweaks.common.features.mineshaft_improvements.MineshaftImprovements;
import svenhjol.charmony.tweaks.common.features.mob_drops.MobDrops;
import svenhjol.charmony.tweaks.common.features.nether_portal_blocks.NetherPortalBlocks;
import svenhjol.charmony.tweaks.common.features.parrots_stay_on_shoulder.ParrotsStayOnShoulder;
import svenhjol.charmony.tweaks.common.features.path_converting.PathConverting;
import svenhjol.charmony.tweaks.common.features.piglin_pointing.PiglinPointing;
import svenhjol.charmony.tweaks.common.features.pigs_find_mushrooms.PigsFindMushrooms;
import svenhjol.charmony.tweaks.common.features.repair_cost_unlimited.RepairCostUnlimited;
import svenhjol.charmony.tweaks.common.features.respawn_anchors_work_everywhere.RespawnAnchorsWorkEverywhere;
import svenhjol.charmony.tweaks.common.features.shulker_box_transferring.ShulkerBoxTransferring;
import svenhjol.charmony.tweaks.common.features.spawners_drop_items.SpawnersDropItems;
import svenhjol.charmony.tweaks.common.features.suspicious_block_creating.SuspiciousBlockCreating;
import svenhjol.charmony.tweaks.common.features.torchflowers_emit_light.TorchflowersEmitLight;
import svenhjol.charmony.tweaks.common.features.totems_work_from_inventory.TotemsWorkFromInventory;
import svenhjol.charmony.tweaks.common.features.trade_improvements.TradeImprovements;

public class CommonInitializer implements ModInitializer {
    @Override
    public void onInitialize() {
        // Ensure charmony is launched first.
        svenhjol.charmony.core.common.CommonInitializer.init();

        // Prepare and run the mod.
        var tweaks = Tweaks.instance();
        tweaks.addFeature(AnimalArmorGrinding.class);
        tweaks.addFeature(AnimalDamageImmunity.class);
        tweaks.addFeature(AnimalReviving.class);
        tweaks.addFeature(CampfiresHealPlayers.class);
        tweaks.addFeature(CropFeatherFalling.class);
        tweaks.addFeature(CropReplanting.class);
        tweaks.addFeature(DeepslateDungeons.class);
        tweaks.addFeature(GrindstoneDisenchanting.class);
        tweaks.addFeature(ItemFrameHiding.class);
        tweaks.addFeature(ItemRepairing.class);
        tweaks.addFeature(ItemRestocking.class);
        tweaks.addFeature(MineshaftImprovements.class);
        tweaks.addFeature(MobDrops.class);
        tweaks.addFeature(NetherPortalBlocks.class);
        tweaks.addFeature(ParrotsStayOnShoulder.class);
        tweaks.addFeature(PathConverting.class);
        tweaks.addFeature(PiglinPointing.class);
        tweaks.addFeature(PigsFindMushrooms.class);
        tweaks.addFeature(RepairCostUnlimited.class);
        tweaks.addFeature(RespawnAnchorsWorkEverywhere.class);
        tweaks.addFeature(ShulkerBoxTransferring.class);
        tweaks.addFeature(SpawnersDropItems.class);
        tweaks.addFeature(SuspiciousBlockCreating.class);
        tweaks.addFeature(TorchflowersEmitLight.class);
        tweaks.addFeature(TotemsWorkFromInventory.class);
        tweaks.addFeature(TradeImprovements.class);
        tweaks.run(Side.Common);
    }
}
