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
public class MessageSelectNextFireMode
{
    public MessageSelectNextFireMode()
    {
    }

    public static void encode(MessageSelectNextFireMode message, FriendlyByteBuf buffer)
    {
    }

    public static MessageSelectNextFireMode decode(FriendlyByteBuf buffer)
    {
        return new MessageSelectNextFireMode();
    }

    public static void handle(MessageSelectNextFireMode message, Supplier<NetworkEvent.Context> supplier)
    {
        supplier.get().enqueueWork(() ->
        {
            ServerPlayer player = supplier.get().getSender();

            if (player != null)
            {
                ServerPlayHandler.handleSelectFireMode(player);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
