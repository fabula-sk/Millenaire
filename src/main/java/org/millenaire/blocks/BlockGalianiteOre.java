package org.millenaire.blocks;

import java.util.Random;

import org.millenaire.items.MillItems;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockGalianiteOre extends Block {

    BlockGalianiteOre() {
        super(AbstractBlock.Properties.of(Material.rock)
                .strength(3.0F, 3.0F)
                .sound(SoundType.STONE));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return MillItems.galianiteDust;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 2;
    }
}
