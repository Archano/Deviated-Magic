package ru.koshibari.deviatedmagic.util.handlers;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.koshibari.deviatedmagic.blocks.machines.ritual_stone.pedestal.TileEntityPedestal;
import ru.koshibari.deviatedmagic.blocks.render.RenderPedestal;

@SideOnly(Side.CLIENT)
public class RenderHandler {
    public static void registerEntityRenders(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new RenderPedestal());
    }
}
