package org.millenaire.building;

import org.millenaire.CommonUtilities;
import org.millenaire.items.MillItems;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.world.item.ItemStack;

public class BuildingBlock 
{
	public static byte OAKSPAWN = 1;
	public static byte SPRUCESPAWN = 2;
	public static byte BIRCHSPAWN = 3;
	public static byte JUNGLESPAWN = 4;
	public static byte ACACIASPAWN = 5;
	public static byte PRESERVEGROUNDDEPTH = 6;
	public static byte PRESERVEGROUNDSURFACE = 7;
	public static byte CLEARTREE = 8;
	public static byte CLEARGROUND = 9;
	public static byte SPAWNERSKELETON = 10;
	public static byte SPAWNERZOMBIE = 11;
	public static byte SPAWNERSPIDER = 12;
	public static byte SPAWNERCAVESPIDER = 13;
	public static byte SPAWNERCREEPER = 14;
	public static byte SPAWNERBLAZE = 15;
	public static byte DISPENDERUNKNOWNPOWDER = 16;
	public static byte TAPESTRY = 17;
	public static byte BYZANTINEICONSMALL = 18;
	public static byte BYZANTINEICONMEDIUM = 19;
	public static byte BYZANTINEICONLARGE = 20;
	public static byte INDIANSTATUE = 21;
	public static byte MAYANSTATUE = 22;
	
	public BlockState blockState;
	public BlockPos position;
	public byte specialBlock;
	
	BuildingBlock(BlockState state, BlockPos pos, byte special)
	{
		blockState = state;
		position = pos;
		specialBlock = special;
	}

	BuildingBlock(BlockState state, BlockPos pos)
	{
		blockState = state;
		position = pos;
		specialBlock = 0;
	}
	
       public void build(Level worldIn, boolean onGeneration)
       {
               if (specialBlock != BuildingBlock.PRESERVEGROUNDDEPTH && specialBlock != BuildingBlock.PRESERVEGROUNDSURFACE && specialBlock != BuildingBlock.CLEARTREE)
               {
                    worldIn.setBlockState(position, blockState);
                    worldIn.playSound(null, position, blockState.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 0.3F, 0.6F);
               }
		
		if (specialBlock == BuildingBlock.PRESERVEGROUNDDEPTH || specialBlock == BuildingBlock.PRESERVEGROUNDSURFACE) 
		{
			Block block = worldIn.getBlockState(position).getBlock();

			final boolean surface = specialBlock == BuildingBlock.PRESERVEGROUNDSURFACE;

			final Block validGroundBlock = CommonUtilities.getValidGroundBlock(block, surface);

			if (validGroundBlock == null) 
			{
				BlockPos below = position.down();
				Block targetblock = null;
				while (targetblock == null && below.getY() > 0) 
				{
					block = worldIn.getBlockState(below).getBlock();
					if (CommonUtilities.getValidGroundBlock(block, surface) != null)
						targetblock = CommonUtilities.getValidGroundBlock(block, surface);
					below = below.down();
				}

                                if (targetblock == Blocks.DIRT && onGeneration)
                                {
                                        targetblock = Blocks.GRASS_BLOCK;
                                }
                                else if (targetblock == Blocks.GRASS_BLOCK && !onGeneration)
                                {
                                        targetblock = Blocks.DIRT;
                                }

                                if (targetblock == Blocks.AIR)
                                {
                                        if (onGeneration)
                                                targetblock = Blocks.GRASS_BLOCK;
                                        else
                                                targetblock = Blocks.DIRT;
                                }

				assert targetblock != null;
                                worldIn.setBlockState(position, targetblock.defaultBlockState());
                                worldIn.playSound(null, position, targetblock.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 0.3F, 0.6F);
			} 
                        else if (onGeneration && validGroundBlock == Blocks.DIRT && worldIn.isEmptyBlock(position.above()))
                        {
                                worldIn.setBlockState(position, Blocks.GRASS_BLOCK.defaultBlockState());
                                worldIn.playSound(null, position, Blocks.GRASS_BLOCK.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 0.3F, 0.6F);
                        }
                        else if (validGroundBlock != block && !(validGroundBlock == Blocks.DIRT && block == Blocks.GRASS_BLOCK))
			{
                                worldIn.setBlockState(position, validGroundBlock.defaultBlockState());
                                worldIn.playSound(null, position, validGroundBlock.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 0.3F, 0.6F);
			}
		}
		else if (specialBlock == BuildingBlock.CLEARTREE) 
		{
			Block block = worldIn.getBlockState(position).getBlock();

                        if (block == Blocks.OAK_LOG || block == Blocks.OAK_LEAVES)
			{
                                worldIn.removeBlock(position, false);
                                worldIn.playSound(null, position, block.getSoundType().getBreakSound(), SoundSource.BLOCKS, 0.3F, 0.6F);

				final Block blockBelow = worldIn.getBlockState(position.down()).getBlock();

				final Block targetBlock = CommonUtilities.getValidGroundBlock(blockBelow, true);

                                if (onGeneration && targetBlock == Blocks.DIRT)
                                {
                                worldIn.setBlockState(position.down(), Blocks.GRASS_BLOCK.defaultBlockState());
                                }
				else if (targetBlock != null) 
				{
                                worldIn.setBlockState(position.down(), targetBlock.defaultBlockState());
				}
			}

		} 
		else if (specialBlock == BuildingBlock.CLEARGROUND) 
		{
			Block block = worldIn.getBlockState(position).getBlock();
			
                        worldIn.removeBlock(position, false);
                        worldIn.playSound(null, position, block.getSoundType().getBreakSound(), SoundSource.BLOCKS, 0.3F, 0.6F);

			final Block blockBelow = worldIn.getBlockState(position.down()).getBlock();

			final Block targetBlock = CommonUtilities.getValidGroundBlock(blockBelow, true);

                        if (onGeneration && targetBlock == Blocks.DIRT)
                        {
                        worldIn.setBlockState(position.down(), Blocks.GRASS_BLOCK.defaultBlockState());
                        }
			else if (targetBlock != null) 
			{
                        worldIn.setBlockState(position.down(), targetBlock.defaultBlockState());
			}
		}
                else if (specialBlock == BuildingBlock.OAKSPAWN)
                {
                        ConfiguredFeature<?, ?> feature = Feature.TREE.configured(TreeFeatures.OAK);
                        feature.place(worldIn, worldIn.getChunkSource().getGenerator(), CommonUtilities.random, position);
                }
                else if (specialBlock == BuildingBlock.SPRUCESPAWN)
                {
                        ConfiguredFeature<?, ?> feature = Feature.TREE.configured(TreeFeatures.SPRUCE);
                        feature.place(worldIn, worldIn.getChunkSource().getGenerator(), CommonUtilities.random, position);
                }
                else if (specialBlock == BuildingBlock.BIRCHSPAWN)
                {
                        ConfiguredFeature<?, ?> feature = Feature.TREE.configured(TreeFeatures.BIRCH);
                        feature.place(worldIn, worldIn.getChunkSource().getGenerator(), CommonUtilities.random, position);
                }
		else if (specialBlock == BuildingBlock.JUNGLESPAWN) {
                        ConfiguredFeature<?, ?> feature = Feature.TREE.configured(TreeFeatures.JUNGLE_TREE);
                        feature.place(worldIn, worldIn.getChunkSource().getGenerator(), CommonUtilities.random, position);
                }
                else if (specialBlock == BuildingBlock.ACACIASPAWN)
                {
                        ConfiguredFeature<?, ?> feature = Feature.TREE.configured(TreeFeatures.ACACIA);
                        feature.place(worldIn, worldIn.getChunkSource().getGenerator(), CommonUtilities.random, position);
                }
                else if (specialBlock == BuildingBlock.SPAWNERSKELETON)
                {
                        worldIn.setBlockState(position, Blocks.SPAWNER.defaultBlockState());
                        final SpawnerBlockEntity tileentitymobspawner = (SpawnerBlockEntity) worldIn.getBlockEntity(position);
                        tileentitymobspawner.getSpawner().setEntityId(EntityType.SKELETON);
                }
                else if (specialBlock == BuildingBlock.SPAWNERZOMBIE)
                {
                        worldIn.setBlockState(position, Blocks.SPAWNER.defaultBlockState());
                        final SpawnerBlockEntity tileentitymobspawner = (SpawnerBlockEntity) worldIn.getBlockEntity(position);
                        tileentitymobspawner.getSpawner().setEntityId(EntityType.ZOMBIE);
                }
                else if (specialBlock == BuildingBlock.SPAWNERSPIDER)
                {
                        worldIn.setBlockState(position, Blocks.SPAWNER.defaultBlockState());
                        final SpawnerBlockEntity tileentitymobspawner = (SpawnerBlockEntity) worldIn.getBlockEntity(position);
                        tileentitymobspawner.getSpawner().setEntityId(EntityType.SPIDER);
                }
                else if (specialBlock == BuildingBlock.SPAWNERCAVESPIDER)
                {
                        worldIn.setBlockState(position, Blocks.SPAWNER.defaultBlockState());
                        final SpawnerBlockEntity tileentitymobspawner = (SpawnerBlockEntity) worldIn.getBlockEntity(position);
                        tileentitymobspawner.getSpawner().setEntityId(EntityType.CAVE_SPIDER);
                }
                else if (specialBlock == BuildingBlock.SPAWNERCREEPER)
                {
                        worldIn.setBlockState(position, Blocks.SPAWNER.defaultBlockState());
                        final SpawnerBlockEntity tileentitymobspawner = (SpawnerBlockEntity) worldIn.getBlockEntity(position);
                        tileentitymobspawner.getSpawner().setEntityId(EntityType.CREEPER);
                }
                else if (specialBlock == BuildingBlock.SPAWNERBLAZE)
                {
                        worldIn.setBlockState(position, Blocks.SPAWNER.defaultBlockState());
                        final SpawnerBlockEntity tileentitymobspawner = (SpawnerBlockEntity) worldIn.getBlockEntity(position);
                        tileentitymobspawner.getSpawner().setEntityId(EntityType.BLAZE);
                }
                else if (specialBlock == BuildingBlock.DISPENDERUNKNOWNPOWDER)
                {
                        worldIn.setBlockState(position, Blocks.DISPENSER.defaultBlockState());
                        final DispenserBlockEntity dispenser = (DispenserBlockEntity)worldIn.getBlockEntity(position);
                        dispenser.addItem(new ItemStack(MillItems.unknownPowder, 2));
                }
	}
	
	public void buildPath()
	{
		//Make code to build paths
	}
}
