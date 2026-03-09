package com.HOTARU.testMod.item;

import com.HOTARU.testMod.TestMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class modItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);//这里创建forge延迟注册器，告诉我们要注册的类型是item，传入我的mod_id下



    //这一块注册具体物品
    public static final RegistryObject<Item> TEST_ITEM_1 =
            ITEMS.register("test_item_1",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEST_ITEM_2 =
            ITEMS.register("test_item_2",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEST_ITEM_3 =
            ITEMS.register("material/test_item_3",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEST_ITEM_4 =
            ITEMS.register("material/test_item_4",()->new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }



}
