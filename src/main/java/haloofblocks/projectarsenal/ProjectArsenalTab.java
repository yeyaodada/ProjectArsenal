package haloofblocks.projectarsenal;

import com.mrcrayfish.guns.client.CustomGunManager;
import com.mrcrayfish.guns.item.GunItem;
import haloofblocks.projectarsenal.core.registry.ArsenalItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Creative tab for Project Arsenal
 *
 * @author Autovw
 */
public class ProjectArsenalTab extends ItemGroup
{
    /**
     * Instance of the {@link ProjectArsenalTab}
     */
    public static final ItemGroup TAB = new ProjectArsenalTab(ProjectArsenal.MOD_ID);

    private ProjectArsenalTab(String label)
    {
        super(label);
    }

    @Override
    public ItemStack makeIcon()
    {
        GunItem gun = (GunItem) ArsenalItems.GOLDEN_HAWK.get();
        ItemStack stack = gun.getDefaultInstance();
        stack.getOrCreateTag().putInt("AmmoCount", gun.getGun().getGeneral().getMaxAmmo());
        return stack;
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items)
    {
        super.fillItemList(items);
        CustomGunManager.fill(items);
    }
}
