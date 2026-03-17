package com.HOTARU.testMod.client;

import com.HOTARU.testMod.ModMenuTypes;
import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.container.screen.GalaxyFurnaceScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD,value= Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        //在这里注册渲染器
        registerScreens();
    }

    private static void registerScreens(){
        MenuScreens.register(ModMenuTypes.GALAXY_FURNACE_MENU.get(), GalaxyFurnaceScreen::new);
    }
}
