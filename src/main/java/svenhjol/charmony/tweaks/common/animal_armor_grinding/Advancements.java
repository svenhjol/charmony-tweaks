package svenhjol.charmony.tweaks.common.animal_armor_grinding;

import net.minecraft.server.level.ServerPlayer;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;

public final class Advancements extends Setup<AnimalArmorGrinding> {
    public Advancements(AnimalArmorGrinding feature) {
        super(feature);
    }

    public void groundAnimalArmor(ServerPlayer player) {
        AdvancementHelper.trigger("ground_animal_armor", player);
    }
}
