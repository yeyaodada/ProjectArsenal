package haloofblocks.projectarsenal.network;

import haloofblocks.projectarsenal.common.event.FireModesHandler;
import haloofblocks.projectarsenal.common.item.ArsenalGunItem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * @author Autovw
 */
public class ServerPlayHandler
{
    public static void handleSelectFireMode(ServerPlayerEntity player)
    {
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof ArsenalGunItem) || !((ArsenalGunItem) stack.getItem()).hasFireModeSelector())
            return;

        // Change selected fire mode to the next available fire mode
        FireModesHandler.nextFireMode(stack);
    }

    public static void handleBurstReset(ServerPlayerEntity player)
    {
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof ArsenalGunItem) || !((ArsenalGunItem) stack.getItem()).hasFireModeSelector())
            return;

        // Reset the burst counter + remove tag
        FireModesHandler.resetBurstCount(stack);
    }
}
