package com.HOTARU.testMod.datagen;

import com.HOTARU.testMod.block.ModBlocks;
import com.HOTARU.testMod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider{
    public ModBlockLootTablesProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

     @Override
    protected void generate() {
        //在这里添加方块的掉落表
         dropSelf(ModBlocks.TEST_BLOCK_1.get());
         dropSelf(ModBlocks.TEST_BLOCK_2.get());
         dropSelf(ModBlocks.GALAXY_BLOCK.get());
         dropSelf(ModBlocks.GALAXY_FURNACE.get());
         add(ModBlocks.TEST_BLOCK_3.get(),block -> createOreLikeDrops(ModBlocks.TEST_BLOCK_3.get(), Items.DIAMOND,1.0F,2.0F));
         add(ModBlocks.ZIJIE_WANG.get(),block -> createOreLikeDrops(ModBlocks.ZIJIE_WANG.get(),ModItems.SHIT.get(),20.0F,25.0F));
         add(ModBlocks.GALAXY_ORE.get(),block->createOreLikeDrops(ModBlocks.GALAXY_ORE.get(),ModItems.RAW_GALAXY.get(),1.0F,2.0F));

    }

    protected LootTable.Builder createOreLikeDrops(Block pBlock, Item item,float min,float max) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))//添加最大最小值
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));

    }




    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
