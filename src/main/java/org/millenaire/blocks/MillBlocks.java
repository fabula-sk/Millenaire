package org.millenaire.blocks;

import org.millenaire.Millenaire;
import org.millenaire.entities.TileEntityMillChest;
import org.millenaire.entities.TileEntityMillSign;
import org.millenaire.entities.TileEntityVillageStone;
import org.millenaire.items.ItemBlockDecorativeEarth;
import org.millenaire.items.ItemBlockDecorativeSodPlank;
import org.millenaire.items.ItemBlockDecorativeStone;
import org.millenaire.items.ItemBlockDecorativeWood;
import org.millenaire.items.ItemMillPath;
import org.millenaire.items.ItemMillPathSlab;
import org.millenaire.items.ItemOrientedSlab;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MillBlocks {

        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Millenaire.MODID);
        public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Millenaire.MODID);

        public static RegistryObject<BlockEntityType<TileEntityMillChest>> MILL_CHEST_TILE;
        public static RegistryObject<BlockEntityType<TileEntityMillSign>> MILL_SIGN_TILE;
        public static RegistryObject<BlockEntityType<TileEntityVillageStone>> VILLAGE_STONE_TILE;

	public static Block blockDecorativeStone;
	public static Block blockDecorativeWood;
	public static Block blockDecorativeEarth;

	public static Block emptySericulture;
	public static Block mudBrick;
	
	public static Block thatchSlab;
	public static Block thatchSlabDouble;
	public static Block thatchStairs;

	public static Block byzantineTile;
	public static Block byzantineStoneTile;
	public static Block byzantineTileSlab;
	public static Block byzantineTileSlabDouble;
	public static Block byzantineTileStairs;

	public static Block paperWall;

	public static Block blockSodPlanks;
	public static Block blockCarving;

	public static Block cropTurmeric;
	public static Block cropRice;
	public static Block cropMaize;
	public static Block cropGrapeVine;

	public static Block blockMillChest;

	public static Block blockMillSign;

	public static Block blockAlchemists;

	public static Block blockMillPath;
	public static Block blockMillPathSlab;
	public static Block blockMillPathSlabDouble;
	
	public static Block galianiteOre;
	
	public static Block villageStone;
	
	public static Block storedPosition;

	public static void preinitialize() {

		//Decorative
		blockDecorativeStone = new BlockDecorativeStone().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("blockDecorativeStone", () -> blockDecorativeStone);

		blockDecorativeWood = new BlockDecorativeWood().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("blockDecorativeWood", () -> blockDecorativeWood);

		blockDecorativeEarth = new BlockDecorativeEarth().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("blockDecorativeEarth", () -> blockDecorativeEarth);

		blockSodPlanks = new BlockDecorativeSodPlank().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("blockSodPlank", () -> blockSodPlanks);

		emptySericulture = new BlockDecorativeUpdate(Material.wood, blockDecorativeWood.getDefaultState().withProperty(BlockDecorativeWood.VARIANT, BlockDecorativeWood.EnumType.SERICULTURE)).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("emptySericulture", () -> emptySericulture);

		mudBrick = new BlockDecorativeUpdate(Material.ground, blockDecorativeEarth.getDefaultState().withProperty(BlockDecorativeEarth.VARIANT, BlockDecorativeEarth.EnumType.DRIEDBRICK)).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("mudBrick", () -> mudBrick);

		thatchSlab = new BlockDecorativeOrientedSlabHalf(Material.wood).setCreativeTab(Millenaire.tabMillenaire);
		thatchSlabDouble = new BlockDecorativeOrientedSlabDouble(Material.wood, thatchSlab);
                BLOCKS.register("thatchSlab", () -> thatchSlab);
                BLOCKS.register("thatchSlabDouble", () -> thatchSlabDouble);
		
		thatchStairs = new BlockDecorativeOrientedStairs(blockDecorativeWood.getDefaultState().withProperty(BlockDecorativeWood.VARIANT, BlockDecorativeWood.EnumType.THATCH)).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("thatchStairs", () -> thatchStairs);
		
		byzantineTile = new BlockDecorativeOriented(Material.rock).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("byzantineTile", () -> byzantineTile);

                byzantineStoneTile = new BlockDecorativeOriented(Material.rock).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("byzantineStoneTile", () -> byzantineStoneTile);

		byzantineTileSlab = new BlockDecorativeOrientedSlabHalf(Material.rock).setCreativeTab(Millenaire.tabMillenaire);
		byzantineTileSlabDouble = new BlockDecorativeOrientedSlabDouble(Material.rock, byzantineTileSlab);
                BLOCKS.register("byzantineTileSlab", () -> byzantineTileSlab);
                BLOCKS.register("byzantineTileSlabDouble", () -> byzantineTileSlabDouble);

		byzantineTileStairs = new BlockDecorativeOrientedStairs(byzantineStoneTile.getDefaultState()).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("byzantineTileStairs", () -> byzantineTileStairs);

                paperWall = new BlockDecorativePane(Material.cloth).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("paperWall", () -> paperWall);

                blockCarving = new BlockDecorativeCarving(Material.rock);
                BLOCKS.register("inuitCarving", () -> blockCarving);

		//Crops
                cropTurmeric = new BlockMillCrops(false, false).setCreativeTab(null);
                BLOCKS.register("cropTurmeric", () -> cropTurmeric);

                cropRice = new BlockMillCrops(true, false).setCreativeTab(null);
                BLOCKS.register("cropRice", () -> cropRice);

                cropMaize = new BlockMillCrops(false, true).setCreativeTab(null);
                BLOCKS.register("cropMaize", () -> cropMaize);

                cropGrapeVine = new BlockMillCrops(false, false).setCreativeTab(null);
                BLOCKS.register("cropGrapeVine", () -> cropGrapeVine);

		//Chests
                blockMillChest = new BlockMillChest().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("blockMillChest", () -> blockMillChest);
                MILL_CHEST_TILE = TILE_ENTITIES.register("mill_chest", () -> BlockEntityType.Builder.of(TileEntityMillChest::new, blockMillChest).build(null));

		//Sign
                blockMillSign = new BlockMillSign();
                BLOCKS.register("blockMillSign", () -> blockMillSign);
                MILL_SIGN_TILE = TILE_ENTITIES.register("mill_sign", () -> BlockEntityType.Builder.of(TileEntityMillSign::new, blockMillSign).build(null));

		//Alchemists
                blockAlchemists = new BlockAlchemists().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("blockAlchemists", () -> blockAlchemists);

		//Paths
                blockMillPath = new BlockMillPath().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("blockMillPath", () -> blockMillPath);

                blockMillPathSlab = new BlockMillPathSlabHalf().setCreativeTab(Millenaire.tabMillenaire);
                blockMillPathSlabDouble = new BlockMillPathSlabDouble();
                BLOCKS.register("blockMillPathSlab", () -> blockMillPathSlab);
                BLOCKS.register("blockMillPathSlabDouble", () -> blockMillPathSlabDouble);

		//Ores
                galianiteOre = new BlockMillOre(BlockMillOre.EnumMillOre.GALIANITE);
                BLOCKS.register("galianiteOre", () -> galianiteOre);
    	
    	//Village Stone
        villageStone = new BlockVillageStone().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("villageStone", () -> villageStone);
                VILLAGE_STONE_TILE = TILE_ENTITIES.register("village_stone", () -> BlockEntityType.Builder.of(TileEntityVillageStone::new, villageStone).build(null));
		
		//StoredPosition
                storedPosition = new StoredPosition().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("storedPosition", () -> storedPosition);
	}

	public static void recipes() {
		GameRegistry.addSmelting(mudBrick, new ItemStack(blockDecorativeStone, 1, 1), 0.3f);
		GameRegistry.addRecipe(new ItemStack(byzantineStoneTile, 6),
				"AAA",
				"BBB",
				'A', new ItemStack(byzantineTile), 'B', new ItemStack(Blocks.stone));
		GameRegistry.addRecipe(new ItemStack(byzantineTileStairs, 4),
				"A  ",
				"BA ",
				"BBA",
				'A', new ItemStack(byzantineTile), 'B', new ItemStack(Blocks.stone));
		
		//Paths
		for(int i = 0; i < BlockMillPath.EnumType.values().length; i++)
    	{
    		GameRegistry.addRecipe(new ItemStack(blockMillPathSlab, 6, i), 
    				"AAA",
    				'A', new ItemStack(blockMillPath, 1, i));
    		GameRegistry.addRecipe(new ItemStack(blockMillPath, 1, i), 
    				"A",
    				"A",
    				'A', new ItemStack(blockMillPathSlab, 1, i));
    	}
	}

	public static void prerender() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeStone), 0, new ModelResourceLocation(Millenaire.MODID + ":goldOrnament", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeStone), 1, new ModelResourceLocation(Millenaire.MODID + ":cookedBrick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeStone), 2, new ModelResourceLocation(Millenaire.MODID + ":galianiteBlock", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeWood), 0, new ModelResourceLocation(Millenaire.MODID + ":plainTimberFrame", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeWood), 1, new ModelResourceLocation(Millenaire.MODID + ":crossTimberFrame", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeWood), 2, new ModelResourceLocation(Millenaire.MODID + ":thatch", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeWood), 3, new ModelResourceLocation(Millenaire.MODID + ":sericulture", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeEarth), 0, new ModelResourceLocation(Millenaire.MODID + ":dirtWall", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeEarth), 1, new ModelResourceLocation(Millenaire.MODID + ":driedBrick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(thatchSlabDouble), 0, new ModelResourceLocation(Millenaire.MODID + ":thatch"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(byzantineTileSlabDouble), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantineTile", "inventory"));

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 0, new ModelResourceLocation(Millenaire.MODID + ":sodOak", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 1, new ModelResourceLocation(Millenaire.MODID + ":sodPine", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 2, new ModelResourceLocation(Millenaire.MODID + ":sodBirch", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 3, new ModelResourceLocation(Millenaire.MODID + ":sodJungle", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 4, new ModelResourceLocation(Millenaire.MODID + ":sodJungle", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 5, new ModelResourceLocation(Millenaire.MODID + ":sodPine", "inventory"));

		//Paths
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 0, new ModelResourceLocation(Millenaire.MODID + ":pathDirt", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 1, new ModelResourceLocation(Millenaire.MODID + ":pathGravel", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 2, new ModelResourceLocation(Millenaire.MODID + ":pathSlab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 3, new ModelResourceLocation(Millenaire.MODID + ":pathSandstoneSlab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 4, new ModelResourceLocation(Millenaire.MODID + ":pathOchreSlab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 5, new ModelResourceLocation(Millenaire.MODID + ":pathSlabAndGravel", "inventory"));

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 0, new ModelResourceLocation(Millenaire.MODID + ":pathDirtHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 1, new ModelResourceLocation(Millenaire.MODID + ":pathGravelHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 2, new ModelResourceLocation(Millenaire.MODID + ":pathSlabHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 3, new ModelResourceLocation(Millenaire.MODID + ":pathSandstoneSlabHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 4, new ModelResourceLocation(Millenaire.MODID + ":pathOchreSlabHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 5, new ModelResourceLocation(Millenaire.MODID + ":pathSlabAndGravelHalf", "inventory"));

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 0, new ModelResourceLocation(Millenaire.MODID + ":pathDirt", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 1, new ModelResourceLocation(Millenaire.MODID + ":pathGravel", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 2, new ModelResourceLocation(Millenaire.MODID + ":pathSlab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 3, new ModelResourceLocation(Millenaire.MODID + ":pathSandstoneSlab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 4, new ModelResourceLocation(Millenaire.MODID + ":pathOchreSlab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 5, new ModelResourceLocation(Millenaire.MODID + ":pathSlabAndGravel", "inventory"));

	}

	public static void render() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

		renderItem.getItemModelMesher().register(Item.getItemFromBlock(emptySericulture), 0, new ModelResourceLocation(Millenaire.MODID + ":emptySericulture", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(mudBrick), 0, new ModelResourceLocation(Millenaire.MODID + ":mudBrick", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(thatchSlab), 0, new ModelResourceLocation(Millenaire.MODID + ":thatchSlab", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(thatchStairs), 0, new ModelResourceLocation(Millenaire.MODID + ":thatchStairs", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(byzantineTile), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantineTile", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(byzantineStoneTile), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantineStoneTile", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(byzantineTileSlab), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantineTileSlab", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(paperWall), 0, new ModelResourceLocation(Millenaire.MODID + ":paperWall", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(byzantineTileStairs), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantineTileStairs", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(blockCarving), 0, new ModelResourceLocation(Millenaire.MODID + ":inuitCarving", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(blockMillChest), 0, new ModelResourceLocation(Millenaire.MODID + ":blockMillChest", "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMillChest.class, new TileEntityChestRenderer());
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(blockMillSign), 0, new ModelResourceLocation(Millenaire.MODID + ":blockMillSign", "inventory"));		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMillSign.class, new TileEntitySignRenderer());
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(blockAlchemists), 0, new ModelResourceLocation(Millenaire.MODID + ":blockAlchemists", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(galianiteOre), 0, new ModelResourceLocation(Millenaire.MODID + ":galianiteOre", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(villageStone), 0, new ModelResourceLocation(Millenaire.MODID + ":villageStone", "inventory"));
		renderItem.getItemModelMesher().getModelManager().getBlockModelShapes().registerBuiltInBlocks(storedPosition);
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(storedPosition), 0, new ModelResourceLocation(Millenaire.MODID + ":storedPosition", "inventory"));
	}
}
