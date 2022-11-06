package haloofblocks.projectarsenal.core.registry;

import com.mrcrayfish.guns.item.GunItem;
import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.ProjectArsenalTab;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Autovw
 */
public class ArsenalItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectArsenal.MOD_ID);

    // ===== GUNS ===== //
    public static final RegistryObject<Item> AA_TWELVE = registerGun("aa12");
    public static final RegistryObject<Item> AUTO_NINE = registerGun("auto9");
    public static final RegistryObject<Item> DESERT_EAGLE = registerGun("desert_eagle");
    public static final RegistryObject<Item> DP_TWENTY_SEVEN = registerGun("dp27");
    public static final RegistryObject<Item> GOLDEN_HAWK = registerGun("golden_hawk");
    public static final RegistryObject<Item> M_TWO_FOUR_NINE = registerGun("m249");
    public static final RegistryObject<Item> MAXIM_NINE = registerGun("maxim9");
    public static final RegistryObject<Item> MOSSOU = registerGun("mossou");
    public static final RegistryObject<Item> MP_FIVE_A_FOUR = registerGun("mp5a4");
    public static final RegistryObject<Item> VAL = registerGun("val");
    // ===== ATTACHMENTS ===== //

    // ===== AMMUNITION ===== //

    private static RegistryObject<Item> registerGun(String name)
    {
        return ITEMS.register(name, () -> new GunItem(new Item.Properties().stacksTo(1).tab(ProjectArsenalTab.TAB)));
    }
}
