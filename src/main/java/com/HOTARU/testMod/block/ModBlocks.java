package com.HOTARU.testMod.block;

import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.block.galaxy_furnace.GalaxyFurnace;
import com.HOTARU.testMod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);


    public static final RegistryObject<Block> TEST_BLOCK_1 =
            registerBlock("test_block_1",()->new Block(BlockBehaviour.Properties.of().strength(1.5F,3.0F)));
    public static final RegistryObject<Block> TEST_BLOCK_2 =
            registerBlock("test_block_2",()->new Block(BlockBehaviour.Properties.of().strength(3.0F,3.0F)));
    public static final RegistryObject<Block> TEST_BLOCK_3 =
            registerBlock("test_block_3",()->new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<Block> ZIJIE_WANG =
            registerBlock("zijie_wang",()->new Block(BlockBehaviour.Properties.of().strength(1.0F,3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GALAXY_ORE =
            registerBlock("galaxy_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GALAXY_BLOCK =
            registerBlock("galaxy_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GALAXY_FURNACE =
            registerBlock("galaxy_furnace", GalaxyFurnace::new);






    private static <T extends Block>void registerBlockItems(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name,()->new BlockItem(block.get(),new Item.Properties()));
    }

    private static<T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> blocks =BLOCKS.register(name,block);
        registerBlockItems(name,blocks);
        return blocks;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }



}
