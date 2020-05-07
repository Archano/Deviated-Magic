package ru.koshibari.deviatedmagic.base;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityIndestructableItem extends EntityItem {

    public EntityIndestructableItem(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        isImmuneToFire = true;
    }

    public EntityIndestructableItem(World world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
        isImmuneToFire = true;
    }

    public EntityIndestructableItem(World worldIn) {
        super(worldIn);
        isImmuneToFire = true;
    }

    @Override
    public boolean cannotPickup() {
        return true;
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source.getDamageType().equals(DamageSource.OUT_OF_WORLD.damageType)) {
            return true;
        }
        // prevent any damage besides out of world
        return false;
    }
}