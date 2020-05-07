package ru.koshibari.deviatedmagic.blocks.machines.ritual_stone.pedestal;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityPedestal extends TileEntity {
    public final ItemStackHandler items = new ItemStackHandler(1) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.items.deserializeNBT(compound.getCompoundTag("Inventory"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("Inventory", this.items.serializeNBT());
        return compound;
    }

    public IItemHandlerModifiable getItemHandler() {
        return this.items;
    }

    public static void dropItems(IItemHandlerModifiable inventory, TileEntity tileEntity){
        tileEntity.getWorld().spawnEntity(new EntityItem(tileEntity.getWorld(), tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), inventory.extractItem(0, 1, false)));
    }
}

