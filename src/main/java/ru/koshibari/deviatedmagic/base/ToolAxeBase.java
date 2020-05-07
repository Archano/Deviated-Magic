package ru.koshibari.deviatedmagic.base;

import net.minecraft.item.ItemAxe;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.init.ModItems;
import ru.koshibari.deviatedmagic.util.handlers.ConfigHandler;

import java.util.Arrays;

public class ToolAxeBase extends ItemAxe {
    public ToolAxeBase(String name, ToolMaterial material) {
        super(material, material.getAttackDamage(), material.getEfficiency());
        setUnlocalizedName(name);
        setRegistryName(name);
        if (!Arrays.asList(ConfigHandler.EXCLUDE).contains(name)) {
            setCreativeTab(DeviatedMagic.dmCreativeTab);
        }
        ModItems.ITEMS.add(this);
    }
}