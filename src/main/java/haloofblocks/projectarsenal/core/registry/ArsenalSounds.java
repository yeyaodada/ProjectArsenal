package haloofblocks.projectarsenal.core.registry;

import haloofblocks.projectarsenal.ProjectArsenal;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Autovw
 */
public class ArsenalSounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ProjectArsenal.MOD_ID);

    public static final RegistryObject<SoundEvent> AA_TWELVE = register("item.aa12.fire");
    public static final RegistryObject<SoundEvent> AUTO_NINE = register("item.auto9.fire");
    public static final RegistryObject<SoundEvent> DESERT_EAGLE = register("item.desert_eagle.fire");
    public static final RegistryObject<SoundEvent> DP_TWENTY_SEVEN = register("item.dp27.fire");
    public static final RegistryObject<SoundEvent> GOLDEN_HAWK = register("item.golden_hawk.fire");
    public static final RegistryObject<SoundEvent> M_TWO_FOUR_NINE = register("item.m249.fire");
    public static final RegistryObject<SoundEvent> MAXIM_NINE = register("item.maxim9.fire");
    public static final RegistryObject<SoundEvent> MOSSOU = register("item.mossou.fire");
    public static final RegistryObject<SoundEvent> MP_FIVE_A_FOUR = register("item.mp5a4.fire");
    public static final RegistryObject<SoundEvent> VAL = register("item.val.fire");

    /**
     * Helper method used to register sounds
     * @param name The string name of the fire sound
     */
    private static RegistryObject<SoundEvent> register(String name)
    {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(ProjectArsenal.MOD_ID, name)));
    }
}
