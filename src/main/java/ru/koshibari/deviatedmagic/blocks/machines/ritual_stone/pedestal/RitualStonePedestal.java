package ru.koshibari.deviatedmagic.blocks.machines.ritual_stone.pedestal;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import ru.koshibari.deviatedmagic.base.BlockContainerBase;
import ru.koshibari.deviatedmagic.base.EntityIndestructableItem;
import ru.koshibari.deviatedmagic.blocks.machines.infuser.TileEntityInfuser;
import ru.koshibari.deviatedmagic.init.ModBlocks;

public class RitualStonePedestal extends BlockContainerBase {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public static final AxisAlignedBB RITUAL_STONE_PEDESTAL = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.875D, 0.9375D);

    public static boolean keepInventory;

    public RitualStonePedestal(String name, Material material) {
        super(name, material);
        setHardness(3.0f);
        setResistance(15.0f);
        setHarvestLevel("pickaxe", 2);
        setSoundType(SoundType.STONE);
        this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }



    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return RITUAL_STONE_PEDESTAL;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
        if (stack.hasDisplayName()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            /*if (tileEntity instanceof TileEntityRitualStone) {
                ((TileEntityRitualStone) tileEntity).setCustomName(stack.getDisplayName());
            }*/
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntityInfuser tileentity = (TileEntityInfuser) worldIn.getTileEntity(pos);
            InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
            super.breakBlock(worldIn, pos, state);
        }
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);
        if (facing.getAxis() == EnumFacing.Axis.Y) {
            facing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
    }

    private boolean canBlockStay(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (hand.equals(EnumHand.MAIN_HAND)) {
            ItemStack res = playerIn.getHeldItem(hand).copy();
            res.setCount(1);
            playerIn.getHeldItem(hand).shrink(1);
            worldIn.spawnEntity(new EntityIndestructableItem(worldIn, pos.getX(), pos.getY() + 0.875D, pos.getZ(), res));
        }
        return false;
    }
}
