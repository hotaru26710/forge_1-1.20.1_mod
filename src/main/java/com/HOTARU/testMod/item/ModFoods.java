package com.HOTARU.testMod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public  static  final FoodProperties MEAT_BONE = new FoodProperties.Builder().nutrition(3).saturationMod(0.4f)
            .effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200),0.2f).build();
    public  static  final FoodProperties SHIT = new FoodProperties.Builder().nutrition(1).saturationMod(0f)
            .effect(()->new MobEffectInstance(MobEffects.BLINDNESS,200),1.0f)
            .effect(()->new MobEffectInstance(MobEffects.DIG_SLOWDOWN,200),1.0f)
            .effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200),1.0f)
            .effect(()->new MobEffectInstance(MobEffects.HUNGER,200),1.0f)
            .effect(()->new MobEffectInstance(MobEffects.WEAKNESS,200),1.0f)
            .effect(()->new MobEffectInstance(MobEffects.POISON,200),1.0f)
            .effect(()->new MobEffectInstance(MobEffects.CONFUSION,200),1.0f)
            .effect(()->new MobEffectInstance(MobEffects.UNLUCK,200),1.0f).alwaysEat().build();
}
