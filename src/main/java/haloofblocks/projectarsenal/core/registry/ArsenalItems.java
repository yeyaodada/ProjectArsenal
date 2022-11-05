package haloofblocks.projectarsenal.core.registry;

import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.item.GunItem;
import haloofblocks.projectarsenal.ProjectArsenal;
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

    // ===== ATTACHMENTS ===== //

    // ===== AMMUNITION ===== //

    private static RegistryObject<Item> registerGun(String name)
    {
        return ITEMS.register(name, () -> new GunItem(new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    }
}
