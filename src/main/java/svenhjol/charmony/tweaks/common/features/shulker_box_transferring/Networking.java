package svenhjol.charmony.tweaks.common.features.shulker_box_transferring;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.tweaks.Tweaks;

public final class Networking extends Setup<ShulkerBoxTransferring> {
    public Networking(ShulkerBoxTransferring feature) {
        super(feature);
    }

    public record C2SAddItemToShulkerBox() implements CustomPacketPayload {
        public static Type<C2SAddItemToShulkerBox> TYPE = new Type<>(Tweaks.id("add_item_to_shulker_box"));
        static StreamCodec<FriendlyByteBuf, C2SAddItemToShulkerBox> CODEC = StreamCodec.of(C2SAddItemToShulkerBox::encode, C2SAddItemToShulkerBox::decode);

        public static void send() {
            ClientPlayNetworking.send(new C2SAddItemToShulkerBox());
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }

        private static C2SAddItemToShulkerBox decode(FriendlyByteBuf buf) {
            return new C2SAddItemToShulkerBox();
        }

        private static void encode(FriendlyByteBuf buf, C2SAddItemToShulkerBox self) {
            // no op
        }
    }
}
