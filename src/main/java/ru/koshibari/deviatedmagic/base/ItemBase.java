package ru.koshibari.deviatedmagic.base;

import java.util.Arrays;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import ru.koshibari.deviatedmagic.init.ModItems;
import ru.koshibari.deviatedmagic.util.Reference;
import ru.koshibari.deviatedmagic.util.handlers.ConfigHandler;

public class ItemBase extends Item {
    public Map<Integer, String> map;

    public ItemBase(String name, CreativeTabs tab){
        setUnlocalizedName(name);
        setRegistryName(name);
        setHasSubtypes(false);
        if(!Arrays.asList(ConfigHandler.EXCLUDE).contains(name)){
            setCreativeTab(tab);
        }
        ModItems.ITEMS.add(this);
    }

    public ItemBase(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setHasSubtypes(false);
        if(!Arrays.asList(ConfigHandler.EXCLUDE).contains(name)){
            setCreativeTab(Reference.CT_COMMON);
        }
        ModItems.ITEMS.add(this);
    }

    public ItemBase(String name, CreativeTabs tab, Map<Integer, String> map){
        setRegistryName(name);
        setHasSubtypes(true);
        setUnlocalizedName(name);
        this.map = map;
        if(!Arrays.asList(ConfigHandler.EXCLUDE).contains(name)){
            setCreativeTab(tab);
        }
        ModItems.ITEMS.add(this);
    }

    public ItemBase(String name, Map<Integer, String> map){
        setRegistryName(name);
        setHasSubtypes(true);
        setUnlocalizedName(name);
        this.map = map;
        if(!Arrays.asList(ConfigHandler.EXCLUDE).contains(name)){
            setCreativeTab(Reference.CT_COMMON);
        }
        ModItems.ITEMS.add(this);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            if(this.hasSubtypes){
                for (int i = 0; i < map.size(); i++) {
                    ItemStack stack = new ItemStack(this, 1, i);
                    items.add(stack);
                }
            } else {
                items.add(new ItemStack(this));
            }
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
