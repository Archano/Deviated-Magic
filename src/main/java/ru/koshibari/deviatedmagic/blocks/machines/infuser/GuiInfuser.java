package ru.koshibari.deviatedmagic.blocks.machines.infuser;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import ru.koshibari.deviatedmagic.util.Reference;

public class GuiInfuser extends GuiContainer {
    private static final ResourceLocation INFUSER_TEXTURE = new ResourceLocation(Reference.MODID + ":textures/gui/infuser.png");
    private final InventoryPlayer playerInventory;
    private final IInventory tileInfuser;

    public GuiInfuser(InventoryPlayer inventory, IInventory infuserInventory) {
        super(new ContainerInfuser(inventory, infuserInventory));
        this.playerInventory = inventory;
        this.tileInfuser = infuserInventory;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(INFUSER_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if (TileEntityInfuser.isBurning(this.tileInfuser)) {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 36, j + 33 - k, 176, 13 - k, 13, k + 1);
        }

        int l = this.getCookProgressScaled(26);
        this.drawTexturedModalRect(i + 84, j + 24, 190, 0, 8, l + 1);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.tileInfuser.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    private int getCookProgressScaled(int pixels) {
        int i = this.tileInfuser.getField(2);
        int j = this.tileInfuser.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels) {
        int i = this.tileInfuser.getField(1);
        if (i == 0) {
            i = 200;
        }
        return this.tileInfuser.getField(0) * pixels / i;
    }
}
