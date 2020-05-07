package ru.koshibari.deviatedmagic.blocks.machines.infuser;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.base.BlockContainerBase;
import ru.koshibari.deviatedmagic.init.ModBlocks;
import ru.koshibari.deviatedmagic.util.handlers.ConfigHandler;

import javax.annotation.Nullable;

public class Infuser extends BlockContainerBase {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static boolean isBurning;
    public static boolean keepInventory;

    public Infuser(String name, Material materialIn, boolean isBurning) {
        super(name, materialIn);
        setHardness(3.0f);
        setResistance(15.0f);
        setHarvestLevel("pickaxe", 0);

        if(isBurning){
            setLightLevel(2.0f);
        } else {
            setLightLevel(0.0f);
        }
        Infuser.isBurning = isBurning;
        setSoundType(SoundType.STONE);
        this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityInfuser();
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

        keepInventory = true;

        if (active) {
            worldIn.setBlockState(pos, ModBlocks.INFUSER_ON.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        } else {
            worldIn.setBlockState(pos, ModBlocks.INFUSER_OFF.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(DeviatedMagic.instance, ConfigHandler.GUI_INFUSER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
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
            if (tileEntity instanceof TileEntityInfuser) {
                ((TileEntityInfuser) tileEntity).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if(!keepInventory)
        {
            TileEntityInfuser tileentity = (TileEntityInfuser)worldIn.getTileEntity(pos);
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
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
