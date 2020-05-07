package ru.koshibari.deviatedmagic.items.soulgem;

import ru.koshibari.deviatedmagic.base.ItemBase;

public class Zombie extends ItemBase {
    public Zombie(String name) {
        super(name);
        setMaxDamage(64);
        setMaxStackSize(1);
    }
}
