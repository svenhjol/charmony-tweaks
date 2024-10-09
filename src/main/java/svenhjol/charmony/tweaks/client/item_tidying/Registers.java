package svenhjol.charmony.tweaks.client.item_tidying;

import net.minecraft.client.gui.components.WidgetSprites;
import svenhjol.charmony.scaffold.base.Setup;
import svenhjol.charmony.scaffold.events.RenderScreenCallback;
import svenhjol.charmony.scaffold.events.SetupScreenCallback;
import svenhjol.charmony.tweaks.Tweaks;

public class Registers extends Setup<ItemTidying> {
    public final WidgetSprites tidyButton;

    public Registers(ItemTidying feature) {
        super(feature);

        tidyButton = new WidgetSprites(
            Tweaks.instance().id("widget/item_tidying/tidy_button"),
            Tweaks.instance().id("widget/item_tidying/tidy_button_highlighted")
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
