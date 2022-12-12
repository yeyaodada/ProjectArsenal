package haloofblocks.projectarsenal.network.message;

import haloofblocks.projectarsenal.network.ServerPlayHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author Autovw
 */
public class MessageResetBurst
{
    public MessageResetBurst()
    {
    }

    public static void encode(MessageResetBurst message, PacketBuffer buffer)
    {
    }

    public static MessageResetBurst decode(PacketBuffer buffer)
    {
        return new MessageResetBurst();
    }

    public static void handle(MessageResetBurst message, Supplier<NetworkEvent.Context> supplier)
    {
        supplier.get().enqueueWork(() ->
        {
            ServerPlayerEntity player = supplier.get().getSender();

            if (player != null)
            {
                ServerPlayHandler.handleBurstReset(player);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
