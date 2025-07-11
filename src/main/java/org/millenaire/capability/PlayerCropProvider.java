package org.millenaire.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PlayerCropProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(IPlayerCropData.class)
    public static final Capability<IPlayerCropData> CROP_CAPABILITY = null;

    private IPlayerCropData instance = CROP_CAPABILITY == null ? null : CROP_CAPABILITY.getDefaultInstance();

    public static void register() {
        CapabilityManager.INSTANCE.register(IPlayerCropData.class, new Capability.IStorage<IPlayerCropData>() {
            @Override
            public NBTBase writeNBT(Capability<IPlayerCropData> capability, IPlayerCropData instance, EnumFacing side) {
                return instance.writeNBT();
            }

            @Override
            public void readNBT(Capability<IPlayerCropData> capability, IPlayerCropData instance, EnumFacing side, NBTBase nbt) {
                instance.readNBT((NBTTagCompound) nbt);
            }
        }, PlayerCropData::new);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CROP_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CROP_CAPABILITY) {
            return CROP_CAPABILITY.cast(instance);
        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return instance == null ? new NBTTagCompound() : instance.writeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if (instance != null) {
            instance.readNBT(nbt);
        }
    }
}
