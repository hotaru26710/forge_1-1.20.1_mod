package com.HOTARU.testMod.datagen;

import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.item.ModFuelItem;
import com.HOTARU.testMod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ARM_ZIJIE_WANG.get());
        basicItem(ModItems.LEGS_ZIJIE_WANG.get());
        basicItem(ModItems.BODY_ZIJIE_WANG.get());
        basicItem(ModItems.HEAD_ZIJIE_WANG.get());
        basicItem(ModItems.PIECE_ZIJIE_WANG.get());
        basicItem(ModItems.SHIT.get());
        basicItem(ModItems.TEST_ITEM_1.get());
        basicItem(ModItems.TEST_ITEM_2.get());
        basicItem(ModItems.TEST_ITEM_3.get());
        basicItem(ModItems.TEST_ITEM_4.get());
        basicItem(ModItems.RAW_GALAXY.get());
        basicItem(ModItems.GALAXY.get());
        basicItem(ModItems.MEAT_BONE.get());
        basicItem(ModItems.FUEL_CELL.get());
        basicItem(ModItems.GALAXY_AXE.get());
        basicItem(ModItems.GALAXY_HOE.get());
        basicItem(ModItems.GALAXY_PIKAXE.get());
        basicItem(ModItems.GALAXY_SHOVEL.get());
        basicItem(ModItems.GALAXY_SWORD.get());
        basicItem(ModItems.GALAXY_ARMOR_HELMET.get());
        basicItem(ModItems.GALAXY_ARMOR_BOOTS.get());
        basicItem(ModItems.GALAXY_ARMOR_CHESTPLATE.get());
        basicItem(ModItems.GALAXY_ARMOR_LEGGINGS.get());

        withExistingParent("galaxy_block", modLoc("block/galaxy_block"));
        withExistingParent("galaxy_ore", modLoc("block/galaxy_ore"));
        withExistingParent("test_block_1", modLoc("block/test_block_1"));
        withExistingParent("test_block_2", modLoc("block/test_block_2"));
        withExistingParent("test_block_3", modLoc("block/test_block_3"));
        withExistingParent("zijie_wang", modLoc("block/zijie_wang"));


    }
}
