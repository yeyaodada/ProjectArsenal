package haloofblocks.projectarsenal.core.registry;

import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.common.blockentity.ArsenalWorkbenchBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * @author Autovw
 */
public class ArsenalBlockEntities
{
    public static final DeferredRegister<TileEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ProjectArsenal.MOD_ID);

    public static final RegistryObject<TileEntityType<ArsenalWorkbenchBlockEntity>> ARSENAL_WORKBENCH = register("arsenal_workbench", ArsenalWorkbenchBlockEntity::new, () -> new Block[]{ArsenalBlocks.ARSENAL_WORKBENCH.get()});

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String id, Supplier<T> factoryIn, Supplier<Block[]> validBlocksSupplier)
    {
        return BLOCK_ENTITIES.register(id, () -> TileEntityType.Builder.of(factoryIn, validBlocksSupplier.get()).build(null));
    }
}
