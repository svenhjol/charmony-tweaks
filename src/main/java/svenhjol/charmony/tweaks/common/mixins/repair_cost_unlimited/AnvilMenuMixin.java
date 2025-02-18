package svenhjol.charmony.tweaks.common.mixins.repair_cost_unlimited;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import svenhjol.charmony.tweaks.client.mixins.repair_cost_visible.AnvilScreenMixin;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
    /**
     * A player in creative mode can repair or enchant without "too expensive".
     * Remove the message by always returning true for this check.
     * This modifies the updated output slot on the server side.
     * @see AnvilScreenMixin for the client side.
     */
    @Redirect(
        method = "createResult",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;hasInfiniteMaterials()Z",
            ordinal = 1
        )
    )
    private boolean hookCreateResultCheckAbilities(Player instance) {
        return true;
    }
}
