package ru.koshibari.deviatedmagic.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.koshibari.deviatedmagic.DeviatedMagic;
import ru.koshibari.deviatedmagic.base.ItemBase;
import ru.koshibari.deviatedmagic.init.ModBlocks;
import ru.koshibari.deviatedmagic.init.ModItems;
import ru.koshibari.deviatedmagic.util.Reference;
import ru.koshibari.deviatedmagic.world.DMWorldGenCustomStructure;
import ru.koshibari.deviatedmagic.world.DMWorldGen;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> itemRegister) {
        itemRegister.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> blockRegister) {
        blockRegister.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ModItems.ITEMS) {
        	if(item.getHasSubtypes()) {
                NonNullList<ItemStack> list = NonNullList.create();
                item.getSubItems(Reference.CT_COMMON, list);
                for(ItemStack item1 : list){
                    DeviatedMagic.proxy.registerItemRenderer(item1.getItem(), item1.getItemDamage(), "inventory", ((ItemBase)item1.getItem()).getMetaName(item1));
                }
        	} else {
        		DeviatedMagic.proxy.registerItemRenderer(item, 0, "inventory");
        	}
        }

        for (Block block : ModBlocks.BLOCKS) {
            DeviatedMagic.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
        }
    }

    public static void preInitRegistries(FMLPreInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new DMWorldGen(), 0);
        GameRegistry.registerWorldGenerator(new DMWorldGenCustomStructure(), 0);
        ConfigHandler.registerConfig(event);
        EventHandler.registerEvents();
        EnumHandler.addEnums();
    }

    public static void initRegistries() {
        NetworkRegistry.INSTANCE.registerGuiHandler(DeviatedMagic.instance, new GuiHandler());
        RecipeHandler.registerRecipes();
        RenderHandler.registerEntityRenders();
    }

    public static void postInitRegistries() {

    }
}
