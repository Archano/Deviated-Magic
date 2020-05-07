package ru.koshibari.deviatedmagic.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import ru.koshibari.deviatedmagic.init.ModBlocks;

import java.util.Random;

public class DMWorldGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) {
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
        if (world.provider.getDimension() == -1) {
            generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
        if (world.provider.getDimension() == 1) {
            generateEnd(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }

    public void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateOre(ModBlocks.SOUL_GEM_ORE.getDefaultState(), BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX * 16, chunkZ * 16, 16, 64, random.nextInt(7) + 2, 18);
    }

    public void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

    }

    public void generateEnd(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

    }

    public void generateOre(IBlockState ore, BlockMatcher replacer, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
        int deltaY = maxY - minY;
        for (int i = 0; i < chances; i++) {
            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
            WorldGenMinable genMinable = new WorldGenMinable(ore, size, replacer);
            genMinable.generate(world, random, pos);
        }
    }


}
