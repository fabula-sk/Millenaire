package org.millenaire.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.WoodType;

import org.millenaire.entities.TileEntityMillSign;

public class BlockMillSign extends WallSignBlock {

    public BlockMillSign() {
        super(BlockBehaviour.Properties.of(Material.WOOD).strength(-1.0F, 6000000.0F), WoodType.OAK);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityMillSign();
    }
}
