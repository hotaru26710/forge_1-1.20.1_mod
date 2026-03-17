package com.HOTARU.testMod.block.blockentity;

import com.HOTARU.testMod.ModBlockEntities;
import com.HOTARU.testMod.container.menu.GalaxyFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;


// 这个类是 Galaxy Furnace 方块的 Block Entity（方块实体）类。
// Block Entity 是 Minecraft 中用于存储方块额外数据和逻辑的系统，比如容器、机器等需要保存状态的方块都会有对应的 Block Entity。
public class GalaxyFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    private int progress = 0;// 进度条，0-100，表示当前熔炉的工作进度。
    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch(index) {
                case 0 -> progress;
                default -> 0;
            };
        }

        @Override
        public void set(int index,int value){
            if(index==0){
                progress=value;
            }
        }

       @Override
       public int getCount() {
            return 1;
        }
    };

    public GalaxyFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GALAXY_FURNACE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public void tick(){
        progress++;
        setChanged();
    }

    public int getProgress() {
        return progress;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag){
        super.saveAdditional(pTag);
        pTag.putInt("Progress", progress);// 将进度保存到 NBT 标签中，键名为 "Progress"。
        pTag.put("Inventory", itemHandler.serializeNBT());// 将物品栏数据保存到 NBT 标签中，键名为 "Inventory"。
    }

    @Override
    public void load(CompoundTag pTag){
        super.load(pTag);
        this.progress = pTag.getInt("Progress");// 从 NBT 标签中读取进度，键名为 "Progress"。
        itemHandler.deserializeNBT(pTag.getCompound("Inventory"));// 从 NBT 标签中读取物品栏数据，键名为 "Inventory"。
    }

    //返回界面标题，决定gui的名称
    @Override
    public Component getDisplayName() {
        return Component.literal("Galaxy Furnace");
    }

    //当玩家打开界面时创建menu对象，决定gui的内容
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player){
        return new GalaxyFurnaceMenu(id, inventory, this, data);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //在这个实体中加入物品栏
    //输入槽索引 1
    public static final int INPUT_SLOT_1 = 0;
    //输入槽索引 2
    public static final int INPUT_SLOT_2 = 1;
    //输出槽索引
    public static final int OUTPUT_SLOT = 2;

    /**
     * 工业处理单元的内部物品栏。
     *
     * 这里使用 Forge 提供的 ItemStackHandler 作为库存实现。
     * 当前机器一共拥有三个槽位：
     * 0 -> 输入槽 1
     * 1 -> 输入槽 2
     * 2 -> 输出槽
     */
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            // 当物品栏内容发生变化时，标记方块实体为已更改，以便保存数据。
            setChanged();
        }


        /**
         * 控制某个槽位是否允许放入指定物品。
         *
         * 当前实现中：
         * 两个输入槽允许放入物品
         * 输出槽不允许手动放入物品
         *
         * 这正符合大多数机器的常见逻辑：
         * 玩家把原料放进输入槽而非输出槽，产物只会出现在输出槽。
         */
        @Override
        public boolean isItemValid(int slot, @Nullable ItemStack stack){
            return slot == INPUT_SLOT_1 || slot == INPUT_SLOT_2;
        }

    };
    /**
     * 返回当前机器内部的物品处理器。
     *
     * Menu 会通过这个方法获取库存，
     * 再基于它创建真正的 GUI 槽位。
     */
    public IItemHandler getItemHandler() {
        return itemHandler;
    }

    /// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //掉落逻辑
    /**
     * 将当前机器内部的所有物品掉落到世界中。
     *
     * ItemStackHandler 不是 Containers.dropContents 直接支持的容器类型，
     * 因此这里先创建一个临时的 SimpleContainer，
     * 再把 itemHandler 中的物品逐个拷贝进去，
     * 最后统一掉落。
     */
    public void drops() {
        //创建一个临时容器，大小与 itemHandler 一致
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        //将 itemHandler 中的物品逐个拷贝到临时容器中
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        //使用 Containers.dropContents 将临时容器中的物品掉落到世界中
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
}
