package svenhjol.charmony.tweaks.client.features.mob_textures.custom_renderers;

import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;

import java.util.UUID;

public class CustomCowRenderer extends CowRenderer implements CustomRenderer {
    private UUID uuid;

    public CustomCowRenderer(EntityRendererProvider.Context context) {
        super(context);
        handlers.fillLayersFromOld(context, this, EntityType.COW);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return handlers.texture(uuid, registers.cows);
    }

    @Override
    public void extractRenderState(Cow cow, LivingEntityRenderState livingEntityRenderState, float f) {
        super.extractRenderState(cow, livingEntityRenderState, f);
        this.uuid = cow.getUUID();
    }
}
