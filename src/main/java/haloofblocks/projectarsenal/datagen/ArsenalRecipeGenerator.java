package haloofblocks.projectarsenal.datagen;

import com.mrcrayfish.guns.crafting.WorkbenchIngredient;
import com.mrcrayfish.guns.crafting.WorkbenchRecipeBuilder;
import haloofblocks.projectarsenal.core.registry.ArsenalItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author Autovw
 */
public class ArsenalRecipeGenerator extends RecipeProvider
{
    public ArsenalRecipeGenerator(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
    {
        WorkbenchRecipeBuilder.crafting(ArsenalItems.AA_TWELVE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 16))
                .addIngredient(WorkbenchIngredient.of(Items.ANVIL, 1))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_anvil", has(Items.ANVIL))
                .build(consumer);
    }
}
