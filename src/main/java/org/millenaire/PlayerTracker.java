package org.millenaire;

import org.millenaire.capability.IPlayerCropData;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public final class PlayerTracker {

    @CapabilityInject(IPlayerCropData.class)
    public static final Capability<IPlayerCropData> CROP_CAPABILITY = null;

    private PlayerTracker() {}

    public static IPlayerCropData get(EntityPlayer player) {
        return player.getCapability(CROP_CAPABILITY, null);
    }
}
