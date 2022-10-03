package haloofblocks.projectarsenal.network;

import com.mrcrayfish.guns.network.message.IMessage;
import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.network.message.MessageCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.Supplier;

/**
 * @author Autovw
 */
public class PacketHandler
{
    public static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel HANDSHAKE_CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(ProjectArsenal.MOD_ID, "handshake"), () -> PROTOCOL_VERSION, s -> true, s -> true);
    private static final SimpleChannel PLAY_CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(ProjectArsenal.MOD_ID, "play"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);;
    private static int nextMessageId = 0;

    public static void setup()
    {
        registerPlayMessage(MessageCraft.class, MessageCraft::new, LogicalSide.SERVER);
    }

    private static <T extends IMessage> void registerPlayMessage(Class<T> clazz, Supplier<T> messageSupplier, LogicalSide side)
    {
        PLAY_CHANNEL.registerMessage(nextMessageId++, clazz, IMessage::encode, buffer ->
        {
            T t = messageSupplier.get();
            t.decode(buffer);
            return t;
        }, (t, supplier) ->
        {
            if (supplier.get().getDirection().getReceptionSide() != side)
                throw new RuntimeException("Attempted to handle message " + clazz.getSimpleName() + " on the wrong logical side!");
            t.handle(supplier);
        }
        );
    }

    public static SimpleChannel getHandshakeChannel()
    {
        return HANDSHAKE_CHANNEL;
    }

    public static SimpleChannel getPlayChannel()
    {
        return PLAY_CHANNEL;
    }
}
