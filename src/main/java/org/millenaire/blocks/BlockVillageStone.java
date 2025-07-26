package org.millenaire.blocks;

import java.util.Random;

import org.millenaire.entities.TileEntityVillageStone;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.BlockGetter;

public class BlockVillageStone extends Block implements EntityBlock
{

        BlockVillageStone()
        {
                super(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 6000000.0F));
        }
	
        @Override
    public RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }
	
        @Override
    public java.util.List<net.minecraft.world.item.ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootContext.Builder builder)
    {
        return java.util.Collections.emptyList();
    }
	
        @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit)
    {
                if(worldIn.isClientSide)
                {
                        playerIn.displayClientMessage(new TextComponent("The Village name almost seems to shimmer in the twilight"), true);
                }

                BlockEntity tile = worldIn.getBlockEntity(pos);
                if(tile instanceof TileEntityVillageStone)
                {
                        TileEntityVillageStone te = (TileEntityVillageStone) tile;
                        if(te.testVar < 16)
                                te.testVar++;
                        else
                                te.testVar = 0;
                }

        return InteractionResult.SUCCESS;
    }
	
        public void negate(Level worldIn, BlockPos pos, Player playerIn)
        {
                TileEntityVillageStone te;

                if(worldIn.getBlockEntity(pos) instanceof TileEntityVillageStone)
                        te = (TileEntityVillageStone) worldIn.getBlockEntity(pos);
                else
                {
                        System.err.println("Negation failed.  TileEntity not loaded correctly.");
                        return;
                }

                te.willExplode = true;
                worldIn.scheduleTick(pos, this, 60);
                worldIn.playSound(null, pos, SoundEvents.PORTAL_TRAVEL, SoundSource.BLOCKS, 1.0F, 0.01F);
        }

        @Override
        public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand)
        {
                TileEntityVillageStone te;

                if(worldIn.getBlockEntity(pos) instanceof TileEntityVillageStone)
                {
                        te = (TileEntityVillageStone) worldIn.getBlockEntity(pos);

                        if(te.willExplode)
                        {
                                //Do Some Stuff
                                worldIn.removeBlock(pos, false);
                                worldIn.explode(null, pos.getX() + 0.5D, pos.getY()+ 0.5D, pos.getZ()+ 0.5D, 2.0F, Explosion.BlockInteraction.BREAK);
                        }
                }
                else
                {
                        System.err.println("Negation failed.  TileEntity not loaded correctly.");
                }
        }

        @Override
        public BlockEntity newBlockEntity(BlockGetter worldIn)
        {
                return new TileEntityVillageStone();
        }
}
