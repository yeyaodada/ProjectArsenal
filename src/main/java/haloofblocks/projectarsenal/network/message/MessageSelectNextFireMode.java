package haloofblocks.projectarsenal.network.message;

import haloofblocks.projectarsenal.network.ServerPlayHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

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

    public static void encode(MessageSelectNextFireMode message, PacketBuffer buffer)
    {
    }

    public static MessageSelectNextFireMode decode(PacketBuffer buffer)
    {
        return new MessageSelectNextFireMode();
    }

    public static void handle(MessageSelectNextFireMode message, Supplier<NetworkEvent.Context> supplier)
    {
        supplier.get().enqueueWork(() ->
        {
            ServerPlayerEntity player = supplier.get().getSender();

            if (player != null)
            {
                ServerPlayHandler.handleSelectFireMode(player);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
