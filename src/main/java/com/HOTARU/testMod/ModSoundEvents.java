package com.HOTARU.testMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {


    public static final SoundEvent ARMOR_EQUIP_NETHERITE = register("item.armor.equip_netherite");

    private static SoundEvent register(String pName) {
        String[] parts = pName.split(":");
        ResourceLocation location = parts.length == 2 ? ResourceLocation.tryBuild(parts[0], parts[1]) : ResourceLocation.tryBuild("minecraft", parts[0]);
        return SoundEvent.createVariableRangeEvent(location);
    }
}
