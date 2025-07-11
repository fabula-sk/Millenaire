package org.millenaire.capability;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerCropData implements IPlayerCropData {
    private Map<Item, Boolean> cropKnowledge = new HashMap<>();

    @Override
    public void setCanUseCrop(Item crop, boolean canUse) {
        cropKnowledge.put(crop, canUse);
    }

    @Override
    public boolean canPlayerUseCrop(Item crop) {
        return cropKnowledge.getOrDefault(crop, false);
    }

    @Override
    public NBTTagCompound writeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        for (Item i : cropKnowledge.keySet()) {
            tag.setBoolean(Item.itemRegistry.getNameForObject(i).toString(), cropKnowledge.get(i));
        }
        return tag;
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        cropKnowledge.clear();
        for (String key : nbt.getKeySet()) {
            Item i = Item.getByNameOrId(key);
            if (i != null) {
                cropKnowledge.put(i, nbt.getBoolean(key));
            }
        }
    }
}
