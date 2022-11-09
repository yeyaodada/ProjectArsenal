package haloofblocks.projectarsenal.core.registry;

import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.ProjectArsenalTab;
import haloofblocks.projectarsenal.common.item.ArsenalGunItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Autovw
 */
public class ArsenalItems
{
    /**
     * The {@link DeferredRegister} we use for registering items.
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectArsenal.MOD_ID);

    // ===== GUNS ===== //
    public static final RegistryObject<Item> AA_TWELVE = registerGun("aa12", false);
    public static final RegistryObject<Item> AUTO_NINE = registerGun("auto9", false);
    public static final RegistryObject<Item> DESERT_EAGLE = registerGun("desert_eagle", false);
    public static final RegistryObject<Item> DP_TWENTY_SEVEN = registerGun("dp27", false);
    public static final RegistryObject<Item> GOLDEN_HAWK = registerGun("golden_hawk", false);
    public static final RegistryObject<Item> M_TWO_FOUR_NINE = registerGun("m249", false);
    public static final RegistryObject<Item> MAXIM_NINE = registerGun("maxim9", false);
    public static final RegistryObject<Item> MOSSOU = registerGun("mossou", false);
    public static final RegistryObject<Item> MP_FIVE_A_FOUR = registerGun("mp5a4", false);
    public static final RegistryObject<Item> VAL = registerGun("val", false);
    public static final RegistryObject<Item> SHRIKE = registerGun("shrike", true);
    public static final RegistryObject<Item> GLOCK_TWENTY_ONE = registerGun("glock21", true);
    public static final RegistryObject<Item> P_TWO_FIFTY = registerGun("p250", false);
    public static final RegistryObject<Item> MARK_XIX = registerGun("markxix", false);

    // ===== ATTACHMENTS ===== //

    // ===== AMMUNITION ===== //

    /**
     * Helper method for registering guns
     *
     * @param name The in-game name of the gun
     * @param canColor If the gun can be colored or not
     * @return {@link RegistryObject} for the gun item
     */
    private static RegistryObject<Item> registerGun(String name, boolean canColor)
    {
        return ITEMS.register(name, () -> new ArsenalGunItem(new Item.Properties().stacksTo(1).tab(ProjectArsenalTab.TAB), canColor));
    }
}
