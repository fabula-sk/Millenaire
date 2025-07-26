package org.millenaire.capability;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public interface IPlayerCropData {
    void setCanUseCrop(Item crop, boolean canUse);
    boolean canPlayerUseCrop(Item crop);
    CompoundNBT writeNBT();
    void readNBT(CompoundNBT nbt);
}
