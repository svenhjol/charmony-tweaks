package svenhjol.charmony.tweaks.client.item_tidying;

import com.ibm.icu.impl.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import svenhjol.charmony.api.ItemTidyingButtonTweak;
import svenhjol.charmony.core.base.Setup;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handlers extends Setup<ItemTidying> {
    private static final int LEFT = 159;
    private static final int TOP = 12;

    private final List<ImageButton> sortingButtons = new ArrayList<>();

    public Handlers(ItemTidying feature) {
        super(feature);
    }

    public void setupScreen(Screen screen) {
        var minecraft = Minecraft.getInstance();
        var player = minecraft.player;

        if (player == null) return;
        if (!(screen instanceof AbstractContainerScreen<?> containerScreen)) return;

        var clazz = containerScreen.getClass();
        if (feature().providers.blacklisted.contains(clazz)) return;
        ItemTidyingButtonTweak tweak = feature().providers.tweaks.get(clazz);

        sortingButtons.clear();
        var menu = containerScreen.getMenu();
        var x = containerScreen.leftPos + LEFT;
        var y = containerScreen.topPos - TOP;

        if (tweak != null) {
            var offset = tweak.getXYOffset();
            x += offset.getFirst();
            y += offset.getSecond();
        }

        // Gather slot types.
        var slots = menu.slots;

        List<Slot> playerSlots = new LinkedList<>();
        List<Slot> containerSlots = new LinkedList<>();

        for (var slot : slots) {
            if (slot.container == player.getInventory()) {
                playerSlots.add(slot);
            } else {
                containerSlots.add(slot);
            }
        }

        if (!containerSlots.isEmpty() && feature().providers.whitelisted.contains(clazz)) {
            addSortingButton(screen, x, y + containerSlots.getFirst().y,
                click -> sortContainer(containerSlots, containerScreen, -1, -1));
        }

        if (!playerSlots.isEmpty()) {
            addSortingButton(screen, x, y + playerSlots.getFirst().y,
                click -> sortContainer(playerSlots, containerScreen, 9, -1));
        }

        sortingButtons.forEach(containerScreen::addRenderableWidget);
    }

    public void sortContainer(List<Slot> slots, AbstractContainerScreen<?> screen, int startIndex, int endIndex) {
        // Get a map of all the unique items and their stack size in each slot.
        Map<Item, CopyOnWriteArrayList<Pair<Slot, Integer>>> merge = new HashMap<>();

        if (screen instanceof InventoryScreen) {
            startIndex = 9;
            endIndex = 35;
        }

        for (Slot slot : slots) {
            if (!canUseSlotIndex(slot, startIndex, endIndex)) continue;

            var stack = slot.getItem();
            var item = stack.getItem();
            var count = stack.getCount();
            var max = stack.getMaxStackSize();

            if (stack.isEmpty()) continue;
            if (count == max) continue;

            merge.computeIfAbsent(item, l -> new CopyOnWriteArrayList<>());
            var holders = merge.get(item);

            if (count > 0) {
                holders.add(Pair.of(slot, count));
            }
        }

        // Merge each slot to the next one of the same item.
        try {
            for (Item item : merge.keySet()) {
                var max = item.getDefaultMaxStackSize();
                var holders = merge.get(item);
                if (holders.size() == 1) continue; // No other slots have this item, it's unique.

                for (int i = 0; i < holders.size() - 1; i++) {
                    var holder = holders.get(i);
                    var slot = holder.first;
                    var count = holder.second;

                    // We need to deplete this holder by adding it to the next ones.
                    var pointer = i + 1;
                    while (pointer < holders.size() && count > 0) {
                        var nextHolder = holders.get(pointer);
                        var nextSlot = nextHolder.first;
                        var nextCount = nextHolder.second;
                        if (nextCount == max) {
                            pointer++;
                            continue;
                        }

                        var min = nextCount;
                        for (var n = min; n < max; n++) {
                            screen.slotClicked(slot, slot.getContainerSlot(), 0, ClickType.PICKUP);
                            screen.slotClicked(nextSlot, nextSlot.getContainerSlot(), 1, ClickType.PICKUP);
                            screen.slotClicked(slot, slot.getContainerSlot(), 0, ClickType.PICKUP);
                            nextCount++;
                            if (--count == 0) {
                                break;
                            }
                        }

                        holders.set(i, Pair.of(slot, count));
                        holders.set(pointer, Pair.of(nextSlot, nextCount));
                    }
                }
            }
        } catch (Exception e) {
            feature().log().error(e.getMessage());
        }

        List<Slot> occupied = new LinkedList<>();

        for (Slot slot : slots) {
            if (!canUseSlotIndex(slot, startIndex, endIndex)) continue;

            var stack = slot.getItem();
            if (stack.isEmpty()) continue;
            occupied.add(slot);
        }

        var current = 0;
        var max = occupied.size();

        try {
            for (int i = 0; i < slots.size(); i++) {
                var slot = slots.get(i);
                if (!canUseSlotIndex(slot, startIndex, endIndex)) continue;

                var stack = slot.getItem();
                if (stack.isEmpty() && current < max) {
                    var occupiedSlot = occupied.get(current);
                    screen.slotClicked(occupiedSlot, occupiedSlot.index, 0, ClickType.PICKUP);
                    screen.slotClicked(slot, slot.index, 0, ClickType.PICKUP);
                }
                current++;
            }
        } catch (Exception e) {
            feature().log().error(e.getMessage());
        }
    }

    public void renderScreen(AbstractContainerScreen<?> screen, GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Re-render when recipe is opened/closed.
        var x = screen.leftPos;
        sortingButtons.forEach(button -> button.setPosition(x + LEFT, button.getY()));
    }

    public void addSortingButton(Screen screen, int x, int y, Button.OnPress callback) {
        sortingButtons.add(new ImageButton(x, y, 10, 10, feature().registers.tidyButton, callback));
    }

    private boolean canUseSlotIndex(Slot slot, int start, int end) {
        return (start == -1 || (start > -1 && slot.getContainerSlot() >= start))
            && (end == -1 || (end > -1 && slot.getContainerSlot() <= end));
    }
}
