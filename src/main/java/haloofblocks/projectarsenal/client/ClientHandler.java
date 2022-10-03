package haloofblocks.projectarsenal.client;

import haloofblocks.projectarsenal.client.screen.ArsenalWorkbenchScreen;
import haloofblocks.projectarsenal.core.registry.ArsenalContainers;
import net.minecraft.client.gui.ScreenManager;

/**
 * @author Autovw
 */
public class ClientHandler {
    public static void setup()
    {
        ScreenManager.register(ArsenalContainers.ARSENAL_WORKBENCH.get(), ArsenalWorkbenchScreen::new);
    }
}
