package com.HOTARU.testMod.datagen;

import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.block.ModBlocks;
import com.HOTARU.testMod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModZhCnLangProvider extends LanguageProvider{
    public ModZhCnLangProvider(PackOutput output) {
        super(output, TestMod.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        //这里填写模组物块翻译
        add(ModBlocks.TEST_BLOCK_1.get(), "测试方块1");
        add(ModBlocks.TEST_BLOCK_2.get(), "测试方块2");
        add(ModBlocks.TEST_BLOCK_3.get(), "测试方块3");
        add(ModBlocks.ZIJIE_WANG.get(), "王子杰");
        add(ModBlocks.GALAXY_ORE.get(), "宇宙矿石");
        add(ModBlocks.GALAXY_BLOCK.get(), "宇宙块");
        add(ModBlocks.GALAXY_FURNACE.get(), "宇宙熔炉");
        //这里填写模组物品的翻译
        add(ModItems.ARM_ZIJIE_WANG.get(),"王子杰的手臂");
        add(ModItems.LEGS_ZIJIE_WANG.get(),"王子杰的腿");
        add(ModItems.BODY_ZIJIE_WANG.get(),"王子杰的身体");
        add(ModItems.HEAD_ZIJIE_WANG.get(),"王子杰的头");
        add(ModItems.PIECE_ZIJIE_WANG.get(),"王子杰的碎片");
        add(ModItems.TEST_ITEM_1.get(),"测试物品1");
        add(ModItems.TEST_ITEM_2.get(),"测试物品2");
        add(ModItems.TEST_ITEM_3.get(),"测试物品3");
        add(ModItems.TEST_ITEM_4.get(),"测试物品4");
        add(ModItems.SHIT.get(),"答辩");
        add(ModItems.MEAT_BONE.get(),"肉骨");
        add(ModItems.GALAXY.get(),"宇宙矿");
        add(ModItems.RAW_GALAXY.get(),"宇宙锭");
        add(ModItems.FUEL_CELL.get(),"燃料电池");
        add(ModItems.GALAXY_AXE.get(),"宇宙斧");
        add(ModItems.GALAXY_HOE.get(),"宇宙锄");
        add(ModItems.GALAXY_PIKAXE.get(),"宇宙镐");
        add(ModItems.GALAXY_SHOVEL.get(),"宇宙锹");
        add(ModItems.GALAXY_SWORD.get(),"宇宙剑");
        //这里填写创造物品栏的翻译
        add("itemGroup.test_tab_1","测试物品栏1");
        add("itemGroup.test_tab_2","测试物品栏2");
        add("itemGroup.test_tab_3","测试物品栏3");
        add("itemGroup.zijie_wang_tab","王子杰的物品栏");

        }
    }
