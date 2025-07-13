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
import org.millenaire.blocks.BlockGalianiteOre;
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
                BLOCKS.register("block_decorative_stone", () -> blockDecorativeStone);

		blockDecorativeWood = new BlockDecorativeWood().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("block_decorative_wood", () -> blockDecorativeWood);

		blockDecorativeEarth = new BlockDecorativeEarth().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("block_decorative_earth", () -> blockDecorativeEarth);

		blockSodPlanks = new BlockDecorativeSodPlank().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("block_sod_plank", () -> blockSodPlanks);

		emptySericulture = new BlockDecorativeUpdate(Material.wood, blockDecorativeWood.getDefaultState().withProperty(BlockDecorativeWood.VARIANT, BlockDecorativeWood.EnumType.SERICULTURE)).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("empty_sericulture", () -> emptySericulture);

		mudBrick = new BlockDecorativeUpdate(Material.ground, blockDecorativeEarth.getDefaultState().withProperty(BlockDecorativeEarth.VARIANT, BlockDecorativeEarth.EnumType.DRIEDBRICK)).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("mud_brick", () -> mudBrick);

		thatchSlab = new BlockDecorativeOrientedSlabHalf(Material.wood).setCreativeTab(Millenaire.tabMillenaire);
		thatchSlabDouble = new BlockDecorativeOrientedSlabDouble(Material.wood, thatchSlab);
                BLOCKS.register("thatch_slab", () -> thatchSlab);
                BLOCKS.register("thatch_slab_double", () -> thatchSlabDouble);
		
		thatchStairs = new BlockDecorativeOrientedStairs(blockDecorativeWood.getDefaultState().withProperty(BlockDecorativeWood.VARIANT, BlockDecorativeWood.EnumType.THATCH)).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("thatch_stairs", () -> thatchStairs);
		
		byzantineTile = new BlockDecorativeOriented(Material.rock).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("byzantine_tile", () -> byzantineTile);

                byzantineStoneTile = new BlockDecorativeOriented(Material.rock).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("byzantine_stone_tile", () -> byzantineStoneTile);

		byzantineTileSlab = new BlockDecorativeOrientedSlabHalf(Material.rock).setCreativeTab(Millenaire.tabMillenaire);
		byzantineTileSlabDouble = new BlockDecorativeOrientedSlabDouble(Material.rock, byzantineTileSlab);
                BLOCKS.register("byzantine_tile_slab", () -> byzantineTileSlab);
                BLOCKS.register("byzantine_tile_slab_double", () -> byzantineTileSlabDouble);

		byzantineTileStairs = new BlockDecorativeOrientedStairs(byzantineStoneTile.getDefaultState()).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("byzantine_tile_stairs", () -> byzantineTileStairs);

                paperWall = new BlockDecorativePane(Material.cloth).setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("paper_wall", () -> paperWall);

                blockCarving = new BlockDecorativeCarving(Material.rock);
                BLOCKS.register("inuit_carving", () -> blockCarving);

		//Crops
                cropTurmeric = new BlockMillCrops(false, false).setCreativeTab(null);
                BLOCKS.register("crop_turmeric", () -> cropTurmeric);

                cropRice = new BlockMillCrops(true, false).setCreativeTab(null);
                BLOCKS.register("crop_rice", () -> cropRice);

                cropMaize = new BlockMillCrops(false, true).setCreativeTab(null);
                BLOCKS.register("crop_maize", () -> cropMaize);

                cropGrapeVine = new BlockMillCrops(false, false).setCreativeTab(null);
                BLOCKS.register("crop_grape_vine", () -> cropGrapeVine);

		//Chests
                blockMillChest = new BlockMillChest().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("block_mill_chest", () -> blockMillChest);
                MILL_CHEST_TILE = TILE_ENTITIES.register("mill_chest", () -> BlockEntityType.Builder.of(TileEntityMillChest::new, blockMillChest).build(null));

		//Sign
                blockMillSign = new BlockMillSign();
                BLOCKS.register("block_mill_sign", () -> blockMillSign);
                MILL_SIGN_TILE = TILE_ENTITIES.register("mill_sign", () -> BlockEntityType.Builder.of(TileEntityMillSign::new, blockMillSign).build(null));

		//Alchemists
                blockAlchemists = new BlockAlchemists().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("block_alchemists", () -> blockAlchemists);

		//Paths
                blockMillPath = new BlockMillPath().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("block_mill_path", () -> blockMillPath);

                blockMillPathSlab = new BlockMillPathSlabHalf().setCreativeTab(Millenaire.tabMillenaire);
                blockMillPathSlabDouble = new BlockMillPathSlabDouble();
                BLOCKS.register("block_mill_path_slab", () -> blockMillPathSlab);
                BLOCKS.register("block_mill_path_slab_double", () -> blockMillPathSlabDouble);

                //Ores
                galianiteOre = new BlockGalianiteOre();
                BLOCKS.register("galianite_ore", () -> galianiteOre);
    	
    	//Village Stone
        villageStone = new BlockVillageStone().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("village_stone", () -> villageStone);
                VILLAGE_STONE_TILE = TILE_ENTITIES.register("village_stone", () -> BlockEntityType.Builder.of(TileEntityVillageStone::new, villageStone).build(null));
		
		//StoredPosition
                storedPosition = new StoredPosition().setCreativeTab(Millenaire.tabMillenaire);
                BLOCKS.register("stored_position", () -> storedPosition);
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
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeStone), 0, new ModelResourceLocation(Millenaire.MODID + ":gold_ornament", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeStone), 1, new ModelResourceLocation(Millenaire.MODID + ":cooked_brick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeStone), 2, new ModelResourceLocation(Millenaire.MODID + ":galianite_block", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeWood), 0, new ModelResourceLocation(Millenaire.MODID + ":plain_timber_frame", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeWood), 1, new ModelResourceLocation(Millenaire.MODID + ":cross_timber_frame", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeWood), 2, new ModelResourceLocation(Millenaire.MODID + ":thatch", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeWood), 3, new ModelResourceLocation(Millenaire.MODID + ":sericulture", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeEarth), 0, new ModelResourceLocation(Millenaire.MODID + ":dirt_wall", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockDecorativeEarth), 1, new ModelResourceLocation(Millenaire.MODID + ":dried_brick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(thatchSlabDouble), 0, new ModelResourceLocation(Millenaire.MODID + ":thatch"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(byzantineTileSlabDouble), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantine_tile", "inventory"));

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 0, new ModelResourceLocation(Millenaire.MODID + ":sod_oak", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 1, new ModelResourceLocation(Millenaire.MODID + ":sod_pine", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 2, new ModelResourceLocation(Millenaire.MODID + ":sod_birch", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 3, new ModelResourceLocation(Millenaire.MODID + ":sod_jungle", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 4, new ModelResourceLocation(Millenaire.MODID + ":sod_jungle", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockSodPlanks), 5, new ModelResourceLocation(Millenaire.MODID + ":sod_pine", "inventory"));

		//Paths
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 0, new ModelResourceLocation(Millenaire.MODID + ":path_dirt", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 1, new ModelResourceLocation(Millenaire.MODID + ":path_gravel", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 2, new ModelResourceLocation(Millenaire.MODID + ":path_slab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 3, new ModelResourceLocation(Millenaire.MODID + ":path_sandstone_slab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 4, new ModelResourceLocation(Millenaire.MODID + ":path_ochre_slab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPath), 5, new ModelResourceLocation(Millenaire.MODID + ":path_slab_and_gravel", "inventory"));

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 0, new ModelResourceLocation(Millenaire.MODID + ":pathDirtHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 1, new ModelResourceLocation(Millenaire.MODID + ":pathGravelHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 2, new ModelResourceLocation(Millenaire.MODID + ":pathSlabHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 3, new ModelResourceLocation(Millenaire.MODID + ":pathSandstoneSlabHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 4, new ModelResourceLocation(Millenaire.MODID + ":pathOchreSlabHalf", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlab), 5, new ModelResourceLocation(Millenaire.MODID + ":pathSlabAndGravelHalf", "inventory"));

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 0, new ModelResourceLocation(Millenaire.MODID + ":path_dirt", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 1, new ModelResourceLocation(Millenaire.MODID + ":path_gravel", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 2, new ModelResourceLocation(Millenaire.MODID + ":path_slab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 3, new ModelResourceLocation(Millenaire.MODID + ":path_sandstone_slab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 4, new ModelResourceLocation(Millenaire.MODID + ":path_ochre_slab", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMillPathSlabDouble), 5, new ModelResourceLocation(Millenaire.MODID + ":path_slab_and_gravel", "inventory"));

	}

	public static void render() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

		renderItem.getItemModelMesher().register(Item.getItemFromBlock(emptySericulture), 0, new ModelResourceLocation(Millenaire.MODID + ":empty_sericulture", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(mudBrick), 0, new ModelResourceLocation(Millenaire.MODID + ":mud_brick", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(thatchSlab), 0, new ModelResourceLocation(Millenaire.MODID + ":thatchSlab", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(thatchStairs), 0, new ModelResourceLocation(Millenaire.MODID + ":thatch_stairs", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(byzantineTile), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantine_tile", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(byzantineStoneTile), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantineStoneTile", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(byzantineTileSlab), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantineTileSlab", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(paperWall), 0, new ModelResourceLocation(Millenaire.MODID + ":paperWall", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(byzantineTileStairs), 0, new ModelResourceLocation(Millenaire.MODID + ":byzantineTileStairs", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(blockCarving), 0, new ModelResourceLocation(Millenaire.MODID + ":inuit_carving", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(blockMillChest), 0, new ModelResourceLocation(Millenaire.MODID + ":block_mill_chest", "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMillChest.class, new TileEntityChestRenderer());
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(blockMillSign), 0, new ModelResourceLocation(Millenaire.MODID + ":block_mill_sign", "inventory"));		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMillSign.class, new TileEntitySignRenderer());
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(blockAlchemists), 0, new ModelResourceLocation(Millenaire.MODID + ":block_alchemists", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(galianiteOre), 0, new ModelResourceLocation(Millenaire.MODID + ":galianite_ore", "inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(villageStone), 0, new ModelResourceLocation(Millenaire.MODID + ":village_stone", "inventory"));
		renderItem.getItemModelMesher().getModelManager().getBlockModelShapes().registerBuiltInBlocks(storedPosition);
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(storedPosition), 0, new ModelResourceLocation(Millenaire.MODID + ":stored_position", "inventory"));
	}
}
