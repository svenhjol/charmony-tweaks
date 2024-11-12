package svenhjol.charmony.tweaks.client.features.mob_textures.custom_renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TurtleRenderer;
import net.minecraft.client.renderer.entity.state.TurtleRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Turtle;

import java.util.UUID;

public class CustomTurtleRenderer extends TurtleRenderer implements CustomRenderer {
    private UUID uuid;

    public CustomTurtleRenderer(EntityRendererProvider.Context context) {
        super(context);
        handlers.fillLayersFromOld(context, this, EntityType.TURTLE);
    }

    @Override
    public ResourceLocation getTextureLocation(TurtleRenderState turtleRenderState) {
        return handlers.texture(uuid, registers.turtles);
    }

    @Override
    public void extractRenderState(Turtle turtle, TurtleRenderState turtleRenderState, float f) {
        super.extractRenderState(turtle, turtleRenderState, f);
        this.uuid = turtle.getUUID();
    }
}
