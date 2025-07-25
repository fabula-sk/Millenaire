package org.millenaire.generation;

import java.util.Random;

import org.millenaire.MillConfig;
import org.millenaire.VillageTracker;
import org.millenaire.blocks.MillBlocks;

import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BiomeLoadingEvent;

// IWorldGenerator is deprecated on modern Forge versions; use biome loading events instead.

public class VillageGenerator {

       /**
        * Hook for modern Forge world generation. Called during {@link BiomeLoadingEvent}.
        */
       public static void onBiomeLoading(BiomeLoadingEvent event) {
               // TODO integrate generation logic with new biome loading system
       }
	
	/**
	 * Attempt to generate the village
	 */
       private static boolean generateVillageAt(Random rand, BlockPos pos, World world) {
		if(!MillConfig.generateVillages && !MillConfig.generateLoneBuildings || (world.getSpawnPoint().distanceSq(pos) < MillConfig.spawnDistance)) {
			return false;
		}
		if(world.isRemote) {
			return false;
		}
		if(!VillageTracker.get(world).getNearVillages(pos, MillConfig.minVillageDistance).isEmpty()) {
			return false;
		}
		else {
			Player generatingPlayer = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), -1);
                        if(rand.nextInt(50) == 1 && world.getChunkFromBlockCoords(pos).isLoaded()) {
                                world.setBlockState(pos, MillBlocks.villageStone.getDefaultState());
                        }
			return false;
		}
	}
}
