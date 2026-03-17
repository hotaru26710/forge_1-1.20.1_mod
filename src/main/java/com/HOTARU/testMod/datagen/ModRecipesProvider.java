package com.HOTARU.testMod.datagen;

import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.block.ModBlocks;
import com.HOTARU.testMod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipesProvider(PackOutput pOutput) {
        super(pOutput);
    }

    public static final List<ItemLike> GALAXY = List.of(ModItems.RAW_GALAXY.get(), ModBlocks.GALAXY_ORE.get());
    public static final List<ItemLike> MEAT_BONE = List.of(ModItems.ARM_ZIJIE_WANG.get(), ModItems.LEGS_ZIJIE_WANG.get(), ModItems.BODY_ZIJIE_WANG.get(), ModItems.HEAD_ZIJIE_WANG.get());

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, GALAXY, RecipeCategory.MISC, ModItems.GALAXY.get(), 0.25F, 200, "galaxy");
        oreBlasting(pWriter, GALAXY, RecipeCategory.MISC, ModItems.GALAXY.get(), 0.25F, 100, "galaxy");

        oreSmelting(pWriter, MEAT_BONE, RecipeCategory.MISC, ModItems.MEAT_BONE.get(), 0.25F, 200, "meat_bone");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocks.GALAXY_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.GALAXY.get())
                .unlockedBy(getHasName(ModItems.GALAXY.get()), has(ModItems.GALAXY.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GALAXY.get(),9)
                .requires(ModBlocks.GALAXY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.GALAXY_BLOCK.get()), has(ModBlocks.GALAXY_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PIECE_ZIJIE_WANG.get(),1)
                .requires(ModItems.SHIT.get())
                .unlockedBy(getHasName(ModItems.SHIT.get()),has(ModItems.SHIT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.ARM_ZIJIE_WANG.get(),1)
                .pattern("X  ")
                .pattern(" XX")
                .pattern(" XX")
                .define('X',ModItems.PIECE_ZIJIE_WANG.get())
                .unlockedBy(getHasName(ModItems.PIECE_ZIJIE_WANG.get()),has(ModItems.PIECE_ZIJIE_WANG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.LEGS_ZIJIE_WANG.get(),1)
                .pattern("X X")
                .pattern("X X")
                .pattern("X X")
                .define('X',ModItems.PIECE_ZIJIE_WANG.get())
                .unlockedBy(getHasName(ModItems.PIECE_ZIJIE_WANG.get()),has(ModItems.PIECE_ZIJIE_WANG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.BODY_ZIJIE_WANG.get(),1)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X',ModItems.PIECE_ZIJIE_WANG.get())
                .unlockedBy(getHasName(ModItems.PIECE_ZIJIE_WANG.get()),has(ModItems.PIECE_ZIJIE_WANG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.HEAD_ZIJIE_WANG.get(),1)
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .define('X',ModItems.PIECE_ZIJIE_WANG.get())
                .unlockedBy(getHasName(ModItems.PIECE_ZIJIE_WANG.get()),has(ModItems.PIECE_ZIJIE_WANG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.ZIJIE_WANG.get(),1)
                .pattern("XZX")
                .pattern("ABA")
                .pattern("XYX")
                .define('X',ModItems.PIECE_ZIJIE_WANG.get())
                .define('Y',ModItems.LEGS_ZIJIE_WANG.get())
                .define('Z',ModItems.HEAD_ZIJIE_WANG.get())
                .define('A',ModItems.ARM_ZIJIE_WANG.get())
                .define('B',ModItems.BODY_ZIJIE_WANG.get())
                .unlockedBy(getHasName(ModItems.PIECE_ZIJIE_WANG.get()),has(ModItems.PIECE_ZIJIE_WANG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.GALAXY_FURNACE.get(),1)
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .define('X',ModItems.GALAXY.get())
                .unlockedBy(getHasName(ModItems.GALAXY.get()),has(ModItems.GALAXY.get()))
                .save(pWriter);
    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, TestMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
