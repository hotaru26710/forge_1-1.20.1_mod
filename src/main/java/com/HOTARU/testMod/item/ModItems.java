package com.HOTARU.testMod.item;

import com.HOTARU.testMod.TestMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);//这里创建forge延迟注册器，告诉我们要注册的类型是item，传入我的mod_id下



    //这一块注册具体物品
    public static final RegistryObject<Item> TEST_ITEM_1 =
            ITEMS.register("test_item_1",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEST_ITEM_2 =
            ITEMS.register("test_item_2",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEST_ITEM_3 =
            ITEMS.register("test_item_3",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEST_ITEM_4 =
            ITEMS.register("test_item_4",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_GALAXY =
            ITEMS.register("raw_galaxy",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> GALAXY =
            ITEMS.register("galaxy",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHIT =
            ITEMS.register("shit",()->new Item(new Item.Properties().food(ModFoods.SHIT)));
    public static final RegistryObject<Item> MEAT_BONE =
            ITEMS.register("meat_bone",()->new Item(new Item.Properties().food(ModFoods.MEAT_BONE)));
    public static final RegistryObject<Item> HEAD_ZIJIE_WANG =
            ITEMS.register("head_zijie_wang",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARM_ZIJIE_WANG =
            ITEMS.register("arm_zijie_wang",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> BODY_ZIJIE_WANG =
            ITEMS.register("body_zijie_wang",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> LEGS_ZIJIE_WANG =
            ITEMS.register("legs_zijie_wang",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> PIECE_ZIJIE_WANG =
            ITEMS.register("piece_zijie_wang",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> FUEL_CELL =
            ITEMS.register("fuel_cell",()->new ModFuelItem(new Item.Properties(),3000));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }



}