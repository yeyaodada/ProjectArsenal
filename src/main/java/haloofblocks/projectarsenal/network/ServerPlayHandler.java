package haloofblocks.projectarsenal.network;

import com.mrcrayfish.guns.common.Gun;
import haloofblocks.projectarsenal.common.FireMode;
import haloofblocks.projectarsenal.common.item.ArsenalGunItem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * @author Autovw
 */
public class ServerPlayHandler
{
    public static void handleSelectFireMode(int nextFireMode, ServerPlayerEntity player)
    {
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof ArsenalGunItem))
            return;

        ArsenalGunItem gunItem = (ArsenalGunItem) stack.getItem();

        FireMode fireMode = gunItem.getFireMode();

        // Change selected fire mode
        gunItem.setSelectedFireMode(fireMode.getFireModes().get(nextFireMode));
    }
}
