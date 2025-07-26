package org.millenaire.entities;

import net.minecraft.entity.player.Inventory;
import net.minecraft.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.millenaire.blocks.MillBlocks;

public class TileEntityMillChest extends ChestBlockEntity implements MenuProvider
{
	private boolean isLocked = true;
	
        public TileEntityMillChest() {
                super(MillBlocks.MILL_CHEST_TILE.get());
        }
	
        public boolean setLock()
        {
                isLocked = !isLocked;

                if (level != null) {
                        for (Direction dir : Direction.Plane.HORIZONTAL) {
                                BlockPos pos = worldPosition.relative(dir);
                                if (level.getBlockEntity(pos) instanceof TileEntityMillChest) {
                                        TileEntityMillChest other = (TileEntityMillChest) level.getBlockEntity(pos);
                                        other.isLocked = this.isLocked;
                                }
                        }
                }

                return isLocked;
        }
	
	public boolean isLockedFor(Player playerIn)
	{
		if(playerIn == null)
		{
            return false;
        }
		
		//final Building building = mw.getBuilding(buildingPos);

		//if (building == null)
		//	return true;

		//if (building.lockedForPlayer(playerIn.getDisplayName()))
		//	return true;
		
		return isLocked;
	}
	
	@Override
        public void load(CompoundTag compound)
    {
        super.load(compound);

        if(compound.contains("millChestLocked"))
        {
            isLocked = compound.getBoolean("millChestLocked");
        }
    }
	
	@Override
        public CompoundTag save(CompoundTag compound)
    {
        super.save(compound);

        compound.putBoolean("millChestLocked", isLocked);
        return compound;
    }
	
        @Override
        public Component getDisplayName()
    {
        return new TextComponent("Mill Chest");
    }

        @Override
        public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player)
    {
        return ChestMenu.threeRows(id, playerInventory, this);
    }
}
