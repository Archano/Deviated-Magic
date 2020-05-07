package ru.koshibari.deviatedmagic.blocks.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import ru.koshibari.deviatedmagic.blocks.machines.ritual_stone.pedestal.TileEntityPedestal;
import scala.Int;

public class RenderPedestal extends TileEntitySpecialRenderer<TileEntityPedestal> {

    @Override
    public void render(TileEntityPedestal tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStack stack = tile.items.getStackInSlot(0);
        if (stack != ItemStack.EMPTY) {
            EntityItem entityItem = new EntityItem(tile.getWorld(), 0.0D, 0.0D, 0.0D, stack);
            entityItem.hoverStart = 0.0F;
            entityItem.lifespan = Int.MaxValue();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x + 0.5F, (float)y + 0.6F, (float)z + 0.5F);
            RenderManager man = Minecraft.getMinecraft().getRenderManager();
            man.renderEntity(entityItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
            GL11.glPopMatrix();
        }
    }
}
