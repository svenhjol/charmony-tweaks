package svenhjol.charmony.tweaks.client.mob_textures;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import svenhjol.charmony.core.base.Setup;

import java.util.List;
import java.util.UUID;

public final class Handlers extends Setup<MobTextures> {
    public Handlers(MobTextures feature) {
        super(feature);
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<S>, U extends S> void fillLayersFromOld(
        EntityRendererProvider.Context context, LivingEntityRenderer<T, U, M> renderer, EntityType<T> type) {
        EntityRenderer<?, ?> old = context.getEntityRenderDispatcher().renderers.get(type);

        if (old != null) {
            var layerRenderers = renderer.layers;
            layerRenderers.clear();

            ((LivingEntityRenderer<T, U, M>)old).layers
                .stream()
                .peek(layer -> layer.renderer = renderer)
                .forEach(layerRenderers::add);
        }
    }

    public ResourceLocation texture(UUID uuid, Pair<List<ResourceLocation>, List<ResourceLocation>> textures) {
        // TODO: simple UUID caching.
        var normal = textures.getFirst();
        var rare = textures.getSecond();

        var isRare = feature().rareTypeChance() > 0
            && !rare.isEmpty()
            && (uuid.getLeastSignificantBits() + uuid.getMostSignificantBits()) % feature().rareTypeChance() == 0;

        var set = isRare ? rare : normal;
        if (set.isEmpty()) {
            return null;
        }

        var choice = Math.abs((int)(uuid.getMostSignificantBits() % set.size()));
        return set.get(choice);
    }
}
