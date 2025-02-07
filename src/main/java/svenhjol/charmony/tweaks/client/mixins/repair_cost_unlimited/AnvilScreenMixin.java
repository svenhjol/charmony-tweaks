package svenhjol.charmony.tweaks.client.mixins.repair_cost_unlimited;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import svenhjol.charmony.core.base.Environment;
import svenhjol.charmony.tweaks.common.mixins.repair_cost_unlimited.AnvilMenuMixin;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
    /**
     * A player in creative mode does not see the "Too Expensive" message.
     * Remove the message by always returning true for this check.
     * This targets the *display* of the message on the client side.
     * @see AnvilMenuMixin for the server side.
     */
    @Redirect(
        method = "renderLabels",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;hasInfiniteMaterials()Z"
        )
    )
    private boolean hookRenderLabelsCheckAbilities(LocalPlayer instance) {
        if (!Environment.usesCharmonyServer()) {
            return instance.hasInfiniteMaterials(); // Default behaviour
        }
        return true;
    }
}
