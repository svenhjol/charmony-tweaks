package svenhjol.charmony.tweaks.client.item_tidying;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.components.WidgetSprites;
import org.lwjgl.glfw.GLFW;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.RenderScreenCallback;
import svenhjol.charmony.core.events.SetupScreenCallback;
import svenhjol.charmony.tweaks.Tweaks;

public class Registers extends Setup<ItemTidying> {
    public final WidgetSprites tidyButtonSprite;
    public final KeyMapping tidyInventoryKey;

    public Registers(ItemTidying feature) {
        super(feature);

        this.tidyButtonSprite = new WidgetSprites(
            Tweaks.id("widget/item_tidying/tidy_button"),
            Tweaks.id("widget/item_tidying/tidy_button_highlighted")
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
        };
    }
}
