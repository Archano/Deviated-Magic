package ru.koshibari.deviatedmagic.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandlerModifiable;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.blocks.machines.ritual_stone.pedestal.TileEntityPedestal;

public class Helper {
    public static boolean putStackOnTile(EntityPlayer player, EnumHand hand, BlockPos pos, int slot, boolean sound) {
        TileEntity tile = player.world.getTileEntity(pos);
        IItemHandlerModifiable handler = ((TileEntityPedestal) tile).getItemHandler();
        if (handler != null) {
            ItemStack remain = handler.extractItem(0, 1, false);
            ItemStack handStack = player.getHeldItem(hand);
            if (remain != ItemStack.EMPTY) {
                if (handStack != ItemStack.EMPTY) {
                    if (!ItemStack.areItemStackShareTagsEqual(remain, handStack)) {
                        player.addItemStackToInventory(remain);
                        handler.insertItem(0, new ItemStack(handStack.getItem(), 1), false);
                        handStack.shrink(1);
                        return true;
                    } else {
                        player.addItemStackToInventory(remain);
                        handler.insertItem(0, ItemStack.EMPTY, false);
                        return true;
                    }
                } else {
                    player.addItemStackToInventory(remain);
                    handler.insertItem(0, ItemStack.EMPTY, false);
                    return true;
                }
            } else {
                handler.insertItem(0, new ItemStack(handStack.getItem(), 1), false);
                handStack.shrink(1);
                return true;
            }
        }
        return false;
    }
}
