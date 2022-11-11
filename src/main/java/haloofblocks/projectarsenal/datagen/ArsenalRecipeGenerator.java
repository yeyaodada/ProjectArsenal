package haloofblocks.projectarsenal.datagen;

import com.mrcrayfish.guns.GunMod;
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
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 32))
                .addIngredient(WorkbenchIngredient.of(Items.ANVIL, 1))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Items.FIREWORK_STAR, 3))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 2))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_anvil", has(Items.ANVIL))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Items.GUNPOWDER))
                .addCriterion("has_netherite_ingots", has(Tags.Items.INGOTS_NETHERITE))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.AUTO_NINE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 40))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 2))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Items.GUNPOWDER, 15))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_netherite_ingots", has(Tags.Items.INGOTS_NETHERITE))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.CZ_SEVEN_FIVE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 24))
                .addIngredient(WorkbenchIngredient.of(Items.WHITE_CONCRETE, 32))
                .addIngredient(WorkbenchIngredient.of(Items.GLOWSTONE_DUST, 3))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Items.GUNPOWDER, 10))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_white_concrete", has(Items.WHITE_CONCRETE))
                .addCriterion("has_glowstone_dust", has(Items.GLOWSTONE_DUST))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.DESERT_EAGLE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 64))
                .addIngredient(WorkbenchIngredient.of(Items.EMERALD, 3))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Items.GUNPOWDER, 20))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_emerald", has(Items.EMERALD))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.DP_TWENTY_SEVEN.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 128))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 3))
                .addIngredient(WorkbenchIngredient.of(Items.SPRUCE_PLANKS, 16))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Items.GUNPOWDER, 20))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_netherite_ingots", has(Tags.Items.INGOTS_NETHERITE))
                .addCriterion("has_spruce_planks", has(Items.SPRUCE_PLANKS))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.GLOCK_TWENTY_ONE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 32))
                .addIngredient(WorkbenchIngredient.of(Items.WHITE_CONCRETE, 32))
                .addIngredient(WorkbenchIngredient.of(Items.DIAMOND, 1))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Items.GUNPOWDER, 20))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_white_concrete", has(Items.WHITE_CONCRETE))
                .addCriterion("has_diamond", has(Items.DIAMOND))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.GOLDEN_HAWK.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_GOLD, 64))
                .addIngredient(WorkbenchIngredient.of(Items.DIAMOND, 7))
                .addIngredient(WorkbenchIngredient.of(Items.HEART_OF_THE_SEA, 1))
                .addIngredient(WorkbenchIngredient.of(Items.SOUL_TORCH, 1))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_diamond", has(Items.DIAMOND))
                .addCriterion("has_heart_of_the_sea", has(Items.HEART_OF_THE_SEA))
                .addCriterion("has_soul_torch", has(Items.SOUL_TORCH))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.M_TWO_FOUR_NINE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 200))
                .addIngredient(WorkbenchIngredient.of(Items.IRON_NUGGET, 75))
                .addIngredient(WorkbenchIngredient.of(Items.IRON_BLOCK, 10))
                .addIngredient(WorkbenchIngredient.of(Items.GREEN_CONCRETE, 32))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 3))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_iron_nugget", has(Items.IRON_NUGGET))
                .addCriterion("has_iron_block", has(Items.IRON_BLOCK))
                .addCriterion("has_green_concrete", has(Items.GREEN_CONCRETE))
                .addCriterion("has_ingots_netherite", has(Tags.Items.INGOTS_NETHERITE))
                .build(consumer);
    }
}
