package org.millenaire.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockState;

class BlockDecorativeOrientedStairs extends BlockStairs
{
	BlockDecorativeOrientedStairs(BlockState modelState)
	{
		super(modelState);
		
		this.useNeighborBrightness = true;
	}

}
