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
public class MessageSelectFireMode
{
    private final int nextFireMode;

    public MessageSelectFireMode(int nextFireMode)
    {
        this.nextFireMode = nextFireMode;
    }

    public static void encode(MessageSelectFireMode message, PacketBuffer buffer)
    {
        buffer.writeInt(message.nextFireMode);
    }

    public static MessageSelectFireMode decode(PacketBuffer buffer)
    {
        return new MessageSelectFireMode(buffer.readInt());
    }

    public static void handle(MessageSelectFireMode message, Supplier<NetworkEvent.Context> supplier)
    {
        supplier.get().enqueueWork(() ->
        {
            ServerPlayerEntity player = supplier.get().getSender();

            if (player != null)
            {
                ServerPlayHandler.handleSelectFireMode(message.nextFireMode, player);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
