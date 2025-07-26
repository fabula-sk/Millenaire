package org.millenaire.capability;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

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
    public CompoundNBT writeNBT() {
        CompoundNBT tag = new CompoundNBT();
        for (Item i : cropKnowledge.keySet()) {
            tag.putBoolean(ForgeRegistries.ITEMS.getKey(i).toString(), cropKnowledge.get(i));
        }
        return tag;
    }

    @Override
    public void readNBT(CompoundNBT nbt) {
        cropKnowledge.clear();
        for (String key : nbt.getKeySet()) {
            Item i = ForgeRegistries.ITEMS.getValue(new ResourceLocation(key));
            if (i != null) {
                cropKnowledge.put(i, nbt.getBoolean(key));
            }
        }
    }
}
