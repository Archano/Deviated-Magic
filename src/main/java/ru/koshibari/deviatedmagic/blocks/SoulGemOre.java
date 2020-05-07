package ru.koshibari.deviatedmagic.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import ru.koshibari.deviatedmagic.base.BlockBase;
import ru.koshibari.deviatedmagic.init.ModItems;

import java.util.Random;

public class SoulGemOre extends BlockBase {
    public SoulGemOre(String name, Material material) {
        super(name, material);
        setHardness(3.0f);
        setResistance(15.0f);
        setHarvestLevel("pickaxe", 2);
        setLightLevel(0.0f);
        setSoundType(SoundType.STONE);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.SOUL_GEM;
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(3) + 2;
    }
}
