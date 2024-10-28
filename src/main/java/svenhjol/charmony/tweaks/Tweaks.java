package svenhjol.charmony.tweaks;

import net.minecraft.resources.ResourceLocation;
import svenhjol.charmony.core.annotations.ModDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.enums.Side;

@ModDefinition(id = Tweaks.ID, sides = {Side.Client},
    name = "Tweaks",
    description = "Small client tweaks that don't change core gameplay. Compatible with vanilla servers such as Realms.")
public final class Tweaks extends Mod {
    public static final String ID = "charmony-tweaks";
    private static Tweaks instance;

    private Tweaks() {}

    public static Tweaks instance() {
        if (instance == null) {
            instance = new Tweaks();
        }
        return instance;
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}
