package haloofblocks.projectarsenal;

import haloofblocks.projectarsenal.client.ClientHandler;
import haloofblocks.projectarsenal.core.registry.ArsenalItems;
import haloofblocks.projectarsenal.core.registry.ArsenalSounds;
import haloofblocks.projectarsenal.network.PacketHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Autovw
 */
@Mod(ProjectArsenal.MOD_ID)
public class ProjectArsenal
{
    public static final String MOD_ID = "projectarsenal";
    private static final Logger LOGGER = LogManager.getLogger();

    public ProjectArsenal()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ArsenalItems.ITEMS.register(bus);
        ArsenalSounds.SOUNDS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(PacketHandler::setup);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(ClientHandler::setup);
    }
}
