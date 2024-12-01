package svenhjol.charmony.tweaks.client.features.item_frame_hiding;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import svenhjol.charmony.core.base.Environment;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.client.ClientRegistry;
import svenhjol.charmony.tweaks.common.features.item_frame_hiding.Networking;

public final class Registers extends Setup<ItemFrameHiding> {
    public Registers(ItemFrameHiding feature) {
        super(feature);

        if (Environment.usesCharmonyServer()) {
            // Create a particle to show when applying and removing amethyst.
            ClientRegistry.instance().particle(feature.common.get().registers.particleType, Particle::new);

            // Handle packets being sent from the server.
            ClientPlayNetworking.registerGlobalReceiver(Networking.S2CAddAmethyst.TYPE,
                feature().handlers::addToItemFrame);
            ClientPlayNetworking.registerGlobalReceiver(Networking.S2CRemoveAmethyst.TYPE,
                feature().handlers::removeFromItemFrame);
        }
    }
}
