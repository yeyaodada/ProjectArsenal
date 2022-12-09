package haloofblocks.projectarsenal.client.event;

import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.common.FireModes;
import haloofblocks.projectarsenal.common.item.ArsenalGunItem;
import haloofblocks.projectarsenal.network.PacketHandler;
import haloofblocks.projectarsenal.network.message.MessageResetBurst;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

/**
 * @author Autovw
 */
@Mod.EventBusSubscriber(modid = ProjectArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientBurstFireHandler
{
    @SubscribeEvent
    public static void onMouseButtonEvent(InputEvent.MouseButton event)
    {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null)
            return;

        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof ArsenalGunItem gunItem) || !gunItem.hasFireMode())
            return;

        if (!gunItem.getSelectedFireMode().equals(FireModes.BURST))
            return;

        if (mc.options.keyAttack.isDown() && event.getAction() == GLFW.GLFW_PRESS)
        {
            // Send packet to reset the burst fire counter
            PacketHandler.getPlayChannel().sendToServer(new MessageResetBurst());
        }
    }
}
