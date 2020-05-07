package ru.koshibari.deviatedmagic.util.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.koshibari.deviatedmagic.blocks.machines.infuser.InfuserRecipes;
import ru.koshibari.deviatedmagic.init.ModItems;

public class RecipeHandler {
    public static void registerRecipes() {
        InfuserRecipes.instance().addCookingForBlock(Blocks.GLASS, ModItems.SOUL_GEM, new ItemStack(ModItems.FLASK, 2));
        InfuserRecipes.instance().addCooking(Items.REDSTONE, Items.GLOWSTONE_DUST, new ItemStack(ModItems.CORRUPTION_BLEND, 3));
        InfuserRecipes.instance().addCooking(ModItems.UPGRADE_MATTER_TIER_1, Items.GOLDEN_SWORD, new ItemStack(ModItems.SOUL_EXTRACTOR, 1), true);
        InfuserRecipes.instance().addCooking(ModItems.UPGRADE_MATTER_TIER_1, Items.GOLDEN_AXE, new ItemStack(ModItems.BLOODY_AXE, 1), true);
        InfuserRecipes.instance().addCooking(ModItems.UPGRADE_MATTER_TIER_1, Items.GOLDEN_PICKAXE, new ItemStack(ModItems.BLOODY_PICKAXE, 1), true);
        InfuserRecipes.instance().addCooking(ModItems.UPGRADE_MATTER_TIER_1, Items.GOLDEN_SHOVEL, new ItemStack(ModItems.BLOODY_SHOVEL, 1), true);
        InfuserRecipes.instance().addCooking(ModItems.UPGRADE_MATTER_TIER_1, Items.GOLDEN_HOE, new ItemStack(ModItems.BLOODY_HOE, 1), true);
    }
}
