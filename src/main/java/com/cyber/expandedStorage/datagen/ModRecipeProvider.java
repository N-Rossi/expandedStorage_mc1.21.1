package com.cyber.expandedStorage.datagen;

import com.cyber.expandedStorage.ExpandedStorage;
import com.cyber.expandedStorage.block.ModBlocks;
import com.cyber.expandedStorage.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder{
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> GALLIUM_SMELTABLES = List.of(ModItems.RAW_GALLIUM,
                ModBlocks.GALLIUM_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GALLIUM_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.GALLIUM_INGOT.get())
                .unlockedBy("has_gallium", has(ModItems.GALLIUM_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GALLIUM_INGOT.get(), 9)
                .requires(ModBlocks.GALLIUM_BLOCK)
                .unlockedBy("has_gallium_block", has(ModBlocks.GALLIUM_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STORAGE_CONTROLLER.get())
                .pattern("WIW")
                .pattern("IGI")
                .pattern("WIW")
                .define('W', ItemTags.PLANKS)
                .define('I', Items.IRON_INGOT)
                .define('G', ModBlocks.GALLIUM_BLOCK)
                .unlockedBy("has_gallium_block", has(ModBlocks.GALLIUM_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STORAGE_EXTENDER.get())
                .pattern("WWW")
                .pattern("WGW")
                .pattern("WWW")
                .define('W', ItemTags.PLANKS)
                .define('G', ModItems.GALLIUM_INGOT)
                .unlockedBy("has_gallium_block", has(ModBlocks.GALLIUM_BLOCK)).save(recipeOutput);

//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GALLIUM_INGOT.get(), 18)
//                .requires(ModBlocks.MAGIC_BLOCK)
//                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK))
//                .save(recipeOutput, "tutorialmod:GALLIUM_from_magic_block");

        oreSmelting(recipeOutput, GALLIUM_SMELTABLES, RecipeCategory.MISC, ModItems.GALLIUM_INGOT.get(), 0.25f, 200, "GALLIUM");
        oreBlasting(recipeOutput, GALLIUM_SMELTABLES, RecipeCategory.MISC, ModItems.GALLIUM_INGOT.get(), 0.25f, 100, "GALLIUM");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, ExpandedStorage.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
