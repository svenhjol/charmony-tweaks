package svenhjol.charmony.tweaks.common.features.trade_improvements;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.common.CommonRegistry;
import svenhjol.charmony.core.common.GenericTrades;
import svenhjol.charmony.core.helpers.TagHelper;

import javax.annotation.Nullable;
import java.util.Optional;

public class Registers extends Setup<TradeImprovements> {
    public Registers(TradeImprovements feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return this::run;
    }

    private void run() {
        var registry = CommonRegistry.forFeature(feature());

        if (feature().enchantedBooks()) {
            var tier = 2;
            var xp = 5;
            registry.villagerTrade(() -> VillagerProfession.LIBRARIAN, tier, () -> new GenericTrades.EmeraldsForItems(
                Items.ENCHANTED_BOOK, 1, 0, 5, 0, xp, 16));
        }

        if (feature().repairedAnvils()) {
            var tier = 2;
            var xp = 5;
            registry.villagerTrade(() -> VillagerProfession.ARMORER, tier, () -> new AnvilRepair(tier, xp));
            registry.villagerTrade(() -> VillagerProfession.WEAPONSMITH, tier, () -> new AnvilRepair(tier, xp));
            registry.villagerTrade(() -> VillagerProfession.TOOLSMITH, tier, () -> new AnvilRepair(tier, xp));
        }

        if (feature().leatherForRottenFlesh()) {
            var tier = 3;
            var xp = 10;
            registry.villagerTrade(() -> VillagerProfession.LEATHERWORKER, tier, () -> new GenericTrades.ItemsForItems(
                Items.ROTTEN_FLESH, Items.LEATHER, 10, 5, 1, 0, xp, 8));
        }

        if (feature().beefForRottenFlesh()) {
            var tier = 3;
            var xp = 10;
            registry.villagerTrade(() -> VillagerProfession.BUTCHER, tier, () -> new GenericTrades.ItemsForItems(
                Items.ROTTEN_FLESH, Items.BEEF, 8, 5, 1, 0, xp, 8));
        }

        if (feature().phantomMembrane()) {
            var tier = 4;
            var xp = 15;
            registry.villagerTrade(() -> VillagerProfession.CLERIC, tier, () -> new GenericTrades.EmeraldsForItems(
                Items.PHANTOM_MEMBRANE, 3, 3, 1, 0, xp, 8));
        }

        ServerLifecycleEvents.SERVER_STARTING.register(this::handleServerStarting);
    }

    private void handleServerStarting(MinecraftServer server) {
        var registry = CommonRegistry.forFeature(feature());

        if (feature().coloredWools()) {
            var wools = TagHelper.getValues(BuiltInRegistries.BLOCK, BlockTags.WOOL).stream()
                .map(block -> (VillagerTrades.ItemListing) new GenericTrades.ItemsForEmeralds(block, 3, 2, 8, 0, 0, 2))
                .toList();
            registry.wandererTradeTier("colored_wools", wools);
        }

        if (feature().concretePowders()) {
            var concretePowders = TagHelper.getValues(BuiltInRegistries.BLOCK, BlockTags.CONCRETE_POWDER).stream()
                .map(block -> (VillagerTrades.ItemListing) new GenericTrades.ItemsForEmeralds(block, 3, 2, 8, 0, 0, 2))
                .toList();
            registry.wandererTradeTier("concrete_powders", concretePowders);
        }

        if (feature().coloredConcretes()) {
            var concretes = TagHelper.getValues(BuiltInRegistries.BLOCK, ConventionalBlockTags.CONCRETES).stream()
                .map(block -> (VillagerTrades.ItemListing) new GenericTrades.ItemsForEmeralds(block, 5, 2, 8, 0, 0, 2))
                .toList();
            registry.wandererTradeTier("colored_concretes", concretes);
        }

        if (feature().glazedTerracottas()) {
            var glazedTerracottas = TagHelper.getValues(BuiltInRegistries.BLOCK, ConventionalBlockTags.GLAZED_TERRACOTTAS).stream()
                .map(block -> (VillagerTrades.ItemListing) new GenericTrades.ItemsForEmeralds(block, 9, 2, 8, 0, 0, 2))
                .toList();
            registry.wandererTradeTier("glazed_terracottas", glazedTerracottas);
        }

        if (feature().coloredTerracottas()) {
            var coloredTerracottas = TagHelper.getValues(BuiltInRegistries.BLOCK, Tags.COLORED_TERRACOTTAS).stream()
                .map(block -> (VillagerTrades.ItemListing) new GenericTrades.ItemsForEmeralds(block, 3, 2, 8, 0, 0, 2))
                .toList();
            registry.wandererTradeTier("colored_terracottas", coloredTerracottas);
        }

        if (feature().coloredGlass()) {
            var coloredGlass = TagHelper.getValues(BuiltInRegistries.BLOCK, Tags.COLORED_GLASS).stream()
                .map(block -> (VillagerTrades.ItemListing) new GenericTrades.ItemsForEmeralds(block, 3, 2, 8, 0, 0, 2))
                .toList();
            registry.wandererTradeTier("colored_glass", coloredGlass);
        }

        if (feature().coloredBundles()) {
            var bundles = TagHelper.getValues(BuiltInRegistries.ITEM, Tags.COLORED_BUNDLES).stream()
                .map(block -> (VillagerTrades.ItemListing) new GenericTrades.ItemsForEmeralds(block, 28, 8, 1, 0, 0, 1))
                .toList();
            registry.wandererTradeTier("colored_bundles", bundles);
        }

        if (feature().coloredCandles()) {
            var candles = TagHelper.getValues(BuiltInRegistries.ITEM, Tags.COLORED_CANDLES).stream()
                .map(block -> (VillagerTrades.ItemListing) new GenericTrades.ItemsForEmeralds(block, 3, 2, 8, 0, 0, 2))
                .toList();
            registry.wandererTradeTier("colored_candles", candles);
        }
    }

    public static class AnvilRepair implements VillagerTrades.ItemListing {
        private final int villagerXp;
        private final int maxUses;

        public AnvilRepair(int villagerXp, int maxUses) {
            this.villagerXp = villagerXp;
            this.maxUses = maxUses;
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity merchant, RandomSource random) {
            var item = random.nextBoolean() ? Items.DAMAGED_ANVIL : Items.CHIPPED_ANVIL;

            return new MerchantOffer(
                GenericTrades.getCost(item, 1),
                Optional.of(GenericTrades.getCost(random, Items.IRON_INGOT, 5, 4)),
                new ItemStack(Items.ANVIL),
                maxUses,
                villagerXp,
                0.2f
            );
        }
    }
}
