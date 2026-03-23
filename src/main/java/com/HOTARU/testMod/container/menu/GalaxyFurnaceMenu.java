package com.HOTARU.testMod.container.menu;

import com.HOTARU.testMod.ModMenuTypes;
import com.HOTARU.testMod.block.ModBlocks;
import com.HOTARU.testMod.block.blockentity.GalaxyFurnaceBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class GalaxyFurnaceMenu extends AbstractContainerMenu {
    /**
     * 当前菜单绑定的方块实体。
     * Menu 本身并不存储机器逻辑，它只是作为界面逻辑层，
     * 因此需要持有 BlockEntity 的引用来访问真实数据。
     */
    public final GalaxyFurnaceBlockEntity blockEntity;
    /**
     * 当前菜单所在的世界。
     * 主要用于 stillValid 检查玩家是否仍然可以访问该方块。
     */
    private final Level level;
    /**
     * 用于同步简单整数数据的容器。
     * 这里主要用于同步 BlockEntity 中的 progress 等数值。
     */
    private final ContainerData data;

    private final RecipeType<? extends AbstractCookingRecipe> recipeType;

    /**
     * 客户端构造器。
     *
     * 当服务端要求客户端打开界面时，
     * Forge 会通过网络发送一个 FriendlyByteBuf，
     * 其中包含方块的位置等信息。
     *
     * 客户端通过读取这个位置，
     * 再从世界中获取对应的 BlockEntity。
     */
    public GalaxyFurnaceMenu(int id, Inventory inv, FriendlyByteBuf buf){
        this(id,inv,
                inv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(3));
    }

    /**
     * 服务端构造器。
     *
     * 当玩家真正打开界面时，服务端会创建 Menu，
     * 并把 BlockEntity 与 ContainerData 传入。
     */
    public GalaxyFurnaceMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.GALAXY_FURNACE_MENU.get(), id);
        this.blockEntity = (GalaxyFurnaceBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;
        this.recipeType=RecipeType.SMELTING;
            //添加玩家背包与快捷栏
        addPlayerInventory(inv,8,84);
        addPlayerHotbar(inv,8,142);
        //添加机器槽位
        this.addMachineSlots(blockEntity.getItemHandler());
        // 将 ContainerData 添加到菜单中，以便自动同步。
        addDataSlots(data);
    }

    /**
     * Shift 点击快速移动物品的逻辑。
     *
     * 由于当前菜单还没有任何物品槽位，
     * 因此这里暂时返回 null。
     * 在后续实现物品槽时，这里会被完善。
     * 因为没有槽位，所以目前返回 null 是安全的
     *
     */
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (this.canSmelt(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    protected boolean isFuel(ItemStack pStack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(pStack, this.recipeType) > 0;
    }

    protected boolean canSmelt(ItemStack pStack) {
        return this.level.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>)this.recipeType, new SimpleContainer(pStack), this.level).isPresent();
    }

    /**
     * 检查玩家是否仍然可以使用该界面。
     *
     * 如果玩家距离方块太远，或者方块已经被破坏，
     * 菜单就会自动关闭。
     */
    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.GALAXY_FURNACE.get());
    }

    /**
     * 提供对 BlockEntity 的访问。
     * Screen 或其他逻辑可以通过 Menu 获取对应的机器实例。目前暂时没有使用此方法。
     */
    public GalaxyFurnaceBlockEntity getBlockEntity(){
        return this.blockEntity;
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //创建机器槽位
    //输入槽索引 1
    private static final int INPUT_SLOT = 0;
    //输入槽索引 2
    private static final int FUEL_SLOT = 1;
    //输出槽索引
    private static final int OUTPUT_SLOT = 2;
    /**
     * 向当前 Menu 中添加机器自身的槽位。
     *
     * 这里使用 SlotItemHandler，将 BlockEntity 中的 ItemStackHandler
     * 直接绑定到 GUI 槽位上。
     *
     * 参数说明：
     * handler     -> 机器内部库存
     * INPUT_SLOT  -> 输入槽索引
     * OUTPUT_SLOT -> 输出槽索引
     * 77, 38      -> 输入槽在 GUI 中的位置（改成你自己 GUI 贴图里的实际位置）
     * 142, 38     -> 输出槽在 GUI 中的位置（同上）
     */
    private void addMachineSlots(IItemHandler handler) {
        // 这里的 SlotItemHandler 是 Forge 提供的一个通用槽位实现，
        // 它会自动处理与 ItemStackHandler 的交互。
        this.addSlot(new SlotItemHandler(handler, INPUT_SLOT, 56, 17));
        this.addSlot(new SlotItemHandler(handler, FUEL_SLOT, 56, 53));
        this.addSlot(new SlotItemHandler(handler, OUTPUT_SLOT, 116, 35));
    }

    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //玩家背包接入
    /**
     * 添加玩家背包区域（3 行 * 9 列）。
     *
     * Inventory 中前 9 个槽位属于快捷栏，
     * 从索引 9 开始才是主背包，因此这里使用：
     * col + row * 9 + 9
     */
    private void addPlayerInventory(Inventory inv,int leftCol,int topRow){
        for(int row=0;row<3;++row){
            for(int col=0;col<9;++col){
                this.addSlot(new Slot(inv,col+row*9+9,leftCol+col*18,topRow+row*18));
            }
        }
    }
    // 添加玩家快捷栏（1 行 * 9 列）。
    private void addPlayerHotbar(Inventory inv,int leftCol,int topRow) {
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inv, col, leftCol + col * 18, topRow));
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //动态UI数据同步
    public boolean isLit() {
        return this.data.get(0) > 0;
    }

    public int getScaledProgress(int pixels){
        int progress = this.data.get(0);
        int maxProgress = 100; // 这里的 maxProgress 应该与 BlockEntity 中定义的最大进度值一致
        return maxProgress>0?progress * pixels / maxProgress:0;
    }

    public int getScaledBurnTime(int pixels){
        int burnTime = this.data.get(1);
        int maxBurnTime = this.data.get(2);
        if(maxBurnTime<=0){
            return 0;
        }
        return burnTime * pixels / maxBurnTime;
    }

}
