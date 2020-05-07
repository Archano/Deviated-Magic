package ru.koshibari.deviatedmagic.util.handlers;

import net.minecraftforge.common.MinecraftForge;
import ru.koshibari.deviatedmagic.events.EntityDrops;

public class EventHandler {
    public static void registerEvents(){
        EntityDrops zombieKillEvent = new EntityDrops();

        MinecraftForge.EVENT_BUS.register(zombieKillEvent);
    }
}
