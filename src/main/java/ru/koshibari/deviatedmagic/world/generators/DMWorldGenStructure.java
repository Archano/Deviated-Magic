package ru.koshibari.deviatedmagic.world.generators;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.util.interfaces.IStructure;
import ru.koshibari.deviatedmagic.util.Reference;
import ru.koshibari.deviatedmagic.util.handlers.LootTableHandler;

import java.util.Random;

public class DMWorldGenStructure extends WorldGenerator implements IStructure {
    public String structureName;
    public boolean genInv;
    public BlockPos invPos;
    public ResourceLocation lootTable;

    public DMWorldGenStructure(String name, boolean genInventory, BlockPos pos, ResourceLocation lootTable) {
        this.structureName = name;
        this.genInv = genInventory;
        this.invPos = pos;
        this.lootTable = lootTable;
    }

    public DMWorldGenStructure(String name) {
        this.structureName = name;
        this.genInv = false;
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
            if (this.genInv){
                BlockPos pos1 = new BlockPos(pos.getX() + this.invPos.getX(), pos.getY() + this.invPos.getY(), pos.getZ() + this.invPos.getZ());
                TileEntity te = world.getTileEntity(pos1);
                ((TileEntityChest)te).setLootTable(this.lootTable, rand.nextInt());
                ((TileEntityChest)te).fillWithLoot(null);
            }
        }
    }
}
