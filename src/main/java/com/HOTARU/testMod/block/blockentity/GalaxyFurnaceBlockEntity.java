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
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.openjdk.nashorn.internal.runtime.options.Option;

import javax.annotation.Nullable;
import java.util.Optional;




// 这个类是 Galaxy Furnace 方块的 Block Entity（方块实体）类。
// Block Entity 是 Minecraft 中用于存储方块额外数据和逻辑的系统，比如容器、机器等需要保存状态的方块都会有对应的 Block Entity。
public class GalaxyFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    private int progress = 0;// 进度条，0-100，表示当前熔炉的工作进度。
    private int burnTime = 0;
    private int currentItemBurnTime = 0;

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch(index) {
                case 0 -> progress;
                case 1 -> burnTime;
                case 2 ->currentItemBurnTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index,int value){
            switch (index){
                case 0 ->progress=value;
                case 1 ->burnTime=value;
                case 2 ->currentItemBurnTime=value;
            }
        }

       @Override
       public int getCount() {
            return 3;
        }
    };

    public GalaxyFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GALAXY_FURNACE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public void tick(){
        if(level == null || level.isClientSide()) {
            return;
        }
        // 这里可以添加熔炉的工作逻辑，比如处理输入物品、
        boolean dirty = false;
        if(hasFuel()){
            if(burnTime<=0){
                consumeFuel();
                dirty=true;
            }
            if(burnTime>0&&canSmelt()){
                burnTime--;
                progress++;
                if (progress>100){
                    craftItem();
                    progress=0;
                    dirty=true;
                }
            }
            else if(!canSmelt()){
                progress=0;
            }
            else{
                burnTime=0;
                currentItemBurnTime=0;
                if(progress>0){
                    progress=Math.max(0,progress -2);
                    dirty=true;
                }
            }
            if(dirty){
                setChanged();
            }
        }


    }



    private boolean hasFuel(){
        ItemStack fuelstack = itemHandler.getStackInSlot(INPUT_SLOT_2);
        return !fuelstack.isEmpty()&&getBurnTime(fuelstack)>0;
    }

    private void consumeFuel(){
        ItemStack fuelstack = itemHandler.getStackInSlot(INPUT_SLOT_2);
        if(!fuelstack.isEmpty()){
            int burnTimeValue = getBurnTime(fuelstack);
            if (burnTimeValue > 0){
                burnTime = burnTimeValue;
                currentItemBurnTime = burnTimeValue;
                fuelstack.shrink(1);
                itemHandler.setStackInSlot(INPUT_SLOT_2,fuelstack);
            }
        }
    }

    private int getBurnTime(ItemStack fuelstack){
        return net.minecraftforge.common.ForgeHooks.getBurnTime(fuelstack,RecipeType.SMELTING);
    }

    private boolean canSmelt(){
        ItemStack inputstack = itemHandler.getStackInSlot(INPUT_SLOT_1);
        if(!hasFuel())return false;
        Optional<SmeltingRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty())return false;
        ItemStack result = recipe.get().getResultItem(level.registryAccess());
        ItemStack outputstack = itemHandler.getStackInSlot(OUTPUT_SLOT);
        if(outputstack.isEmpty())return true;
        if(!outputstack.isSameItem(outputstack,result))return false;
        if(outputstack.getCount()+result.getCount()<=outputstack.getMaxStackSize())return true;
        return false;
    }

    private Optional<SmeltingRecipe> getCurrentRecipe() {
        ItemStack inputStack = itemHandler.getStackInSlot(INPUT_SLOT_1);
        if (inputStack.isEmpty() || level == null) return Optional.empty();

        return level.getRecipeManager().getRecipeFor(
                RecipeType.SMELTING,
                new SimpleContainer(inputStack),
                level
        );
    }

    private void craftItem(){
        Optional<SmeltingRecipe> recipe=getCurrentRecipe();
        if(recipe.isEmpty())return;
        ItemStack inputstack=itemHandler.getStackInSlot(INPUT_SLOT_1);
        ItemStack result=recipe.get().getResultItem(level.registryAccess());
        ItemStack outputstack=itemHandler.getStackInSlot(OUTPUT_SLOT);
        
        // 消耗输入物品
        inputstack.shrink(1);
        itemHandler.setStackInSlot(INPUT_SLOT_1,inputstack);
        
        // 设置或增加输出物品
        if(outputstack.isEmpty()) {
            itemHandler.setStackInSlot(OUTPUT_SLOT,result.copy());
        }
        else {
            outputstack.grow(result.getCount());
            itemHandler.setStackInSlot(OUTPUT_SLOT,outputstack);
        }
    }

    public int getProgress() {
        return progress;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public int getCurrentItemBurnTime(){
        return currentItemBurnTime;
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
         * 输入槽 1 (索引 0) 只允许放入可熔炼的物品
         * 燃料槽 (索引 1) 只允许放入可燃物品
         * 输出槽 (索引 2) 不允许手动放入物品
         */
        @Override
        public boolean isItemValid(int slot, @Nullable ItemStack stack){
            if (stack == null || stack.isEmpty()) {
                return false;
            }
            return switch (slot) {
                case INPUT_SLOT_1 -> true; // 输入槽允许放入任何物品（由配方系统判断）
                case INPUT_SLOT_2 -> getBurnTime(stack) > 0; // 燃料槽只允许可燃物品
                case OUTPUT_SLOT -> false; // 输出槽不允许放入物品
                default -> false;
            };
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
