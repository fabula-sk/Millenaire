package org.millenaire.items;

import org.millenaire.MillTabs;
import org.millenaire.blocks.BlockMillSign;
import org.millenaire.blocks.MillBlocks;

import net.minecraft.world.entity.player.Player;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.World;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;

public class ItemMillSign extends Item
{
        ItemMillSign() { super(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)); }

	/**
	 * Called when a Block is right-clicked with this Item
	 */
        public InteractionResult useOn(UseOnContext context)
        {
                ItemStack stack = context.getItemInHand();
                Player playerIn = context.getPlayer();
                World worldIn = context.getLevel();
                BlockPos pos = context.getClickedPos();
                Direction side = context.getClickedFace();

                if (side == Direction.DOWN)
                {
                        return InteractionResult.FAIL;
                }
                else if (!worldIn.getBlockState(pos).getBlock().getMaterial().isSolid())
                {
                        return InteractionResult.FAIL;
                }
                else
                {
                        pos = pos.offset(side);

                        if (playerIn == null || !playerIn.mayUseItemAt(pos, side, stack))
                        {
                                return InteractionResult.FAIL;
                        }
                        else if (worldIn.isRemote)
                        {
                                return InteractionResult.SUCCESS;
                        }
                        else
                        {
                                worldIn.setBlockState(pos, MillBlocks.blockMillSign.getDefaultState().withProperty(BlockMillSign.FACING, side), 3);

                                stack.shrink(1);
                                TileEntity tileentity = worldIn.getTileEntity(pos);

                                return InteractionResult.SUCCESS;
                        }
                }
        }
}