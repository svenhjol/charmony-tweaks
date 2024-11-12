package svenhjol.charmony.tweaks.client.features.compasses_show_position;

import svenhjol.charmony.api.CompassOverlayItem;
import svenhjol.charmony.api.CompassOverlayProvider;
import svenhjol.charmony.core.Api;
import svenhjol.charmony.core.base.Setup;

import java.util.ArrayList;
import java.util.List;

public final class Providers extends Setup<CompassesShowPosition> {
    public final List<CompassOverlayItem> overlayItems = new ArrayList<>();

    public Providers(CompassesShowPosition feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> Api.consume(CompassOverlayProvider.class,
            provider -> overlayItems.addAll(provider.getCompassOverlayItems()));
    }
}
