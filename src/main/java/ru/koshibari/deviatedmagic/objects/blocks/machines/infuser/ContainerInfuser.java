package ru.koshibari.deviatedmagic.objects.blocks.machines.infuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.koshibari.deviatedmagic.objects.blocks.machines.infuser.slots.SlotInfuserFuel;
import ru.koshibari.deviatedmagic.objects.blocks.machines.infuser.slots.SlotInfuserOutput;

public class ContainerInfuser extends Container {
    private final IInventory tileInfuser;
    private int cookTime, totalCookTime, infuserBurnTime, currentItemBurnTime;

    public ContainerInfuser(InventoryPlayer playerInventory, IInventory infuserInventory) {
        this.tileInfuser = infuserInventory;
        this.addSlotToContainer(new SlotInfuserFuel(infuserInventory, 0, 16, 17));
        this.addSlotToContainer(new Slot(infuserInventory, 1, 62, 17));
        this.addSlotToContainer(new Slot(infuserInventory, 2, 98, 17));
        this.addSlotToContainer(new SlotInfuserOutput(playerInventory.player, infuserInventory, 3, 80, 53));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileInfuser);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.cookTime != this.tileInfuser.getField(2)) {
                icontainerlistener.sendWindowProperty(this, 2, this.tileInfuser.getField(2));
            }

            if (this.infuserBurnTime != this.tileInfuser.getField(0)) {
                icontainerlistener.sendWindowProperty(this, 0, this.tileInfuser.getField(0));
            }

            if (this.currentItemBurnTime != this.tileInfuser.getField(1)) {
                icontainerlistener.sendWindowProperty(this, 1, this.tileInfuser.getField(1));
            }

            if (this.totalCookTime != this.tileInfuser.getField(3)) {
                icontainerlistener.sendWindowProperty(this, 3, this.tileInfuser.getField(3));
            }
        }

        this.cookTime = this.tileInfuser.getField(2);
        this.infuserBurnTime = this.tileInfuser.getField(0);
        this.currentItemBurnTime = this.tileInfuser.getField(1);
        this.totalCookTime = this.tileInfuser.getField(3);
    }

    @Override
    public void updateProgressBar(int id, int data) {
        this.tileInfuser.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileInfuser.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (TileEntityInfuser.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
