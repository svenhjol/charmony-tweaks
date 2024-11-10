package svenhjol.charmony.tweaks.common.animal_armor_grinding;

import net.minecraft.world.level.ItemLike;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.GrindstoneEvents;

import java.util.HashMap;
import java.util.Map;

public class Registers extends Setup<AnimalArmorGrinding> {
    public final Map<ItemLike, ItemLike> recipes = new HashMap<>();

    public Registers(AnimalArmorGrinding feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            GrindstoneEvents.CAN_PLACE.handle(feature().handlers::handleCanPlace);
            GrindstoneEvents.CALCULATE_OUTPUT.handle(feature().handlers::handleCalculateOutput);
            GrindstoneEvents.ON_TAKE.handle(feature().handlers::handleOnTake);
        };
    }
}
