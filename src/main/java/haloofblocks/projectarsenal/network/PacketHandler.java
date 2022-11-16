package haloofblocks.projectarsenal.network;

import haloofblocks.projectarsenal.ProjectArsenal;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * @author Autovw
 */
// TODO move to Framework's API
public class PacketHandler
{
    public static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel HANDSHAKE_CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(ProjectArsenal.MOD_ID, "handshake"), () -> PROTOCOL_VERSION, s -> true, s -> true);
    private static final SimpleChannel PLAY_CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(ProjectArsenal.MOD_ID, "play"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int nextMessageId = 0;

    public static void setup()
    {
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
