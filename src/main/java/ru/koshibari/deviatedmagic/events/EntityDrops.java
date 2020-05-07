package ru.koshibari.deviatedmagic.events;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.koshibari.deviatedmagic.init.ModItems;

import java.util.Random;

@Mod.EventBusSubscriber
public class EntityDrops {
    @SubscribeEvent
    public static void onEntityDrops(LivingDropsEvent event){
        if(event.getEntity() instanceof EntityZombie){
            World world = event.getEntity().world;
            BlockPos pos = event.getEntity().getPosition();
            Random rand = new Random();
            if (rand.nextInt(8) == 0){
                event.getDrops().add(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.COAGULATED_BLOOD)));
            }
        }
    }
}
