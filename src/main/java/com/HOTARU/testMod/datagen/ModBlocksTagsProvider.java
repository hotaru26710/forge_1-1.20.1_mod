package com.HOTARU.testMod.datagen;

import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;


import java.util.concurrent.CompletableFuture;

public class ModBlocksTagsProvider extends BlockTagsProvider {

    public ModBlocksTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,@Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //在这里添加方块的标签
         tag(BlockTags.MINEABLE_WITH_PICKAXE)
                 .add(ModBlocks.TEST_BLOCK_1.get(),
                         ModBlocks.TEST_BLOCK_2.get(),
                         ModBlocks.TEST_BLOCK_3.get(),
                         ModBlocks.ZIJIE_WANG.get(),
                         ModBlocks.GALAXY_ORE.get(),
                         ModBlocks.GALAXY_BLOCK.get(),
                         ModBlocks.GALAXY_FURNACE.get()



                 );

    }
}
