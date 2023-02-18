package haloofblocks.projectarsenal.client;

import haloofblocks.projectarsenal.ProjectArsenal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Autovw
 */
@Mod.EventBusSubscriber(modid = ProjectArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public enum SpecialModels
{
    AA_TWELVE("gun/aa12"),
    AUTO_NINE("gun/auto9"),
    CZ_SEVEN_FIVE("gun/cz75"),
    DESERT_EAGLE("gun/desert_eagle"),
    DESERT_EAGLE_CLASSIC("gun/desert_eagle_classic"),
    DP_TWENTY_SEVEN("gun/dp27"),
    GLOCK_TWENTY_ONE("gun/glock21"),
    GOLDEN_HAWK("gun/golden_hawk"),
    M_NINE("gun/m9"),
    M_TWO_FOUR_NINE("gun/m249"),
    M_NINETEEN_ELEVEN("gun/m1911"),
    MARK_XIX("gun/markxix"),
    MAXIM_NINE("gun/maxim9"),
    MOSSOU("gun/mossou"),
    MP_FIVE_A_FOUR("gun/mp5a4"),
    P_NINETY("gun/p90"),
    P_TWO_FIFTY("gun/p250"),
    PRISMATIC("gun/prismatic"),
    SCAR_H("gun/scarh"),
    SHRIKE("gun/shrike"),
    VAL("gun/val"),
    VECTOR("gun/vector");

    private final ResourceLocation modelLoc;
    private BakedModel cachedModel;

    SpecialModels(String modelName)
    {
        this.modelLoc = new ResourceLocation(ProjectArsenal.MOD_ID, "special/" + modelName);
    }

    public BakedModel getModel() {
        if (this.cachedModel == null)
        {
            this.cachedModel = Minecraft.getInstance().getModelManager().getModel(this.modelLoc);
        }
        return this.cachedModel;
    }

    @SubscribeEvent
    public static void onRegisterAdditionalModelEvent(final ModelEvent.RegisterAdditional event)
    {
        for (SpecialModels specialModel : values())
        {
            event.register(specialModel.modelLoc);
        }
    }

    @SubscribeEvent
    public static void onBakingCompletedEvent(final ModelEvent.BakingCompleted event)
    {
        for (SpecialModels specialModel : values())
        {
            specialModel.cachedModel = null;
        }
    }
}
