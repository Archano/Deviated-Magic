package ru.koshibari.deviatedmagic.blocks.machines.infuser;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class InfuserRecipes {
    private static final InfuserRecipes INSTANCE = new InfuserRecipes();
    private final Table<Item, Item, ItemStack> smeltingList = HashBasedTable.<Item, Item, ItemStack>create();

    public static InfuserRecipes instance() {
        return INSTANCE;
    }

    public void addCookingForBlock(Block input1, Item input2, ItemStack res, boolean noRand) {
        this.addCookingRecipe(Item.getItemFromBlock(input1), input2, res, noRand);
    }

    public void addCookingForBlock(Block input1, Item input2, ItemStack res) {
        this.addCookingRecipe(Item.getItemFromBlock(input1), input2, res, false);
    }

    public void addCookingForBlock(Item input1, Block input2, ItemStack res, boolean noRand) {
        this.addCookingRecipe(input1, Item.getItemFromBlock(input2), res, noRand);
    }

    public void addCookingForBlock(Item input1, Block input2, ItemStack res) {
        this.addCookingRecipe(input1, Item.getItemFromBlock(input2), res, false);
    }

    public void addCookingForBlock(Block input1, Block input2, ItemStack res, boolean noRand) {
        this.addCookingRecipe(Item.getItemFromBlock(input1), Item.getItemFromBlock(input2), res, noRand);
    }

    public void addCookingForBlock(Block input1, Block input2, ItemStack res) {
        this.addCookingRecipe(Item.getItemFromBlock(input1), Item.getItemFromBlock(input2), res, false);
    }

    public void addCooking(Item input1, Item input2, ItemStack res, boolean noRand) {
        this.addCookingRecipe(input1, input2, res, noRand);
    }

    public void addCooking(Item input1, Item input2, ItemStack res) {
        this.addCookingRecipe(input1, input2, res, false);
    }

    public void addCookingRecipe(Item input1, Item input2, ItemStack stack, boolean noRand) {
        if (getCookingResult(input1, input2) != ItemStack.EMPTY) {
            net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}", input1, input2, stack);
            return;
        }
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("noRand", noRand);
        ItemStack temp = new ItemStack(stack.getItem(), stack.getCount(), 0);
        temp.writeToNBT(nbt);
        this.smeltingList.put(input1, input2, temp);
    }

    public ItemStack getCookingResult(Item input1, Item input2) {
        ItemStack result;
        NBTTagCompound nbt;
        boolean flag = this.smeltingList.contains(input1, input2);
        boolean flag1 = this.smeltingList.contains(input2, input1);
        int count;

        if (flag || flag1) {
            if (flag) {
                result = this.smeltingList.get(input1, input2).copy();
            } else {
                result = this.smeltingList.get(input2, input1).copy();
            }
            nbt = result.serializeNBT();

            if (nbt.getBoolean("noRand")) {
                count = result.getCount();
                result = new ItemStack(result.getItem(), count);
                return result;
            } else if (!nbt.getBoolean("noRand")) {
                Random rand = new Random();
                count = 1 + rand.nextInt(result.getCount());
                result.setCount(count);
                return result;
            }
        }

        return ItemStack.EMPTY;
    }
}
