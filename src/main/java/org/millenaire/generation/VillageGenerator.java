package org.millenaire.generation;

import java.util.Random;

import org.millenaire.MillConfig;
import org.millenaire.VillageTracker;
import org.millenaire.blocks.MillBlocks;

import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.VerticalAnchor;

// IWorldGenerator is deprecated on modern Forge versions; use biome loading events instead.

public class VillageGenerator {

       private static final ConfiguredFeature<?, ?> GALIANITE_ORE_CONFIG =
               Feature.ORE.configured(
                       new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE,
                               MillBlocks.galianiteOre.get().defaultBlockState(), 8));

       private static final PlacedFeature GALIANITE_ORE = GALIANITE_ORE_CONFIG.placed(
                       CountPlacement.of(20),
                       InSquarePlacement.spread(),
                       HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)));

       /**
        * Hook for modern Forge world generation. Called during {@link BiomeLoadingEvent}.
       */
       public static void onBiomeLoading(BiomeLoadingEvent event) {
               event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GALIANITE_ORE);
       }
	
	/**
	 * Attempt to generate the village
	 */
       private static boolean generateVillageAt(Random rand, BlockPos pos, Level world) {
		if(!MillConfig.generateVillages && !MillConfig.generateLoneBuildings || (world.getSpawnPoint().distanceSq(pos) < MillConfig.spawnDistance)) {
			return false;
		}
               if(world.isClientSide) {
                       return false;
               }
		if(!VillageTracker.get(world).getNearVillages(pos, MillConfig.minVillageDistance).isEmpty()) {
			return false;
		}
                else {
                        Player generatingPlayer = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), -1);
                        if(rand.nextInt(50) == 1) {
                                world.setBlock(pos, MillBlocks.villageStone.get().defaultBlockState(), 3);
                        }
                        return false;
                }
	}
}
