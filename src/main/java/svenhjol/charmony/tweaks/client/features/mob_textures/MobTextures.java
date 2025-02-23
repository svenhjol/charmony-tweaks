package svenhjol.charmony.tweaks.client.features.mob_textures;

import svenhjol.charmony.core.annotations.Configurable;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Client, description = """
    Mobs may spawn with alternative textures.""")
public final class MobTextures extends SidedFeature {
    public final Handlers handlers;
    public final Registers registers;

    @Configurable(name = "Snow golem face textures", description = "If true, snow golems may spawn with alternative derp faces.")
    private static boolean snowGolems = true;

    @Configurable(name = "Wandering trader textures", description = "If true, wandering traders may spawn with alternative textures.")
    private static boolean wanderingTraders = true;

    public boolean snowGolems() {
        return snowGolems;
    }

    public boolean wanderingTraders() {
        return wanderingTraders;
    }

    public MobTextures(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
        registers = new Registers(this);
    }
}
