package svenhjol.charmony.tweaks.client.features.mob_textures.custom_renderers;

import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.client.renderer.entity.state.SquidRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Squid;

import java.util.UUID;

public class CustomSquidRenderer extends SquidRenderer<Squid> implements CustomRenderer {
    private UUID uuid;

    public CustomSquidRenderer(EntityRendererProvider.Context context) {
        // TODO: this model registering might be b0rk, check.
        super(context, new SquidModel(context.bakeLayer(ModelLayers.SQUID)), new SquidModel(context.bakeLayer(ModelLayers.SQUID)));
        handlers.fillLayersFromOld(context, this, EntityType.SQUID);
    }

    @Override
    public ResourceLocation getTextureLocation(SquidRenderState squidRenderState) {
        return handlers.texture(uuid, registers.squids);
    }

    @Override
    public void extractRenderState(Squid squid, SquidRenderState squidRenderState, float f) {
        super.extractRenderState(squid, squidRenderState, f);
        this.uuid = squid.getUUID();
    }
}
