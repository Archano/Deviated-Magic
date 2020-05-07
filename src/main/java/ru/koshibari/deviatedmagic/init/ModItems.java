package ru.koshibari.deviatedmagic.init;

import net.minecraft.item.*;
import ru.koshibari.deviatedmagic.base.*;
import ru.koshibari.deviatedmagic.items.SoulGem;
import ru.koshibari.deviatedmagic.items.soulgem.Enderman;
import ru.koshibari.deviatedmagic.items.soulgem.Zombie;
import ru.koshibari.deviatedmagic.items.tools.SoulExtractor;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();


    public static final ItemBase SOUL_GEM = new SoulGem("soulgem");
    public static final ItemBase SOUL_GEM_ZOMBIE = new Zombie("soulgem_zombie");
    public static final ItemBase SOUL_GEM_ENDERMAN = new Enderman("soulgem_enderman");
    public static final ItemBase FLASK = new ItemBase("flask");
    public static final ItemBase CORRUPTION_BLEND = new ItemBase("corruption_blend");
    public static final ItemBase COAGULATED_BLOOD = new ItemBase("coagulated_blood");
    public static final ItemBase UPGRADE_MATTER_TIER_1 = new ItemBase("upgrade_matter_1");

    public static final ItemSword SOUL_EXTRACTOR = new SoulExtractor("soulextractor", ModMaterials.TOOL_BLOOD);
    public static final ItemPickaxe BLOODY_PICKAXE = new ToolPickaxeBase("bloody_pickaxe", ModMaterials.TOOL_BLOOD);
    public static final ItemAxe BLOODY_AXE = new ToolAxeBase("bloody_axe", ModMaterials.TOOL_BLOOD);
    public static final ItemSpade BLOODY_SHOVEL = new ToolShovelBase("bloody_shovel", ModMaterials.TOOL_BLOOD);
    public static final ItemHoe BLOODY_HOE = new ToolHoeBase("bloody_hoe", ModMaterials.TOOL_BLOOD);
}
