package org.millenaire.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.block.Block;

public class BlockDecorativeCarving extends BlockDecorativeOriented {

        BlockDecorativeCarving(Material materialIn) {
                super(materialIn);
        }

        /**
         * Old bounding box logic handled via {@code setBlockBoundsBasedOnState}
         * has been replaced with voxel shapes.
         */
        private static final VoxelShape NS_SHAPE = Block.box(4.0D, 0.0D, 0.0D, 12.0D, 8.0D, 16.0D);
        private static final VoxelShape EW_SHAPE = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 8.0D, 12.0D);

        @Override
        public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
                return state.getValue(FACING).getAxis() == Direction.Axis.Z ? NS_SHAPE : EW_SHAPE;
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
                return getShape(state, worldIn, pos, context);
        }

    @SideOnly(Side.CLIENT)
    public float getAmbientOcclusionLightValue() { return 0.85F; }

}