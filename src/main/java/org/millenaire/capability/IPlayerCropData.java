package org.millenaire.capability;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public interface IPlayerCropData {
    void setCanUseCrop(Item crop, boolean canUse);
    boolean canPlayerUseCrop(Item crop);
    NBTTagCompound writeNBT();
    void readNBT(NBTTagCompound nbt);
}
