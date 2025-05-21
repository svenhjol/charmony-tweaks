package svenhjol.charmony.tweaks.client.features.maps_show_when_hovering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.state.MapRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import svenhjol.charmony.core.base.Setup;

import java.util.List;

public class Handlers extends Setup<MapsShowWhenHovering> {
    public static final RenderType MAP_BACKGROUND = RenderType.text(ResourceLocation.parse("textures/map/map_background.png"));
    private final MapRenderState mapRenderState = new MapRenderState();

    public Handlers(MapsShowWhenHovering feature) {
        super(feature);
    }

    /**
     * Show a rendered map when hovering over a filled map item.
     */
    public void renderMapTooltip(GuiGraphics guiGraphics, ItemStack stack, List<ClientTooltipComponent> lines, int tx, int ty) {
        if (stack != null && stack.getItem() == Items.FILLED_MAP) {
            var minecraft = Minecraft.getInstance();
            var level = minecraft.level;
            if (level == null) return;

            if (!stack.has(DataComponents.MAP_ID)) return;
            var mapId = stack.get(DataComponents.MAP_ID);

            var data = MapItem.getSavedData(mapId, level);
            if (data == null) return;

            var poseStack = guiGraphics.pose();
            ty -= 16;

            var x = tx;
            var y = ty - 72;
            var w = 64;
            var light = 240;
            var right = x + w;

            if (right > minecraft.getWindow().getGuiScaledWidth()) {
                x = minecraft.getWindow().getGuiScaledWidth() - w;
            }

            if (y < 0) {
                y = ty + lines.size() * 10 + 8;
            }

            poseStack.pushMatrix();
            poseStack.translate(x, y, 500.0);
            poseStack.scale(0.5F, 0.5F, 1.0F);

            var bufferSource = minecraft.renderBuffers().bufferSource();
            var vertexConsumer = bufferSource.getBuffer(MAP_BACKGROUND);
            var matrix4f = poseStack.last().pose();

            vertexConsumer.addVertex(matrix4f, -7.0F, 135.0F, 0.0F).setColor(255, 255, 255, 255).setUv(0.0F, 1.0F).setLight(light);
            vertexConsumer.addVertex(matrix4f, 135.0F, 135.0F, 0.0F).setColor(255, 255, 255, 255).setUv(1.0F, 1.0F).setLight(light);
            vertexConsumer.addVertex(matrix4f, 135.0F, -7.0F, 0.0F).setColor(255, 255, 255, 255).setUv(1.0F, 0.0F).setLight(light);
            vertexConsumer.addVertex(matrix4f, -7.0F, -7.0F, 0.0F).setColor(255, 255, 255, 255).setUv(0.0F, 0.0F).setLight(light);

            poseStack.pushMatrix();
            poseStack.translate(0.0, 0.0, 1.0);

            MapRenderer mapRenderer = minecraft.getMapRenderer();
            mapRenderer.extractRenderState(mapId, data, this.mapRenderState);
            mapRenderer.render(this.mapRenderState, poseStack, bufferSource, false, light);

            poseStack.popMatrix();
            poseStack.popMatrix();
        }
    }
}
