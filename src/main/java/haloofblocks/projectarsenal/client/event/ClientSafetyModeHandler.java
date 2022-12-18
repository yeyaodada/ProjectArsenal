package haloofblocks.projectarsenal.client.event;

import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.common.FireModes;
import haloofblocks.projectarsenal.common.item.ArsenalGunItem;
import haloofblocks.projectarsenal.core.registry.ArsenalSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

/**
 * @author Autovw
 */
@Mod.EventBusSubscriber(modid = ProjectArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientSafetyModeHandler
{
    @SubscribeEvent
    public static void onMouseButtonEvent(InputEvent.MouseButton event)
    {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null)
            return;

        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof ArsenalGunItem gunItem) || !gunItem.hasFireModeSelector())
            return;

        if (!gunItem.getSelectedFireMode(stack).equals(FireModes.SAFETY))
            return;

        if (mc.options.keyAttack.isDown() && event.getAction() == GLFW.GLFW_PRESS)
        {
            // Play sound effect when player attempts to fire gun in safety mode
            player.playSound(ArsenalSounds.GUNFIRE_BLOCKED.get());
        }
    }
}
