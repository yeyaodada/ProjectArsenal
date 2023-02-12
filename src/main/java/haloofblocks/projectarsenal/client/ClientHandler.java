package haloofblocks.projectarsenal.client;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;
import com.mrcrayfish.guns.client.render.gun.model.SimpleModel;
import haloofblocks.projectarsenal.client.event.HudOverlay;
import haloofblocks.projectarsenal.core.registry.ArsenalItems;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author Autovw
 */
public class ClientHandler
{
    public static void setup()
    {
        MinecraftForge.EVENT_BUS.addListener(KeyBindings::register);
        MinecraftForge.EVENT_BUS.addListener(HudOverlay::onRenderTick);

        registerModelOverrides();
    }

    public static void registerModelOverrides()
    {
        ModelOverrides.register(ArsenalItems.AA_TWELVE.get(), new SimpleModel(SpecialModels.AA_TWELVE::getModel));
        ModelOverrides.register(ArsenalItems.AUTO_NINE.get(), new SimpleModel(SpecialModels.AUTO_NINE::getModel));
        ModelOverrides.register(ArsenalItems.CZ_SEVEN_FIVE.get(), new SimpleModel(SpecialModels.CZ_SEVEN_FIVE::getModel));
        ModelOverrides.register(ArsenalItems.DESERT_EAGLE.get(), new SimpleModel(SpecialModels.DESERT_EAGLE::getModel));
        ModelOverrides.register(ArsenalItems.DESERT_EAGLE_CLASSIC.get(), new SimpleModel(SpecialModels.DESERT_EAGLE_CLASSIC::getModel));
    }
}
