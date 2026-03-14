package com.HOTARU.testMod.datagen;

import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TestMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //在这里添加方块的状态和模型生成代码
         simpleBlock(ModBlocks.TEST_BLOCK_1.get(),cubeAll(ModBlocks.TEST_BLOCK_1.get()));
         simpleBlock(ModBlocks.TEST_BLOCK_2.get(),cubeAll(ModBlocks.TEST_BLOCK_2.get()));
         simpleBlock(ModBlocks.TEST_BLOCK_3.get(),cubeAll(ModBlocks.TEST_BLOCK_3.get()));
         simpleBlock(ModBlocks.ZIJIE_WANG.get(),cubeAll(ModBlocks.ZIJIE_WANG.get()));
         simpleBlock(ModBlocks.GALAXY_ORE.get(),cubeAll(ModBlocks.GALAXY_ORE.get()));
         simpleBlock(ModBlocks.GALAXY_BLOCK.get(),cubeAll(ModBlocks.GALAXY_BLOCK.get()));
    }
}