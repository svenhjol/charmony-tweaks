package svenhjol.charmony.tweaks.client.features.item_frame_hiding;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.client.ClientRegistry;
import svenhjol.charmony.core.client.DeferredParticle;
import svenhjol.charmony.tweaks.common.features.item_frame_hiding.Networking.*;

public final class Registers extends Setup<ItemFrameHiding> {
    public final DeferredParticle particle;

    public Registers(ItemFrameHiding feature) {
        super(feature);

        particle = ClientRegistry.instance().particle(feature().common.registers.particleType, Particle::new);

        // Handle packets being sent from the server
        ClientPlayNetworking.registerGlobalReceiver(S2CAddAmethyst.TYPE,
            feature().handlers::addToItemFrame);
        ClientPlayNetworking.registerGlobalReceiver(S2CRemoveAmethyst.TYPE,
            feature().handlers::removeFromItemFrame);
    }
}
