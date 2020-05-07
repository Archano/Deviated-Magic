package ru.koshibari.deviatedmagic.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.init.ModBlocks;
import ru.koshibari.deviatedmagic.init.ModItems;
import ru.koshibari.deviatedmagic.util.handlers.ConfigHandler;

import java.util.Arrays;
import java.util.Random;

public class BlockBase extends Block {
    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        if(!Arrays.asList(ConfigHandler.EXCLUDE).contains(name)){
            setCreativeTab(DeviatedMagic.dmCreativeTab);
        }
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }
}
