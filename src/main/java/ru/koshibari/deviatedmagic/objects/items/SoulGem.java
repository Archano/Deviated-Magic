package ru.koshibari.deviatedmagic.objects.items;

import ru.koshibari.deviatedmagic.base.ItemBase;
import ru.koshibari.deviatedmagic.util.handlers.EnumHandler;

public class SoulGem extends ItemBase {

	public SoulGem(String name) {
    	super(name, EnumHandler.SoulGemTypes);
    }
}
