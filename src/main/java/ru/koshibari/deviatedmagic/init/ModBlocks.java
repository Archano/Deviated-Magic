package ru.koshibari.deviatedmagic.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.koshibari.deviatedmagic.base.BlockBase;
import ru.koshibari.deviatedmagic.base.BlockContainerBase;
import ru.koshibari.deviatedmagic.blocks.machines.infuser.Infuser;
import ru.koshibari.deviatedmagic.blocks.*;
import ru.koshibari.deviatedmagic.blocks.machines.ritual_stone.RitualStone;
import ru.koshibari.deviatedmagic.blocks.machines.ritual_stone.pedestal.RitualStonePedestal;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final BlockBase SOUL_GEM_ORE = new SoulGemOre("soulgem_ore", Material.ROCK);;

    public static final BlockContainerBase RITUAL_STONE = new RitualStone("ritual_stone", Material.IRON);
    public static final BlockContainerBase RITUAL_STONE_PEDESTAL = new RitualStonePedestal("ritual_stone_pedestal", Material.IRON);
    public static final BlockContainerBase INFUSER_OFF = new Infuser("infuser_off", Material.ROCK, false);
    public static final BlockContainerBase INFUSER_ON = new Infuser("infuser_on", Material.ROCK, true);
}
