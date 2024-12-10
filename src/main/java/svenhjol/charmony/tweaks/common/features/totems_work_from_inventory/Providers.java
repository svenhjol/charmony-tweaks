package svenhjol.charmony.tweaks.common.features.totems_work_from_inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import svenhjol.charmony.api.TotemInventoryCheckProvider;
import svenhjol.charmony.api.TotemType;
import svenhjol.charmony.core.Api;
import svenhjol.charmony.core.base.Setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Providers extends Setup<TotemsWorkFromInventory> implements TotemInventoryCheckProvider {
    public final List<TotemInventoryCheckProvider> inventoryCheckProviders = new ArrayList<>();

    public Providers(TotemsWorkFromInventory feature) {
        super(feature);
    }

    @Override
    public Optional<ItemStack> findTotemFromInventory(Player player, TotemType totemType) {
        if (totemType == TotemType.UNDYING) {
            var mainHand = player.getMainHandItem();
            if (mainHand.is(Items.TOTEM_OF_UNDYING)) {
                return Optional.of(mainHand);
            }

            var offHand = player.getOffhandItem();
            if (offHand.is(Items.TOTEM_OF_UNDYING)) {
                return Optional.of(offHand);
            }

            for (var item : player.getInventory().items) {
                if (item.is(Items.TOTEM_OF_UNDYING)) {
                    return Optional.of(item);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Runnable boot() {
        return () -> {
            Api.registerProvider(this);
            Api.consume(TotemInventoryCheckProvider.class, inventoryCheckProviders::add);
        };
    }
}
