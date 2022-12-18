package haloofblocks.projectarsenal.network;

import haloofblocks.projectarsenal.common.event.FireModesHandler;
import haloofblocks.projectarsenal.common.item.ArsenalGunItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

/**
 * @author Autovw
 */
public class ServerPlayHandler
{
    public static void handleSelectFireMode(ServerPlayer player)
    {
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof ArsenalGunItem gunItem) || !gunItem.hasFireModeSelector())
            return;

        // Change selected fire mode to the next available fire mode
        FireModesHandler.nextFireMode(stack);
    }

    public static void handleBurstReset(ServerPlayer player)
    {
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof ArsenalGunItem gunItem) || !gunItem.hasFireModeSelector())
            return;

        // Reset the burst counter + remove tag
        FireModesHandler.resetBurstCount(stack);
    }
}
