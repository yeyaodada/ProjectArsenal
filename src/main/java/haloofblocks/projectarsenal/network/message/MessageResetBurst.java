package haloofblocks.projectarsenal.network.message;

import haloofblocks.projectarsenal.network.ServerPlayHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author Autovw
 */
// TODO move to Framework's API
public class MessageResetBurst
{
    public MessageResetBurst()
    {
    }

    public static void encode(MessageResetBurst message, FriendlyByteBuf buffer)
    {
    }

    public static MessageResetBurst decode(FriendlyByteBuf buffer)
    {
        return new MessageResetBurst();
    }

    public static void handle(MessageResetBurst message, Supplier<NetworkEvent.Context> supplier)
    {
        supplier.get().enqueueWork(() ->
        {
            ServerPlayer player = supplier.get().getSender();

            if (player != null)
            {
                ServerPlayHandler.handleBurstReset(player);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
