package ru.koshibari.deviatedmagic.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ru.koshibari.deviatedmagic.objects.blocks.machines.infuser.ContainerInfuser;
import ru.koshibari.deviatedmagic.objects.blocks.machines.infuser.GuiInfuser;
import ru.koshibari.deviatedmagic.objects.blocks.machines.infuser.TileEntityInfuser;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ConfigHandler.GUI_INFUSER){
            return new ContainerInfuser(player.inventory, (TileEntityInfuser)world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == ConfigHandler.GUI_INFUSER)
        {
            return new GuiInfuser(player.inventory, (TileEntityInfuser)world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}
