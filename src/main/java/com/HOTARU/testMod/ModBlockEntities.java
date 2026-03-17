package com.HOTARU.testMod;

import com.HOTARU.testMod.block.ModBlocks;
import com.HOTARU.testMod.block.blockentity.GalaxyFurnaceBlockEntity;
import com.HOTARU.testMod.block.galaxy_furnace.GalaxyFurnace;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,TestMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<GalaxyFurnaceBlockEntity>> GALAXY_FURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("galaxy_furnace_block_entity",()->
                    // Builder.of(构造器引用, 绑定的方块...)
                    BlockEntityType.Builder.of(
                    GalaxyFurnaceBlockEntity::new,// 如何创建 BE
                    ModBlocks.GALAXY_FURNACE.get()// 哪些方块可以拥有它
            ).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
