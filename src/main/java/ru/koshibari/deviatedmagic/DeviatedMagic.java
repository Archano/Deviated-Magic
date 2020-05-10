package ru.koshibari.deviatedmagic;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import ru.koshibari.deviatedmagic.gui.DMCreativeTab;
import ru.koshibari.deviatedmagic.proxy.CommonProxy;
import ru.koshibari.deviatedmagic.util.Reference;
import ru.koshibari.deviatedmagic.util.handlers.EnumHandler;
import ru.koshibari.deviatedmagic.util.handlers.RegistryHandler;

import java.io.File;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSION)
public class DeviatedMagic {
    public static File config;
    public static Logger logger;

    @Mod.Instance(Reference.MODID)
    public static DeviatedMagic instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    public static final CreativeTabs dmCreativeTab = new DMCreativeTab();

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        RegistryHandler.preInitRegistries(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        RegistryHandler.initRegistries();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        RegistryHandler.postInitRegistries();
    }
}
