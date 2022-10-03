package haloofblocks.projectarsenal.core.registry;

import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.common.block.ArsenalWorkbenchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Autovw
 */
public class ArsenalBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectArsenal.MOD_ID);

    public static final RegistryObject<Block> ARSENAL_WORKBENCH = register("arsenal_workbench", () -> new ArsenalWorkbenchBlock(Block.Properties.of(Material.METAL).strength(1.5F)));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, block1 -> new BlockItem(block1, new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, BlockItem> supplier)
    {
        T block = blockSupplier.get();
        if(supplier != null)
        {
            ArsenalItems.ITEMS.register(name, () -> supplier.apply(block));
        }
        return ArsenalBlocks.BLOCKS.register(name, () -> block);
    }
}
