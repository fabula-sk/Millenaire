package org.millenaire.blocks;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.StateDefinition;

public class BlockDecorativeStone extends Block
{
    BlockDecorativeStone() {
        super(Properties.of(Material.STONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        // no block states
    }
}
