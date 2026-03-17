package com.HOTARU.testMod.container.screen;

import com.HOTARU.testMod.TestMod;
import com.HOTARU.testMod.container.menu.GalaxyFurnaceMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;



public class GalaxyFurnaceScreen extends AbstractContainerScreen<GalaxyFurnaceMenu> {
    /**
     * GUI 背景贴图的位置。
     * ResourceLocation 的格式为：modid:path
     * 这里对应的实际文件路径是：
     * assets/tutorial/textures/container/industrial_processing_unit.png
     */
    private static final ResourceLocation GUI=
            ResourceLocation.fromNamespaceAndPath(TestMod.MOD_ID,"textures/gui/galaxy_furnace.png");

    /**
     * Screen 构造器。
     *
     * menu：当前界面绑定的 Menu（逻辑层）
     * playerInventory：玩家物品栏
     * title：界面标题
     *
     * Screen 只负责渲染界面，并不直接处理机器逻辑，
     * 真正的数据来源仍然是 Menu → BlockEntity。
     */
    public GalaxyFurnaceScreen(GalaxyFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        // GUI 的宽度与高度（像素）
        // 这些值通常需要与背景贴图尺寸保持一致
        this.imageWidth =176;
        this.imageHeight=174;
    }
    /**
     * 渲染 GUI 背景。
     *
     * 该方法负责绘制界面的底层贴图。
     * 在这里我们只绘制一张固定的 GUI 背景图。
     */
    @Override
    protected void renderBg(GuiGraphics guiGraphics,float partialTick,int mouseX,int mouseY){
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.getShaderColor();
        RenderSystem.setShaderTexture(0,GUI);
        int x=(width-imageWidth)/2;
        int y=(height-imageHeight)/2;
        guiGraphics.blit(GUI,x,y,0,0,imageWidth,imageHeight);
    }

    /**
     * 整个界面的渲染入口。
     *
     * 渲染顺序通常是：
     * 1. 绘制背景
     * 2. 绘制 GUI
     * 3. 绘制按钮、槽位等组件
     */
    @Override
    public void render(GuiGraphics guiGraphics,int mouseX,int mouseY,float partialTick) {
        // 绘制背景（包括模态背景）
        this.renderBackground(guiGraphics);
        // 绘制 GUI 背景和组件
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
