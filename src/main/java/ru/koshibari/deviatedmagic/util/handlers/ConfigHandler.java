package ru.koshibari.deviatedmagic.util.handlers;

import it.unimi.dsi.fastutil.ints.Int2IntAVLTreeMap;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.util.Reference;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    //Gui id`s
    public static int GUI_INFUSER = 0;

    //Structure Spawns
    public static boolean LOOT1_SPAWN = true;

    //Othere
    public static String[] EXCLUDE = new String[]{"infuser_on"};

    public static void init(File file) {
        config = new Configuration(file);

        String category;
        category = "GUI IDs";
        config.addCustomCategoryComment(category, "Set IDs for each GUI.");
        GUI_INFUSER = config.getInt("GUI Infuser", category, 0, 0, 500, "GUI ID for the Infuser");

        category = "Spawning Structures";
        config.addCustomCategoryComment(category, "Is structure spawns");
        LOOT1_SPAWN = config.getBoolean("Spawn LOOT1", category, true, "Is LOOT1 Spawns");

        category = "Othere";
        config.addCustomCategoryComment(category, "Othere configs");
        EXCLUDE = config.getStringList("Excluded from CT", category, new String[]{"infuser_on"}, "Items that was excluded from creative tab");
        config.save();
    }

    public static void registerConfig(FMLPreInitializationEvent event) {
        DeviatedMagic.config = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
        DeviatedMagic.config.mkdirs();
        init(new File(DeviatedMagic.config.getPath(), Reference.MODID + ".cfg"));
    }
}
