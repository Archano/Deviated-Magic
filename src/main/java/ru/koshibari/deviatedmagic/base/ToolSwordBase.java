package ru.koshibari.deviatedmagic.base;

import net.minecraft.item.ItemSword;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.init.ModItems;
import ru.koshibari.deviatedmagic.util.handlers.ConfigHandler;

import java.util.Arrays;

public class ToolSwordBase extends ItemSword {
    public ToolSwordBase(String name, ToolMaterial material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        if(!Arrays.asList(ConfigHandler.EXCLUDE).contains(name)){
            setCreativeTab(DeviatedMagic.dmCreativeTab);
        }
        ModItems.ITEMS.add(this);
    }
}
