package com.HOTARU.testMod.block.galaxy_furnace;

import com.HOTARU.testMod.ModBlockEntities;
import com.HOTARU.testMod.block.blockentity.GalaxyFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.client.event.RenderHighlightEvent;
import net.minecraftforge.network.NetworkHooks;
import org.w3c.dom.Entity;

import javax.annotation.Nullable;

// 继承 HorizontalDirectionalBlock，使方块天然支持水平四方向（N/S/E/W）朝向。
public class GalaxyFurnace extends HorizontalDirectionalBlock implements EntityBlock {
    // 方块的朝向属性（水平四方向）。
    // 直接复用 Minecraft 已有的 FACING 定义，而不是重新创建一个属性。
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public GalaxyFurnace(){
        // 定义方块基础属性（硬度、声音等后续可在这里扩展）
        super(Properties.of());
        // 注册默认方块状态。
        // 当方块尚未被放置或没有额外信息时，默认朝向 NORTH。
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    // 玩家放置方块时调用，用于确定最终的方块状态。
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // 获取玩家当前面朝方向，并取反方向。
        // 这样机器的“正面”会朝向玩家，
        // 实现“放下去就看到正面”的直觉效果。
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    // 向方块状态系统注册我们新增的属性。
    // 如果不在这里添加 FACING，游戏就无法获取到方块状态，进而在启动阶段崩溃。
     @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        // 创建并返回这个方块对应的 Block Entity 实例。
        // 这里我们直接调用 GalaxyFurnaceBlockEntity 的构造器，
        // 并传入方块位置和状态。
        return new GalaxyFurnaceBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == ModBlockEntities.GALAXY_FURNACE_BLOCK_ENTITY.get()
                ? (lvl, p, st, be) -> ((GalaxyFurnaceBlockEntity) be).tick()
                : null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, net.minecraft.world.entity.player.Player player, net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof GalaxyFurnaceBlockEntity juicer) {
                NetworkHooks.openScreen((ServerPlayer) player, juicer, pos);
            }
            else {
                throw new IllegalStateException("Container provider is missing");
            }
        }
        return InteractionResult.SUCCESS;
    }

    //在方块移除时，如果方块被破坏了，我们需要把里面的物品掉落出来。
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newstate,boolean isMoving){
        // 只有当旧方块与新方块不是同一个方块时，
        // 才说明当前方块真的被替换/破坏了
        if(state.getBlock() != newstate.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof GalaxyFurnaceBlockEntity galaxyFurnace){
               //如果是GalaxyFurnaceBlockEntity，我们就把它的物品掉落出来。
                galaxyFurnace.drops();
            }
            super.onRemove(state, level, pos, newstate, isMoving);
        }
    }

}
