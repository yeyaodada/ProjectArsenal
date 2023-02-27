package haloofblocks.projectarsenal;

import haloofblocks.projectarsenal.client.ClientHandler;
import haloofblocks.projectarsenal.config.Config;
import haloofblocks.projectarsenal.core.registry.ArsenalItems;
import haloofblocks.projectarsenal.core.registry.ArsenalSounds;
import haloofblocks.projectarsenal.datagen.*;
import haloofblocks.projectarsenal.network.PacketHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ArsenalItems.ITEMS.register(bus);
        ArsenalSounds.SOUNDS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(PacketHandler::setup);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(ClientHandler::setup);
    }

    private void gatherData(final GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new ArsenalSoundDefinitionsGenerator(packOutput, helper));

        generator.addProvider(event.includeServer(), new ArsenalRecipeGenerator(packOutput));
    }
}
