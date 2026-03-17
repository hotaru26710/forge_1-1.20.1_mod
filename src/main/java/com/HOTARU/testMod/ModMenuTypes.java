package com.HOTARU.testMod;

import com.HOTARU.testMod.container.menu.GalaxyFurnaceMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES=
            DeferredRegister.create(ForgeRegistries.MENU_TYPES,TestMod.MOD_ID);

    //注册区
    public static final RegistryObject<MenuType<GalaxyFurnaceMenu>> GALAXY_FURNACE_MENU =
            registerMenuType("galaxy_furnace_menu", GalaxyFurnaceMenu::new);
    //结束

     private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
         return MENU_TYPES.register(name,()-> IForgeMenuType.create(factory));
     }

     public static void register(IEventBus eventBus){
         MENU_TYPES.register(eventBus);
     }


}
