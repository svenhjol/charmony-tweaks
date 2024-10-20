package svenhjol.charmony.tweaks.client.item_tidying;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.*;
import svenhjol.charmony.api.ItemTidyingBlacklistProvider;
import svenhjol.charmony.api.ItemTidyingButtonTweak;
import svenhjol.charmony.api.ItemTidyingButtonTweakProvider;
import svenhjol.charmony.api.ItemTidyingWhitelistProvider;
import svenhjol.charmony.core.Api;
import svenhjol.charmony.core.base.Setup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Providers extends Setup<ItemTidying> implements
    ItemTidyingWhitelistProvider,
    ItemTidyingBlacklistProvider,
    ItemTidyingButtonTweakProvider {

    public final List<Class<? extends Screen>> whitelisted = new ArrayList<>();
    public final List<Class<? extends Screen>> blacklisted = new ArrayList<>();
    public final Map<Class<? extends Screen>, ItemTidyingButtonTweak> tweaks = new HashMap<>();

    public Providers(ItemTidying feature) {
        super(feature);
    }

    @Override
    public List<ItemTidyingButtonTweak> getItemTidyingButtonTweaks() {
        // Offset the button by these X and Y coordinates on these screens.
        return List.of(
            new ItemTidyingButtonTweak() {
                @Override
                public Class<? extends Screen> getScreen() {
                    return MerchantScreen.class;
                }

                @Override
                public Pair<Integer, Integer> getXYOffset() {
                    return Pair.of(100, 0);
                }
            },
            new ItemTidyingButtonTweak() {
                @Override
                public Class<? extends Screen> getScreen() {
                    return InventoryScreen.class;
                }

                @Override
                public Pair<Integer, Integer> getXYOffset() {
                    return Pair.of(0, 76);
                }
            }
        );
    }

    @Override
    public List<Class<? extends Screen>> getItemTidyingBlacklistedScreens() {
        // Don't show the button on these screens.
        return List.of(
            CreativeModeInventoryScreen.class,
            BeaconScreen.class
        );
    }

    @Override
    public List<Class<? extends Screen>> getItemTidyingWhitelistedScreens() {
        // Add two sorting buttons to screens with a container.
        return List.of(
            ContainerScreen.class,
            HopperScreen.class,
            ShulkerBoxScreen.class,
            DispenserScreen.class
        );
    }

    @Override
    public Runnable boot() {
        return () -> {
            Api.registerProvider(this);
            Api.consume(ItemTidyingButtonTweakProvider.class,
                provider -> provider.getItemTidyingButtonTweaks().forEach(
                    tweak -> tweaks.put(tweak.getScreen(), tweak)));

            Api.consume(ItemTidyingWhitelistProvider.class,
                provider -> whitelisted.addAll(provider.getItemTidyingWhitelistedScreens()));

            Api.consume(ItemTidyingBlacklistProvider.class,
                provider -> blacklisted.addAll(provider.getItemTidyingBlacklistedScreens()));
        };
    }
}
