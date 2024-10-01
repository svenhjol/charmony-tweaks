package svenhjol.charmony.tweaks.nano.client.mob_textures.custom_renderers;

import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;

import java.util.UUID;

public class CustomChickenRenderer extends ChickenRenderer implements CustomRenderer {
    private UUID uuid;

    public CustomChickenRenderer(EntityRendererProvider.Context context) {
        super(context);
        handlers.fillLayersFromOld(context, this, EntityType.CHICKEN);
    }

    @Override
    public ResourceLocation getTextureLocation(ChickenRenderState state) {
        return handlers.texture(uuid, registers.chickens);
    }

    @Override
    public void extractRenderState(Chicken chicken, ChickenRenderState chickenRenderState, float f) {
        super.extractRenderState(chicken, chickenRenderState, f);
        this.uuid = chicken.getUUID();
    }
}
