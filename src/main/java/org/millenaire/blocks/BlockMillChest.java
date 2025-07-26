package org.millenaire.blocks;

import org.millenaire.entities.TileEntityMillChest;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import org.millenaire.gui.MillChestMenu;
import org.millenaire.gui.MillMenus;
import org.millenaire.blocks.MillBlocks;

public class BlockMillChest extends ChestBlock {

    public BlockMillChest() {
        super(BlockBehaviour.Properties.of(Material.WOOD).strength(-1.0F, 6000000.0F),
                () -> MillBlocks.MILL_CHEST_TILE.get());
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            clearContainer(level, pos, newState);
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult hit) {
        if (!level.isClientSide) {
            MenuProvider provider = new SimpleMenuProvider((id, inv, p) -> new MillChestMenu(MillMenus.CHEST_MENU.get(), id),
                    new TextComponent("Mill Chest"));
            NetworkHooks.openGui((ServerPlayer) player, provider, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityMillChest();
    }
}
