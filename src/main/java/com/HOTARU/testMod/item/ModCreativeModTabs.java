package com.HOTARU.testMod.item;


import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TEST_TAB_1 =
            CREATIVE_MODE_TABS.register("test_tab_1",()-> CreativeModeTab.builder()
                    .icon(()->new ItemStack(ModItems.TEST_ITEM_1.get()))
                    .title(Component.translatable("itemGroup.test_tab_1"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TEST_ITEM_1.get());
                        pOutput.accept(ModItems.TEST_ITEM_2.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> TEST_TAB_2 =
            CREATIVE_MODE_TABS.register("test_tab_2",()-> CreativeModeTab.builder()
                    .icon(()->new ItemStack(ModItems.TEST_ITEM_3.get()))
                    .title(Component.translatable("itemGroup.test_tab_2"))
                    .displayItems((pParameters, pOutput) -> {
                      pOutput.accept(ModItems.TEST_ITEM_3.get());
                      pOutput.accept(ModItems.TEST_ITEM_4.get());
                      pOutput.accept(ModItems.GALAXY.get());
                      pOutput.accept(ModItems.RAW_GALAXY.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> TEST_TAB_3 =
            CREATIVE_MODE_TABS.register("test_tab_3",()-> CreativeModeTab.builder()
                    .icon(()->new ItemStack(ModBlocks.TEST_BLOCK_1.get()))
                    .title(Component.translatable("itemGroup.test_tab_3"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.TEST_BLOCK_1.get());
                        pOutput.accept(ModBlocks.TEST_BLOCK_2.get());
                        pOutput.accept(ModBlocks.TEST_BLOCK_3.get());
                        pOutput.accept(ModBlocks.GALAXY_ORE.get());
                        pOutput.accept(ModBlocks.GALAXY_BLOCK.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> ZIJIE_WANG =
            CREATIVE_MODE_TABS.register("zijie_wang_tab",()-> CreativeModeTab.builder()
                    .icon(()->new ItemStack(ModBlocks.ZIJIE_WANG.get()))
                    .title(Component.translatable("itemGroup.zijie_wang_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.HEAD_ZIJIE_WANG.get());
                        pOutput.accept(ModItems.ARM_ZIJIE_WANG.get());
                        pOutput.accept(ModItems.BODY_ZIJIE_WANG.get());
                        pOutput.accept(ModItems.LEGS_ZIJIE_WANG.get());
                        pOutput.accept(ModItems.PIECE_ZIJIE_WANG.get());
                        pOutput.accept(ModItems.SHIT.get());
                        pOutput.accept(ModBlocks.ZIJIE_WANG.get());
                        pOutput.accept(ModItems.MEAT_BONE.get());
                    }).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
