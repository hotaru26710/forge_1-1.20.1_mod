package com.HOTARU.testMod.item;

import com.HOTARU.testMod.TestMod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, TestMod.MOD_ID);//这里创建forge延迟注册器，告诉我们要注册的类型是potion，传入我的mod_id下


    //在这里注册药水
    public static final RegistryObject<Potion> TEST_POTION =
            POTIONS.register("test_potion",()->new Potion(
                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED,10000,20),
                    new MobEffectInstance(MobEffects.JUMP,10000,20),
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,10000,20),
                    new MobEffectInstance(MobEffects.HEALTH_BOOST,10000,20)

            ));//注册一个叫test_potion的药水，效果是给予玩家速度、跳跃、抗性和生命提升，持续时间10000tick，等级20

    public static ItemStack createTestPotionStack() {
        return PotionUtils.setPotion(new ItemStack(Items.POTION), TEST_POTION.get());
    }





    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
