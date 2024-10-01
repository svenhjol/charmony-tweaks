package svenhjol.charmony.tweaks.nano.client.mob_textures.custom_renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SnowGolemRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.SnowGolem;

import java.util.UUID;

public class CustomSnowGolemRenderer extends SnowGolemRenderer implements CustomRenderer {
    private UUID uuid;

    public CustomSnowGolemRenderer(EntityRendererProvider.Context context) {
        super(context);
        handlers.fillLayersFromOld(context, this, EntityType.SNOW_GOLEM);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return handlers.texture(uuid, registers.snowGolems);
    }

    @Override
    public void extractRenderState(SnowGolem snowGolem, LivingEntityRenderState livingEntityRenderState, float f) {
        super.extractRenderState(snowGolem, livingEntityRenderState, f);
        this.uuid = snowGolem.getUUID();
    }
}
