package svenhjol.charmony.tweaks.client.item_tidying;

import net.minecraft.client.gui.components.WidgetSprites;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.RenderScreenCallback;
import svenhjol.charmony.core.events.SetupScreenCallback;
import svenhjol.charmony.tweaks.Tweaks;

public class Registers extends Setup<ItemTidying> {
    public final WidgetSprites tidyButton;

    public Registers(ItemTidying feature) {
        super(feature);

        tidyButton = new WidgetSprites(
            Tweaks.id("widget/item_tidying/tidy_button"),
            Tweaks.id("widget/item_tidying/tidy_button_highlighted")
        );
    }

    @Override
    public Runnable boot() {
        return () -> {
            RenderScreenCallback.EVENT.register(feature().handlers::renderScreen);
            SetupScreenCallback.EVENT.register(feature().handlers::setupScreen);
        };
    }
}
