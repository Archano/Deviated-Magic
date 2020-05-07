package ru.koshibari.deviatedmagic.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.koshibari.deviatedmagic.blocks.machines.infuser.TileEntityInfuser;
import ru.koshibari.deviatedmagic.util.Reference;

public class TileEntityHandler {
    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityInfuser.class, new ResourceLocation(Reference.MODID + ":infuser"));
    }
}
