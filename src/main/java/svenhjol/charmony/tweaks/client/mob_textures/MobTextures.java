package svenhjol.charmony.tweaks.client.mob_textures;

import svenhjol.charmony.scaffold.base.Mod;
import svenhjol.charmony.scaffold.base.ModFeature;
import svenhjol.charmony.scaffold.annotations.Configurable;
import svenhjol.charmony.scaffold.annotations.Feature;
import svenhjol.charmony.scaffold.enums.Side;

@Feature(side = Side.Client, description = """
    Mobs may spawn with alternative textures.""")
public final class MobTextures extends ModFeature {
    public final Handlers handlers;
    public final Registers registers;

    @Configurable(name = "Cows", description = "If true, cows may spawn with alternative textures.")
    private static boolean cows = true;

    @Configurable(name = "Chickens", description = "If true, chickens may spawn with alternative textures.")
    private static boolean chickens = true;

    @Configurable(name = "Dolphins", description = "If true, dolphins may spawn with alternative textures.")
    private static boolean dolphins = true;

    @Configurable(name = "Pigs", description = "If true, pigs may spawn with alternative textures.")
    private static boolean pigs = true;

    @Configurable(name = "Sheep", description = "If true, sheep face and 'shorn' textures match their wool color.")
    private static boolean sheep = true;

    @Configurable(name = "Snow golems", description = "If true, snow golems may spawn with alternative derp faces.")
    private static boolean snowGolems = true;

    @Configurable(name = "Squids", description = "If true, squids may spawn with alternative textures.")
    private static boolean squids = true;

    @Configurable(name = "Turtles", description = "If true, turtles may spawn with alternative textures.")
    private static boolean turtles = true;

    @Configurable(name = "Wandering traders", description = "If true, wandering traders may spawn with alternative textures.")
    private static boolean wanderingTraders = true;

    public boolean cows() {
        return cows;
    }

    public boolean chickens() {
        return chickens;
    }

    public boolean dolphins() {
        return dolphins;
    }

    public boolean pigs() {
        return pigs;
    }

    public boolean sheep() {
        return sheep;
    }

    public boolean snowGolems() {
        return snowGolems;
    }

    public boolean squids() {
        return squids;
    }

    public boolean turtles() {
        return turtles;
    }

    public boolean wanderingTraders() {
        return wanderingTraders;
    }

    public int rareTypeChance() {
        return 1000;
    }

    public MobTextures(Mod mod) {
        super(mod);
        handlers = new Handlers(this);
        registers = new Registers(this);
    }
}
