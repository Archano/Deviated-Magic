package ru.koshibari.deviatedmagic.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.koshibari.deviatedmagic.base.ToolSwordBase;
import ru.koshibari.deviatedmagic.init.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoulExtractor extends ToolSwordBase {
    public SoulExtractor(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        InventoryPlayer inv = ((EntityPlayer) attacker).inventory;

        checkAndAdd(ModItems.SOUL_GEM_ZOMBIE, "Zombie", ModItems.SOUL_GEM_ZOMBIE.getMaxDamage(), target, inv);
        checkAndAdd(ModItems.SOUL_GEM_ENDERMAN, "Enderman", ModItems.SOUL_GEM_ENDERMAN.getMaxDamage(), target, inv);

        return false;
    }

    public static void checkAndAdd(Item item, String entityName, int maxDmg, Entity target, InventoryPlayer inv) {
        List<ItemStack> entity = new ArrayList<ItemStack>();
        for (int i = 0; i < maxDmg + 1; i++) {
            entity.add(new ItemStack(item, 1, i));
        }
        if (!target.isEntityAlive()) {
            if (target.getName().equals(entityName)) {
                boolean found = false;
                for (int i = 0; i < maxDmg + 1; i++) {
                    if (inv.hasItemStack(entity.get(i))) {
                        ItemStack st = inv.getStackInSlot(inv.getSlotFor(entity.get(i)));
                        st.setItemDamage(st.getItemDamage() - new Random().nextInt(8) - 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    if (inv.hasItemStack(new ItemStack(ModItems.SOUL_GEM))) {
                        inv.decrStackSize(inv.getSlotFor(new ItemStack(ModItems.SOUL_GEM)), 1);
                        inv.addItemStackToInventory(new ItemStack(item, 1, maxDmg));
                    }
                }
            }
        }
    }
}
