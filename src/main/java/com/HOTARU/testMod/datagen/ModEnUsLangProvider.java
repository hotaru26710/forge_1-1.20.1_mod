package com.HOTARU.testMod.datagen;

import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.block.ModBlocks;
import com.HOTARU.testMod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.common.Mod;

public class ModEnUsLangProvider extends LanguageProvider {
    public ModEnUsLangProvider(PackOutput output) {
        super(output, TestMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //这里填写模组物块翻译
        add(ModBlocks.TEST_BLOCK_1.get(), "Test Block 1");
        add(ModBlocks.TEST_BLOCK_2.get(), "Test Block 2");
        add(ModBlocks.TEST_BLOCK_3.get(), "Test Block 3");
        add(ModBlocks.ZIJIE_WANG.get(), "Zijie Wang");
        add(ModBlocks.GALAXY_ORE.get(), "Galaxy Ore");
        add(ModBlocks.GALAXY_BLOCK.get(), "Galaxy Block");
        add(ModBlocks.GALAXY_FURNACE.get(), "Galaxy Furnace");
        //这里填写模组物品的翻译
        add(ModItems.ARM_ZIJIE_WANG.get(),"zijie's arm");
        add(ModItems.LEGS_ZIJIE_WANG.get(),"zijie's leg");
        add(ModItems.BODY_ZIJIE_WANG.get(),"zijie's body");
        add(ModItems.HEAD_ZIJIE_WANG.get(),"zijie's head");
        add(ModItems.PIECE_ZIJIE_WANG.get(),"zijie's piece");
        add(ModItems.TEST_ITEM_1.get(),"test item 1");
        add(ModItems.TEST_ITEM_2.get(),"test item 2");
        add(ModItems.TEST_ITEM_3.get(),"test item 3");
        add(ModItems.TEST_ITEM_4.get(),"test item 4");
        add(ModItems.SHIT.get(),"shit");
        add(ModItems.MEAT_BONE.get(),"meat bone");
        add(ModItems.GALAXY.get(),"Galaxy");
        add(ModItems.RAW_GALAXY.get(),"Raw Galaxy");
        add(ModItems.FUEL_CELL.get(),"fuel cell");
        add(ModItems.GALAXY_AXE.get(),"galaxy axe");
        add(ModItems.GALAXY_HOE.get(),"galaxy hoe");
        add(ModItems.GALAXY_PIKAXE.get(),"galaxy pickaxe");
        add(ModItems.GALAXY_SHOVEL.get(),"galaxy shovel");
        add(ModItems.GALAXY_SWORD.get(),"galaxy sword");
        //这里填写创造物品栏的翻译
        add("itemGroup.test_tab_1","test_tab_1");
        add("itemGroup.test_tab_2","test_tab_2");
        add("itemGroup.test_tab_3","test_tab_3");
        add("itemGroup.zijie_wang_tab","zijie_wang_tab");

    }
}
