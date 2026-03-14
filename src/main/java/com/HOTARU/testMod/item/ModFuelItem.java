package com.HOTARU.testMod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class ModFuelItem extends Item {
    private int burntime = 0;

    public ModFuelItem(Properties pProperties, int burntime) {
        super(pProperties);
        this.burntime=burntime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return burntime;
    }
}
