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

        public static final RegistryObject<Block> blockDecorativeStone = BLOCKS.register("block_decorative_stone", BlockDecorativeStone::new);
        public static final RegistryObject<Block> blockDecorativeWood = BLOCKS.register("block_decorative_wood", BlockDecorativeWood::new);
        public static final RegistryObject<Block> blockDecorativeEarth = BLOCKS.register("block_decorative_earth", BlockDecorativeEarth::new);

        public static final RegistryObject<Block> blockSodPlanks = BLOCKS.register("block_sod_plank", BlockDecorativeSodPlank::new);
        public static final RegistryObject<Block> emptySericulture = BLOCKS.register("empty_sericulture", () -> new BlockDecorativeUpdate(Material.wood, blockDecorativeWood.get().getDefaultState().withProperty(BlockDecorativeWood.VARIANT, BlockDecorativeWood.EnumType.SERICULTURE)));
        public static final RegistryObject<Block> mudBrick = BLOCKS.register("mud_brick", () -> new BlockDecorativeUpdate(Material.ground, blockDecorativeEarth.get().getDefaultState().withProperty(BlockDecorativeEarth.VARIANT, BlockDecorativeEarth.EnumType.DRIEDBRICK)));

        public static final RegistryObject<Block> thatchSlab = BLOCKS.register("thatch_slab", () -> new BlockDecorativeOrientedSlabHalf(Material.wood));
        public static final RegistryObject<Block> thatchSlabDouble = BLOCKS.register("thatch_slab_double", () -> new BlockDecorativeOrientedSlabDouble(Material.wood, thatchSlab.get()));
        public static final RegistryObject<Block> thatchStairs = BLOCKS.register("thatch_stairs", () -> new BlockDecorativeOrientedStairs(blockDecorativeWood.get().getDefaultState().withProperty(BlockDecorativeWood.VARIANT, BlockDecorativeWood.EnumType.THATCH)));

        public static final RegistryObject<Block> byzantineTile = BLOCKS.register("byzantine_tile", () -> new BlockDecorativeOriented(Material.rock));
        public static final RegistryObject<Block> byzantineStoneTile = BLOCKS.register("byzantine_stone_tile", () -> new BlockDecorativeOriented(Material.rock));
        public static final RegistryObject<Block> byzantineTileSlab = BLOCKS.register("byzantine_tile_slab", () -> new BlockDecorativeOrientedSlabHalf(Material.rock));
        public static final RegistryObject<Block> byzantineTileSlabDouble = BLOCKS.register("byzantine_tile_slab_double", () -> new BlockDecorativeOrientedSlabDouble(Material.rock, byzantineTileSlab.get()));
        public static final RegistryObject<Block> byzantineTileStairs = BLOCKS.register("byzantine_tile_stairs", () -> new BlockDecorativeOrientedStairs(byzantineStoneTile.get().getDefaultState()));

        public static final RegistryObject<Block> paperWall = BLOCKS.register("paper_wall", () -> new BlockDecorativePane(Material.cloth));

        public static final RegistryObject<Block> blockCarving = BLOCKS.register("inuit_carving", () -> new BlockDecorativeCarving(Material.rock));

        public static final RegistryObject<Block> cropTurmeric = BLOCKS.register("crop_turmeric", () -> new BlockMillCrops(false, false));
        public static final RegistryObject<Block> cropRice = BLOCKS.register("crop_rice", () -> new BlockMillCrops(true, false));
        public static final RegistryObject<Block> cropMaize = BLOCKS.register("crop_maize", () -> new BlockMillCrops(false, true));
        public static final RegistryObject<Block> cropGrapeVine = BLOCKS.register("crop_grape_vine", () -> new BlockMillCrops(false, false));

        public static final RegistryObject<Block> blockMillChest = BLOCKS.register("block_mill_chest", BlockMillChest::new);
        public static final RegistryObject<Block> blockMillSign = BLOCKS.register("block_mill_sign", BlockMillSign::new);

        public static RegistryObject<BlockEntityType<TileEntityMillChest>> MILL_CHEST_TILE = TILE_ENTITIES.register("mill_chest", () -> BlockEntityType.Builder.of(TileEntityMillChest::new, blockMillChest.get()).build(null));
        public static RegistryObject<BlockEntityType<TileEntityMillSign>> MILL_SIGN_TILE = TILE_ENTITIES.register("mill_sign", () -> BlockEntityType.Builder.of(TileEntityMillSign::new, blockMillSign.get()).build(null));

        public static final RegistryObject<Block> blockAlchemists = BLOCKS.register("block_alchemists", BlockAlchemists::new);

        public static final RegistryObject<Block> blockMillPath = BLOCKS.register("block_mill_path", BlockMillPath::new);
        public static final RegistryObject<Block> blockMillPathSlab = BLOCKS.register("block_mill_path_slab", BlockMillPathSlabHalf::new);
        public static final RegistryObject<Block> blockMillPathSlabDouble = BLOCKS.register("block_mill_path_slab_double", BlockMillPathSlabDouble::new);

        public static final RegistryObject<Block> galianiteOre = BLOCKS.register("galianite_ore", BlockGalianiteOre::new);

        public static final RegistryObject<Block> villageStone = BLOCKS.register("village_stone", BlockVillageStone::new);
        public static RegistryObject<BlockEntityType<TileEntityVillageStone>> VILLAGE_STONE_TILE = TILE_ENTITIES.register("village_stone", () -> BlockEntityType.Builder.of(TileEntityVillageStone::new, villageStone.get()).build(null));

        public static final RegistryObject<Block> storedPosition = BLOCKS.register("stored_position", StoredPosition::new);


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
