package org.millenaire.blocks;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.core.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDecorativeOriented extends BlockDirectional
{

	BlockDecorativeOriented(Material materialIn) { super(materialIn); }
	
	@Override
	public BlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    // Metadata handling removed in favor of pure block states
	
	@Override
	protected BlockState createBlockState() { return new BlockState(this, FACING); }
}
