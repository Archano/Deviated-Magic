package ru.koshibari.deviatedmagic.base;

import java.util.Arrays;
import java.util.Map;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.init.ModItems;
import ru.koshibari.deviatedmagic.util.handlers.ConfigHandler;
import ru.koshibari.deviatedmagic.util.handlers.EnumHandler;

import javax.annotation.Nullable;

public class ItemBase extends Item {
    public Map<Integer, String> map;
    public final String NAME;

    public ItemBase(String name, CreativeTabs tab){
        setUnlocalizedName(name);
        setRegistryName(name);
        setHasSubtypes(false);
        this.NAME = name;
        if(!Arrays.asList(ConfigHandler.EXCLUDE).contains(name)){
            setCreativeTab(tab);
        }
        ModItems.ITEMS.add(this);
    }

    public ItemBase(String name, CreativeTabs tab, Map<Integer, String> map){
        setRegistryName(name);
        setHasSubtypes(true);
        setUnlocalizedName(name);
        this.map = map;
        this.NAME = name;
        if(!Arrays.asList(ConfigHandler.EXCLUDE).contains(name)){
            setCreativeTab(tab);
        }
        ModItems.ITEMS.add(this);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(this.hasSubtypes){
            for (int i = 0; i < map.size(); i++) {
                ItemStack stack = new ItemStack(this.setCreativeTab(tab), 1, i);
                items.add(stack);
            }
        } else {
            items.add(new ItemStack(this));
        }
    }

    public String getMetaName(ItemStack stack) {
        if(stack.getHasSubtypes()){
            for(int i = 0; i < this.map.size();i++){
                if (stack.getItemDamage() == i){
                    return "." + this.map.get(i);
                }
            }
        }
        return "";
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return DeviatedMagic.dmCreativeTab;
    }

    public static void registerModelsForVariants(Item item) {
    	if(item instanceof ItemBase && item.getHasSubtypes()) {
    		NonNullList<ItemStack> list = NonNullList.create();
            ((ItemBase) item).getSubItems(DeviatedMagic.dmCreativeTab, list);
    		for (ItemStack stack : list) {
    			DeviatedMagic.proxy.registerItemRenderer(stack.getItem(), stack.getItemDamage(), "inventory", ((ItemBase) item).getMetaName(stack));
    			ModelBakery.registerItemVariants(item, new ResourceLocation(item.getRegistryName() + ((ItemBase) item).getMetaName(stack)));
    		}
    	}
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getHasSubtypes()) {
            for (int i = 0; i < map.size(); i++) {
                if (stack.getItemDamage() == i) {
                    return this.getUnlocalizedName() + "." + map.get(i);
                } else {
                    continue;
                }
            }
        }
        return this.getUnlocalizedName();
    }
}
