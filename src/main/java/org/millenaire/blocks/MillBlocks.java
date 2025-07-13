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
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import org.millenaire.blocks.BlockGalianiteOre;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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
                // crafting and smelting recipes moved to JSON files in data/millenaire/recipes
        }

    public static void prerender() {
        // models are loaded automatically via registry names
    }

    public static void render() {
        // rendering handled by default item models
    }
}
