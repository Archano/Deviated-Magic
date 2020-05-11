package ru.koshibari.deviatedmagic.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.base.*;
import ru.koshibari.deviatedmagic.objects.items.Hammer;
import ru.koshibari.deviatedmagic.objects.items.ItemPlate;
import ru.koshibari.deviatedmagic.objects.items.SoulGem;
import ru.koshibari.deviatedmagic.objects.items.tools.IronKnife;
import ru.koshibari.deviatedmagic.objects.items.tools.SoulExtractor;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item SOUL_GEM = new SoulGem("soulgem");
    public static final Item FLASK = new ItemBase("flask");
    public static final Item CORRUPTION_BLEND = new ItemBase("corruption_blend");
    public static final Item COAGULATED_BLOOD = new ItemBase("coagulated_blood");
    public static final Item UPGRADE_MATTER = new ItemBase("upgrade_matter");
    public static final Item PLATE = new ItemPlate("plate");
    public static final Item HAMMER = new Hammer("hammer");

    public static final ToolSwordBase SOUL_EXTRACTOR = new SoulExtractor("soulextractor", ModMaterials.TOOL_BLOOD);
    public static final ToolPickaxeBase BLOODY_PICKAXE = new ToolPickaxeBase("bloody_pickaxe", ModMaterials.TOOL_BLOOD);
    public static final ToolAxeBase BLOODY_AXE = new ToolAxeBase("bloody_axe", ModMaterials.TOOL_BLOOD);
    public static final ToolShovelBase BLOODY_SHOVEL = new ToolShovelBase("bloody_shovel", ModMaterials.TOOL_BLOOD);
    public static final ToolHoeBase BLOODY_HOE = new ToolHoeBase("bloody_hoe", ModMaterials.TOOL_BLOOD);
    public static final ItemToolBase IRON_KNIFE = new IronKnife("iron_knife", ToolMaterial.IRON);
}
