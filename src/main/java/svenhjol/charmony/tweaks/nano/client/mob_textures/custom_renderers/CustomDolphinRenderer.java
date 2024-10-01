package svenhjol.charmony.tweaks.nano.client.mob_textures.custom_renderers;

import net.minecraft.client.renderer.entity.DolphinRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.DolphinRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Dolphin;

import java.util.UUID;

public class CustomDolphinRenderer extends DolphinRenderer implements CustomRenderer {
    private UUID uuid;

    public CustomDolphinRenderer(EntityRendererProvider.Context context) {
        super(context);
        handlers.fillLayersFromOld(context, this, EntityType.DOLPHIN);
    }

    @Override
    public ResourceLocation getTextureLocation(DolphinRenderState dolphinRenderState) {
        return handlers.texture(uuid, registers.dolphins);
    }

    @Override
    public void extractRenderState(Dolphin dolphin, DolphinRenderState dolphinRenderState, float f) {
        super.extractRenderState(dolphin, dolphinRenderState, f);
        this.uuid = dolphin.getUUID();
    }
}
