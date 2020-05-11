package ru.koshibari.deviatedmagic.objects.blocks.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;
import ru.koshibari.deviatedmagic.objects.blocks.machines.ritual_stone.pedestal.RitualStonePedestal;
import ru.koshibari.deviatedmagic.objects.blocks.machines.ritual_stone.pedestal.TileEntityPedestal;
import scala.Int;

public class RenderPedestal extends TileEntitySpecialRenderer<TileEntityPedestal> {

    @Override
    public void render(TileEntityPedestal tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStack stack = tile.items.getStackInSlot(0);
        if (!stack.isEmpty()) {
            EntityItem entityItem = new EntityItem(tile.getWorld(), 0.0D, 0.0D, 0.0D, stack);
            entityItem.hoverStart = 0.0F;
            entityItem.lifespan = Int.MaxValue();
            entityItem.setEntityInvulnerable(true);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x + 0.5F, (float)y + 0.6F, (float)z + 0.5F);
            EnumFacing facing = tile.getWorld().getBlockState(tile.getPos()).getValue(RitualStonePedestal.FACING);
            switch (facing){
                case EAST: {
                    GL11.glRotatef(270F, 0F, 1F,0F);
                    break;
                }
                case WEST:{
                    GL11.glRotatef(90F, 0F, 1F,0F);
                    break;
                }
                case NORTH :
                case SOUTH: {
                    GL11.glRotatef(180F, 0F, 1F,0F);
                    break;
                }
                default: {
                	break;
                }
            }
            RenderManager man = Minecraft.getMinecraft().getRenderManager();
            man.renderEntity(entityItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
            GL11.glPopMatrix();
        }
    }
}
