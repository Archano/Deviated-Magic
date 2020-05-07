package ru.koshibari.deviatedmagic.world.generators;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import ru.koshibari.deviatedmagic.util.interfaces.IStructure;
import ru.koshibari.deviatedmagic.util.Reference;
import ru.koshibari.deviatedmagic.util.handlers.LootTableHandler;

import java.util.Random;

public class DMWorldGenStructure extends WorldGenerator implements IStructure {
    public static String structureName;

    public DMWorldGenStructure(String name) {
        structureName = name;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        generateStructure(worldIn, position, rand);
        return false;
    }

    public void generateStructure(World world, BlockPos pos, Random rand){
        MinecraftServer server = world.getMinecraftServer();
        TemplateManager manager = worldServer.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(Reference.MODID, structureName);
        Template template = manager.getTemplate(server, location);

        if (template != null){
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            template.addBlocksToWorld(world, pos, placementSettings);
            BlockPos pos1 = pos.add(3, 2, 2);
            TileEntity te = world.getTileEntity(pos1);
            ((TileEntityChest)te).setLootTable(LootTableHandler.LOOT1, rand.nextInt());
            ((TileEntityChest)te).fillWithLoot(null);
        }
    }
}
