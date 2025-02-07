package svenhjol.charmony.tweaks.client.features.mob_textures;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.tweaks.client.features.mob_textures.custom_renderers.*;

import java.util.*;

public final class Registers extends Setup<MobTextures> {
    public final Pair<List<ResourceLocation>, List<ResourceLocation>> chickens = Pair.of(new ArrayList<>(), new ArrayList<>());
    public final Pair<List<ResourceLocation>, List<ResourceLocation>> cows = Pair.of(new ArrayList<>(), new ArrayList<>());
    public final Pair<List<ResourceLocation>, List<ResourceLocation>> dolphins = Pair.of(new ArrayList<>(), new ArrayList<>());
    public final Pair<List<ResourceLocation>, List<ResourceLocation>> snowGolems = Pair.of(new ArrayList<>(), new ArrayList<>());
    public final Pair<List<ResourceLocation>, List<ResourceLocation>> squids = Pair.of(new ArrayList<>(), new ArrayList<>());
    public final Pair<List<ResourceLocation>, List<ResourceLocation>> turtles = Pair.of(new ArrayList<>(), new ArrayList<>());
    public final Pair<List<ResourceLocation>, List<ResourceLocation>> wanderingTraders = Pair.of(new ArrayList<>(), new ArrayList<>());
    public final Map<DyeColor, ResourceLocation> sheep = new HashMap<>();

    public Registers(MobTextures feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            if (feature().dolphins()) EntityRendererRegistry.register(EntityType.DOLPHIN, CustomDolphinRenderer::new);
            if (feature().sheep()) EntityRendererRegistry.register(EntityType.SHEEP, CustomSheepRenderer::new);
            if (feature().snowGolems()) EntityRendererRegistry.register(EntityType.SNOW_GOLEM, CustomSnowGolemRenderer::new);
            if (feature().squids()) EntityRendererRegistry.register(EntityType.SQUID, CustomSquidRenderer::new);
            if (feature().turtles()) EntityRendererRegistry.register(EntityType.TURTLE, CustomTurtleRenderer::new);
            if (feature().wanderingTraders()) EntityRendererRegistry.register(EntityType.WANDERING_TRADER, CustomWanderingTraderRenderer::new);

            ClientEntityEvents.ENTITY_LOAD.register(this::clientEntityLoad);
        };
    }

    private void clientEntityLoad(Entity entity, ClientLevel level) {
        if (!(entity instanceof Player)) return;
        setupColorTextures(sheep, MobType.SHEEP, "sheep");
        setupCustomTextures(dolphins.getFirst(), MobType.DOLPHIN, "dolphin", 3, true);
        setupCustomTextures(dolphins.getSecond(), MobType.DOLPHIN, "rare_dolphin", 2, false);
        setupCustomTextures(snowGolems.getFirst(), MobType.SNOW_GOLEM, "snow_golem", 5, true);
        setupCustomTextures(squids.getFirst(), MobType.SQUID, "squid", 4, true);
        setupCustomTextures(squids.getSecond(), MobType.SQUID, "rare_squid", 2, false);
        setupCustomTextures(turtles.getFirst(), MobType.TURTLE, "turtle", 3, true);
        setupCustomTextures(turtles.getSecond(), MobType.TURTLE, "rare_turtle", 1, false);
        setupCustomTextures(wanderingTraders.getFirst(), MobType.WANDERING_TRADER, "wandering_trader", 4, true);
    }

    private void setupCustomTextures(List<ResourceLocation> set, MobType mobType, String baseName, int max, boolean withVanilla) {
        set.clear();

        if (withVanilla) {
            set.add(mobType.vanillaTexture());
        }

        for (var i = 1; i <= max; i++) {
            set.add(mobType.customTexture(feature().mod(), baseName + i));
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void setupColorTextures(Map<DyeColor, ResourceLocation> set, MobType mobType, String baseName) {
        set.clear();

        for (DyeColor color : DyeColor.values()) {
            set.put(color, mobType.customTexture(feature().mod(), baseName + "_" + color.toString()));
        }
    }
}
