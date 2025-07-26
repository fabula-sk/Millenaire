package org.millenaire.blocks;

import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMillPathSlab extends BlockSlab
{
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockMillPath.EnumType.class);
	private static final PropertyBool SEAMLESS = PropertyBool.create("seamless");

	BlockMillPathSlab()
	{
		super(Material.ground);

		if(this.isDouble())
                this.setDefaultState(this.blockState.getBaseState().withProperty(SEAMLESS, true));
        else
                this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM));

                this.useNeighborBrightness = true;
        }
	
	@Override
	public boolean isDouble() { return false; }

	@Override
    public Item getItemDropped(BlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MillBlocks.blockMillPathSlab.get());
    }



    }
    
	@Override
    public IProperty getVariantProperty() { return VARIANT; }

    @Override
    public Object getVariant(ItemStack stack)
    {
        return BlockMillPath.EnumType.byMetadata(stack.getMetadata() & 7);
    }


    // Metadata handling removed in favor of pure block states
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        if (this.isDouble()) {
            builder.add(SEAMLESS, VARIANT);
        } else {
            builder.add(HALF, VARIANT);
        }
    }

}
