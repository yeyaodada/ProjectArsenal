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
        MinecraftForge.EVENT_BUS.addListener(HudOverlay::onRenderTick);

        KeyBindings.register();

        registerModelOverrides();
    }

    public static void registerModelOverrides()
    {
        ModelOverrides.register(ArsenalItems.AA_TWELVE.get(), new SimpleModel(SpecialModels.AA_TWELVE::getModel));
        ModelOverrides.register(ArsenalItems.AUTO_NINE.get(), new SimpleModel(SpecialModels.AUTO_NINE::getModel));
        ModelOverrides.register(ArsenalItems.CZ_SEVEN_FIVE.get(), new SimpleModel(SpecialModels.CZ_SEVEN_FIVE::getModel));
        ModelOverrides.register(ArsenalItems.DESERT_EAGLE.get(), new SimpleModel(SpecialModels.DESERT_EAGLE::getModel));
        ModelOverrides.register(ArsenalItems.DESERT_EAGLE_CLASSIC.get(), new SimpleModel(SpecialModels.DESERT_EAGLE_CLASSIC::getModel));
        ModelOverrides.register(ArsenalItems.DP_TWENTY_SEVEN.get(), new SimpleModel(SpecialModels.DP_TWENTY_SEVEN::getModel));
        ModelOverrides.register(ArsenalItems.GLOCK_TWENTY_ONE.get(), new SimpleModel(SpecialModels.GLOCK_TWENTY_ONE::getModel));
        ModelOverrides.register(ArsenalItems.GOLDEN_HAWK.get(), new SimpleModel(SpecialModels.GOLDEN_HAWK::getModel));
        ModelOverrides.register(ArsenalItems.M_NINE.get(), new SimpleModel(SpecialModels.M_NINE::getModel));
        ModelOverrides.register(ArsenalItems.M_TWO_FOUR_NINE.get(), new SimpleModel(SpecialModels.M_TWO_FOUR_NINE::getModel));
        ModelOverrides.register(ArsenalItems.M_NINETEEN_ELEVEN.get(), new SimpleModel(SpecialModels.M_NINETEEN_ELEVEN::getModel));
        ModelOverrides.register(ArsenalItems.MARK_XIX.get(), new SimpleModel(SpecialModels.MARK_XIX::getModel));
        ModelOverrides.register(ArsenalItems.MAXIM_NINE.get(), new SimpleModel(SpecialModels.MAXIM_NINE::getModel));
        ModelOverrides.register(ArsenalItems.MOSSOU.get(), new SimpleModel(SpecialModels.MOSSOU::getModel));
        ModelOverrides.register(ArsenalItems.MP_FIVE_A_FOUR.get(), new SimpleModel(SpecialModels.MP_FIVE_A_FOUR::getModel));
        ModelOverrides.register(ArsenalItems.P_NINETY.get(), new SimpleModel(SpecialModels.P_NINETY::getModel));
        ModelOverrides.register(ArsenalItems.P_TWO_FIFTY.get(), new SimpleModel(SpecialModels.P_TWO_FIFTY::getModel));
        ModelOverrides.register(ArsenalItems.PRISMATIC.get(), new SimpleModel(SpecialModels.PRISMATIC::getModel));
        ModelOverrides.register(ArsenalItems.SCARH.get(), new SimpleModel(SpecialModels.SCAR_H::getModel));
        ModelOverrides.register(ArsenalItems.SHRIKE.get(), new SimpleModel(SpecialModels.SHRIKE::getModel));
        ModelOverrides.register(ArsenalItems.VAL.get(), new SimpleModel(SpecialModels.VAL::getModel));
        ModelOverrides.register(ArsenalItems.VECTOR.get(), new SimpleModel(SpecialModels.VECTOR::getModel));
    }
}
