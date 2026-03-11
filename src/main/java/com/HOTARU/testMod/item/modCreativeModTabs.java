package com.HOTARU.testMod.item;


import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.block.modBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class modCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TEST_TAB_1 =
            CREATIVE_MODE_TABS.register("test_tab_1",()-> CreativeModeTab.builder()
                    .icon(()->new ItemStack(modItems.TEST_ITEM_1.get()))
                    .title(Component.translatable("itemGroup.test_tab_1"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(modItems.TEST_ITEM_1.get());
                        pOutput.accept(modItems.TEST_ITEM_2.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> TEST_TAB_2 =
            CREATIVE_MODE_TABS.register("test_tab_2",()-> CreativeModeTab.builder()
                    .icon(()->new ItemStack(modItems.TEST_ITEM_3.get()))
                    .title(Component.translatable("itemGroup.test_tab_2"))
                    .displayItems((pParameters, pOutput) -> {
                      pOutput.accept(modItems.TEST_ITEM_3.get());
                      pOutput.accept(modItems.TEST_ITEM_4.get());
                      pOutput.accept(Items.DIAMOND);
                    }).build());

    public static final RegistryObject<CreativeModeTab> TEST_TAB_3 =
            CREATIVE_MODE_TABS.register("test_tab_3",()-> CreativeModeTab.builder()
                    .icon(()->new ItemStack(modBlocks.TEST_BLOCK_1.get()))
                    .title(Component.translatable("itemGroup.test_tab_3"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(modBlocks.TEST_BLOCK_1.get());
                        pOutput.accept(modBlocks.TEST_BLOCK_2.get());
                        pOutput.accept(modBlocks.TEST_BLOCK_3.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> ZIJIE_WANG =
            CREATIVE_MODE_TABS.register("zijie_wang_tab",()-> CreativeModeTab.builder()
                    .icon(()->new ItemStack(modBlocks.ZIJIE_WANG.get()))
                    .title(Component.translatable("itemGroup.zijie_wang_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(modItems.HEAD_ZIJIE_WANG.get());
                        pOutput.accept(modItems.ARM_ZIJIE_WANG.get());
                        pOutput.accept(modItems.BODY_ZIJIE_WANG.get());
                        pOutput.accept(modItems.LEGS_ZIJIE_WANG.get());
                        pOutput.accept(modItems.PIECE_ZIJIE_WANG.get());
                        pOutput.accept(modItems.SHIT.get());
                        pOutput.accept(modBlocks.ZIJIE_WANG.get());
                    }).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
