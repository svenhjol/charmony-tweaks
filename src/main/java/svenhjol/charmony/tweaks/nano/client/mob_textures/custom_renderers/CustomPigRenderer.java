package svenhjol.charmony.tweaks.nano.client.mob_textures.custom_renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;

import java.util.UUID;

public class CustomPigRenderer extends PigRenderer implements CustomRenderer {
    private UUID uuid;

    public CustomPigRenderer(EntityRendererProvider.Context context) {
        super(context);
        handlers.fillLayersFromOld(context, this, EntityType.PIG);
    }

    @Override
    public ResourceLocation getTextureLocation(PigRenderState pigRenderState) {
        return handlers.texture(uuid, registers.pigs);
    }

    @Override
    public void extractRenderState(Pig pig, PigRenderState pigRenderState, float f) {
        super.extractRenderState(pig, pigRenderState, f);
        this.uuid = pig.getUUID();
    }
}
