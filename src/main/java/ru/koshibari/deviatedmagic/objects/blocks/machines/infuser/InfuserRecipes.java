package ru.koshibari.deviatedmagic.objects.blocks.machines.infuser;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class InfuserRecipes {
	private static final InfuserRecipes INSTANCE = new InfuserRecipes();

	public static final Table<Map<String, String>, Map<String, String>, Map<String, String>> smeltingList = HashBasedTable
			.<Map<String, String>, Map<String, String>, Map<String, String>>create();

	public static InfuserRecipes instance() {
		return INSTANCE;
	}

	public void add(ItemStack input1, ItemStack input2, ItemStack stack, boolean noRand) {
		if (getCookingResult(input1, input2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(input1, input2, stack, noRand);
	}

	public void add(ItemStack input1, ItemStack input2, ItemStack stack) {
		if (getCookingResult(input1, input2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(input1, input2, stack, false);
	}

	public void add(Item input1, Item input2, ItemStack stack) {
		ItemStack i1 = new ItemStack(input1);
		ItemStack i2 = new ItemStack(input2);
		if (getCookingResult(i1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(i1, i2, stack, false);
	}

	public void add(Item input1, Item input2, ItemStack stack, boolean noRand) {
		ItemStack i1 = new ItemStack(input1);
		ItemStack i2 = new ItemStack(input2);
		if (getCookingResult(i1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(i1, i2, stack, noRand);
	}

	public void add(Block input1, Item input2, ItemStack stack) {
		ItemStack i1 = new ItemStack(Item.getItemFromBlock(input1));
		ItemStack i2 = new ItemStack(input2);
		if (getCookingResult(i1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(i1, i2, stack, false);
	}

	public void add(Block input1, Item input2, ItemStack stack, boolean noRand) {
		ItemStack i1 = new ItemStack(Item.getItemFromBlock(input1));
		ItemStack i2 = new ItemStack(input2);
		if (getCookingResult(i1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(i1, i2, stack, noRand);
	}

	public void add(Block input1, Block input2, ItemStack stack, boolean noRand) {
		ItemStack i1 = new ItemStack(Item.getItemFromBlock(input1));
		ItemStack i2 = new ItemStack(Item.getItemFromBlock(input2));
		if (getCookingResult(i1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(i1, i2, stack, noRand);
	}

	public void add(Block input1, Block input2, ItemStack stack) {
		ItemStack i1 = new ItemStack(Item.getItemFromBlock(input1));
		ItemStack i2 = new ItemStack(Item.getItemFromBlock(input2));
		if (getCookingResult(i1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(i1, i2, stack, false);
	}

	public void add(Item input1, Block input2, ItemStack stack, boolean noRand) {
		ItemStack i1 = new ItemStack(input1);
		ItemStack i2 = new ItemStack(Item.getItemFromBlock(input2));
		if (getCookingResult(i1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(i1, i2, stack, noRand);
	}

	public void add(Item input1, Block input2, ItemStack stack) {
		ItemStack i1 = new ItemStack(input1);
		ItemStack i2 = new ItemStack(Item.getItemFromBlock(input2));
		if (getCookingResult(i1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(i1, i2, stack, false);
	}

	public void add(Block input1, ItemStack input2, ItemStack stack) {
		ItemStack i1 = new ItemStack(input1);
		
		if (getCookingResult(i1, input2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}", input1,input2, stack); 
			return; 
		}
		this.putInList(i1, input2, stack, false);
	}

	public void add(Block input1, ItemStack input2, ItemStack stack, boolean noRand) {
		ItemStack i1 = new ItemStack(input1);
		if (getCookingResult(i1, input2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(i1, input2, stack, noRand);
	}

	public void add(ItemStack input1, Block input2, ItemStack stack, boolean noRand) {
		ItemStack i2 = new ItemStack(input2);
		if (getCookingResult(input1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(input1, i2, stack, noRand);
	}

	public void add(ItemStack input1, Block input2, ItemStack stack) {
		ItemStack i2 = new ItemStack(input2);
		if (getCookingResult(input1, i2) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} + {} = {}",
					input1, input2, stack);
			return;
		}
		this.putInList(input1, i2, stack, false);
	}

	public void putInList(ItemStack i1, ItemStack i2, ItemStack res, boolean noRand) {
		Map<String, String> item1 = new HashMap<String, String>();
		item1.put("item", i1.getUnlocalizedName());
		item1.put("meta", String.valueOf(i1.getItemDamage()));
		Map<String, String> item2 = new HashMap<String, String>();
		item2.put("item", i2.getUnlocalizedName());
		item2.put("meta", String.valueOf(i2.getItemDamage()));
		Map<String, String> result = new HashMap<String, String>();
		result.put("item", res.getItem().getRegistryName().toString());
		result.put("meta", String.valueOf(res.getItemDamage()));
		result.put("count", String.valueOf(res.getCount()));
		result.put("noRand", String.valueOf(noRand));
		this.smeltingList.put(item1, item2, result);
		this.smeltingList.put(item2, item1, result);
	}

	public ItemStack getCookingResult(ItemStack input1, ItemStack input2) {
		ItemStack result;
		NBTTagCompound nbt;
		ItemStack copy1 = input1.copy();
		Map<String, String> item1 = new HashMap<String, String>();
		item1.put("item", copy1.getUnlocalizedName());
		item1.put("meta", String.valueOf(copy1.getItemDamage()));
		ItemStack copy2 = input2.copy();
		Map<String, String> item2 = new HashMap<String, String>();
		item2.put("item", copy2.getUnlocalizedName());
		item2.put("meta", String.valueOf(copy2.getItemDamage()));

		if (this.smeltingList.contains(item1, item2)) {
			Map<String, String> res = this.smeltingList.get(item1, item2);

			int count = Integer.parseInt(res.get("count"));
			int meta = Integer.parseInt(res.get("meta"));
			Item item = Item.REGISTRY.getObject(new ResourceLocation(res.get("item")));

			if (res.get("noRand").equals("true")) {
				result = new ItemStack(item, count, meta);
				return result;
			} else if (res.get("noRand").equals("false")) {
				Random rand = new Random();
				count = 1 + rand.nextInt(count);
				return new ItemStack(item, count, meta);
			}
		}

		return ItemStack.EMPTY;
	}
}
