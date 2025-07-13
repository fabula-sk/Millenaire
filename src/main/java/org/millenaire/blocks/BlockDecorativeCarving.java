package org.millenaire.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;

public class BlockDecorativeCarving extends BlockDecorativeOriented {

        BlockDecorativeCarving(Material materialIn) {
                super(materialIn);
        }

	@Override
	public boolean isOpaqueCube() { return false; }

	@Override
	public boolean isFullBlock() { return false; }

	@Override
    public boolean isFullCube() { return false; }

    @SideOnly(Side.CLIENT)
    public float getAmbientOcclusionLightValue() { return 0.85F; }

	@Override
        public void setBlockBoundsBasedOnState(BlockGetter worldIn, BlockPos pos)
        {
                BlockState iblockstate = worldIn.getBlockState(pos);

		if (iblockstate.getBlock() == this)
		{
                        if (iblockstate.getValue(FACING) == Direction.NORTH || iblockstate.getValue(FACING) == Direction.SOUTH)
			{
				this.setBlockBounds(0.25F, 0.0F, 0.0F, 0.75F, 0.5F, 1.0F);
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 0.5F, 0.75F);
			}
		}

	}
}