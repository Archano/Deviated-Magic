package ru.koshibari.deviatedmagic.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.Set;

import com.google.common.collect.Sets;

public class ToolAxeBase extends ItemToolBase {
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);
	
    public ToolAxeBase(String name, ToolMaterial material) {
        super(name, material, EFFECTIVE_ON);
        this.attackDamage = material.getAttackDamage();
        this.attackSpeed = material.getEfficiency();
    }
    
    protected ToolAxeBase(String name, ToolMaterial material, float damage, float speed)
    {
        super(name, material, EFFECTIVE_ON);
        this.attackDamage = damage;
        this.attackSpeed = speed;
    }
    
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
    }
    
}