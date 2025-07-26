package org.millenaire.items;

import org.millenaire.PlayerTracker;

import net.minecraft.block.Block;
import net.minecraft.world.entity.player.Player;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;

public class ItemMillSeeds extends ItemSeeds
{

	ItemMillSeeds(Block crops)
	{
		super(crops, Blocks.farmland);
	}

        @Override
        public InteractionResult useOn(UseOnContext context) {
                Player playerIn = context.getPlayer();
                Level worldIn = context.getLevel();
                if(playerIn != null && !worldIn.isClientSide && PlayerTracker.get(playerIn).canPlayerUseCrop(context.getItemInHand().getItem())) {
                        return super.useOn(context);
                }
                return InteractionResult.FAIL;
        }
}
