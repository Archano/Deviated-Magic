package ru.koshibari.deviatedmagic.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import ru.koshibari.deviatedmagic.init.ModItems;
import ru.koshibari.deviatedmagic.util.Reference;

public class DMCreativeTab extends CreativeTabs {

    public DMCreativeTab() {
        super(Reference.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.SOUL_EXTRACTOR);
    }
}
