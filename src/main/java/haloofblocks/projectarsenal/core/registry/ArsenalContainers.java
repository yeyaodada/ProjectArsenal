package haloofblocks.projectarsenal.core.registry;

import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.common.blockentity.ArsenalWorkbenchBlockEntity;
import haloofblocks.projectarsenal.common.container.ArsenalWorkbenchContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Autovw
 */
public class ArsenalContainers
{
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ProjectArsenal.MOD_ID);

    public static final RegistryObject<ContainerType<ArsenalWorkbenchContainer>> ARSENAL_WORKBENCH = register("arsenal_workbench", (IContainerFactory<ArsenalWorkbenchContainer>) (windowId, playerInventory, data) ->
    {
        ArsenalWorkbenchBlockEntity workstation = (ArsenalWorkbenchBlockEntity) playerInventory.player.level.getBlockEntity(data.readBlockPos());
        return new ArsenalWorkbenchContainer(windowId, playerInventory, workstation);
    }
    );

    private static <T extends Container> RegistryObject<ContainerType<T>> register(String name, ContainerType.IFactory<T> factory)
    {
        return CONTAINERS.register(name, () -> new ContainerType<>(factory));
    }
}
