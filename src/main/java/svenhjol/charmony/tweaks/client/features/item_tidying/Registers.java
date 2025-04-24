package svenhjol.charmony.tweaks.client.features.item_tidying;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import org.lwjgl.glfw.GLFW;
import svenhjol.charmony.api.*;
import svenhjol.charmony.core.Api;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.RenderScreenCallback;
import svenhjol.charmony.core.events.SetupScreenCallback;
import svenhjol.charmony.tweaks.TweaksMod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class Registers extends Setup<ItemTidying> {
    public final WidgetSprites lightModeTidyButton;
    public final WidgetSprites darkModeTidyButton;
    public final KeyMapping tidyInventoryKey;

    public final List<Predicate<Screen>> darkModeTests = new ArrayList<>();
    public final List<Class<? extends Screen>> whitelisted = new ArrayList<>();
    public final List<Class<? extends Screen>> blacklisted = new ArrayList<>();
    public final Map<Class<? extends Screen>, ItemTidyingButtonTweak> tweaks = new HashMap<>();

    public Registers(ItemTidying feature) {
        super(feature);

        this.lightModeTidyButton = new WidgetSprites(
            TweaksMod.id("widget/item_tidying/tidy_button"),
            TweaksMod.id("widget/item_tidying/tidy_button_highlighted")
        );

        this.darkModeTidyButton = new WidgetSprites(
            TweaksMod.id("widget/item_tidying/dark_mode_tidy_button"),
            TweaksMod.id("widget/item_tidying/dark_mode_tidy_button_highlighted")
        );

        this.tidyInventoryKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.charmony-tweaks.tidyInventory",
            GLFW.GLFW_KEY_APOSTROPHE,
            "key.categories.inventory"));
    }

    @Override
    public Runnable boot() {
        return () -> {
            RenderScreenCallback.EVENT.register(feature().handlers::renderScreen);
            SetupScreenCallback.EVENT.register(feature().handlers::setupScreen);

            Api.consume(ItemTidyingButtonTweakProvider.class,
                provider -> provider.getItemTidyingButtonTweaks().forEach(
                    tweak -> tweaks.put(tweak.getScreen(), tweak)));

            Api.consume(ItemTidyingWhitelistProvider.class,
                provider -> whitelisted.addAll(provider.getItemTidyingWhitelistedScreens()));

            Api.consume(ItemTidyingBlacklistProvider.class,
                provider -> blacklisted.addAll(provider.getItemTidyingBlacklistedScreens()));

            Api.consume(UsesDarkMode.class,
                provider -> darkModeTests.add(provider.usingDarkMode()));
        };
    }
}
