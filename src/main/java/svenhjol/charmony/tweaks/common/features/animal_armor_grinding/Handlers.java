package svenhjol.charmony.tweaks.common.features.animal_armor_grinding;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.events.GrindstoneEvents.GrindstoneMenuInstance;

@SuppressWarnings("unused")
public final class Handlers extends Setup<AnimalArmorGrinding> {
    public Handlers(AnimalArmorGrinding feature) {
        super(feature);
    }

    @SuppressWarnings("SameReturnValue")
    public boolean handleOnTake(GrindstoneMenuInstance instance, Player player, ItemStack stack) {
        if (player.level().isClientSide()) return false;

        var slot0 = instance.input.getItem(0);
        var slot1 = instance.input.getItem(1);

        if (feature().registers.recipes.containsKey(slot0.getItem()) || feature().registers.recipes.containsKey(slot1.getItem())) {
            feature().advancements.groundAnimalArmor((ServerPlayer) player);
        }

        return false;
    }

    public boolean handleCalculateOutput(GrindstoneMenuInstance instance) {
        var slot0 = instance.input.getItem(0);
        var slot1 = instance.input.getItem(1);

        if (slot0.isEnchanted() || slot1.isEnchanted()) {
            return false;
        }

        if (feature().registers.recipes.containsKey(slot0.getItem()) && slot0.getDamageValue() == 0 && slot1.isEmpty()) {
            instance.output.setItem(0, new ItemStack(feature().registers.recipes.get(slot0.getItem())));
        } else if (feature().registers.recipes.containsKey(slot1.getItem()) && slot1.getDamageValue() == 0 && slot0.isEmpty()) {
            instance.output.setItem(0, new ItemStack(feature().registers.recipes.get(slot1.getItem())));
        } else {
            return false;
        }

        instance.menu.broadcastChanges();
        return true;
    }

    public boolean handleCanPlace(Container container, ItemStack stack) {
        return feature().registers.recipes.containsKey(stack.getItem());
    }
}
