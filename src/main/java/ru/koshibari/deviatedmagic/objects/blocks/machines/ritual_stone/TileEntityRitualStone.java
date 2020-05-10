package ru.koshibari.deviatedmagic.objects.blocks.machines.ritual_stone;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.koshibari.deviatedmagic.init.ModBlocks;

public class TileEntityRitualStone extends TileEntity implements ITickable {

    @Override
    public void update() {

    }

    public static boolean isStructureComplete(World world, BlockPos pos) {
        BlockPos pos1 = pos.add(4, 0, 0);
        if (world.getBlockState(pos1).getBlock() == ModBlocks.RITUAL_STONE_PEDESTAL) {
            pos1.add(-2, 0, 4);
            if (world.getBlockState(pos1).getBlock() == ModBlocks.RITUAL_STONE_PEDESTAL) {
                
            } else {
                pos1.add(-2, 0, -8);
                if (world.getBlockState(pos1).getBlock() == ModBlocks.RITUAL_STONE_PEDESTAL) {

                } else {
                    return false;
                }
            }
        } else {
            pos1 = pos.add(-8, 0, 0);
            if (world.getBlockState(pos1).getBlock() == ModBlocks.RITUAL_STONE_PEDESTAL) {

            } else {
                pos1 = pos.add(8, 0, 4);
                if (world.getBlockState(pos1).getBlock() == ModBlocks.RITUAL_STONE_PEDESTAL) {

                } else {
                    pos1 = pos.add(8, 0, -8);
                    if (world.getBlockState(pos1).getBlock() == ModBlocks.RITUAL_STONE_PEDESTAL) {

                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }


}
