package haloofblocks.projectarsenal.client;

import haloofblocks.projectarsenal.ProjectArsenal;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

/**
 * @author Autovw
 */
public class KeyBindings
{
    public static final KeyBinding KEY_SELECT_FIRE_MODE = new KeyBinding("key." + ProjectArsenal.MOD_ID + ".select_fire_mode", GLFW.GLFW_KEY_V, "key.categories." + ProjectArsenal.MOD_ID);

    public static void register()
    {
        ClientRegistry.registerKeyBinding(KEY_SELECT_FIRE_MODE);
    }
}
