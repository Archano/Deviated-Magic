package ru.koshibari.deviatedmagic.blocks.machines.infuser.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.koshibari.deviatedmagic.blocks.machines.infuser.TileEntityInfuser;

public class SlotInfuserFuel extends Slot {
    public SlotInfuserFuel(IInventory infuserInventory, int index, int x, int y) {
        super(infuserInventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return TileEntityInfuser.isItemFuel(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        return super.getItemStackLimit(stack);
    }
}
