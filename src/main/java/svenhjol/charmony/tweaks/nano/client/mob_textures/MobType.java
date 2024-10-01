package svenhjol.charmony.tweaks.nano.client.mob_textures;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;
import svenhjol.charmony.scaffold.nano.Mod;

import java.util.Locale;

public enum MobType implements StringRepresentable {
    CHICKEN("chicken"),
    COW("cow/cow"),
    DOLPHIN("dolphin"),
    PIG("pig/pig"),
    SHEEP("sheep"),
    SNOW_GOLEM("snow_golem"),
    SQUID("squid/squid"),
    TURTLE("turtle/big_sea_turtle"),
    WANDERING_TRADER("wandering_trader");

    private final ResourceLocation vanillaTexture;

    MobType(String vanillaTexture) {
        this.vanillaTexture = ResourceLocation.parse("textures/entity/" + vanillaTexture + ".png");
    }

    public ResourceLocation vanillaTexture() {
        return vanillaTexture;
    }

    public ResourceLocation customTexture(Mod mod, String texture) {
        return ResourceLocation.tryBuild(mod.id(), "textures/entity/" + this.getSerializedName() + "/" + texture + ".png");
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}