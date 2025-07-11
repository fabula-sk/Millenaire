package org.millenaire;

import java.util.ArrayList;
import java.util.List;

import org.millenaire.blocks.MillBlocks;
import org.millenaire.entities.EntityMillVillager;
import org.millenaire.generation.VillageGenerator;
import org.millenaire.gui.MillAchievement;
import org.millenaire.gui.MillGuiHandler;
import org.millenaire.items.MillItems;
import org.millenaire.networking.MillPacket;
import org.millenaire.networking.PacketExportBuilding;
import org.millenaire.networking.PacketImportBuilding;
import org.millenaire.networking.PacketSayTranslatedMessage;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;


@Mod(Millenaire.MODID)
public class Millenaire
{
	public static final String MODID = "millenaire";
	public static final String NAME = "Mill\u00e9naire";
	public static final String VERSION = "7.0.0";
	public static final String GUIFACTORY = "org.millenaire.gui.MillGuiFactory";

	public static boolean isServer = true;
	
	public List<Block> forbiddenBlocks;
	
        @Instance
        public static Millenaire instance = new Millenaire();
        public static SimpleNetworkWrapper simpleNetworkWrapper;

        public Millenaire() {
                FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
                FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

                MillBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
                MillItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
                EntityMillVillager.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

                MinecraftForge.EVENT_BUS.register(this);
        }
	
	public static final CreativeTabs tabMillenaire = new CreativeTabs("MillTab")
	{
		public Item getTabIconItem() { return MillItems.denierOr; }
	};
	
        private void setup(final FMLCommonSetupEvent event)
        {
                MillConfig.preinitialize();
                MinecraftForge.EVENT_BUS.register(new RaidEvent.RaidEventHandler());

                setForbiddenBlocks();

                MillBlocks.preinitialize();
                MillBlocks.recipes();

                MillItems.preinitialize();
                MillItems.recipies();
                EntityMillVillager.preinitialize();

                MillCulture.preinitialize();

                MillAchievement.preinitialize();

                simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("MillChannel");
                simpleNetworkWrapper.registerMessage(MillPacket.PacketHandlerOnServer.class, MillPacket.class, 0, Side.SERVER);
                simpleNetworkWrapper.registerMessage(PacketImportBuilding.Handler.class, PacketImportBuilding.class, 1, Side.SERVER);
                simpleNetworkWrapper.registerMessage(PacketSayTranslatedMessage.Handler.class, PacketSayTranslatedMessage.class, 2, Side.CLIENT);
                simpleNetworkWrapper.registerMessage(PacketExportBuilding.Handler.class, PacketExportBuilding.class, 3, Side.SERVER);

                NetworkRegistry.INSTANCE.registerGuiHandler(instance, new MillGuiHandler());
                GameRegistry.registerWorldGenerator(new VillageGenerator(), 1000);
        }

        private void clientSetup(final FMLClientSetupEvent event)
        {
                MillBlocks.prerender();
                MillItems.prerender();

                EntityMillVillager.prerender();

                MillConfig.eventRegister();

                MillBlocks.render();
                MillItems.render();

                isServer = false;
        }
	
	private void setForbiddenBlocks()
	{
		String parsing = MillConfig.forbiddenBlocks.substring(11);
		forbiddenBlocks = new ArrayList<Block>();
                for (final String name : parsing.split(", |,"))
                {
                        Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name));
                        if (b != null)
                        {
                                forbiddenBlocks.add(b);
                        }
                }
	}
	
        @SubscribeEvent
        public void serverLoad(FMLServerStartingEvent event) {
                event.registerServerCommand(new MillCommand());
        }
}
