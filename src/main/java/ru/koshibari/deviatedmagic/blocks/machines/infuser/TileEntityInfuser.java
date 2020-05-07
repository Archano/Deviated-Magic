package ru.koshibari.deviatedmagic.blocks.machines.infuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityInfuser extends TileEntityLockable implements ITickable, ISidedInventory {
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{3};
    private static final int[] SLOTS_SIDES = new int[]{1, 2};
    private NonNullList<ItemStack> infuserItemStacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    private int infuserBurnTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String infuserCustomName;

    @Override
    public int getSizeInventory() {
        return this.infuserItemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.infuserItemStacks) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.infuserItemStacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.infuserItemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.infuserItemStacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemStack = this.infuserItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemStack) && ItemStack.areItemStackShareTagsEqual(stack, itemStack);
        this.infuserItemStacks.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
        if (index == 0 && !flag) {
            this.totalCookTime = this.getCookTime();
            this.cookTime = 0;
        }
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.infuserCustomName : "container.infuser";
    }

    @Override
    public boolean hasCustomName() {
        return this.infuserCustomName != null && !this.infuserCustomName.isEmpty();
    }

    public void setCustomName(String infuserCustomName) {
        this.infuserCustomName = infuserCustomName;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.infuserItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.infuserItemStacks);
        this.infuserBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.infuserItemStacks.get(0));

        if (compound.hasKey("CustomName", 8)) {
            this.infuserCustomName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", this.infuserBurnTime);
        compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.infuserItemStacks);

        if (this.hasCustomName()) {
            compound.setString("CustomName", this.infuserCustomName);
        }

        return compound;
    }

    public static int getItemBurnTime(ItemStack itemStack) {
        if (itemStack.getItem() == Items.ENDER_PEARL) {
            return 1600;
        }
        return 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning() {
        return this.infuserBurnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory) {
        return inventory.getField(0) > 0;
    }

    public void update() {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning()) {
            --this.infuserBurnTime;
        }

        if (!this.world.isRemote) {
            ItemStack itemstack = this.infuserItemStacks.get(0);

            if (this.isBurning() || !itemstack.isEmpty() && !((ItemStack) this.infuserItemStacks.get(0)).isEmpty()) {
                if (!this.isBurning() && this.canSmelt()) {
                    this.infuserBurnTime = getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.infuserBurnTime;

                    if (this.isBurning()) {
                        flag1 = true;

                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.infuserItemStacks.set(0, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt()) {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime();
                        this.smeltItem();
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                Infuser.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    public static int getCookTime() {
        return 100;
    }

    private boolean canSmelt() {
        if (this.infuserItemStacks.get(1).isEmpty() || this.infuserItemStacks.get(2).isEmpty()) {
            return false;
        } else {
            ItemStack st = InfuserRecipes.instance().getCookingResult(this.infuserItemStacks.get(1).getItem(), this.infuserItemStacks.get(2).getItem());
            if (st == ItemStack.EMPTY) {
                return false;
            } else {
                ItemStack itemstack1 = this.infuserItemStacks.get(3);

                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.isItemEqual(st)) {
                    return false;
                } else if (itemstack1.getCount() + st.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + st.getCount() <= itemstack1.getMaxStackSize()) {
                    return true;
                } else {
                    return itemstack1.getCount() + st.getCount() <= st.getMaxStackSize();
                }
            }
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack mat1 = this.infuserItemStacks.get(1);
            ItemStack mat2 = this.infuserItemStacks.get(2);
            ItemStack result = InfuserRecipes.instance().getCookingResult(mat1.getItem(), mat2.getItem());
            ItemStack resultSlot = this.infuserItemStacks.get(3);

            if (resultSlot.isEmpty()) {
                this.setInventorySlotContents(3, result);
            } else if (resultSlot.getItem() == result.getItem()) {
                this.setInventorySlotContents(3, new ItemStack(result.getItem(), resultSlot.getCount() + result.getCount()));
            }

            mat1.shrink(1);
            mat2.shrink(1);
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 3) {
            return false;
        } else if (index != 0) {
            return true;
        } else {
            return isItemFuel(stack);
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public String getGuiID() {
        return "deviatedmagic:infuser";
    }

    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.DOWN) {
            return SLOTS_BOTTOM;
        } else {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerInfuser(playerInventory, this);
    }

    public int getField(int id) {
        switch (id) {
            case 0:
                return this.infuserBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.infuserBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
                break;
            default:
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {
        this.infuserItemStacks.clear();
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}
