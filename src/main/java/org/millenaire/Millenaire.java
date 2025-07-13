package org.millenaire;

import java.util.ArrayList;
import java.util.List;

import org.millenaire.blocks.MillBlocks;
import org.millenaire.entities.EntityMillVillager;
import org.millenaire.generation.VillageGenerator;
import org.millenaire.gui.MillAchievement;
import org.millenaire.gui.MillMenus;
import org.millenaire.gui.ParchmentScreen;
import org.millenaire.gui.MillChestScreen;
import org.millenaire.gui.OptionsScreen;
import org.millenaire.gui.ChiefScreen;
import org.millenaire.items.MillItems;
import org.millenaire.items.ItemMillAmulet;
import org.millenaire.networking.MillNetwork;
import org.millenaire.capability.PlayerCropProvider;
import org.millenaire.capability.CapabilityEvents;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.client.registry.ScreenManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
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
        public static SimpleChannel channel = MillNetwork.CHANNEL;

        public Millenaire() {
                FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
                FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

                MillBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
                MillBlocks.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
                MillItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
                EntityMillVillager.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
                MillMenus.MENUS.register(FMLJavaModLoadingContext.get().getModEventBus());

                MinecraftForge.EVENT_BUS.register(this);
        }
	
	
        private void setup(final FMLCommonSetupEvent event)
        {
                MillConfig.preinitialize();
                PlayerCropProvider.register();
                new CapabilityEvents();
                MinecraftForge.EVENT_BUS.register(new RaidEvent.RaidEventHandler());

                setForbiddenBlocks();

                MillBlocks.preinitialize();
                MillBlocks.recipes();

                MillItems.preinitialize();
                MillItems.recipies();
                EntityMillVillager.preinitialize();

                MillCulture.preinitialize();

                MillAchievement.preinitialize();

                MillNetwork.init();

                MinecraftForge.EVENT_BUS.addListener(VillageGenerator::onBiomeLoading);
        }

        private void clientSetup(final FMLClientSetupEvent event)
        {
                MillBlocks.prerender();
                MillItems.prerender();

                EntityMillVillager.prerender();

                MillConfig.eventRegister();

                MillBlocks.render();
                MillItems.render();

                ScreenManager.registerFactory(MillMenus.PARCHMENT_MENU.get(), ParchmentScreen::new);
                ScreenManager.registerFactory(MillMenus.CHEST_MENU.get(), MillChestScreen::new);
                ScreenManager.registerFactory(MillMenus.OPTIONS_MENU.get(), OptionsScreen::new);
                ScreenManager.registerFactory(MillMenus.CHIEF_MENU.get(), ChiefScreen::new);

                // Bind tile entity renderers
                ClientRegistry.bindTileEntityRenderer(MillBlocks.MILL_CHEST_TILE.get(), TileEntityChestRenderer::new);
                ClientRegistry.bindTileEntityRenderer(MillBlocks.MILL_SIGN_TILE.get(), TileEntitySignRenderer::new);

                // Register item color handlers
                ItemColors colors = Minecraft.getInstance().getItemColors();
                colors.register((stack, tint) ->
                        stack.getItem() instanceof ItemMillAmulet ?
                                ((ItemMillAmulet) stack.getItem()).getColor(stack, tint) : 0xFFFFFF,
                        MillItems.amuletAlchemist,
                        MillItems.amuletVishnu,
                        MillItems.amuletYggdrasil);

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
               MillCommand.register(event.getServer().getCommands().getDispatcher());
       }
}
