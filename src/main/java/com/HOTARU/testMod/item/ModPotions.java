package com.HOTARU.testMod.item;

import com.HOTARU.testMod.TestMod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, TestMod.MOD_ID);//这里创建forge延迟注册器，告诉我们要注册的类型是potion，传入我的mod_id下


    //在这里注册药水
    public static final RegistryObject<Potion> TEST_POTION =
            POTIONS.register("test_potion",()->new Potion(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,10000,99)));







    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
