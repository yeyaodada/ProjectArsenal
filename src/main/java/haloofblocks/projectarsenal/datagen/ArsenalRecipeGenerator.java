package haloofblocks.projectarsenal.datagen;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.crafting.WorkbenchIngredient;
import com.mrcrayfish.guns.crafting.WorkbenchRecipeBuilder;
import com.mrcrayfish.guns.datagen.GunGen;
import com.mrcrayfish.guns.item.GunItem;
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
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 20))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 2))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_anvil", has(Items.ANVIL))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_firework_star", has(Items.FIREWORK_STAR))
                .addCriterion("has_gunpowder", has(Items.GUNPOWDER))
                .addCriterion("has_netherite_ingots", has(Tags.Items.INGOTS_NETHERITE))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.AUTO_NINE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 40))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 2))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 15))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_netherite_ingots", has(Tags.Items.INGOTS_NETHERITE))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.CZ_SEVEN_FIVE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 24))
                .addIngredient(WorkbenchIngredient.of(Items.WHITE_CONCRETE, 32))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.DUSTS_GLOWSTONE, 3))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 10))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_white_concrete", has(Items.WHITE_CONCRETE))
                .addCriterion("has_glowstone_dust", has(Tags.Items.DUSTS_GLOWSTONE))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.DESERT_EAGLE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 64))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GEMS_EMERALD, 3))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 20))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_emerald", has(Tags.Items.GEMS_EMERALD))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.DP_TWENTY_SEVEN.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 128))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 3))
                .addIngredient(WorkbenchIngredient.of(Items.SPRUCE_PLANKS, 16))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 20))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_netherite_ingots", has(Tags.Items.INGOTS_NETHERITE))
                .addCriterion("has_spruce_planks", has(Items.SPRUCE_PLANKS))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.GLOCK_TWENTY_ONE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 32))
                .addIngredient(WorkbenchIngredient.of(Items.WHITE_CONCRETE, 32))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GEMS_DIAMOND, 1))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 20))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_white_concrete", has(Items.WHITE_CONCRETE))
                .addCriterion("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.GOLDEN_HAWK.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_GOLD, 64))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GEMS_DIAMOND, 7))
                .addIngredient(WorkbenchIngredient.of(Items.HEART_OF_THE_SEA, 1))
                .addIngredient(WorkbenchIngredient.of(Items.SOUL_TORCH, 1))
                .addIngredient(WorkbenchIngredient.of(Items.GOLDEN_HORSE_ARMOR, 1))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .addCriterion("has_gold_ingots", has(Tags.Items.INGOTS_GOLD))
                .addCriterion("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                .addCriterion("has_heart_of_the_sea", has(Items.HEART_OF_THE_SEA))
                .addCriterion("has_soul_torch", has(Items.SOUL_TORCH))
                .addCriterion("has_golden_horse_armor", has(Items.GOLDEN_HORSE_ARMOR))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.M_TWO_FOUR_NINE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 200))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 75))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.STORAGE_BLOCKS_IRON, 10))
                .addIngredient(WorkbenchIngredient.of(Items.GREEN_CONCRETE, 32))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 3))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_iron_block", has(Tags.Items.STORAGE_BLOCKS_IRON))
                .addCriterion("has_green_concrete", has(Items.GREEN_CONCRETE))
                .addCriterion("has_ingots_netherite", has(Tags.Items.INGOTS_NETHERITE))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.M_NINETEEN_ELEVEN.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 48))
                .addIngredient(WorkbenchIngredient.of(Items.WHITE_CONCRETE, 24))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 10))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_white_concrete", has(Items.WHITE_CONCRETE))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.MARK_XIX.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 64))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE, 3))
                .addIngredient(WorkbenchIngredient.of(Items.BLACK_CONCRETE, 16))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 20))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_redstone_block", has(Tags.Items.STORAGE_BLOCKS_REDSTONE))
                .addCriterion("has_black_concrete", has(Items.BLACK_CONCRETE))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.MAXIM_NINE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 32))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GEMS_EMERALD, 1))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 10))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_emerald", has(Items.EMERALD))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.MOSSOU.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 32))
                .addIngredient(WorkbenchIngredient.of(Items.ACACIA_PLANKS, 48))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Items.FIREWORK_STAR, 3))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 20))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.STORAGE_BLOCKS_DIAMOND, 1))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_acacia_planks", has(Items.ACACIA_PLANKS))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_firework_star", has(Items.FIREWORK_STAR))
                .addCriterion("has_gunpowder", has(Items.GUNPOWDER))
                .addCriterion("has_diamond_block", has(Tags.Items.STORAGE_BLOCKS_DIAMOND))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.MP_FIVE_A_FOUR.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 50))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 10))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.P_TWO_FIFTY.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 64))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 10))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.PRISMATIC.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 100))
                .addIngredient(WorkbenchIngredient.of(Items.WHITE_CONCRETE, 50))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 2))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 20))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 1))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_white_concrete", has(Items.WHITE_CONCRETE))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .addCriterion("has_ingots_netherite", has(Tags.Items.INGOTS_NETHERITE))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.SHRIKE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 150))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 35))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.STORAGE_BLOCKS_IRON, 7))
                .addIngredient(WorkbenchIngredient.of(Items.WHITE_CONCRETE, 75))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 3))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_iron_block", has(Tags.Items.STORAGE_BLOCKS_IRON))
                .addCriterion("has_white_concrete", has(Items.WHITE_CONCRETE))
                .addCriterion("has_ingots_netherite", has(Tags.Items.INGOTS_NETHERITE))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.VAL.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 16))
                .addIngredient(WorkbenchIngredient.of(Items.GREEN_CONCRETE, 24))
                .addIngredient(WorkbenchIngredient.of(Items.SPONGE, 1))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 10))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_green_concrete", has(Items.GREEN_CONCRETE))
                .addCriterion("has_sponge", has(Items.SPONGE))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ArsenalItems.VECTOR.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 32))
                .addIngredient(WorkbenchIngredient.of(Items.WHITE_CONCRETE, 32))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_NETHERITE, 2))
                .addIngredient(WorkbenchIngredient.of(Items.FIRE_CHARGE, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 10))
                .addCriterion("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
                .addCriterion("has_white_concrete", has(Items.GREEN_CONCRETE))
                .addCriterion("has_ingots_netherite", has(Tags.Items.INGOTS_NETHERITE))
                .addCriterion("has_fire_charge", has(Items.FIRE_CHARGE))
                .addCriterion("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .build(consumer);

    }
}
