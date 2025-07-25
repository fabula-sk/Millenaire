package org.millenaire.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.BlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        return Item.getItemFromBlock(MillBlocks.blockMillPathSlab);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) { return Item.getItemFromBlock(MillBlocks.blockMillPathSlab); }
    
        /**
         * Shapes replacing the old setBlockBounds based logic.
         */
        private static final VoxelShape DOUBLE_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
        private static final VoxelShape TOP_SHAPE = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 15.0D, 16.0D);
        private static final VoxelShape BOTTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);

        @Override
        public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
                if (this.isDouble()) {
                        return DOUBLE_SHAPE;
                }
                return state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP ? TOP_SHAPE : BOTTOM_SHAPE;
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
                return getShape(state, worldIn, pos, context);
        }
	
    @Override
    public boolean doesSideBlockRendering(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
        if (this.isDouble())
        {
            return false;
        }
        
        // face is on the block being rendered, not this block.
        EnumBlockHalf side = world.getBlockState(pos).getValue(HALF);
        return (side == EnumBlockHalf.TOP && face == EnumFacing.DOWN) || (side == EnumBlockHalf.BOTTOM && face == EnumFacing.UP);
    }


    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName() + "." + BlockMillPath.EnumType.byMetadata(meta).getUnlocalizedName();
    }
    
	@Override
    public IProperty getVariantProperty() { return VARIANT; }

    @Override
    public Object getVariant(ItemStack stack)
    {
        return BlockMillPath.EnumType.byMetadata(stack.getMetadata() & 7);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        if (itemIn != Item.getItemFromBlock(MillBlocks.blockMillPathSlabDouble))
        {
            BlockMillPath.EnumType[] aenumtype = BlockMillPath.EnumType.values();

            for (BlockMillPath.EnumType enumtype : aenumtype) {
                list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
            }
        }
    }

    // Metadata handling removed in favor of pure block states
    
    @Override
    protected BlockState createBlockState()
    {
        return this.isDouble() ? new BlockState(this, SEAMLESS, VARIANT): new BlockState(this, HALF, VARIANT);
    }

}
