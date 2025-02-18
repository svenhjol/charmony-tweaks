package svenhjol.charmony.tweaks.client.features.mob_textures.custom_renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.item.DyeColor;
import svenhjol.charmony.tweaks.client.features.mob_textures.MobType;

public class CustomSheepRenderer extends SheepRenderer implements CustomRenderer {
    private DyeColor fleeceColor;

    public CustomSheepRenderer(EntityRendererProvider.Context context) {
        super(context);
        handlers.fillLayersFromOld(context, this, EntityType.SHEEP);
    }

    @Override
    public ResourceLocation getTextureLocation(SheepRenderState sheepRenderState) {
        return registers.sheep.getOrDefault(fleeceColor, MobType.SHEEP.vanillaTexture());
    }

    @Override
    public void extractRenderState(Sheep sheep, SheepRenderState sheepRenderState, float f) {
        super.extractRenderState(sheep, sheepRenderState, f);
        this.fleeceColor = sheep.getColor();
    }
}
