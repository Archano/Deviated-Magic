package ru.koshibari.deviatedmagic.blocks.machines.infuser.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotInfuserOutput extends Slot {
    private final EntityPlayer player;
    private int removeCount;

    public SlotInfuserOutput(EntityPlayer player, IInventory infuserInventory, int index, int x, int y) {
        super(infuserInventory, index, x, y);
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int amount)
    {
        if(this.getHasStack())
        {
            this.removeCount += Math.min(amount, this.getStack().getCount());
        }
        return super.decrStackSize(amount);
    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
    {
        this.onCrafting(stack);
        super.onTake(thePlayer, stack);
        return stack;
    }

    @Override
    protected void onCrafting(ItemStack stack, int amount)
    {
        this.removeCount += amount;
    }

}
